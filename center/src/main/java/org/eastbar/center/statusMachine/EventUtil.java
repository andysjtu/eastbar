package org.eastbar.center.statusMachine;

import org.eastbar.codec.DozerUtil;
import org.eastbar.codec.TermReport;

/**
 * Created by andyliang on 6/28/15.
 */
public class EventUtil {
    public static HostEvent convertFromTermReport(TermReport termReport) {
        HostEvent hostEvent = new HostEvent();
        hostEvent.setAccount(termReport.getAccount());
        hostEvent.setAuthOrg(termReport.getAuthOrg());
        hostEvent.setCertId(termReport.getId());
        hostEvent.setIdType(termReport.getIdType());
        hostEvent.setIp(termReport.getIp());
        hostEvent.setLoginTime(termReport.getLoginTime());
        hostEvent.setLogoutTime(termReport.getLogoutTime());
        hostEvent.setMacAddress(termReport.getMacAddress());
        hostEvent.setName(termReport.getName());
        hostEvent.setNation(termReport.getNation());
        hostEvent.setOs(termReport.getOs());
        hostEvent.setVersion(termReport.getVersion());
        hostEvent.setSiteCode(termReport.getSiteCode());
        boolean conn = termReport.isConnected();
        boolean online = termReport.isOnline();
        int status = 0;
        if (conn && online) {
            status = 3;
        } else if (conn) {
            status = 2;
        } else if (online) {
            status = 1;
        }
        hostEvent.setStatus(status);
        return hostEvent;
    }


}
