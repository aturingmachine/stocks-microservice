package com.example.symbols.symbolsapi.controller;

import com.example.symbols.symbolsapi.model.Symbol;
import com.example.symbols.symbolsapi.repository.SymbolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
