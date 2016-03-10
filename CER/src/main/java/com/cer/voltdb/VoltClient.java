package com.cer.voltdb;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;

@Component 
@Scope("singleton")
public class VoltClient {

	protected Client client;

	@Value("#{systemProperties.hostNames}")
	private String hostNames;
	
	@Value("#{systemProperties.hostusername}")
	private String username;

	@Value("#{systemProperties.hostpassword}")
	private String password;

	public Client getClient() throws UnknownHostException, IOException {
		if (this.client == null) {
			String[] hostArray = hostNames.split(",");
			this.initClient(hostArray);
		}
		return this.client;
	}

	private void initClient(String hostNames[]) throws UnknownHostException, IOException {
		if (client == null) {
			ClientConfig config = new ClientConfig(username, password);
			this.client = ClientFactory.createClient(config);
			this.initConnections(hostNames);
		}
	}

	private void initConnections(String hostNames[]) throws UnknownHostException, IOException {
		if (hostNames != null && hostNames.length > 0) {
			for (String host : hostNames) {
				this.client.createConnection(host.trim());
			}
		} else {
			throw new UnknownHostException("No hosts specified");
		}
	}

}
