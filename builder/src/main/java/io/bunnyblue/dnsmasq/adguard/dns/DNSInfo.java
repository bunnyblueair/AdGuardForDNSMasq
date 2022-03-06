package io.bunnyblue.dnsmasq.adguard.dns;

import java.io.Serializable;

public class DNSInfo implements Serializable {
    public String address;
    public long timestamp;

    public DNSInfo() {
    }

    public DNSInfo(String address) {
        this.address = address;
        timestamp=System.currentTimeMillis();
    }

    public boolean vaild(){
        return System.currentTimeMillis()<timestamp+(1000*3600*24*15L);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DNSInfo dnsInfo = (DNSInfo) o;

        return address.equals(dnsInfo.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}
