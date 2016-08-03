package pl.lodz.p.service;

import java.util.ArrayList;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import pl.lodz.p.domain.entities.DynamicJob;
import pl.lodz.p.domain.entities.Parameter;

@Service
public class DynamicJobService {

	public void schedule(DynamicJob dynamicJob) {
		try {
			JobDetail job = JobBuilder.newJob(DynamicJob.class).withIdentity(dynamicJob.getName(), "default group")
					.build();

			ArrayList<String> command = new ArrayList<>();
			command.add(dynamicJob.getPath());
			for (Parameter param : dynamicJob.getParameters()) {
				command.add(param.getValue());
			}
			job.getJobDataMap().put("command", command);

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(dynamicJob.getName().concat(" trigger"), "groupAll")
					.withSchedule(CronScheduleBuilder.cronSchedule(dynamicJob.getCronExpression())).build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
