package practice.newbalance.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.member.DeliveryAddress;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    public Optional<DeliveryAddress> findByDefaultYN(Boolean defaultYN);
    public List<DeliveryAddress> findByMemberId(Long id);
}
