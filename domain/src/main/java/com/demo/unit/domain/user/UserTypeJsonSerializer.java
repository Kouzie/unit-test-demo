package com.demo.unit.domain.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserTypeJsonSerializer extends JsonSerializer<UserType> {

    @Override
    public void serialize(UserType userType, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeString(userType.name());
    }
}
