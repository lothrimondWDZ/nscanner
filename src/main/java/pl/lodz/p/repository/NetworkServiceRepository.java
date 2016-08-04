package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.NetworkService;

public interface NetworkServiceRepository extends JpaRepository<NetworkService, Long> {

}
