package com.demo.unit.domain.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserTypeJsonDeserializer extends JsonDeserializer<UserType> {

    @Override
    public UserType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        for (UserType value : UserType.values()) {
            if (value.name().equals(p.getText())) {
                return value;
            }
        }
        return null;
    }
}