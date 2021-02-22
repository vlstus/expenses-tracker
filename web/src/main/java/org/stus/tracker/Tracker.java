package org.stus.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.stus"})
@EnableScheduling
@EnableAsync
public class Tracker {

    public static void main(String[] args) {
        SpringApplication.run(Tracker.class,args);
    }

}
