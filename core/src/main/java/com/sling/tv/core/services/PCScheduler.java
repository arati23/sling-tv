package com.sling.tv.core.services;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(name = "com.sling.tv.core.scheduler", label = "PC Scheduler", description = "Scheduler to run the PC jobs  ", metatype = true, immediate = true)
@Service
@Property(name = "scheduler.expression", value = "0 0/1 * 1/1 * ? *")
public class PCScheduler implements Runnable {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Override
    public void run() {
        LOG.info("Entering run method!!!");

    }
}