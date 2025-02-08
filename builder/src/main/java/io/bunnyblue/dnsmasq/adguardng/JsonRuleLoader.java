package io.bunnyblue.dnsmasq.adguardng;

import io.bunnyblue.dnsmasq.adguard.dns.DNSInfo;
import io.bunnyblue.dnsmasq.util.DNSMasqFormat;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.Cache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class JsonRuleLoader {
    // public static final String CACHE="builder/geosite-category-ads-all.json";
private String filePath;

    public JsonRuleLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> read() {
        final List<String> cacheLines = new ArrayList<>();
        try {
            String rawData = FileUtils.readFileToString(new File(filePath));
            JSONObject jsonObject = new JSONObject(rawData);
            JSONArray rulesObject = jsonObject.getJSONArray("rules");
            rulesObject.iterator().forEachRemaining(o -> {
                JSONObject ruleObject = (JSONObject) o;
                if (ruleObject.has("domain")) {
                    ruleObject.getJSONArray("domain").forEach(domain -> {
                        cacheLines.add(DNSMasqFormat.format(domain.toString()));
                        
                      //  cacheLines.add(DNSMasqFormat.formatIpset(domain.toString()));
                    });

                }
                if (ruleObject.has("domain_suffix")) {
                    ruleObject.getJSONArray("domain_suffix").forEach(domain -> {
                        cacheLines.add(DNSMasqFormat.format(domain.toString()));
                      //  cacheLines.add(DNSMasqFormat.formatIpset(domain.toString()));
                    });
                }


            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        //   writeToFile();
return  cacheLines;
    }

}
