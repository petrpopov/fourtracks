package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.instagram.api.Comment;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
abstract class CommentsInfoMixin {
    @JsonCreator
    CommentsInfoMixin(
            @JsonProperty("count") int total,
            @JsonProperty("data") List<Comment> list) {}
    

}
