package com.nuce.service_gara.service;

import com.nuce.service_gara.model.Device;
import com.nuce.service_gara.model.request.DeviceRequestDTO;

public interface NotifyService {
    Device createDevice(DeviceRequestDTO req);
}
