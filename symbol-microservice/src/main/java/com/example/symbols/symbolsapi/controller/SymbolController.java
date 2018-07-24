package com.example.symbols.symbolsapi.controller;

import com.example.symbols.symbolsapi.model.Symbol;
import com.example.symbols.symbolsapi.repository.SymbolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/symbols")
public class SymbolController {

    private SymbolRepository symbols;

    public SymbolController(SymbolRepository symbols) {
        this.symbols = symbols;
    }

    @GetMapping("/{symbol}")
    public Integer getIdBySymbol(@PathVariable("symbol") String symbol) {
        return symbols.findIdBySymbol(symbol).getId();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void load() {
      ArrayList<String> arr = new ArrayList<>(Arrays.asList("AAPL", "GOOG", "MSFT", "PVTL", "AMZN"));

      arr.forEach(i -> symbols.save(new Symbol(i)));
    }
}
