package org.springframework.social.instagram.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.instagram.api.InstagramProfile;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InstagramProfileList {
	
	private List<InstagramProfile> list;
	
	@JsonCreator
	public InstagramProfileList(@JsonProperty("data") List<InstagramProfile> list) {
		this.list = list;
	}
	
	public List<InstagramProfile> getList() {
		return list;
	}

}
