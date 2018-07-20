package com.example.symbols.symbolsapi.repository;

import com.example.symbols.symbolsapi.model.Symbol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends CrudRepository<Symbol, Integer> {

  Symbol findIdBySymbol(String symbol);
}
