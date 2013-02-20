package org.springframework.social.foursquare.custom;

import org.springframework.social.foursquare.api.*;

import java.util.Date;

/**
 * User: petrpopov
 * Date: 20.02.13
 * Time: 17:09
 */
public class CheckinCustom {

    private String id;
    private FoursquareUser user;
    private Date createdAt;
    private String type;
    private String shout;
    private boolean mayor;
    private String timeZoneOffset;
    private String timeZone;
    private VenueCustom venue;
    private Photos photos;
    private CheckinSource source;
    private CheckinCommentInfo comments;

    public CheckinCustom() {
    }

    public CheckinCustom(String id, FoursquareUser user, Date createdAt, String type, String shout, boolean mayor, String timeZoneOffset, String timeZone, VenueCustom venue, Photos photos, CheckinSource source, CheckinCommentInfo comments) {
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.type = type;
        this.shout = shout;
        this.mayor = mayor;
        this.timeZoneOffset = timeZoneOffset;
        this.timeZone = timeZone;
        this.venue = venue;
        this.photos = photos;
        this.source = source;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FoursquareUser getUser() {
        return user;
    }

    public void setUser(FoursquareUser user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShout() {
        return shout;
    }

    public void setShout(String shout) {
        this.shout = shout;
    }

    public boolean isMayor() {
        return mayor;
    }

    public void setMayor(boolean mayor) {
        this.mayor = mayor;
    }

    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setTimeZoneOffset(String timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    public VenueCustom getVenue() {
        return venue;
    }

    public void setVenue(VenueCustom venue) {
        this.venue = venue;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public CheckinSource getSource() {
        return source;
    }

    public void setSource(CheckinSource source) {
        this.source = source;
    }

    public CheckinCommentInfo getComments() {
        return comments;
    }

    public void setComments(CheckinCommentInfo comments) {
        this.comments = comments;
    }
}
