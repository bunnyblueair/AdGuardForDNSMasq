package io.bunnyblue.dnsmasq.adguard;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * imasdk.googleapis.com/js/sdkloader/ima3.js
 */
public class DNSGuardFilter {
    static HashSet<String> whiteList = new HashSet<>();

    static {
        whiteList.add("gstatic.com");
        whiteList.add("mmstat.com");
    }

    public static boolean ignore(String domain) {
        for (String root :
                whiteList) {
            if (domain.contains(root)) {
                return true;
            }


        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(">>>>>>rebuild filter...");
        ArrayList<String> purged = new ArrayList<>();
        File filter = new File("builder/filter.txt");
        Collection<String> hosts = FileUtils.readLines(filter);
        for (String domain :
                hosts) {
            //   if (domain.contains("mmstat.com")) continue;

            if (domain.startsWith("@@||")) continue;
            if (domain.startsWith("!")) {
                purged.add("# " + domain);
                continue;
            }
            if (domain.startsWith("||")) {
                if (ignore(domain)) continue;
                //全部屏蔽
                String newDom = domain.replaceAll("\\^", "");//.replaceAll("||","");

                newDom = newDom.substring(2);
                if (newDom.contains("*")) {
                    System.err.println(newDom);
                } else {

                    purged.add(String.format("address=/.%s/", newDom));
                }
            }

        }
        purged.addAll(prebuild());
                System.out.println(">>>>>>append prebuild ==="+prebuild());
        FileUtils.writeLines(new File("builder/adguard-dnsmasq-raw.conf"), purged);
        System.out.println(">>>>>>rebuild filter and wrote to builder/adguard-dnsmasq-raw.conf");
    }

    public static List<String> prebuild() {
        return Arrays.asList("address=/cnzz.mmstat.com/",
                "address=/gj.mmstat.com/",
                "address=/oneid.mmstat.com/",
                "address=/ac.mmstat.com/",
                "address=/gm.mmstat.com/",
                "address=/go.mmstat.com/",
                "address=/gxb.mmstat.com/",
                "address=/hz.mmstat.com/",
                "address=/p3p.mmstat.com/",
                "address=/res.mmstat.com/",
                "address=/wgo.mmstat.com/",
                "address=/.58.com/",
                "address=/.ganjin.com/",
                "address=/2.taobao.com/“,
                "address=/.goofish.com/",
                "address=/yt.mmstat.com/");



    }
}
