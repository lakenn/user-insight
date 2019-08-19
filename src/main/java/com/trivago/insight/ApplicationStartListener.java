package com.trivago.insight;

import com.trivago.insight.task.FileParsingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private FileParsingTask fileParsingTask;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // schedule file parsing task
        threadPoolTaskExecutor.execute(fileParsingTask);
    }
}
