package com.nuce.service_gara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableScheduling
public class ServiceGaraApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGaraApplication.class, args);
    }

    @RequestMapping("/")
    public String test(){
        return "index";
    }

}
