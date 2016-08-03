package pl.lodz.p.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PARAMETER")
public class Parameter {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_script_seq_gen")
	@SequenceGenerator(name = "test_script_seq_gen", sequenceName = "test_script_seq")
	private Long id;
	@Column
	private String value;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "test_script")
	private TestScript testScript;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TestScript getTestScript() {
		return testScript;
	}

	public void setTestScript(TestScript testScript) {
		this.testScript = testScript;
	}

}
