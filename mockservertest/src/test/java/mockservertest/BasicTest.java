package mockservertest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;



@RunWith(JUnit4.class)
public class BasicTest {

	//private MockServerClient mockServerClient = new MockServerClient("localhost",1081);
	
	@Rule
	public MockServerRule mockServerRule = new MockServerRule(this);

	private MockServerClient mockServerClient;

	@Test
	public void startServer() throws IOException, IllegalAccessException {
		mockServerClient 
				.when(HttpRequest.request().withPath("/books")
						.withMethod("GET"))
				.respond(HttpResponse.response().withStatusCode(200).withBody("{\"data\":[]}"));
		
		Assert.assertTrue(mockServerClient.isRunning());
		System.out.println("==========================");
		System.out.println(mockServerClient.remoteAddress());
		System.out.println(mockServerClient.contextPath());
		System.out.println("==========================");
		
		URL url = new URL("http://localhost:"+mockServerRule.getPort()+"/books");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		System.out.println(content.toString());
	}

}
