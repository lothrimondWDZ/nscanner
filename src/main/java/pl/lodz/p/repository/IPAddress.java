package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.IPaddress;

public interface IPAddress extends JpaRepository<IPaddress, Integer> {

}
