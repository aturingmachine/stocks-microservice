package solstice.bootcamp.stocksapi.controller;


import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/stocks")
public class StockDataController {
  private final StockDataService stockDataService;

  public StockDataController(StockDataService stockDataService) {
    this.stockDataService = stockDataService;
  }

  @PostMapping("/load")
  @ResponseStatus(HttpStatus.CREATED)
  public Iterable<StockData> load() throws IOException {
    return stockDataService.load();
  }

  @GetMapping("")
  public Iterable<StockData> getAll() {
    return null;
  }


  @GetMapping("/{type}/{symbol}/{date}")
  public AggregateData getAggregateData(
      @PathVariable("type") String type,
      @PathVariable("symbol") String symbol,
      @PathVariable("date") String date) throws URISyntaxException {

    return stockDataService.compileData(type, symbol, date);
  }
}
