package com.achala.order_server.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;

public class HttpStatusCodeDeserializer extends JsonDeserializer<HttpStatusCode> {
    @Override
    public HttpStatusCode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String status = jsonParser.getText();

        try{
            return HttpStatus.valueOf(status);
        }catch(IllegalArgumentException e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }
}
