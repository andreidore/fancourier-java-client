package com.github.andreidore.fancourierclient;

import java.util.Set;

import com.github.jknack.handlebars.internal.Param;

import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.dongliu.requests.body.Part;

public class FanCourierClient {

	private static final String CITY_URL = "https://www.selfawb.ro/export_distante_integrat.php";

	private String username;

	private String client_id;

	private String password;

	public FanCourierClient(String username, String client_id, String password) {
		this.username = username;
		this.client_id = client_id;
		this.password = password;
	}

	public Set<City> getCities() {

		RawResponse response = Requests.get(CITY_URL).multiPartBody(Part.param("username", username),
				Part.param("client_id", client_id), Part.param("user_pass", password)).send();

		
		System.out.println(response.getStatusCode());
		
		return null;

	}
	
	

}
