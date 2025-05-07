package WeatherAppProject ; 
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de la ville : ");
        String city = scanner.nextLine();

        String apiKey = "d0deb8a62c8d3776a54b3e57caf92ece"; // ❗ Remplace ça avec ta vraie clé API
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();


            JSONObject obj = new JSONObject(content.toString());

            String weather = obj.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = obj.getJSONObject("main").getDouble("temp");
            int humidity = obj.getJSONObject("main").getInt("humidity");
            double windSpeed = obj.getJSONObject("wind").getDouble("speed");

            System.out.println("\n🔹 Météo : " + weather);
            System.out.println("🌡️ Température : " + temperature + " °C");
            System.out.println("💧 Humidité : " + humidity + " %");
            System.out.println("🌬️ Vent : " + windSpeed + " m/s");

        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }

        scanner.close();
    }
}
