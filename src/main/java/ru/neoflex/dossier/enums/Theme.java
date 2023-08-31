package ru.neoflex.dossier.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Theme {
    FINISH_REGISTRATION("Finish_registration"),
    CREATE_DOCUMENTS("Create_documents"),
    SEND_DOCUMENTS("Send_documents"),
    SEND_SES("Send_ses"),
    CREDIT_ISSUED("Credit_issued"),
    APPLICATION_DENIED("Application_denied");

    private final String themeName;

    @JsonValue
    public  String getThemeName(){
        return themeName;
    }

    public static Theme fromString(String value) {
        for (Theme theme : Theme.values()) {
            if (theme.themeName.equalsIgnoreCase(value)) {
                return theme;
            }
        }
        return null;
    }
}
