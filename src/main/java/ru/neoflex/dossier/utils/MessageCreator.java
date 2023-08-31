package ru.neoflex.dossier.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.neoflex.dossier.enums.Theme;

@Slf4j
@Component
public class MessageCreator {
    public String getTextMessageByTheme(Theme theme, Long applicationId) {
        log.debug("KafkaMessageServiceImpl.getMessageByTopicName - Theme {}", theme);

        //Здесь пишется письмо по теме
        String emailMessage = switch (theme) {
            case FINISH_REGISTRATION -> getFinishRegistrationMessage(applicationId);
            case CREATE_DOCUMENTS -> getCreateDocumentsMessage(applicationId);
            case SEND_DOCUMENTS -> getSendDocumentsMessage(applicationId);
            case SEND_SES -> getSendSesMessage(applicationId);
            case CREDIT_ISSUED -> getCreditIssuedMessage(applicationId);
            case APPLICATION_DENIED -> getApplicationDeniedMessage(applicationId);
        };

        log.debug("KafkaMessageServiceImpl.getMessageByTopicName - EmailMessage {}", emailMessage);

        return emailMessage;
    }

    private String getFinishRegistrationMessage(Long applicationId) {
        return "Finish-registration. Your Application id is " + applicationId;
    }

    private String getCreateDocumentsMessage(Long applicationId) {
        return "Create-documents. Your Application id is " + applicationId;
    }

    private String getSendDocumentsMessage(Long applicationId) {
        return "Send-documents. Your Application id is " + applicationId;
    }

    private String getSendSesMessage(Long applicationId) {
        return "Send-ses. Your Application id is " + applicationId;
    }

    private String getCreditIssuedMessage(Long applicationId) {
        return "Credit issued. Your Application id is " + applicationId;
    }

    private String getApplicationDeniedMessage(Long applicationId) {
        return "Application-denied. Your Application id is  " + applicationId;
    }
}
