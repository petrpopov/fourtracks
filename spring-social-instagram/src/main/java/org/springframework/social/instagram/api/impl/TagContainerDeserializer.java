package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.springframework.social.instagram.api.Tag;

import java.io.IOException;

public class TagContainerDeserializer extends AbstractInstagramDeserializer<TagContainer> {

    @Override 
    public TagContainer deserialize(JsonParser jp, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        return deserializeResponseObject(jp, TagContainer.class, Tag.class);
    }
    
}
