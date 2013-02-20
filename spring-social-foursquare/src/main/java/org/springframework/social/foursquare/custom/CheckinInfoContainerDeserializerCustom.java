package org.springframework.social.foursquare.custom;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.springframework.social.foursquare.api.impl.json.AbstractFoursquareDeserializer;

import java.io.IOException;

/**
 * User: petrpopov
 * Date: 20.02.13
 * Time: 16:45
 */
public class CheckinInfoContainerDeserializerCustom extends AbstractFoursquareDeserializer<CheckinInfoContainerCustom> {
    @Override
    public CheckinInfoContainerCustom deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        return new CheckinInfoContainerCustom(deserializeNestedResponseObject(jp, "checkins", CheckinInfoCustom.class));
    }
}
