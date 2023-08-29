package ru.neoflex.dossier.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Theme {
    FINISH_REGISTRATION("Finish-registration"),
    CREATE_DOCUMENTS("Create-documents"),
    SEND_DOCUMENTS("Send-documents"),
    SEND_SES("Send-ses"),
    CREDIT_ISSUED("Credit-issued"),
    APPLICATION_DENIED("Application-denied");

    private final String themeName;

    @JsonValue
    public  String getThemeName(){
        return themeName;
    }
}
