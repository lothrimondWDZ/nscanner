package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
