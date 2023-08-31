package ru.neoflex.dossier.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.neoflex.dossier.dto.EmailMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailMessageConverter {
    private final ObjectMapper objectMapper;
    public EmailMessage convert(String message)  {
        try {
            return objectMapper.readValue(message, EmailMessage.class);
        }
        catch (JsonProcessingException ex){
            log.error(ex.getMessage());
            return null;
        }
    }
}
