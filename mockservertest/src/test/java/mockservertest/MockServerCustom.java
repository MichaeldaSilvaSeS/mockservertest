package mockservertest;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

public class MockServerCustom {

	private static MockServerCustom newMockServerCustom = new MockServerCustom();
	public ClientAndServer clientAndServer;
	public Integer countTest = 0; 
	
	private MockServerCustom() {
		clientAndServer = ClientAndServer.startClientAndServer(5050);
	}
	
	public void start(){
		countTest = countTest + 1;
	}
	
	
	public MockServerClient getClient(){
		return clientAndServer;
	}
	
	public String getHttp(){
		return "http:/" + getClient().remoteAddress();
	}
	
	public static MockServerCustom getInstance(){
		return newMockServerCustom;
	}
}
