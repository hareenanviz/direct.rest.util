package com.anvizent.rest.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * @author Hareen Bejjanki
 * @author Apurva Deshmukh
 * @author Venkateswararao K
 *
 */
public class DataResponse {

	private List<Message> messages;
	private Object data;
	private HttpStatus status;
	private HashMap<String, String> inputParams;
	private String inputBody;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getHasMessages() {
		Boolean hasMessages = Boolean.FALSE;
		if (messages != null) {
			for (Message message : messages) {
				if (message.getCode() != null) {
					hasMessages = Boolean.TRUE;
					break;
				}
			}
		}
		return hasMessages;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HashMap<String, String> getInputParams() {
		return inputParams;
	}

	public void setInputParams(HashMap<String, String> inputParams) {
		this.inputParams = inputParams;
	}

	public String getInputBody() {
		return inputBody;
	}

	public void setInputBody(String inputBody) {
		this.inputBody = inputBody;
	}

	public static ResponseEntity<String> createSucessResponse() {
		return createSucessResponse(null, null, null, null, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, HttpStatus httpStatus) {
		return createSucessResponse(data, null, null, null, httpStatus);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, HttpStatus httpStatus, HttpStatus dataResponseHTTPStatus) {
		return createSucessResponse(data, null, null, null, httpStatus, dataResponseHTTPStatus);
	}

	public static ResponseEntity<String> createSucessResponse(Object data) {
		return createSucessResponse(data, null, null, null, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes) {
		return createSucessResponse(data, excludes, null, null, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, String inputBody) {
		return createSucessResponse(data, null, null, inputBody, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, String inputBody) {
		return createSucessResponse(data, excludes, null, inputBody, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, HashMap<String, String> inputParams) {
		return createSucessResponse(data, null, inputParams, null, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, HashMap<String, String> inputParams) {
		return createSucessResponse(data, excludes, inputParams, null, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, HashMap<String, String> inputParams, String inputBody) {
		return createSucessResponse(data, null, inputParams, inputBody, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, HashMap<String, String> inputParams, String inputBody) {
		return createSucessResponse(data, excludes, inputParams, inputBody, HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, HashMap<String, String> inputParams, String inputBody,
	        HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		DataResponse dataResponse = new DataResponse();
		dataResponse.data = data;
		dataResponse.inputParams = inputParams;
		dataResponse.inputBody = inputBody;
		dataResponse.status = httpStatus;

		return new ResponseEntity<String>(dataResponse.toJSON(excludes, null), headers, httpStatus);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, HashMap<String, String> inputParams, String inputBody,
	        HttpStatus httpStatus, HttpStatus dataResponseHTTPStatus) {
		return createSucessResponse(data, excludes, null, inputParams, inputBody, httpStatus, dataResponseHTTPStatus);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, List<String> excludes, String dateFormat, HashMap<String, String> inputParams,
	        String inputBody, HttpStatus httpStatus, HttpStatus dataResponseHTTPStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		DataResponse dataResponse = new DataResponse();
		dataResponse.data = data;
		dataResponse.inputParams = inputParams;
		dataResponse.inputBody = inputBody;
		dataResponse.status = dataResponseHTTPStatus;

		return new ResponseEntity<String>(dataResponse.toJSON(excludes, dateFormat), headers, httpStatus);
	}

	public static ResponseEntity<String> createEmptySucessResponse() {
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	public static ResponseEntity<String> createSucessResponse(Object data, String inputBody, HttpStatus httpStatus) {
		return createSucessResponse(data, null, null, inputBody, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable throwable) {
		return createFailureResponse(throwable, null, null, null, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes) {
		return createFailureResponse(exception, excludes, null, null, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, HttpStatus httpStatus) {
		return createFailureResponse(exception, null, null, null, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, Object data, String inputBody, HttpStatus httpStatus) {
		return createFailureResponse(exception, data, null, null, null, inputBody, httpStatus, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, HttpStatus httpStatus) {
		return createFailureResponse(exception, excludes, null, null, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, String inputBody, HttpStatus httpStatus) {
		return createFailureResponse(exception, null, inputBody, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, String inputBody, HttpStatus httpStatus) {
		return createFailureResponse(exception, excludes, null, inputBody, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, HashMap<String, String> inputParams, HttpStatus httpStatus) {
		return createFailureResponse(exception, null, inputParams, null, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, HashMap<String, String> inputParams,
	        HttpStatus httpStatus) {
		return createFailureResponse(exception, excludes, inputParams, null, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, String inputBody) {
		return createFailureResponse(exception, null, null, inputBody, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, String inputBody) {
		return createFailureResponse(exception, excludes, null, inputBody, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, HashMap<String, String> inputParams) {
		return createFailureResponse(exception, null, inputParams, null, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, HashMap<String, String> inputParams) {
		return createFailureResponse(exception, excludes, inputParams, null, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, HashMap<String, String> inputParams, String inputBody) {
		return createFailureResponse(exception, null, inputParams, inputBody, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, HashMap<String, String> inputParams,
	        String inputBody) {
		return createFailureResponse(exception, excludes, inputParams, inputBody, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, List<String> excludes, HashMap<String, String> inputParams,
	        String inputBody, HttpStatus httpStatus) {
		return createFailureResponse(exception, null, excludes, inputParams, inputBody, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, Object data, List<String> excludes, HashMap<String, String> inputParams,
	        String inputBody, HttpStatus httpStatus) {
		return createFailureResponse(exception, data, excludes, inputParams, inputBody, httpStatus, httpStatus);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, Object data, List<String> excludes, HashMap<String, String> inputParams,
	        String inputBody, HttpStatus httpStatus, HttpStatus status) {
		return createFailureResponse(exception, data, excludes, null, inputParams, inputBody, httpStatus, status);
	}

	public static ResponseEntity<String> createFailureResponse(Throwable exception, Object data, List<String> excludes, String dateFormat,
	        HashMap<String, String> inputParams, String inputBody, HttpStatus httpStatus, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		Message message = new Message();
		message.setText(exception.getMessage());
		message.setDetails(ExceptionUtils.getFullStackTrace(exception));
		message.setCode(Message.Code.GENERAL_EXCEPTION);

		List<Message> messages = new ArrayList<Message>();
		messages.add(message);

		DataResponse dataResponse = new DataResponse();
		dataResponse.data = data;
		dataResponse.messages = messages;
		dataResponse.inputParams = inputParams;
		dataResponse.inputBody = inputBody;
		dataResponse.status = status;

		return new ResponseEntity<String>(dataResponse.toJSON(excludes, dateFormat), headers, httpStatus);
	}

	public String toJSON(List<String> excludes, String dateFormat) {
		JSONSerializer jsonSerializer = new JSONSerializer().exclude("*.class");

		if (excludes != null && !excludes.isEmpty()) {
			jsonSerializer.exclude(excludes.toArray(new String[] {}));
		}

		if (dateFormat != null && !dateFormat.isEmpty()) {
			jsonSerializer.transform(new DateTransformer(dateFormat), Date.class);
		}

		return jsonSerializer.include("*.*").serialize(this);
	}

	@Override
	public String toString() {
		return toJSON(null, null);
	}
}
