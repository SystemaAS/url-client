package no.systema.url;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.*;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {
	private static Logger logger = LoggerFactory.getLogger(Client.class);
	
	public static void main(String[] args) {
		Client client = new Client();
		URI uri = null;
		try {
			uri = new URI(args[0]);
			logger.info("Uri init:" + args[0]);
		} catch (URISyntaxException e) {
			logger.error("URI "+ args[0]+" is incorrect");
			e.printStackTrace();
		} 

		client.call(uri);

	}

	private String call(URI uri) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = new ResponseEntity(HttpStatus.BAD_REQUEST);

		try {
			response = restTemplate.getForEntity(uri, String.class);
			logger.info("response="+response);
		} catch (RuntimeException rte) {
			logger.error("There is space for improvements on indata...",rte);
			rte.printStackTrace();
		}

		return response.toString();

	}
}
