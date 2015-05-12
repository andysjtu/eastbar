package org.eastbar.center;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VTerminal {
    private final String terminalIP;
    private final String siteCode;

    private Status status;
    private Customer currentCustomer;

    public VTerminal(String terminalIP, String siteCode) {
        this.terminalIP = terminalIP;
        this.siteCode = siteCode;
    }

    private void changeStatus(Status status){
        this.status = status;
    }

    public void login(){
        this.status = Status.login;
    }
    public void logout(){
        this.status = Status.logout;
    }

    public void offline(){
        this.status = Status.offline;
    }

    public void online(){
        this.status = Status.online;
    }

    public void changeCustomer(Customer customer) {
        this.currentCustomer = customer;
    }


    public static enum Status {
        offline, login, online, logout;
    }


}
