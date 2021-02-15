package io.bunnyblue.dnsmasq.adguard;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            AdGuardDownloader.downloadFilter();
            DNSGuardFilter.main(args);
            DomainFilter.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
