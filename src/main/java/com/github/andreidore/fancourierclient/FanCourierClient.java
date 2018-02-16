package com.github.andreidore.fancourierclient;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

	public List<String[]> getCitiesRaw() {

		RawResponse response = Requests.get(CITY_URL).multiPartBody(Part.param("username", username),
				Part.param("client_id", client_id), Part.param("user_pass", password)).send();

		if (response.getStatusCode() == 200) {

			// TODO optimize this
			String csvData = response.readToText();
			StringReader csvReader = new StringReader(csvData);

			List<String[]> list = new ArrayList<String[]>();

			CSVParser parser = null;
			try {
				parser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

				for (CSVRecord csvRecord : parser) {

					String county = csvRecord.get(0);
					String city = csvRecord.get(1);
					String agency = csvRecord.get(2);
					String distance = csvRecord.get(3);

					list.add(new String[] { county, city, agency, distance });
				}

			} catch (IOException e) {
				throw new FanCourierException("Invalid format csv file.", -1);

			} finally {
				csvReader.close();

				if (parser != null) {
					try {
						parser.close();
					} catch (IOException e) {
						// Ignore this
					}
				}
			}

			return list;

		} else {
			throw FanCourierException.createFromResponse(response);
		}

	}

	public Map<String, Set<City>> getCities() throws FanCourierException {

		List<String[]> list = getCitiesRaw();

		Map<String, Set<City>> map = new HashMap<String, Set<City>>();

		for (String[] row : list) {

			Set<City> cities = map.get(row[0]);
			if (cities == null) {
				cities = new HashSet<City>();
			}
			cities.add(new City(row[1], row[2], Integer.parseInt(row[3])));

		}

		return map;

	}

}
