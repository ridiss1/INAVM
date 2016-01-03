/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ridiss
 */
public class Vms {
       private int VMid ;
       private String Hostname ;
       private String IpAdress;
       private String Status;
//for retreive of data from table
    public Vms(int VMid, String Hostname, String IpAdress, String Status) {
        this.VMid = VMid;
        this.Hostname = Hostname;
        this.IpAdress = IpAdress;
        this.Status = Status;
    }

    public Vms(int VMid) {
        this.VMid = VMid;
    }

    public Vms() {
    }

    /**
     * @return the VMid
     */
    public int getVMid() {
        return VMid;
    }

    /**
     * @param VMid the VMid to set
     */
    public void setVMid(int VMid) {
        this.VMid = VMid;
    }

    /**
     * @return the Hostname
     */
    public String getHostname() {
        return Hostname;
    }

    /**
     * @param Hostname the Hostname to set
     */
    public void setHostname(String Hostname) {
        this.Hostname = Hostname;
    }

    /**
     * @return the IpAdress
     */
    public String getIpAdress() {
        return IpAdress;
    }

    /**
     * @param IpAdress the IpAdress to set
     */
    public void setIpAdress(String IpAdress) {
        this.IpAdress = IpAdress;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

  
        
}
