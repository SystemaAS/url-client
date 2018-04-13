package no.systema.url;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Simple URI client, to use on command line.
 * 
 * @author fredrikmoller
 * @date 2018-04
 */
public class Client {
	private static Logger logger = Logger.getLogger(Client.class);
	
	public static void main(String[] args) throws Throwable {
		Client client = new Client();
		URI uri = null;
		try {
			uri = new URI(args[0]);
		} catch (URISyntaxException e) {
			logger.error("URI "+ args[0]+" is incorrect");
			throw e;
		} 

		client.call(uri);

	}

	private String call(URI uri) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = new ResponseEntity(HttpStatus.BAD_REQUEST);

		try {
			response = restTemplate.getForEntity(uri, String.class);
			logger.debug("response="+response);
		} catch (RuntimeException rte) {
			logger.error("There is space for improvements on indata...",rte);
			rte.printStackTrace();
		}

		return response.toString();

	}

}
