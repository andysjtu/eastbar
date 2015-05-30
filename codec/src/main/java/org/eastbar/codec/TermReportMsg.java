package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class TermReportMsg extends SocketMsg {
    private TermReport report;

    public TermReportMsg(TermReport report) {

    }

    public TermReportMsg(SocketMsg oldMsg) {
        super(oldMsg);
    }
}
