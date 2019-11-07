package network;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONObject;



public class ReadWebPageWeather {

    private String apiKey = "82f0c0ff2fa179f9ebb40b1020bf55e7";
    private String vancouver = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
    private String weatherString;

    public ReadWebPageWeather() {

    }

    public void readWeather() throws IOException {
        BufferedReader br = null;

        try {
            String urlString = vancouver + apiKey;
            URL url = new URL(urlString);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                weatherString = line;
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public String parseWeather() {
        JSONObject obj = new JSONObject(weatherString);

        JSONArray weatherArray = obj.getJSONArray("weather");
        for (int i = 0; i < weatherArray.length(); i++) {
            String description = weatherArray.getJSONObject(i).getString("main");
            return description;
        }

        return null;
    }

    public Double parseTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double temperature = obj.getJSONObject("main").getDouble("temp");
        double temperatureInC = temperature - 273.15;

        return temperatureInC;
    }

    public Double parseMaxTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double maxTemp = obj.getJSONObject("main").getDouble("temp_max");
        double maxTempInC = maxTemp - 273.15;

        return maxTempInC;
    }

    public Double parseMinTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double minTemp = obj.getJSONObject("main").getDouble("temp_min");
        double minTempInC = minTemp - 273.15;

        return minTempInC;
    }

}
