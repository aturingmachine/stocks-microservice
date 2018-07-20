package solstice.bootcamp.stocksapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class StockData {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @JsonProperty("symbol")
  private int company;
  private float price;
  private int volume;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private Date date;

  public StockData(int id, int company, float price, int volume, Timestamp date) {
    this.id = id;
    this.company = company;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public StockData(int company, float price, int volume, Timestamp date) {
    this.company = company;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public StockData(String symbol, float price, int volume, Timestamp date) {
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public StockData() {
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonIgnore
  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("company")
  public int getCompanyId() {
    return company;
  }

  @JsonIgnore
  public void setCompanyId(int company) {
    this.company = company;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }
}