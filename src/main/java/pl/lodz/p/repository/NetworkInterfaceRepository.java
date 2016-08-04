package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.NetworkInterface;

public interface NetworkInterfaceRepository extends JpaRepository<NetworkInterface, Long> {

}
