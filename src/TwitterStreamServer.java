import static spark.Spark.*;
import spark.*;

public class TwitterStreamServer {
    public static void main(String[] args) {

        final TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer();
        streamConsumer.start();

        //setPort(Integer.parseInt(System.getenv("PORT"))); Uncomment this for Heroku
        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello, spark";
            }
        });
    }
}
