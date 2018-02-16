package com.github.andreidore.fancourierclient;

import net.dongliu.requests.RawResponse;

public class FanCourierException extends RuntimeException {

	private int statusCode;

	public FanCourierException(String error, int statusCode) {
		super(error);
		this.statusCode = statusCode;
	}

	public static FanCourierException createFromResponse(RawResponse response) {

		StringBuilder errorText = new StringBuilder();

		int statusCode = response.getStatusCode();

		errorText.append("HTTP code:");
		errorText.append(statusCode);
		errorText.append(". ");

		errorText.append("URL:");
		errorText.append(response.getURL());
		errorText.append(". ");

		return new FanCourierException(errorText.toString(), statusCode);

	}

}
