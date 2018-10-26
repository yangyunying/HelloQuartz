package com.yqy.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * User: yqy
 * Date: 14-2-26
 * Time: 0:16
 */
public class QuartzTest2 {
    public static void main(String[] args) {
        try {
            //1.从StdSchedulerFactory工厂中获取一个任务调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //2. 启动调度器
            scheduler.start();
            System.out.println("scheduler is start...");
            //3. 添加定时任务
            //  3.1 定义job
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            //  3.2 定义Trigger，使得job现在就运行，并每隔3s中运行一次，重复运行5次, withRepeatCount(number)设定job运行次数为number+1
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .withRepeatCount(4))
                    .build();

            //  3.3 交给scheduler去调度
            scheduler.scheduleJob(job, trigger);
            //4. 关闭调度器
            //scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义Job，实现Job接口并实现execute方法
     */
    public static class HelloJob implements Job{

        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("execute job at " + new Date() + " by trigger " + context.getTrigger().getJobKey());
        }
    }
}
