package io.bunnyblue.dnsmasq.adguardng;

import java.util.ArrayList;
import java.util.List;

public class NewFileBuilder {
    public List<String> executeBuild() {
        List<String> list = new ArrayList<>();
        JsonRuleLoader adGuardRuleLoader = new JsonRuleLoader("builder/geosite-category-ads-all.json");
        List<String> adList = adGuardRuleLoader.read();
        list.addAll(adList);
        JsonRuleLoader httpDnsloader = new JsonRuleLoader("builder/geosite-category-httpdns-cn");
        List<String> dns = httpDnsloader.read();
        list.addAll(dns);
        PrebuilderDNSGuardLoader prebuilderDNSGuardLoader = new PrebuilderDNSGuardLoader();
        List<String> prebuild = prebuilderDNSGuardLoader.rebuild();
        list.addAll(prebuild);
        return list;
    }
}
