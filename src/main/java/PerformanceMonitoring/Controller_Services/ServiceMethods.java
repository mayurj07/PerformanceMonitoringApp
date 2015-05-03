package PerformanceMonitoring.Controller_Services;

import PerformanceMonitoring.AppController.BadRequestException;
import PerformanceMonitoring.Model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServiceMethods {


    public static final String COLLECTION_logs = "logs";

    @Autowired
    private MongoTemplate mongoTemplate;


    public Map<String, Object> getVmStats(String vm_name, String start_time,String end_time) throws BadRequestException
    {
        BasicQuery query_stats = new BasicQuery("{timestamp : { \"$gte\" : \""+start_time+"\" , \"$lte\" : \""+end_time+"\"} , vmName : \""+vm_name+"\"}");

        try {
            ArrayList<Stats> metrics = (ArrayList<Stats>) mongoTemplate.find(query_stats, Stats.class,COLLECTION_logs);
            Map<String, Object> metricMap = new HashMap<>();

            ArrayList<Integer> cpu_usage_list = new ArrayList<>();
            ArrayList<Integer> memory_usage_list = new ArrayList<>();
            ArrayList<Integer> net_usage_list = new ArrayList<>();
            ArrayList<Integer> disk_usage_list = new ArrayList<>();

            for(Stats stats: metrics)
            {
                cpu_usage_list.add(stats.getCpuUsage());
                memory_usage_list.add(stats.getMemoryUsage());
                net_usage_list.add(stats.getNetUsage());
                disk_usage_list.add(stats.getDiskUsage());
            }

            metricMap.put("vmName", metrics.get(0).getVmName());
            metricMap.put("cpu.usage.average", cpu_usage_list);
            metricMap.put("memory.usage.average", memory_usage_list);
            metricMap.put("disk.usage.average", disk_usage_list);
            metricMap.put("net.usage.average", net_usage_list);

            return metricMap;
        }
        catch (Exception e)
        {
            throw new BadRequestException("Entity not found or exception" + e);
        }

    }


    public Stats getVM(String id)
    {
        Stats s = mongoTemplate.findById(id, Stats.class, COLLECTION_logs);
        return s;
    }


}
