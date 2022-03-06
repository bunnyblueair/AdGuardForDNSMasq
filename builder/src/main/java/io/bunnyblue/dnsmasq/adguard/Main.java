package io.bunnyblue.dnsmasq.adguard;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static {
       // System.err.println(   System.getProperties().toString());
//        String dns= "127.0.0.1";
////        try {
////            dns = FileUtils.readFileToString(new File("dns.txt"));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        System.setProperty("dns.server",dns);
//        System.setProperty("dns.search",dns);
//        System.setProperty("dns.fallback.server",dns);
//        System.setProperty("dns.fallback.search",dns);
        //String dns = "94.140.14.140";
//        System.setProperty("sun.net.spi.nameservice.nameservers", "127.0.0.1");
//        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

      //  System.setProperty("sun.net.spi.nameservice.provider.1,dns,dnsjava");
    }

    public static void main(String[] args) {

	// write your code here
        try {

            System.out.println("=====downloadFilter.....");
          //  AdGuardDownloader.downloadFilter();
            System.out.println("=====DNSGuardFilter.....");
            DNSCacher.init();
            DNSGuardFilter.main(args);

           // System.setProperty("sun.net.spi.nameservice.nameservers", "119.29.29.29");
//           System.setProperty("sun.net.spi.nameservice.nameservers", dns);
//            System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
            System.out.println("=====DomainFilter.....");
            DomainFilter.init();
            DNSCacher.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
