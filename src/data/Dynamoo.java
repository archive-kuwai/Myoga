package data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.arnx.jsonic.*;
import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.services.dynamodb.*;

public class Dynamoo {
	
	protected static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm (ss.SSS)");
	
    protected static AmazonDynamoDBClient client = null;
    private static void createClient() throws IOException {
        AWSCredentials crednt = new PropertiesCredentials(Dynamoo.class.getResourceAsStream("AwsCredentials.properties"));
        client = new AmazonDynamoDBClient(crednt);
        client.setEndpoint("http://dynamodb.ap-northeast-1.amazonaws.com");
    }
	
	public Dynamoo() throws IOException{
		if(client == null){
			createClient();
		}
	}

	
}
