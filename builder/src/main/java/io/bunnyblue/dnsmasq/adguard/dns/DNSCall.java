package io.bunnyblue.dnsmasq.adguard.dns;

import io.bunnyblue.dnsmasq.adguard.DNSCacher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class DNSCall implements Callable<String> {
    static AtomicInteger count = new AtomicInteger(0);
    public String address;
    public String host;

    public DNSCall() {

    }


    @Override
    public String call() throws Exception {
        if(address.contains(".cibntv.net")||address.contains(".youku.com")){
            //patch 酷喵
            return address;
        }
         if(address.contains(".umeng.com")||address.contains(".umengcloud.com")){
            if(host.equals(".umeng.com")||host.equals(".umengcloud.com")){
                return address;
            }else{
                return null;
            }
        }
        
        count.addAndGet(1);
        if (DNSCacher.hasCached(address)) {
            System.out.println("🍻 used cache  "+address);
            return address;
        }

//        if (true){
//            return address;
//        }
        for (int i = 0; i < 3; i++) {
            i++;
            InetAddress[] addresses = new InetAddress[0];
            char first = host.charAt(0);
            String rootHost = first == '.' ? host.substring(1) : host;
            try {
                addresses = InetAddress
                        .getAllByName(rootHost);

                //  System.out.println(host + " pass " + count.get());
                if (addresses.length > 0) {
                    DNSCacher.update(address);
                    System.out.println("🎉 Pass <<<<<<<<<" + address);
                    return address;
                }
            } catch (UnknownHostException e) {
                Thread.sleep(50);

            }
        }
        System.err.println(host + "  " + count.get() + ">>>>Failed ");

        return null;
    }
}
