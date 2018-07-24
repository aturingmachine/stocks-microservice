package solstice.bootcamp.stocksapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.repository.StockDataRepository;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StockServiceTest {

  @MockBean
  private RestTemplate template;

  @MockBean
  private StockDataRepository repository;

  @InjectMocks
  private StockDataService service;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCompileData() throws URISyntaxException {
    when(repository.compileData(anyInt(), anyString())).thenReturn(
        new AggregateData(100, 100, 100, 100, 1)
    );

    when(template.getForObject(anyString(), any())).thenReturn(1);

    AggregateData agg = service.compileData("date", "AAPL", "2018-06-22");

    assertEquals(100, agg.getHighestPrice(), 0);
    assertEquals(1, agg.getCompany());
  }
}
