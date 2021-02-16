package io.bunnyblue.dnsmasq.adguard;

import java.util.Random;

public class RandomHelper {
    public static int    randomDns(){
        int max=9;
        int min=0;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}
