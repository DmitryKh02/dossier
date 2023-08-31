package ru.neoflex.dossier.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.neoflex.dossier.dto.EmailMessage;

@Slf4j
@Component
public class EmailMessageConverter {
    private static final ObjectMapper objectMapper = JsonMapper.builder().build();

    public static EmailMessage convert(String message){
        try {
            EmailMessage email = objectMapper.convertValue(message, EmailMessage.class);
            return  email;
        }
        catch (RuntimeException exception){
            log.error(exception.getMessage());
            return null;
        }
    }
}
