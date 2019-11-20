package network;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;


public class ReadWebPageWeather {

    private String apiKey = "82f0c0ff2fa179f9ebb40b1020bf55e7";
    private String vancouver = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
    private String weatherString;

    public ReadWebPageWeather() {}

    //REQUIRES: vancouver, apiKey not null
    //MODIFIES: this
    //EFFECTS: accesses the weather api and stores the JSON file into weatherString
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

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the description of the weather
    public String parseWeather() {
        JSONObject obj = new JSONObject(weatherString);

        JSONArray weatherArray = obj.getJSONArray("weather");
        for (int i = 0; i < weatherArray.length(); i++) {
            String description = weatherArray.getJSONObject(i).getString("description");
            return description;
        }

        return null;
    }

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the temperature in celsius, originally kelvins in JSON
    public Double parseTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double temperature = obj.getJSONObject("main").getDouble("temp");
        double temperatureInC = temperature - 273.15;

        return temperatureInC;
    }

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the max temperature in celsius, originally kelvins in JSON
    public Double parseMaxTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double maxTemp = obj.getJSONObject("main").getDouble("temp_max");
        double maxTempInC = maxTemp - 273.15;

        return maxTempInC;
    }

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the min temperature in celsius, originally kelvins in JSON
    public Double parseMinTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double minTemp = obj.getJSONObject("main").getDouble("temp_min");
        double minTempInC = minTemp - 273.15;

        return minTempInC;
    }

    public URL parseIcon() throws MalformedURLException {
        JSONObject obj = new JSONObject(weatherString);

        JSONArray weatherArray = obj.getJSONArray("weather");
        for (int i = 0; i < weatherArray.length(); i++) {
            String icon = weatherArray.getJSONObject(i).getString("icon");
            String iconURL = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
            URL url = new URL(iconURL);
            return url;
        }

        return null;
    }

    public BufferedImage weatherIcon() throws IOException {
        BufferedImage iconImage = ImageIO.read(parseIcon());
        return iconImage;
    }

}
