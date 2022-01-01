package com.example.databasetryv2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShowGrade {
    private final SimpleStringProperty emailstuent;
    private final SimpleStringProperty studgrade;

    public ShowGrade(String emailstuent, String studgrade) {
        this.emailstuent = new SimpleStringProperty(emailstuent);
        this.studgrade = new SimpleStringProperty(studgrade);
    }

    public String getEmailstuent() {
        return emailstuent.get();
    }

    public SimpleStringProperty emailstuentProperty() {
        return emailstuent;
    }

    public String getStudgrade() {
        return studgrade.get();
    }

    public SimpleStringProperty studgradeProperty() {
        return studgrade;
    }
}
