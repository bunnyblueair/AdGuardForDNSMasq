package io.bunnyblue.dnsmasq.adguard;

import io.bunnyblue.dnsmasq.adguard.dns.DNSCall;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class DomainFilter {
    static ForkJoinPool forkJoinPool;

    ArrayList<String> list = new ArrayList<>();

    public static void init() {
        ArrayList<ForkJoinTask<String>> tasks = new ArrayList<ForkJoinTask<String>>();
        forkJoinPool = new ForkJoinPool(96);
        ArrayList<String> list = new ArrayList<>();
        Collection<String> hosts = null;
        try {
            hosts = FileUtils.readLines(new File("adguard-dnsmasq.conf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String data :
                hosts) {
            if (data.startsWith("address=/")) {
                String url = data.replaceAll("address=/", "").replaceAll("/", "");
                DNSCall dnsCall = new DNSCall();
                dnsCall.address = data;
                dnsCall.host = url;
                // forkJoinPool.submit(dnsCall);
                tasks.add(forkJoinPool.submit(dnsCall));

//                                if (ok(url)){
//                                        list.add(data);
//                                }
//                                System.out.println(ok+" vs "+err);

            } else {
                list.add(data);
            }


        }
        for (ForkJoinTask<String> addressTasK : tasks) {
            try {
                String asdress = addressTasK.get();
                if (asdress != null) {
                    list.add(asdress);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            FileUtils.writeLines(new File("adguard-dnsmasq2.conf"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  FileUt
    }
}
