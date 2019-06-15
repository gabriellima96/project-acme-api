package site.gabriellima.acmeapi.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Product implements Serializable {

  @NotBlank
  private String description;

  @NotNull
  private Double price;

  public Product() {}

  public Product(String description, Double price) {
    this.description = description;
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
