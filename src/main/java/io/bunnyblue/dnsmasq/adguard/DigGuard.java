//package io.bunnyblue.dnsmasq.adguard;
//
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import org.xbill.DNS.*;
//
///**
// * @author Brian Wellington &lt;bwelling@xbill.org&gt;
// */
//public class DigGuard {
////    static {
////        System.setProperty("dns.server", "8.8.8.8");
////        System.setProperty("dns.search", "8.8.8.8");
////        System.setProperty("dns.fallback.server", "8.8.8.8");
////        System.setProperty("dns.fallback.search", "8.8.8.8");
////        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,dnsjava");
////    }
//
//    static Name name = null;
//    static int type = Type.A, dclass = DClass.IN;
//
//    static void usage() {
//        System.out.println("; dnsjava dig");
//        System.out.println("Usage: dig [@server] name [<type>] [<class>] [options]");
//        System.exit(0);
//    }
//
//    static DigStatus doQuery(Message response, long ms) {
//
//        String status = Rcode.string(response.getHeader().getRcode());
////      String  rdataToString=response.getQuestion().rdataToString();
////        List<Record> records= response.getSection(1);
//        // System.out.println(status+" "+response.getQuestion());
////        System.out.println(rdataToString);
////        System.out.println(records);
////
//        DigStatus digStatus = new DigStatus();
//        digStatus.resolved = status.equals("NOERROR");
//        digStatus.status = status;
////       // records.get(0).
////        return digStatus;
////      //  System.out.println("; dnsjava dig");
//        //  System.out.println(response);
//        return digStatus;
////        System.out.println(";; Query time: " + ms + " ms");
//    }
//
//    public static DigStatus main(String[] argv) throws IOException {
//        List<String> dnss = Arrays.asList("8.8.8.8", "8.8.4.4", "1.1.1.1", "1.1.0.0", "9.9.9.9", "149.112.112.112", "9.9.9.10", "149.112.112.10", "114.114.114.114", "223.5.5.5");
//        String dnsServer = "94.140.14.140";//dnss.get(RandomHelper.randomDns());
//        String server = null;
//        int arg;
//        Message query, response;
//        Record rec;
//        SimpleResolver2 res = null;
//        boolean printQuery = false;
//        long startTime, endTime;
//
//        if (argv.length < 1) {
//            usage();
//        }
//
//        try {
//            arg = 0;
//            if (argv[arg].startsWith("@")) {
//                server = argv[arg++].substring(1);
//            }
//
//            res = new SimpleResolver2(dnsServer);
//
//            res.setTimeout(Duration.ofSeconds(30));
//            //  res.setTCP(false);
//            String nameString = argv[arg++];
//            if (nameString.equals("-x")) {
//                name = ReverseMap.fromAddress(argv[arg++]);
//                type = Type.PTR;
//                dclass = DClass.IN;
//            } else {
//                name = Name.fromString(nameString, Name.root);
//                type = Type.value(argv[arg]);
//                if (type < 0) {
//                    type = Type.A;
//                } else {
//                    arg++;
//                }
//
//                dclass = DClass.value(argv[arg]);
//                if (dclass < 0) {
//                    dclass = DClass.IN;
//                } else {
//                    arg++;
//                }
//            }
//
//            while (argv[arg].startsWith("-") && argv[arg].length() > 1) {
//                switch (argv[arg].charAt(1)) {
//                    case 'p':
//                        String portStr;
//                        int port;
//                        if (argv[arg].length() > 2) {
//                            portStr = argv[arg].substring(2);
//                        } else {
//                            portStr = argv[++arg];
//                        }
//                        port = Integer.parseInt(portStr);
//                        if (port < 0 || port > 65535) {
//                            System.out.println("Invalid port");
//                            return null;
//                        }
//                        res.setPort(port);
//                        break;
//
//                    case 'b':
//                        String addrStr;
//                        if (argv[arg].length() > 2) {
//                            addrStr = argv[arg].substring(2);
//                        } else {
//                            addrStr = argv[++arg];
//                        }
//                        InetAddress addr;
//                        try {
//                            addr = InetAddress.getByName(addrStr);
//                        } catch (Exception e) {
//                            System.out.println("Invalid address");
//                            return null;
//                        }
//                        res.setLocalAddress(addr);
//                        break;
//
//                    case 'k':
//                        String key;
//                        if (argv[arg].length() > 2) {
//                            key = argv[arg].substring(2);
//                        } else {
//                            key = argv[++arg];
//                        }
//
//                        String[] parts = key.split("[:/]", 3);
//                        switch (parts.length) {
//                            case 2:
//                                res.setTSIGKey(new TSIG(TSIG.HMAC_MD5, parts[0], parts[1]));
//                                break;
//                            case 3:
//                                res.setTSIGKey(new TSIG(parts[0], parts[1], parts[2]));
//                                break;
//                            default:
//                                throw new IllegalArgumentException("Invalid TSIG key specification");
//                        }
//                        break;
//
//                    case 't':
//                        res.setTCP(true);
//                        break;
//
//                    case 'i':
//                        res.setIgnoreTruncation(true);
//                        break;
//
//                    case 'e':
//                        String ednsStr;
//                        int edns;
//                        if (argv[arg].length() > 2) {
//                            ednsStr = argv[arg].substring(2);
//                        } else {
//                            ednsStr = argv[++arg];
//                        }
//                        edns = Integer.parseInt(ednsStr);
//                        if (edns < 0 || edns > 1) {
//                            System.out.println("Unsupported EDNS level: " + edns);
//                            return null;
//                        }
//                        res.setEDNS(edns);
//                        break;
//
//                    case 'd':
//                        res.setEDNS(0, 0, ExtendedFlags.DO);
//                        break;
//
//                    case 'q':
//                        printQuery = true;
//                        break;
//
//                    default:
//                        System.out.print("Invalid option: ");
//                        System.out.println(argv[arg]);
//                }
//                arg++;
//            }
//
//        } catch (ArrayIndexOutOfBoundsException e) {
//            if (name == null) {
//                usage();
//            }
//        }
//        if (res == null) {
//            res = new SimpleResolver2();
//        }
//
//        rec = Record.newRecord(name, type, dclass);
//        query = Message.newQuery(rec);
//        if (printQuery) {
//            System.out.println(query);
//        }
//
//        if (type == Type.AXFR) {
//            System.out.println("; dnsjava dig <> " + name + " axfr");
//            ZoneTransferIn xfrin = ZoneTransferIn.newAXFR(name, res.getAddress(), res.getTSIGKey());
//            xfrin.setTimeout(res.getTimeout());
//            try {
//                xfrin.run(
//                        new ZoneTransferIn.ZoneTransferHandler() {
//                            @Override
//                            public void startAXFR() {
//                            }
//
//                            @Override
//                            public void startIXFR() {
//                            }
//
//                            @Override
//                            public void startIXFRDeletes(Record soa) {
//                            }
//
//                            @Override
//                            public void startIXFRAdds(Record soa) {
//                            }
//
//                            @Override
//                            public void handleRecord(Record r) {
//                                System.out.println(r);
//                            }
//                        });
//            } catch (ZoneTransferException e) {
//                throw new WireParseException(e.getMessage());
//            }
//        } else {
//            startTime = System.currentTimeMillis();
//            response = res.send(query);
//            endTime = System.currentTimeMillis();
//            return doQuery(response, endTime - startTime);
//        }
//        return null;
//    }
//}