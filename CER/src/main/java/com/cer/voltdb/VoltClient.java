package com.cer.voltdb;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;

import com.cer.util.PropertyConfigurer;

@Component 
@Scope("singleton")
public class VoltClient {

	protected final Logger logger = LoggerFactory.getLogger(VoltClient.class);
	
	protected Client client;
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;

	
	private String hostNames;
	
	
	private String username;

	/*@Value("#{systemProperties.hostpassword}")*/
	private String password;

	public Client getClient() throws UnknownHostException, IOException {
		logger.info("getClient Start ");
		if (this.client == null) {
			this.hostNames = propertyConfigurer.getProperty("hostNames");
			String[] hostArray = hostNames.split(",");
			this.initClient(hostArray);
		}
		logger.info("getClient end");
		return this.client;
	}

	private void initClient(String hostNames[]) throws UnknownHostException, IOException {
		logger.info("initClient Start ");
		if (client == null) {
			this.username = propertyConfigurer.getProperty("hostusername");
			this.password = propertyConfigurer.getProperty("hostpassword");
			ClientConfig config = new ClientConfig(username, password);
			this.client = ClientFactory.createClient(config);
			this.initConnections(hostNames);
		}
		logger.info("initClient end ");
	}

	private void initConnections(String hostNames[]) throws UnknownHostException, IOException {
		logger.info("initConnections  Start ");
		if (hostNames != null && hostNames.length > 0) {
			for (String host : hostNames) {
				this.client.createConnection(host.trim());
			}
		} else {
			throw new UnknownHostException("No hosts specified");
		}
		logger.info("initConnections  end ");
	}

}
