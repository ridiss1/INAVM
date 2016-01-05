/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.elbandi.pve2api.data;

import java.util.HashMap;
import java.util.Map;
import net.elbandi.pve2api.Pve2Api;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author metbill
 */
public class Container {
    
    private String ostemplate; // nom du fichier template utilisé
    private String storage;
    private String vmid; // id du container 
    private String cpus; // nombre de cpu
    private String console;

    public String getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(String cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public String getMem_usage() {
        return mem_usage;
    }

    public void setMem_usage(String mem_usage) {
        this.mem_usage = mem_usage;
    }

    public String getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(String disk_usage) {
        this.disk_usage = disk_usage;
    }
    private String disk; // taille disque en GB 
    private String hostname; 
    private String ip_address;
    private String memory; // mémoire en MB
    private String password;
    private String status; // état de la machine "running",...
    private String cpu_usage;
    private String mem_usage;
    private String disk_usage;
    
    public Container (String id,String hostname, String ram, String ramUsage, String cpu, String cpuUsage,String mem, String memUsage) {
        this.vmid=id;
        this.memory=ram;
        this.mem_usage=ramUsage;
        this.cpus=cpu;
        this.cpu_usage=cpuUsage;
        this.hostname=hostname;
        this.disk=mem;
        this.disk_usage=memUsage;
        
    }
    
    //Upate

    public Container(String vmid, String cpus, String disk, String memory) {
        this.vmid = vmid;
        this.cpus = cpus;
        this.disk = disk;
        this.memory = memory;
    }
    
    
    // Création
    public Container(String ostemplate, String vmid, String cpus, String disk, String hostname, String memory, String password) {
        this.ostemplate = ostemplate;
        this.vmid = vmid;
        this.cpus = cpus;
        this.disk = disk;
        this.hostname = hostname;
        this.memory = memory;
        this.password = password;
    }
    
    //Lecture
    public Container(JSONObject data , int requete) throws JSONException {
		cpus = data.getString("cpus");
		disk = data.getString("maxdisk");
		hostname = data.getString("name");
		memory = data.getString("maxmem");
		ip_address = data.getString("ip");
                status = data.getString("status");
                cpu_usage=data.getString("cpu");
                disk_usage=data.getString("disk");
                mem_usage=data.getString("mem");
                
                if (requete == 1){
                    vmid = data.getString("vmid");
                }
                
                
    }

    public Pve2Api.PveParams getCreateParams() {
		return new Pve2Api.PveParams("ostemplate", ostemplate)
				.Add("storage", storage).Add("vmid", vmid).Add("cpus", cpus)
                                .Add("disk", disk).Add("hostname", hostname).Add("ip_address",ip_address)
                                .Add("memory", memory).Add("password", password);
    }
    
    public Pve2Api.PveParams getUpdateParams() {
		return new Pve2Api.PveParams("cpus", cpus)
                                .Add("disk", disk).Add("memory", memory).Add("ip_address",ip_address);
                                
    }
    
    @Override
    public String toString(){
        
        return "cpus="+this.cpus+"\n"+
                "disk="+this.disk+"\n"+"hostname="+this.hostname+"\n"+
                "memory="+this.memory+"\n"+"status="+this.status+"\n"+
                "ip_address="+this.ip_address+"\n"+" ----- Taux d'utilisation --------"+"\n"+
                "cpu_usage="+this.cpu_usage+"\n"+"mem_usage="+this.mem_usage+"\n"+
                "disk_usage="+this.disk_usage ;   
    }
           

    public String getOstemplate() {
        return ostemplate;
    }

    public void setOstemplate(String ostemplate) {
        this.ostemplate = ostemplate;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getVmid() {
        return vmid;
    }

    public void setVmid(String vmid) {
        this.vmid = vmid;
    }

    public String getCpus() {
        return cpus;
    }

    public void setCpus(String cpus) {
        this.cpus = cpus;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    
}
