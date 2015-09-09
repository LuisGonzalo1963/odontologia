/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OEnfProbActual;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.sessions.OEnfProbActualFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "ficha2enfPa")
@ViewScoped
public class Ficha2enfPaJSFManagedBean extends Utilitario implements Serializable {
    
    @EJB
    private OEnfProbActualFacade enfProbActualFacade;
    
    private final OHistOdonto selectHistOdonto;
    private OEnfProbActual editEnfProbActual;
    
    private boolean flagGrabarM;
    
    // <editor-fold defaultstate="collapsed" desc="/** Set y Get">

    public OEnfProbActual getEditEnfProbActual() {
        return editEnfProbActual;
    }
    
    public void setEditEnfProbActual(OEnfProbActual editEnfProbActual) {
        this.editEnfProbActual = editEnfProbActual;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar Motivo">
    public void btnGrabar() {
        
        if(flagGrabarM){
            editEnfProbActual.setEpaId(BigDecimal.ZERO);
            editEnfProbActual.setOHistOdonto(selectHistOdonto);
            editEnfProbActual.setEpaDescripcion(editEnfProbActual.getEpaDescripcion());
            
            enfProbActualFacade.create(editEnfProbActual);
            ponerMensajeInfo("ATENCIÓN", "Síntomas guardados...");
            
        } else {
            enfProbActualFacade.edit(editEnfProbActual);
            ponerMensajeInfo("ATENCIÓN", "Síntomas actualizados...");
        }
        
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        editEnfProbActual = enfProbActualFacade.findByHistOdon(selectHistOdonto);
        
        if (editEnfProbActual == null) {
            System.out.println("no existe... ");
            editEnfProbActual = new OEnfProbActual();
            flagGrabarM = true;
        } else {
            System.out.println("existe... ");
            flagGrabarM = false;

        }
    }

    // </editor-fold>      
    // <editor-fold defaultstate="collapsed" desc="/** ...">
    // </editor-fold>    
    /**
     * Creates a new instance of Ficha2enfPaJSFManagedBean
     */
    public Ficha2enfPaJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }
    
}
