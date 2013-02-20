package org.springframework.social.foursquare.custom;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.foursquare.api.impl.json.CheckinInfoContainerDeserializer;

/**
 * User: petrpopov
 * Date: 20.02.13
 * Time: 16:44
 */
@JsonDeserialize(using=CheckinInfoContainerDeserializerCustom.class)
public class CheckinInfoContainerCustom {

    private final CheckinInfoCustom checkinInfo;

    public CheckinInfoContainerCustom(CheckinInfoCustom checkinInfo) {
        this.checkinInfo = checkinInfo;
    }

    public CheckinInfoCustom getCheckinInfo() {
        return checkinInfo;
    }
}
