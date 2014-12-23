/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.DocumentType;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.Ocil;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.StepType;
import org.mitre.ocil._1.TestActionRef;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JAXBTester {
	private static Map<String, Questionnaire> questionnaires;
	private static Map<String, JAXBElement> testActions;
	private static Map<String, BooleanQuestion> booleanQuestions;

	public static void main(String[] args) {
		Ocil ocil = new Ocil();
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(ocil.getClass()
					.getPackage().getName());
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			ocil = (Ocil) unmarshaller.unmarshal(new File("FileName"));
		} catch (JAXBException ex) {
			return;
		}
		questionnaires = new HashMap<String, Questionnaire>();
		for (Questionnaire q2 : ocil.getQuestionnaire()) {
			questionnaires.put(q2.getId(), q2);
		}
		testActions = new HashMap<String, JAXBElement>();
		for (JAXBElement jaxbe2 : ocil.getTestAction()) {
			if (!jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestionTestAction"))
				continue;
			BooleanQuestionTestAction bqta = (BooleanQuestionTestAction) jaxbe2
					.getValue();
			testActions.put(bqta.getId(), jaxbe2);
		}
		booleanQuestions = new HashMap<String, BooleanQuestion>();
		for (JAXBElement jaxbe : ocil.getQuestion()) {
			if (!jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestion"))
				continue;
			BooleanQuestion bq = (BooleanQuestion) jaxbe.getValue();
			booleanQuestions.put(bq.getId(), bq);
		}
		for (String d : ocil.getDocument().getDescription()) {
			if (d.length() != 0)
				continue;
		}
		for (String n : ocil.getDocument().getNotice()) {
			if (n.length() != 0)
				continue;
		}
		for (Questionnaire q : ocil.getQuestionnaire()) {
			if (q.isChildOnly())
				continue;
			JAXBTester.printQuestionnaire(q, 0);
		}
	}

	private static void printQuestionnaire(Questionnaire q, int indentation) {
		String indent = new String();
		for (int i = 0; i < indentation; ++i) {
			indent = indent + " ";
		}
		for (TestActionRef ref : q.getActions().getTestActionRef()) {
			if (ref.getValue().split(":")[2].equals("questionnaire")) {
				JAXBTester.printQuestionnaire(
						questionnaires.get(ref.getValue()), indentation + 2);
				continue;
			}
			BooleanQuestionTestAction bqta = (BooleanQuestionTestAction) testActions
					.get(ref.getValue()).getValue();
			BooleanQuestion bq = booleanQuestions.get(bqta.getQuestionRef());
			for (String s : bq.getQuestionText()) {
			}
			JAXBTester.printSteps(bq.getInstructions().getStep(),
					indentation + 4);
		}
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
			JAXBTester.printSteps(step.getStep(), indentation + 2);
		}
	}
}
