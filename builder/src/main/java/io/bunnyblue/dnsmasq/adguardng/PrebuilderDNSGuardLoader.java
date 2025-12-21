package io.bunnyblue.dnsmasq.adguardng;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * imasdk.googleapis.com/js/sdkloader/ima3.js
 */
public class PrebuilderDNSGuardLoader {
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

public List<String> rebuild(){
        List<String> purged=new ArrayList<>();
    try {
        purged.addAll(loadYouKu());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


    try {
        purged.addAll(loadXXMaHuaTeng());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    try {
        purged.addAll(disney());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    try {
        purged.addAll(loadPCDN());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    purged.addAll(prebuild());
    return  purged;
}

            public static Collection<String> disney() throws IOException{
         File filter = new File("builder/disney.txt");
        Collection<String> hosts = FileUtils.readLines(filter);
        return hosts;
    }
    
        public static Collection<String> loadPCDN() throws IOException{
         File filter = new File("builder/pcdn.txt");
        Collection<String> hosts = FileUtils.readLines(filter);
        return hosts;
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
                 "address=/.hi.cn/",
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
                 "address=/.v2ex.com/::", 
                 "address=/.chatgpt.com/::", 
                 "address=/.bsky.app/::", 
                 "address=/.googlevideo.com/::", 
                 "address=/.sheerid.com/::", 
                 "address=/.openai.com/::", 
                 "address=/.xdaforums.com/::", 
                 "address=/.anthropic.com/::", 
                 "address=/claude.ai/::", 
                "address=/yt.mmstat.com/");



    }
}
