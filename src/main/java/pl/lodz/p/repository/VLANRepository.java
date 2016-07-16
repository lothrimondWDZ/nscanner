package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.VLAN;

public interface VLANRepository extends JpaRepository<VLAN, Integer> {

}
