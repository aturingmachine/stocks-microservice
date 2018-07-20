package solstice.bootcamp.stocksapi.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class StockDataRepository {

  private final JdbcTemplate template;

  private final RowMapper<StockData> stockDataRowMapper = (ResultSet rs, int row) -> new StockData(
      rs.getInt("company"),
      rs.getFloat("price"),
      rs.getInt("volume"),
      rs.getTimestamp("date")
  );

  private final RowMapper<AggregateData> aggregateDataRowMapper = (ResultSet rs, int row) -> new AggregateData(
      rs.getFloat(1),
      rs.getFloat(2),
      rs.getInt(3),
      rs.getFloat(4),
      rs.getInt("company")
  );

  private final String INSERT = "INSERT INTO stock_data (company, price, volume, date) values(?, ?, ?, ?)";

  private final String GET_ALL = "SELECT * FROM stock_data";

  private final String COMPILE_DATE = "SELECT MAX(price), MIN(price), SUM(volume), " +
      "(select price from stock_data where company = [ID]" +
      " and date = (select max(date) from stock_data where date like '[DATE]%')) as CLOSE_PRICE," +
      " company from stock_data " +
      "where company = [ID] and date like '%[DATE]%'";

  public StockDataRepository(JdbcTemplate template) {
    this.template = template;
  }

  public List<StockData> save(List<StockData> stockData) throws IOException {

    stockData.forEach(datum -> template.update(INSERT,
        datum.getCompanyId(),
        datum.getPrice(),
        datum.getVolume(),
        datum.getDate()
    ));

    return getAll();

  }

  public List<StockData> getAll() throws DataAccessException {
    return template.query(GET_ALL, stockDataRowMapper);
  }


  public AggregateData compileData(int id, String date)
      throws EmptyResultDataAccessException, NullPointerException {

    String queryString = COMPILE_DATE
        .replace("[ID]", Integer.valueOf(id).toString())
        .replace("[DATE]", date);

    return template.queryForObject(queryString, aggregateDataRowMapper);

  }
}