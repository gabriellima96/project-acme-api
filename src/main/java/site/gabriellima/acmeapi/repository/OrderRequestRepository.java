package site.gabriellima.acmeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gabriellima.acmeapi.domain.OrderRequest;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {}
