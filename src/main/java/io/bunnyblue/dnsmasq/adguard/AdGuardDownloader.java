package io.bunnyblue.dnsmasq.adguard;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

public class AdGuardDownloader {
    //AdGuardSDNSFilter path
    public static final String filterUrl = "https://adguardteam.github.io/AdGuardSDNSFilter/Filters/filter.txt";

    public static boolean downloadFilter() throws IOException {
        System.out.println(">>>>>>download filter from AdGuardSDNSFilter...");
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.get().url(filterUrl).build();
            String response = okHttpClient.newCall(request).execute().body().string();
            FileUtils.writeStringToFile(new File("filter.txt"), response);
            System.out.println(">>>>>>downloaded filter from AdGuardSDNSFilter");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return false;
    }
}
