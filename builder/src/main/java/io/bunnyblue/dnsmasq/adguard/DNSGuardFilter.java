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
    private static boolean isRootDomain(String address) {
        return address.indexOf(".") == address.lastIndexOf(".");
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
                    if(isRootDomain(newDom)){
                      purged.add(String.format("address=/.%s/", newDom));
                    }else{
                     purged.add(String.format("address=/%s/", newDom));}

                    
                }
            }

        }
        purged.addAll(loadYouKu());
        purged.addAll(loadXXMaHuaTeng());
        purged.addAll(prebuild());
                System.out.println(">>>>>>append prebuild ==="+prebuild());
        FileUtils.writeLines(new File("builder/adguard-dnsmasq-raw.conf"), purged);
        System.out.println(">>>>>>rebuild filter and wrote to builder/adguard-dnsmasq-raw.conf");
    }
    
    public static Collection<String> loadYouKu() throws IOException{
         File filter = new File("builder/youku.txt");
        Collection<String> hosts = FileUtils.readLines(filter);
        return hosts;
    }
        public static Collection<String> loadXXMaHuaTeng() throws IOException{
         File filter = new File("builder/fxk_tencent.txt");
        Collection<String> hosts = FileUtils.readLines(filter);
        return hosts;
    }
    

    public static List<String> prebuild() {
        return Arrays.asList("address=/cnzz.mmstat.com/",
                 "address=/.pki.plus/",    
                 "address=/.hi.cn",
                "address=/.twlxxl.cn/",
                "address=/.dutils.com/",
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
                "address=/.goofish.com/",
                "address=/.zhuanzhuan.com/",
                "address=/2.taobao.com/",
                "address=/.hpplay.cn/",
                "address=/.hpplay.com.cn/",
                "address=/.lebo.cn/",
                "address=/.bootcss.com/",
                "address=/.bootcdn.net/",
                "address=/.staticfile.net/",
                "address=/.staticfile.org/", 
                "address=/dns.weibo.cn/",             
                "address=/dns.weixin.qq.com/",    
                "address=/dns.weixin.qq.com.cn/",  
                "address=/httpdns.n.netease.com/", 
                "address=/httpdns.alicdn.com/", 
                             
                "address=/yt.mmstat.com/");



    }
}
