package pl.lodz.p.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.domain.entities.TestScript;

public interface TestScriptRepository extends JpaRepository<TestScript, Long> {

}
