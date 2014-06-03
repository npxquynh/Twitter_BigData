package edu.unitn.quynhpx;
import org.scribe.oauth.OAuthService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TwitterStreamConsumer extends Thread {

    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/sample.json";

    public void run(){
        try{
            System.out.println("Starting Twitter public stream consumer thread.");

            // Enter your consumer key and secret below
            OAuthService service = new ServiceBuilder()
                    .provider(TwitterApi.class)
                    .apiKey("OOZKe0dvCzgS6Isxcpg98g")
                    .apiSecret("HkLM90v39VhgptMCPMENUd2Sgq6rS2YpX45xS6Nro")
                    .build();

            // Set your access token
            Token accessToken = new Token("867221126-vsmwC9Gf5DNTh7zQkxxnWojhzAdrEQ0kqKSEZhI7", 
        	    "FCEgFIWVIwdpKYqkQ7YRxHa1sxlT0MFDJN6hfCWQc");

            // Let's generate the request
            System.out.println("Connecting to Twitter Public Stream");
            OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
            request.addHeader("version", "HTTP/1.1");
            request.addHeader("host", "stream.twitter.com");
            request.setConnectionKeepAlive(true);
            request.addHeader("user-agent", "Twitter Stream Reader");
            // request.addBodyParameter("track", "java,heroku,twitter"); // Set keywords you'd like to track here
            service.signRequest(accessToken, request);
            Response response = request.send();

            // Create a reader to read Twitter's stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}