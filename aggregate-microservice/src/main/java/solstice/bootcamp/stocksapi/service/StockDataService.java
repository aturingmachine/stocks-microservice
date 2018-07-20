package solstice.bootcamp.stocksapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.repository.StockDataRepository;

import java.io.IOException;
import java.net.*;
import java.util.List;

@Service
public class StockDataService {

  private StockDataRepository stockDataRepository;
  private RestOperations restTemplate;

  @Value("${stocks.remote.url}")
  private String remoteDataSource;

  public StockDataService(StockDataRepository stockDataRepository, RestTemplate restTemplate) {
    this.stockDataRepository = stockDataRepository;
    this.restTemplate = restTemplate;
  }

  public Iterable<StockData> load() throws IOException {
    URL url = new URL(remoteDataSource);
    URLConnection conn = url.openConnection();

    ObjectMapper mapper = new ObjectMapper(); //New up a Jackson Object Mapper
    List<StockData> data = mapper.readValue(conn.getInputStream(), new TypeReference<List<StockData>>() {
    });

    return stockDataRepository.save(data);
  }

  public Iterable<StockData> getAll() {
    return stockDataRepository.getAll();
  }

  public AggregateData compileData(String type, String symbol, String date) throws URISyntaxException {
    int id = fetchSymbolId(symbol);
    AggregateData data = stockDataRepository.compileData(id, date);
    data.setDateRequested(date);
    data.setType(type);

    return data;
  }

  private int fetchSymbolId(String symbol) throws URISyntaxException {
    return restTemplate.getForObject("//symbols/symbols/" + symbol, Integer.class);
  }

  private AggregateData compileMonth(int id, String date) {
    return null;
  }
}
