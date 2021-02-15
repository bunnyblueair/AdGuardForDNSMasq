package io.bunnyblue.dnsmasq.adguard.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class DNSCall implements Callable<String> {
    static AtomicInteger count = new AtomicInteger(0);
    public String address;
    public String host;

    @Override
    public String call() throws Exception {
        count.addAndGet(1);
        InetAddress[] addresses = new InetAddress[0];
        try {
            addresses = InetAddress
                    .getAllByName(host.substring(1));

          //  System.out.println(host + " pass " + count.get());
            if (addresses.length > 0) {
                return address;
            }
        } catch (UnknownHostException e) {
            System.err.println(host + " " + count.get());
        }
        return null;
    }
}
