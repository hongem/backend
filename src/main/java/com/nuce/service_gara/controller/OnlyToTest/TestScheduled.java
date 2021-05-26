package com.nuce.service_gara.controller.OnlyToTest;

import com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail.AutoExportAndSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestScheduled {

    private static final Logger log = LoggerFactory.getLogger(TestScheduled.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private AutoExportAndSend autoExportAndSend;

    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() throws IOException {
        log.info("The time is now {}", dateFormat.format(new Date()));
//        autoExportAndSend.exportAndSend();
    }
}