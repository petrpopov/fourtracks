package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.instagram.api.Media;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MediaList {
	
	private List<Media> list;
	
	@JsonCreator
	public MediaList(@JsonProperty("data") List<Media> list) {
		this.list = list;
	}
	
	public List<Media> getList() {
		return list;
	}
}
