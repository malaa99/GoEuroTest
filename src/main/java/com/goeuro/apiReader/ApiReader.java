package com.goeuro.apiReader;

import java.io.FileWriter;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.goeuro.apiReader.dto.LocationResponse;

/**
 * Main query class which write the response to CSV file
 *
 */
public class ApiReader {

	/**
	 * ROOT location API root URL
	 */
	private final String API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
	
	/**
	 * CSV file name
	 */
	private final String CSV_FILE_PATH = "C:/locationApiResponse.csv";

	
	public ApiReader() {
	}

	/**
	 * Query API and write the response into CSV file
	 * @param locationName String represent query location name
	 */
	private void query(String locationName) {
		// create jersey client
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(API_URL+locationName);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		// if the request succeeded, parse the response
		if (response.getStatus() == 200) {
			List<LocationResponse> locationResponses = response.readEntity(new GenericType<List<LocationResponse>>() {});
			StringBuilder csvContent = new StringBuilder();
			// write CSV headers
			csvContent.append("_id,").append("name,").append("type,").append("latitude,").append("longitude").append("\n");
			
			// iterate over received locations
			for (LocationResponse locationResponse : locationResponses) {
				csvContent.append(locationResponse.getId()).append(",");
				csvContent.append(locationResponse.getName()).append(",");
				csvContent.append(locationResponse.getType()).append(",");
				csvContent.append(locationResponse.getGeoPosition().getLatitude()).append(",");
				csvContent.append(locationResponse.getGeoPosition().getLongitude()).append("\n");
			}
			// write the CSV file
			try {
				FileWriter writer = new FileWriter(CSV_FILE_PATH);
				writer.append(csvContent.toString());
				writer.flush();
				writer.close();
			} catch (Exception exception) {
				throw new RuntimeException("Error while communicating GoEuro API server", exception);
			}

		} else {
			// throw error that the request didn't succeed
			throw new RuntimeException("Error while communicating GoEuro API server. Status:"+response.getStatus() + ", reason:"+response.getStatusInfo().getReasonPhrase());
		}
	}

	/**
	 * Main method to start the app
	 * @param args expects only one arg which is the location name
	 */
	public static void main(String[] args) {
		if(args == null || args.length == 0){
			throw new IllegalArgumentException("Location name parameter is required");
		}else if(args.length > 1){
			throw new IllegalArgumentException("Only one parameter is allowed (Location name)");
		}
		new ApiReader().query(args[0]);
	}
}
