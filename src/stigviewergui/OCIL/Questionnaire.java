/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.List;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.TextType;
import stigviewergui.OCIL.Question;

public class stigviewergui.OCIL.Questionnaire
extends Question {
    private String description;

    stigviewergui.OCIL.Questionnaire(Questionnaire q) {
        this.id = q.getId();
        this.notes = q.getNotes();
        this.title = q.getTitle().getValue();
        this.description = q.getDescription().getValue();
    }

    public String toString() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }
}

