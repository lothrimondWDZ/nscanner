package pl.lodz.p.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "test_script")
public class TestScript {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_script_seq_gen")
	@SequenceGenerator(name = "test_script_seq_gen", sequenceName = "test_script_seq")
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column(name = "cron_expression")
	private String cronExpression;
	@Column
	private String path;
	@OneToMany(mappedBy = "testScript", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parameter> parameters;
	@Column(name = "last_result")
	private Integer lastResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Integer getLastResult() {
		return lastResult;
	}

	public void setLastResult(Integer lastResult) {
		this.lastResult = lastResult;
	}

}
