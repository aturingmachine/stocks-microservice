package solstice.bootcamp.stocksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.StockData;

@Repository
public interface StockLoadRepository  extends JpaRepository<StockData, Integer> {
}
