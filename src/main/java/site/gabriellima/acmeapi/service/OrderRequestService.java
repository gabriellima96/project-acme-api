package site.gabriellima.acmeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.gabriellima.acmeapi.domain.OrderRequest;
import site.gabriellima.acmeapi.domain.Product;
import site.gabriellima.acmeapi.domain.enums.Status;
import site.gabriellima.acmeapi.exception.ObjectNotFoundException;
import site.gabriellima.acmeapi.repository.OrderRequestRepository;
import site.gabriellima.acmeapi.service.dto.OrderRequestDTO;

@Service
public class OrderRequestService {

  private static final String ORDER_REQUEST_NOT_FOUND = "Order Request %s not found";

  private final OrderRequestRepository orderRequestRepository;

  @Autowired
  public OrderRequestService(OrderRequestRepository orderRequestRepository) {
    this.orderRequestRepository = orderRequestRepository;
  }

  public OrderRequest save(OrderRequest orderRequest) {
    return orderRequestRepository.save(orderRequest);
  }

  public void updateStatusAndNote(Long id, Status status, String note) {
    OrderRequest orderRequest =
        orderRequestRepository
            .findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException(String.format(ORDER_REQUEST_NOT_FOUND, id)));

    orderRequest.setStatus(status);
    orderRequest.setNote(note);

    orderRequestRepository.save(orderRequest);
  }

  public Page<OrderRequest> findAllBySearch(
      String requester, String productDescription, Status status, Pageable pageable) {

    return orderRequestRepository.findAll(
        createExample(requester, productDescription, status), pageable);
  }

  public OrderRequest fromDTO(OrderRequestDTO orderRequestDTO) {
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setRequester(orderRequestDTO.getRequester());
    orderRequest.setProduct(orderRequestDTO.getProduct());
    return orderRequest;
  }

  private Example<OrderRequest> createExample(
      String requester, String productDescription, Status status) {
    ExampleMatcher exampleMatcher =
        ExampleMatcher.matchingAll()
            .withIgnoreCase("requester", "status", "product.description")
            .withMatcher("requester", m -> m.contains())
            .withMatcher("product.description", m -> m.contains());

    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setProduct(new Product(productDescription, null));
    orderRequest.setRequester(requester);
    orderRequest.setStatus(status);

    return Example.of(orderRequest, exampleMatcher);
  }
}
