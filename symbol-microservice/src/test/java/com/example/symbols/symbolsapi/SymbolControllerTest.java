package com.example.symbols.symbolsapi;

import com.example.symbols.symbolsapi.config.SecurityConfiguration;
import com.example.symbols.symbolsapi.controller.SymbolController;
import com.example.symbols.symbolsapi.repository.SymbolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {SecurityConfiguration.class})
public class SymbolControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  SymbolRepository symbols;

  @MockBean
  SymbolController controller;

  @Test
  public void testCreatedStatusOnLoad() throws Exception {
    mvc.perform(post("/symbols"))
        .andExpect(status().isCreated());
  }

  @Test
  public void testOkStatusOnGet() throws Exception {
    mvc.perform(get("/symbols/AAPL"))
        .andExpect(status().isOk());
  }
}
