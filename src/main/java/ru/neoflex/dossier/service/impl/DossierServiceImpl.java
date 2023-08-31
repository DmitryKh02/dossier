package ru.neoflex.dossier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.dto.EmailMessage;
import ru.neoflex.dossier.service.DossierService;
import ru.neoflex.dossier.service.MessageSender;
import ru.neoflex.dossier.serializer.EmailMessageConverter;


@Slf4j
@Service
@RequiredArgsConstructor
public class DossierServiceImpl implements DossierService {
    private final EmailMessageConverter converter;
    private final MessageSender messageSender;

    @Override
    @KafkaListener(topics = {"${application.kafka.topics[0].finish-registration}"})
    public void finishRegistrationByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.finishRegistrationByApplicationId - consumed {}", emailMessage);

        EmailMessage message = converter.convert(emailMessage);

        log.info("DossierServiceImpl.finishRegistrationByApplicationId - EmailMessage - {}", message);
        messageSender.sendMessage(message);
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topics[2].send-documents}"})
    public void sendDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.sendDocumentByApplicationId - consumed {}", emailMessage);

        EmailMessage message = converter.convert(emailMessage);

        log.info("DossierServiceImpl.sendDocumentByApplicationId - EmailMessage - {}", message);
        messageSender.sendMessage(message);
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topics[4].credit-issued}"})
    public void signDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.signDocumentByApplicationId - consumed {}", emailMessage);

        EmailMessage message = converter.convert(emailMessage);

        log.info("DossierServiceImpl.signDocumentByApplicationId - EmailMessage - {}", message);
        messageSender.sendMessage(message);
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topics[3].send-ses}"})
    public void codeDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.codeDocumentByApplicationId - consumed {}", emailMessage);

        EmailMessage message = converter.convert(emailMessage);

        log.info("DossierServiceImpl.codeDocumentByApplicationId - EmailMessage - {}", message);
        messageSender.sendMessage(message);
    }


}
