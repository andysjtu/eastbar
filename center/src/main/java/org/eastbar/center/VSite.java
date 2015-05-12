package org.eastbar.center;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VSite {
    private String siteCode;
    private String siteIP;
    private int kwVersion;
    private int urlVersion;
    private int spVersion;
    private int prgVersion;

    private Status status = Status.Offline;


    public void changeStatus(Status newStatus){
        this.status = newStatus;
    }

    public void online(){
        changeStatus(Status.Online);
    }

    public void offline(){
        changeStatus(Status.Offline);
    }


    public static enum Status{
        Online,Offline;
    }

}
