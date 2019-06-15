package site.gabriellima.acmeapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import site.gabriellima.acmeapi.domain.OrderRequest;
import site.gabriellima.acmeapi.domain.enums.Status;
import site.gabriellima.acmeapi.repository.OrderRequestRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderRequestServiceTest {

  @Mock private OrderRequestRepository orderRequestRepository;

  @InjectMocks private OrderRequestService orderRequestService;

  @Test
  public void shouldCreateOrderRequest() {
    final OrderRequest orderRequest = new OrderRequest();

    when(orderRequestRepository.save(eq(orderRequest))).thenReturn(orderRequest);
    when(orderRequestRepository.findById(eq(orderRequest.getId())))
        .thenReturn(Optional.of(orderRequest));

    final OrderRequest orderRequestSaved = orderRequestService.save(orderRequest);

    assertEquals(orderRequestSaved, orderRequestRepository.findById(orderRequest.getId()).get());
  }

  @Test
  public void shouldUpdateStatusAndNote() {
    final OrderRequest orderRequest = new OrderRequest();
    orderRequest.setId(1L);

    when(orderRequestRepository.findById(eq(orderRequest.getId())))
        .thenReturn(Optional.of(orderRequest));

    orderRequestService.updateStatusAndNote(1L, Status.APPROVED, null);

    orderRequest.setStatus(Status.APPROVED);
    orderRequest.setNote(null);

    when(orderRequestRepository.findById(eq(1L))).thenReturn(Optional.of(orderRequest));

    assertEquals(orderRequest, orderRequestRepository.findById(orderRequest.getId()).get());
  }
}
