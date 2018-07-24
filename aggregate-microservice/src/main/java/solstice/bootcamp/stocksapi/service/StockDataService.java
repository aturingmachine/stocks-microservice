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
import solstice.bootcamp.stocksapi.repository.StockLoadRepository;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;

@Service
public class StockDataService {

  private StockDataRepository stockDataRepository;
  private StockLoadRepository stockLoadRepository;
  private RestOperations restTemplate;

  @Value("${stocks.remote.url}")
  private String remoteDataSource;

  public StockDataService(StockDataRepository stockDataRepository, StockLoadRepository stockLoadRepository, RestTemplate restTemplate) {
    this.stockDataRepository = stockDataRepository;
    this.stockLoadRepository = stockLoadRepository;
    this.restTemplate = restTemplate;
  }

  public Iterable<StockData> load() throws IOException {
    URL url = new URL(remoteDataSource);
    URLConnection conn = url.openConnection();

    ObjectMapper mapper = new ObjectMapper(); //New up a Jackson Object Mapper
    List<StockData> data = Arrays.asList(mapper.readValue(conn.getInputStream(), StockData[].class));

    data.forEach(datum -> stockLoadRepository.save(datum));

    return getAll();
  }

  public Iterable<StockData> getAll() {
    return stockLoadRepository.findAll();
  }

  public AggregateData compileData(String type, String symbol, String date) throws URISyntaxException {
    int id = restTemplate.getForObject("//zuul/symbols/symbols/" + symbol, Integer.class);
    AggregateData data = stockDataRepository.compileData(id, date);
    data.setDateRequested(date);
    data.setType(type);

    return data;
  }
}
