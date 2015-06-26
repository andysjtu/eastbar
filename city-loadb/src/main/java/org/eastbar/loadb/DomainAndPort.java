package org.eastbar.loadb;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class DomainAndPort {
    private String domain;
    private int port;

    public DomainAndPort() {
    }

    public DomainAndPort(String domain, int port) {
        this.domain = domain;
        this.port = port;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainAndPort that = (DomainAndPort) o;

        if (port != that.port) return false;
        return !(domain != null ? !domain.equals(that.domain) : that.domain != null);

    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "DomainAndPort{" +
                "domain='" + domain + '\'' +
                ", port=" + port +
                '}';
    }
}
