package pl.lodz.p.domain.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class DynamicJob implements Job {

	private String name;
	private String path;
	private String cronExpression;
	private List<Parameter> parameters;

	public DynamicJob(TestScript script) {
		this.name = script.getName();
		this.parameters = script.getParameters();
		this.path = script.getPath();
		this.cronExpression = script.getCronExpression();
	}

	public DynamicJob() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		ArrayList<String> command = (ArrayList<String>) data.get("command");
		try {
			Process p = new ProcessBuilder(command.toArray(new String[command.size()])).start();
			// p.exitValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
