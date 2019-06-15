package site.gabriellima.acmeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.gabriellima.acmeapi.domain.OrderRequest;
import site.gabriellima.acmeapi.domain.enums.Status;
import site.gabriellima.acmeapi.exception.ObjectNotFoundException;
import site.gabriellima.acmeapi.repository.OrderRequestRepository;

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
}
