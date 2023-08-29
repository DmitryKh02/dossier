package ru.neoflex.dossier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.dto.EmailMessage;
import ru.neoflex.dossier.enums.Theme;
import ru.neoflex.dossier.service.MessageSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {
    @Value(value = "${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    @Override
    public void sendMessage(EmailMessage emailMessage) {
        log.debug("KafkaMessageServiceImpl.sendMessage - EmailMessage {}", emailMessage);
        String text = getTextMessageByTheme(emailMessage.getTheme());
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailMessage.getAddress());
        mailMessage.setSubject(emailMessage.getTheme().getThemeName());
        mailMessage.setText(text);

        mailSender.send(mailMessage);

        log.debug("KafkaMessageServiceImpl.sendMessage - Message {}", mailMessage);
    }

    private String getTextMessageByTheme(Theme theme) {
        log.debug("KafkaMessageServiceImpl.getMessageByTopicName - Theme {}", theme);

        String emailMessage = switch (theme) {
            case FINISH_REGISTRATION -> "Finish-registration";
            case CREATE_DOCUMENTS -> "Create-documents";
            case SEND_DOCUMENTS -> "Send-documents";
            case SEND_SES -> "Send-ses";
            case CREDIT_ISSUED -> "Credit-issued";
            case APPLICATION_DENIED -> "Application-denied";
        };

        log.debug("KafkaMessageServiceImpl.getMessageByTopicName - EmailMessage {}", emailMessage);

        return emailMessage;
    }
}
