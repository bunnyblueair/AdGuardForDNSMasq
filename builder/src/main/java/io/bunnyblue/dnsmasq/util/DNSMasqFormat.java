package io.bunnyblue.dnsmasq.util;

public class DNSMasqFormat {
    public static String format(String domain) {
        return String.format("address=/%s/127.0.0.1", domain);
    }
    public static String formatIpset(String domain) {
        return String.format("ipset=/%s/ad_list", domain);
    }
}
