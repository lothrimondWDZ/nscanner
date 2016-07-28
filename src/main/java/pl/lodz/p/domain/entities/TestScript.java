package pl.lodz.p.domain.entities;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Entity
@Table(name = "test_script")
@DisallowConcurrentExecution
public class TestScript implements Job {

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

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String path = data.getString("path");
		try {
			Process p = new ProcessBuilder(path).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
