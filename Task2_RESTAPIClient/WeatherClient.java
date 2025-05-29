import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherClient {
    public static void main(String[] args) throws Exception {
        String apiKey = "your_api_key"; // Use a real API key
        String city = "London";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        System.out.println("City: " + json.getString("name"));
        System.out.println("Weather: " + json.getJSONArray("weather").getJSONObject(0).getString("description"));
        System.out.println("Temperature: " + json.getJSONObject("main").getDouble("temp"));
    }
}
