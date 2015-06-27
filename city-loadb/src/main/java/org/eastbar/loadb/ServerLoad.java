package org.eastbar.loadb;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.util.internal.ConcurrentSet;
import org.eastbar.net.DomainAndPort;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class ServerLoad {
    private ConcurrentSet<DomainAndPort> sets = new ConcurrentSet<>();

    private Map<DomainAndPort, List<String>> map = Maps.newConcurrentMap();

    private Map<String, DomainAndPort> allocates = Maps.newConcurrentMap();

    public ServerLoad(Set<DomainAndPort> sets) {
        this.sets.addAll(sets);
    }

    public DomainAndPort allocateServerAddress(String siteCode) {
        DomainAndPort old = allocates.get(siteCode);
        if (old != null) {
            return old;
        }
        DomainAndPort domainAndPort = findLoadLestServer();
        List<String> sites = map.get(domainAndPort);
        if (sites == null) {
            sites = Lists.newArrayList();
            map.put(domainAndPort, sites);
        }
        sites.add(siteCode);
        allocates.put(siteCode, domainAndPort);
        return domainAndPort;
    }

    protected DomainAndPort findLoadLestServer() {
        DomainAndPort domainAndPort = null;
        int loadNum = 0;
        Iterator<DomainAndPort> domainAndPortIterator = sets.iterator();
        while (domainAndPortIterator.hasNext()) {
            DomainAndPort temp = domainAndPortIterator.next();

            List<String> list = map.get(temp);
            if (list == null || list.size() == 0) {
                domainAndPort = temp;
                break;
            }

            if (domainAndPort == null) {
                domainAndPort = temp;
                loadNum = list.size();
            } else {
                if (list.size() < loadNum) {
                    domainAndPort = temp;
                    loadNum = list.size();
                }
            }

        }

        return domainAndPort;
    }

    public static void main(String[] args) {
        DomainAndPort domainAndPort1 = new DomainAndPort();
        domainAndPort1.setDomain("127.0.0.1");
        domainAndPort1.setPort(23);


        DomainAndPort domainAndPort2 = new DomainAndPort();
        domainAndPort2.setDomain("127.0.0.1");
        domainAndPort2.setPort(223);

        DomainAndPort domainAndPort3 = new DomainAndPort();
        domainAndPort3.setDomain("127.0.0.1");
        domainAndPort3.setPort(2223);

        ServerLoad load = new ServerLoad(Sets.newHashSet(domainAndPort1,domainAndPort2,domainAndPort3));
        DomainAndPort x = load.allocateServerAddress("310101001");
        System.out.println(x);

        x = load.allocateServerAddress("310101002");
        System.out.println(x);

        x = load.allocateServerAddress("310101003");
        System.out.println(x);

        x = load.allocateServerAddress("310101004");
        System.out.println(x);

        x = load.allocateServerAddress("310101005");
        System.out.println(x);

        x = load.allocateServerAddress("310101006");
        System.out.println(x);

        x = load.allocateServerAddress("310101007");
        System.out.println(x);




    }
}
