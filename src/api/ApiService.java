package api;

import java.io.IOException;

public interface ApiService {
    String findRatesInPair(String firstCurrency, String secondCurrency, String amount) throws IOException, InterruptedException;

    String findLastValueOfCurrency(String firstValue) throws IOException, InterruptedException;
}
