package PerformanceMonitoring.Controller_Services;

import PerformanceMonitoring.Model.Host;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.PerformanceManager;
import com.vmware.vim25.mo.ServiceInstance;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

public class HelloVM
{
    static final String SERVER_NAME = "130.65.159.14";
    static final String USER_NAME = "vsphere.local\\cmpe283_sec3_student";
    static final String PASSWORD = "cmpe283@sec3";
    private static final String HOSTNAME = "130.65.159.11";
    private static final int SELECTED_COUNTER_ID = 25; // Active (mem) in KB (absolute)

    public void vmtest() throws MalformedURLException, RemoteException
    {

        Host host1 = new Host();

        String url = "https://" + SERVER_NAME + "/sdk/vimService";
        try {
            ServiceInstance si = new ServiceInstance(new URL(url), USER_NAME, PASSWORD, true);
            HostSystem host = (HostSystem) new InventoryNavigator(si.getRootFolder()).searchManagedEntity("HostSystem", HOSTNAME);
            PerformanceManager perfMgr = si.getPerformanceManager();
            System.out.println("Counter ID = " + SELECTED_COUNTER_ID);

            PerfProviderSummary summary = perfMgr.queryPerfProviderSummary(host);
            int perfInterval = summary.getRefreshRate();


            System.out.println("Refresh rate = " + perfInterval);

            java.util.Calendar startTime = Calendar.getInstance();

            java.util.Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.MINUTE,+1);


            PerfMetricId[] queryAvailablePerfMetric = perfMgr.queryAvailablePerfMetric(host, startTime, endTime, perfInterval);

            ArrayList<PerfMetricId> list = new ArrayList<PerfMetricId>();

            for (int i2 = 0; i2 < queryAvailablePerfMetric.length; i2++)
            {
                PerfMetricId perfMetricId = queryAvailablePerfMetric[i2];
                if (SELECTED_COUNTER_ID == perfMetricId.getCounterId()) {
                    list.add(perfMetricId);
                }
            }

           // int counterId = queryAvailablePerfMetric[0].getCounterId();
            //System.out.println(" counterId:  " +counterId);

            //PerfMetricId perfMetricId =  queryAvailablePerfMetric[0];
            //list.add(perfMetricId);

            PerfMetricId[] pmis = list.toArray(new PerfMetricId[list.size()]);

            PerfQuerySpec qSpec = new PerfQuerySpec();
            qSpec.setEntity(host.getMOR());
            qSpec.setMetricId(pmis);
            qSpec.intervalId = perfInterval;

            PerfEntityMetricBase[] pembs = perfMgr.queryPerf(new PerfQuerySpec[] { qSpec });
            for (int i = 0; pembs != null && i < pembs.length; i++)
            {
                PerfEntityMetricBase val = pembs[i];
                PerfEntityMetric pem = (PerfEntityMetric) val;
                PerfMetricSeries[] vals = pem.getValue();
                PerfSampleInfo[] infos = pem.getSampleInfo();

                for (int j = 0; vals != null && j < vals.length; ++j) {
                    PerfMetricIntSeries val1 = (PerfMetricIntSeries) vals[j];
                    long[] longs = val1.getValue();
                    for (int k = 0; k < longs.length; k++) {
                        System.out.println(infos[k].getTimestamp().getTime() + " : " + longs[k]);
                    }
                    System.out.println();
                }
            }
            si.getServerConnection().logout();

        } catch (InvalidProperty e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RuntimeFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


            host1.setId((long) 1);
            host1.setName(HOSTNAME);

          //  return host1;
    }

}