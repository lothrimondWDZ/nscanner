package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.IPaddress;

/**
 * Spring Data JPA repository for the IPaddress entity.
 */
public interface IPaddressRepository extends JpaRepository<IPaddress, Long> {

}
