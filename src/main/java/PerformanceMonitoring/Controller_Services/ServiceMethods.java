package PerformanceMonitoring.Controller_Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class ServiceMethods {


    @Autowired
    private MongoTemplate mongoTemplate;

    //genetrate alphanumericID
    public static String generateBase36ID()
    {
        int id = 0;
        id = (int)((Math.random() * 900000000)+100000000);
        //System.out.print((aNumber));
        return Integer.toString(id, 36).toUpperCase();
    }


    public static long generateID()
    {
        long range = 1234567L;
        Random r = new Random();
        long number = (long) (r.nextDouble() * range);

        return number;
    }




}
