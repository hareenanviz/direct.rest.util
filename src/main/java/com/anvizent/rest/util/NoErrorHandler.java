package com.anvizent.rest.util;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author Hareen Bejjanki
 * @author Apurva Deshmukh
 *
 */
public class NoErrorHandler implements ResponseErrorHandler {

	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;
	}

	public void handleError(ClientHttpResponse response) throws IOException {
	}
}
