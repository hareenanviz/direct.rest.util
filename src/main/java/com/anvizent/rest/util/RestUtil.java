package com.anvizent.rest.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * @author Hareen Bejjanki
 * @author Apurva Deshmukh
 *
 */
public class RestUtil {

	private final RestTemplate restTemplate;

	public RestUtil() {
		this(false);
	}

	public RestUtil(boolean addNoErrorHandler) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);

		restTemplate = new RestTemplate(requestFactory);
		if (addNoErrorHandler) {
			setNoErrorHandler();
		}
	}

	public void setDefaultUriVariables(Map<String, ?> defaultUriVariables) {
		restTemplate.setDefaultUriVariables(defaultUriVariables);
	}

	public void setErrorHandler(ResponseErrorHandler errorHandler) {
		restTemplate.setErrorHandler(errorHandler);
	}

	public void setNoErrorHandler() {
		restTemplate.setErrorHandler(new NoErrorHandler());
	}

	public void setInterceptors(List<ClientHttpRequestInterceptor> interceptors) {
		restTemplate.setInterceptors(interceptors);
	}

	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		restTemplate.setMessageConverters(messageConverters);
	}

	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		restTemplate.setRequestFactory(requestFactory);
	}

	public void setUriTemplateHandler(UriTemplateHandler handler) {
		restTemplate.setUriTemplateHandler(handler);
	}

	public <K, T> ResponseEntity<String> exchange(String url, Map<K, T> requestBody, HttpMethod methodType, HashMap<String, String> headersMap,
	        Object... urlVariables) {
		HttpHeaders headers = new HttpHeaders();
		if (headersMap != null && !headersMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headersMap.entrySet()) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}

		HttpEntity<?> entity = new HttpEntity<String>(new JSONSerializer().exclude("*.class").include("*.*").serialize(requestBody), headers);

		return restTemplate.exchange(url, methodType, entity, String.class, urlVariables);
	}

	public <K, T> ResponseEntity<String> exchange(String url, List<Map<K, T>> requestBody, HttpMethod methodType, HashMap<String, String> headersMap,
	        Object... urlVariables) {
		HttpHeaders headers = new HttpHeaders();
		if (headersMap != null && !headersMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headersMap.entrySet()) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}

		HttpEntity<?> entity = new HttpEntity<String>(new JSONSerializer().exclude("*.class").include("*.*").serialize(requestBody), headers);

		return restTemplate.exchange(url, methodType, entity, String.class, urlVariables);
	}

	public ResponseEntity<String> exchange(String url, String requestBody, HttpMethod methodType, HashMap<String, String> headersMap, Object... urlVariables) {
		HttpHeaders headers = new HttpHeaders();
		if (headersMap != null && !headersMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headersMap.entrySet()) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}

		HttpEntity<?> entity = new HttpEntity<String>(requestBody, headers);

		return restTemplate.exchange(url, methodType, entity, String.class, urlVariables);
	}

	public HashMap<String, Object> getMap(ResponseEntity<String> responseEntity) {
		return new JSONDeserializer<HashMap<String, Object>>().deserialize(responseEntity.getBody());
	}

	public List<HashMap<String, Object>> getListOfMaps(ResponseEntity<String> responseEntity) {
		return new JSONDeserializer<List<HashMap<String, Object>>>().deserialize(responseEntity.getBody());
	}

	public List<String> getListOfString(ResponseEntity<String> responseEntity) {
		return new JSONDeserializer<List<String>>().deserialize(responseEntity.getBody());
	}

	public List<Object> getListOfObject(ResponseEntity<String> responseEntity) {
		return new JSONDeserializer<List<Object>>().deserialize(responseEntity.getBody());
	}

	public static boolean isSuccessStatusCode(ResponseEntity<String> responseEntity) {
		return responseEntity.getStatusCodeValue() - responseEntity.getStatusCodeValue() % 100 == HttpStatus.OK.value();
	}
}
