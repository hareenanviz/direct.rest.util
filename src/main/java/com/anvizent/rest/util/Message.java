package com.anvizent.rest.util;

import flexjson.JSONSerializer;

/**
 * @author Hareen Bejjanki
 *
 */
public class Message {

	private Code code;
	private String text;
	private String details;

	public static enum Code {
		GENERAL_EXCEPTION
	}
	
	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String toJSON() {
		return new JSONSerializer().exclude("*.class").include("*.*").serialize(this);
	}

	@Override
	public String toString() {
		return toJSON();
	}
}
