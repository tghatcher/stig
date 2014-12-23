/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.DocumentType;
import org.mitre.ocil._1.GeneratorType;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.Ocil;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.OperatorType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.ResultChoiceType;
import org.mitre.ocil._1.StepType;
import org.mitre.ocil._1.TestActionRef;
import org.mitre.ocil._1.TextType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TakeTest {
	private static Map<String, Questionnaire> questionnaires;
	private static Map<String, JAXBElement> testActions;
	private static Map<String, BooleanQuestion> booleanQuestions;

	public static void main(String[] args) {
		Ocil ocil = new Ocil();
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(ocil.getClass()
					.getPackage().getName());
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			ocil = (Ocil) unmarshaller.unmarshal(new File("Filename"));
		} catch (JAXBException ex) {
			return;
		}
		questionnaires = new HashMap<String, Questionnaire>();
		for (Questionnaire q : ocil.getQuestionnaire()) {
			questionnaires.put(q.getId(), q);
		}
		testActions = new HashMap<String, JAXBElement>();
		for (JAXBElement jaxbe2 : ocil.getTestAction()) {
			if (jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestionTestAction")) {
				BooleanQuestionTestAction bqta = (BooleanQuestionTestAction) jaxbe2
						.getValue();
				testActions.put(bqta.getId(), jaxbe2);
				continue;
			}
			System.out.println("Need to add more TestAction types");
		}
		booleanQuestions = new HashMap<String, BooleanQuestion>();
		for (JAXBElement jaxbe : ocil.getQuestion()) {
			if (jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestion")) {
				BooleanQuestion bq = (BooleanQuestion) jaxbe.getValue();
				booleanQuestions.put(bq.getId(), bq);
				continue;
			}
			System.out.println("Need to add more Question types");
		}
		System.out.println("OCIL successfully parsed!");
		System.out.println("");
		System.out.println("Schema version "
				+ ocil.getGenerator().getSchemaVersion());
		System.out.println("Timestamp: " + ocil.getGenerator().getTimestamp());
		System.out.println("");
		System.out.println(ocil.getDocument().getTitle());
		for (String d : ocil.getDocument().getDescription()) {
			if (d.length() == 0)
				continue;
			System.out.println("  - " + d);
		}
		for (String n : ocil.getDocument().getNotice()) {
			if (n.length() == 0)
				continue;
			System.out.println("  * " + n);
		}
		System.out.println("");
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();
		System.out.println("Questionnaires:");
		for (Questionnaire q2 : ocil.getQuestionnaire()) {
			if (q2.isChildOnly())
				continue;
			String label = "#" + q2.getId().split(":")[3];
			labels.add(label);
			System.out.println("Starting questionnaire " + label);
			String result = TakeTest.doQuestionnaire(q2);
			results.add(result);
			System.out.println("Questionnaire result:");
			System.out.println("  " + result);
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Final results:");
		for (int i = 0; i < results.size(); ++i) {
			System.out.println((String) labels.get(i) + ": "
					+ (String) results.get(i));
		}
	}

	private static String doQuestionnaire(Questionnaire q) {
		System.out.println(" - " + q.getTitle());
		System.out.println("");
		ArrayList<String> results = new ArrayList<String>();
		for (TestActionRef ref : q.getActions().getTestActionRef()) {
			results.add(TakeTest.doTestAction(ref));
		}
		System.out.println("");
		OperatorType op = q.getActions().getOperation();
		Boolean pass = false;
		if (op.equals((Object) OperatorType.AND)) {
			System.out.println("Op: AND");
			pass = true;
			for (String result : results) {
				if (result.equals("PASS"))
					continue;
				pass = false;
			}
		} else if (op.equals((Object) OperatorType.OR)) {
			System.out.println("Op: OR");
			pass = false;
			for (String result : results) {
				if (!result.equals("PASS"))
					continue;
				pass = true;
			}
		} else {
			System.out.println("There is a serious problem");
		}
		System.out.println("Results:");
		for (String result : results) {
			System.out.println("  " + result);
		}
		System.out.println("");
		if (q.getActions().isNegate()) {
			pass = pass == false;
		}
		if (pass.booleanValue()) {
			return "PASS";
		}
		return "FAIL";
	}

	private static String doTestAction(TestActionRef ref) {
		String result = "ERROR";
		if (ref.getValue().split(":")[2].equals("questionnaire")) {
			return TakeTest.doQuestionnaire(questionnaires.get(ref.getValue()));
		}
		BooleanQuestionTestAction bqta = (BooleanQuestionTestAction) testActions
				.get(ref.getValue()).getValue();
		System.out.println(" - " + bqta.getTitle());
		BooleanQuestion bq = booleanQuestions.get(bqta.getQuestionRef());
		for (String s : bq.getQuestionText()) {
			System.out.println(s);
		}
		System.out.println("  Instructions:");
		TakeTest.printSteps(bq.getInstructions().getStep(), 2);
		char yn = TakeTest.getYN();
		if (yn == 'Y') {
			result = bqta.getWhenTrue().getTestActionRef() != null ? TakeTest
					.doTestAction(bqta.getWhenTrue().getTestActionRef()) : bqta
					.getWhenTrue().getResult();
		} else if (yn == 'N') {
			result = bqta.getWhenFalse().getTestActionRef() != null ? TakeTest
					.doTestAction(bqta.getWhenFalse().getTestActionRef())
					: bqta.getWhenFalse().getResult();
		} else {
			System.out.println("Something is borked");
			return "ERROR";
		}
		System.out.println("");
		if (ref.isNegate()) {
			if (result.equals("PASS")) {
				result = "FAIL";
			} else if (result.equals("FAIL")) {
				result = "PASS";
			}
		}
		return result;
	}

	private static char getYN() {
		return 'Y';
	}

	private static void printSteps(List<StepType> steps, int indentation) {
		if (steps.isEmpty()) {
			return;
		}
		String indent = new String();
		for (int i = 0; i < indentation; ++i) {
			indent = indent + " ";
		}
		for (StepType step : steps) {
			System.out.println(indent + "> " + step.getDescription());
			TakeTest.printSteps(step.getStep(), indentation + 2);
		}
	}
}
