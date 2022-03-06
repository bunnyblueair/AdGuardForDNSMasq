package io.bunnyblue.dnsmasq.adguard;

import io.bunnyblue.dnsmasq.adguard.dns.DNSInfo;
import org.apache.commons.io.FileUtils;
import sun.misc.Cache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DNSCacher {
    public static final String CACHE="builder/dns_cache.dat";
  static HashSet<DNSInfo> dnsInfos=new HashSet<>();
    public static void init(){
        try {
         Collection<String> cacheLines= FileUtils.readLines(new File(CACHE));

            for (String line :
                    cacheLines) {
               String[] data= line.split("@");
                DNSInfo dnsInfo=new DNSInfo();
                dnsInfo.timestamp=Long.valueOf(data[0]);
                dnsInfo.address=data[1];
                if (dnsInfo.vaild()){
                    dnsInfos.add(dnsInfo);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       writeToFile();

    }
    public static void update(String address){
        DNSInfo dnsInfo=new DNSInfo(address);
        if (dnsInfos.contains(dnsInfo)){
            dnsInfos.remove(dnsInfo);
            dnsInfos.add(dnsInfo);
        }else {
            dnsInfos.add(dnsInfo);
        }


    }
    public static boolean hasCached(String address){
        DNSInfo dnsInfo=new DNSInfo(address);
     return dnsInfos.contains(dnsInfo);

    }
    public static  void writeToFile(){
        StringBuffer stringBuffer=new StringBuffer();
        for (DNSInfo info :
                dnsInfos) {
            if (info.vaild()){
                stringBuffer.append(info.timestamp+"@"+info.address).append("\n");
            }

        }
        try {
            FileUtils.writeStringToFile(new File(CACHE),stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
