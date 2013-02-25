package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.springframework.social.instagram.api.Relationship;

import java.io.IOException;

public class RelationshipContainerDeserializer extends AbstractInstagramDeserializer<RelationshipContainer> {
    
    @Override public RelationshipContainer deserialize(JsonParser jp, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        return deserializeResponseObject(jp, RelationshipContainer.class, Relationship.class);
    }
}
