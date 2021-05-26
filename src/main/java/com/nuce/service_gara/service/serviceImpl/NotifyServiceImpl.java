package com.nuce.service_gara.service.serviceImpl;

import com.nuce.service_gara.model.Device;
import com.nuce.service_gara.model.request.DeviceRequestDTO;
import com.nuce.service_gara.service.NotifyService;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Override
    public Device createDevice(DeviceRequestDTO req) {
        Device device = new Device();
        device.setPlayerId(req.getPlayerId());
        device.setOs(req.getOs());
        return device;
    }
}
