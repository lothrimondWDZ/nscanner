package pl.lodz.p.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import pl.lodz.p.config.AutowiringSpringBeanJobFactory;
import pl.lodz.p.domain.entities.DynamicJob;
import pl.lodz.p.domain.entities.Parameter;

@Service
public class DynamicJobService {

	public static String DEFAULT_JOB_GROUP = "default group";
	private Scheduler scheduler;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	private void initScheduler() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
			jobFactory.setApplicationContext(applicationContext);
			scheduler.setJobFactory(jobFactory);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void schedule(DynamicJob dynamicJob) {
		try {
			JobDetail job = JobBuilder.newJob(DynamicJob.class).withIdentity(getJobName(dynamicJob), DEFAULT_JOB_GROUP)
					.build();

			ArrayList<String> command = new ArrayList<>();
			command.add(dynamicJob.getPath());
			if (dynamicJob.getParameters() != null) {
				for (Parameter param : dynamicJob.getParameters()) {
					command.add(param.getValue());
				}
			}
			job.getJobDataMap().put("command", command);
			job.getJobDataMap().put("scriptId", dynamicJob.getScriptId());

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(dynamicJob.getName().concat(" trigger"), "groupAll")
					.withSchedule(CronScheduleBuilder.cronSchedule(dynamicJob.getCronExpression())).build();

			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getJobName(DynamicJob dynamicJob) {
		return dynamicJob.getName().concat(dynamicJob.getScriptId().toString());
	}

	public void editJob(DynamicJob job) {
		removeJob(job);
		schedule(job);
	}

	public void removeJob(DynamicJob job) {
		try {
			scheduler.deleteJob(new JobKey(getJobName(job), DEFAULT_JOB_GROUP));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void unscheduleAllJobs() {
		try {
			scheduler.clear();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void runJob(DynamicJob dynamicJob) {
		try {
			JobDataMap data = new JobDataMap();
			ArrayList<String> command = new ArrayList<>();
			command.add(dynamicJob.getPath());
			if (dynamicJob.getParameters() != null) {
				for (Parameter param : dynamicJob.getParameters()) {
					command.add(param.getValue());
				}
			}
			data.put("command", command);
			data.put("scriptId", dynamicJob.getScriptId());
			scheduler.triggerJob(new JobKey(getJobName(dynamicJob), DEFAULT_JOB_GROUP), data);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
