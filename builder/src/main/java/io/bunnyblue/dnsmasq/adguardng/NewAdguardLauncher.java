package io.bunnyblue.dnsmasq.adguardng;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NewAdguardLauncher {

    public static void main(String[] args)  {
        NewFileBuilder builder = new NewFileBuilder();

        try {
       List<String> domainList= builder.executeBuild();
            FileUtils.writeLines(new File("dist/adguard-dnsmasq-dist.conf"), domainList);
            System.out.println("write to dist/adguard-dnsmasq-dist.conf");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}