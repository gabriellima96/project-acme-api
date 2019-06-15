package site.gabriellima.acmeapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gabriellima.acmeapi.domain.OrderRequest;
import site.gabriellima.acmeapi.domain.enums.Status;
import site.gabriellima.acmeapi.service.OrderRequestService;
import site.gabriellima.acmeapi.service.dto.OrderRequestDTO;
import site.gabriellima.acmeapi.service.dto.StatusDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRequestResource {

  @Autowired private OrderRequestService orderRequestService;

  @PostMapping
  public ResponseEntity save(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
    OrderRequest orderRequest = orderRequestService.fromDTO(orderRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderRequestService.save(orderRequest));
  }

  @GetMapping()
  public ResponseEntity findAllSearch(
      @RequestParam(value = "status", required = false) Status status,
      @RequestParam(value = "requester", required = false) String requester,
      @RequestParam(value = "product", required = false) String productDescription,
      Pageable pageable) {
    return ResponseEntity.ok(
        orderRequestService.findAllBySearch(requester, productDescription, status, pageable));
  }

  @PutMapping("/{id}/status")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateStatus(@PathVariable("id") Long id, @Valid @RequestBody StatusDTO statusDTO) {
    orderRequestService.updateStatusAndNote(id, statusDTO.getStatus(), statusDTO.getNote());
  }
}
