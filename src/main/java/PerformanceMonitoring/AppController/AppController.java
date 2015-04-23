package PerformanceMonitoring.AppController;


import PerformanceMonitoring.Controller_Services.HelloVM;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

@RestController
@RequestMapping("/")
public class AppController
{


    HelloVM hvm = new HelloVM();

    //test vm
    @RequestMapping(value={"vmtest"},method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void testVM() throws MalformedURLException, RemoteException
    {
         hvm.vmtest();
        return;
    }



}