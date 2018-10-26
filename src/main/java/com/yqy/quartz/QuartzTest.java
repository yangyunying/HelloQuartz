package com.yqy.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * User: yqy
 * Date: 14-2-26
 * Time: 0:16
 */
public class QuartzTest {
    public static void main(String[] args) {
        try {
            //1.从StdSchedulerFactory工厂中获取一个任务调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //2. 启动调度器
            scheduler.start();
            System.out.println("scheduler is start...");

            //关闭调度器
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
