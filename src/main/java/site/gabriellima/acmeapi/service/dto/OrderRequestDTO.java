package site.gabriellima.acmeapi.service.dto;

import site.gabriellima.acmeapi.domain.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderRequestDTO implements Serializable {

  @NotBlank
  private String requester;

  @Valid @NotNull
  private Product product;

  public OrderRequestDTO() {}

  public String getRequester() {
    return requester;
  }

  public void setRequester(String requester) {
    this.requester = requester;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
