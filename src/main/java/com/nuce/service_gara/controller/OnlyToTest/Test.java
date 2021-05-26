package com.nuce.service_gara.controller.OnlyToTest;


import com.nuce.service_gara.model.Employee;
import com.nuce.service_gara.model.InOutGate;
import com.nuce.service_gara.model.OneSignalMessage;
import com.nuce.service_gara.model.response.EmployeeResponseDTO;
import com.nuce.service_gara.repository.DeviceRepository;
import com.nuce.service_gara.repository.EmployeeRepo;
import com.nuce.service_gara.repository.InOutGateRepo;
import com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail.AutoExportAndSend;
import com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail.ReportService;
import com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail.SendEmail;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class Test {

    @Autowired
    private RestTemplate template;

    private String url = "https://onesignal.com/api/v1/notifications";
    private String appId = "13948c48-b3ba-4662-a872-f93786f6c00c";

    @Autowired
    private InOutGateRepo inOutGateRepo;

    @Autowired
    private AutoExportAndSend autoExportAndSend;

    @GetMapping("/getAll")
    public List<InOutGate> getAll() {
        List<InOutGate> list = inOutGateRepo.findAll();
        return list;
    }

    @Autowired
    private EmployeeRepo repository;

    @Autowired
    private ReportService service;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format, HttpServletResponse response) throws IOException, JRException {
        return service.exportReport(format, response);
    }


    @PostMapping("/sendEmail")
    public void sendEmail() {
        sendEmail.sendEmail();
    }


    @GetMapping("/unit")
    public EmployeeResponseDTO unit() {
        Optional<Employee> employee = repository.findByUsername("hong");
        EmployeeResponseDTO response = new EmployeeResponseDTO();
        response.setName(employee.get().getName());
        response.setUsername(employee.get().getUsername());
        return response;
    }

    @PostMapping("/hello-world")
    public String pushNotify() {
        String str = "hello world";
        OneSignalMessage message = new OneSignalMessage();
        message.setAppId(appId);
        message.setUrl(url);
        List<String> playerId = deviceRepository.findPlayerId();

        message.setIncludePlayerIds(playerId);
        message.getContents().put("en", str);
        template.postForEntity(message.getUrl(), message, String.class);
        return str;
    }

    @Autowired
    private InOutGateRepo in;

    @PostMapping("/delete")
    public boolean deleteTest(@RequestParam int IOGId, @RequestParam int serviceId) {
        in.deleteRS(IOGId, serviceId);
        return true;
    }

    @PostMapping("/auto-export-and-send-email")
    public String autoExportAndSend() throws IOException {
        autoExportAndSend.exportAndSend();
        return "success";
    }
}
