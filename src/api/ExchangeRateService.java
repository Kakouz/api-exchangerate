package api;

import com.google.gson.*;
import model.ExchangeRatePairResponse;
import utils.Converter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.io.IOException;

public class ExchangeRateService implements ApiService {
    private String API_KEY;
    private String API_URL = "";

    public ExchangeRateService() {
        try (InputStream input = new FileInputStream("src/resources/external-resources.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            this.API_KEY = prop.getProperty("API_KEY");
            this.API_URL = "https://v6.exchangerate-api.com/v6/" + this.API_KEY + "/";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String findRatesInPair(String firstCurrency, String secondCurrency, String amount) throws IOException, InterruptedException {
        URI fullUrl = URI.create(this.API_URL + "pair/" + firstCurrency + "/" + secondCurrency + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(fullUrl).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ExchangeRatePairResponse fullResponse = gson.fromJson(json, ExchangeRatePairResponse.class);
        return Converter.formatResponseToValue(fullResponse.conversion_result());
    }

    @Override
    public String findLastValueOfCurrency(String firstValue) throws IOException, InterruptedException {
        URI fullUrl = URI.create(this.API_URL + "latest/" + firstValue);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(fullUrl).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ExchangeRatePairResponse fullResponse = gson.fromJson(json, ExchangeRatePairResponse.class);
        return Converter.formatResponseToValue(fullResponse.conversion_result());
    }


}
