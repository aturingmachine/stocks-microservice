package solstice.bootcamp.stocksapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import solstice.bootcamp.stocksapi.config.SecurityConfiguration;
import solstice.bootcamp.stocksapi.controller.StockDataController;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.repository.StockDataRepository;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {SecurityConfiguration.class})
public class StockDataControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private StockDataService mockDataService;

  @MockBean
  private StockDataController controller;

  private StockData data = new StockData(5, 100, 200, new Timestamp(2018-06-26));

  @Test
  public void get201OnLoad() throws Exception {
    mvc.perform(post("/stocks/load"))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  public void testResult() throws Exception {
    mvc.perform(get("/stocks/date/AMZN/2018-06-26"))
        .andExpect(status().isOk())
        .andDo(print());
  }
}
