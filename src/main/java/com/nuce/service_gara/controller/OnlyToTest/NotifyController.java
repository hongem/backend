package com.nuce.service_gara.controller.OnlyToTest;

import com.nuce.service_gara.model.Device;
import com.nuce.service_gara.model.request.DeviceRequestDTO;
import com.nuce.service_gara.repository.DeviceRepository;
import com.nuce.service_gara.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private DeviceRepository deviceRepository;

    @PostMapping("/web")
    public ResponseEntity<Device> post(@RequestBody DeviceRequestDTO req) {
        log.info("Rest Request to save Device {}", req);
        Device device = notifyService.createDevice(req);
        Device result = deviceRepository.save(device);
        return ResponseEntity.ok().body(result);
    }


}
