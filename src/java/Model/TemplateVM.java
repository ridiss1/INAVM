/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Jean
 */
public class TemplateVM {
    
    private String templateName;
    private int idTemplate;
    private ArrayList<String> vms;
    
    public TemplateVM(String templateName, int idTemplate)
    {
        this.templateName = templateName;
        this.idTemplate = idTemplate;
        vms = new ArrayList<String>();
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the vms
     */
    public ArrayList<String> getVms() {
        return vms;
    }

    /**
     * @param vms the vms to set
     */
    public void setVms(ArrayList<String> vms) {
        this.vms = vms;
    }

    /**
     * @return the idTemplate
     */
    public int getIdTemplate() {
        return idTemplate;
    }

    /**
     * @param idTemplate the idTemplate to set
     */
    public void setIdTemplate(int idTemplate) {
        this.idTemplate = idTemplate;
    }
    
}
