package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.Network;

public interface NetworkRepository extends JpaRepository<Network, Integer> {

}
