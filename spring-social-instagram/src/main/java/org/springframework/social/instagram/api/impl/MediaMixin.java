package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.instagram.api.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
abstract class MediaMixin {
    @JsonCreator
    public MediaMixin(
            @JsonProperty("id") long id,
            @JsonProperty("filter") String filter,
            @JsonProperty("link") String link,
            @JsonProperty("caption") Caption caption,
            @JsonProperty("user") InstagramProfile user,
            @JsonProperty("location") Location location,
            @JsonProperty("created_time") @JsonDeserialize(using=InstagramDateDeserializer.class) Date createdTime,
            @JsonProperty("user_has_liked") boolean userHasLiked,
            @JsonProperty("images") Map<String,Image> images,
            @JsonProperty("tags") List<String> tags,
            @JsonProperty("likes") LikesInfo likes,
            @JsonProperty("comments") CommentsInfo comments) {}
    
}


