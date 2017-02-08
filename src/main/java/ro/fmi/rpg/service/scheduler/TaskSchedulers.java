package ro.fmi.rpg.service.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by cristian.rosu on 2/8/2017.
 */
@Service
public class TaskSchedulers {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskCheckerWorker taskCheckerWorker;

    @Scheduled(fixedDelayString = "10000")
    public void checkNotificationQueue(){
        LOG.info("In task scheduler");
        taskCheckerWorker.sendBeforeTaskNotificaiton();
        taskCheckerWorker.sendLateTaskEmailNotification();
    }

}
