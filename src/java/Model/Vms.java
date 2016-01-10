/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.elbandi.pve2api.data.Container;

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

      public void writeFile(List<Container> listContainer) {
        PrintWriter ecrire;
        String nomFichier = "/Users/Ridiss/NetBeansProjects/projet cloud/INAVM/web/js/graphe.js";


        try {
            ecrire = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));

            ecrire.println("window.onload = function(){");
            for (Container container : listContainer) {
                String data = "ramdata" + container.getVmid();
                String ramUsage = container.getMem_usage();
                String ramTotal = container.getMemory();
                String memUsage = container.getDisk_usage();
                String memTotal = container.getDisk();
                String concav = "";
                String cpu = container.getCpus();
                String cpuUsage = container.getCpu_usage();
                /**
                 * ************RAM*****************************
                 */
                ecrire.println("var " + data + " = [");
                ecrire.println("{");
                ecrire.println("value: " + ramUsage + ",");
                ecrire.println("color:'#c4e3f3',");
                ecrire.println("highlight: '#FF5A5E',");              
                ecrire.println("label: 'RAM Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + ramTotal + ",");
                ecrire.println("color: 'darkblue',");
                ecrire.println("highlight: '#5AD3D1',");             
                ecrire.println("label: 'RAM Total'");
                ecrire.println("}");
                ecrire.println("];");

                /**
                 * ************CPU*****************************
                 */
                String data1 = "cpudata" + container.getVmid();

                ecrire.println("var " + data1 + " = [");
                ecrire.println("{");
                ecrire.println("value: " + cpuUsage + ",");
                ecrire.println("color:'#c4e3f3',");
                ecrire.println("highlight: '#FF5A5E',");               
                ecrire.println("label: 'CPU Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + cpu + ",");
                ecrire.println("color: 'darkblue',");
                ecrire.println("highlight: '#5AD3D1',");               
                ecrire.println("label: 'CPU Total'");
                ecrire.println("}");
                ecrire.println("];");

                /**
                 * ************DISK*****************************
                 */
                String data2 = "memdata" + container.getVmid();
                ecrire.println("var " + data2 + " = [");
                ecrire.println("{");
                ecrire.println("value: " + memUsage + ",");
                ecrire.println("color:'#c4e3f3',");
                ecrire.println("highlight: '#FF5A5E',"); 
                ecrire.println("label: 'Disk Usage'");
                ecrire.println("},");

                ecrire.println("{");
                ecrire.println("value: " + memTotal + ",");
                ecrire.println("color: 'darkblue',");
                ecrire.println("highlight: '#5AD3D1',");                         
                ecrire.println("label: 'Disk Total'");
                ecrire.println("}");
                ecrire.println("];");

                ecrire.println("var ctx = document.getElementById('" + data + "').getContext('2d');");
                ecrire.println("window.myPie" + data + " = new Chart(ctx).Pie(" + data + ");");

                ecrire.println("var ctx = document.getElementById('" + data1 + "').getContext('2d');");
                ecrire.println("window.myPie" + data1 + " = new Chart(ctx).Pie(" + data1 + ");");

                ecrire.println("var ctx = document.getElementById('" + data2 + "').getContext('2d');");
                ecrire.println("window.myPie" + data2 + " = new Chart(ctx).Pie(" + data2 + ");");

            }
            ecrire.println("};");

            ecrire.close();

        } catch (IOException ex) {
            Logger.getLogger(Vms.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
}
