package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.instagram.api.Tag;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TagList {
	
	private List<Tag> list;
	
	@JsonCreator
	public TagList(@JsonProperty("data") List<Tag> list) {
		this.list = list;
	}
	
	public List<Tag> getList() {
		return list;
	}
}
