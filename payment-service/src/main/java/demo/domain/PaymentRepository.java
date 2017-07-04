package demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, String> {

    Payment findByOrderId(String orderId);
}
