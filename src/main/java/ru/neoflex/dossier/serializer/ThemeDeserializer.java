package ru.neoflex.dossier.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import ru.neoflex.dossier.enums.Theme;

import java.io.IOException;

public class ThemeDeserializer extends JsonDeserializer<Theme> {
    @Override
    public Theme deserialize(JsonParser p, DeserializationContext text) throws IOException {
        TextNode node = p.getCodec().readTree(p);
        String value = node.asText();
        return Theme.fromString(value);
    }
}
