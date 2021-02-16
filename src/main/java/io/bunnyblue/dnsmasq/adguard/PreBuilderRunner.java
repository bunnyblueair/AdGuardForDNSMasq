package io.bunnyblue.dnsmasq.adguard;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class PreBuilderRunner {
    public static void main(String []args) throws IOException {
        File diff=new File("diff.diff");
        ArrayList<String> prebuils=new ArrayList<>();
       Collection<String>  diffs= FileUtils.readLines(diff);
       for (String line:diffs){
           if (line.startsWith("-address")){
             String host=  line.substring(1);
               System.err.println(line);
               prebuils.add(host);
           }
       }
       FileUtils.writeLines(new File("prebuild.conf"),prebuils);
    }
}
