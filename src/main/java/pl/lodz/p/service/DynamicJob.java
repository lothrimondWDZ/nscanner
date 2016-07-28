package pl.lodz.p.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import pl.lodz.p.domain.entities.TestScript;

@Service
public class DynamicJob {

	public void schedule(TestScript testScript) {
		try {
			JobDetail job = JobBuilder.newJob(TestScript.class).withIdentity(testScript.getName(), "default group")
					.build();

			job.getJobDataMap().put("path", testScript.getPath());

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(testScript.getName().concat(" trigger"), "groupAll")
					.withSchedule(CronScheduleBuilder.cronSchedule(testScript.getCronExpression())).build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
