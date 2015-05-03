package PerformanceMonitoring.AppController;


import PerformanceMonitoring.Controller_Services.ServiceMethods;
import PerformanceMonitoring.Model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AppController
{

    @Autowired
    ServiceMethods service = new ServiceMethods();


    //get VM stats
    @RequestMapping(value={"vm/{vm_name}/start/{start_time}/end/{end_time}"},method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getVmStats(@PathVariable(value = "vm_name") String vm_name,
                                        @PathVariable(value = "start_time") String start_time,
                                        @PathVariable(value = "end_time") String end_time)
    {
        return service.getVmStats(vm_name, start_time, end_time);
    }

    //get Host stats
    @RequestMapping(value={"host/{host_name}/start/{start_time}/end/{end_time}"},method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getHostStats(@PathVariable(value = "host_name") String host_name,
                                            @PathVariable(value = "start_time") String start_time,
                                            @PathVariable(value = "end_time") String end_time) {
        return service.getVmStats(host_name, start_time, end_time);
    }


    //get Metrics for a specific VM
    @RequestMapping(value={"/vm/{vm_name}"},method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Stats getvm(@PathVariable(value = "vm_name") String id)
    {
        return service.getVM(id);
    }

}