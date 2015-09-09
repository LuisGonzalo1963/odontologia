/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OSignosVitales;
import he1.odonto.sessions.OSignosVitalesFacade;
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
@ManagedBean(name = "SignosVitales")
@ViewScoped
public class Ficha4SignosVitalesJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OSignosVitalesFacade signosVitalesFacade;

    private final OHistOdonto selectHistOdonto;
    private OSignosVitales editSignosVitales;

    private boolean flagGrabarM;

    // <editor-fold defaultstate="collapsed" desc="/** ">    
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** Set y Get"> 
    public OSignosVitales getEditSignosVitales() {
        return editSignosVitales;
    }

    public void setEditSignosVitales(OSignosVitales editSignosVitales) {
        this.editSignosVitales = editSignosVitales;
    }

    // </editor-fold>        
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        editSignosVitales = signosVitalesFacade.findByHistOdon(selectHistOdonto);
        if (editSignosVitales == null) {
            editSignosVitales = new OSignosVitales();
            flagGrabarM = true;
        } else {
            flagGrabarM = false;
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar">
    public void btnGrabar() {

        if (flagGrabarM) {
            editSignosVitales.setSviId(BigDecimal.ZERO);
            editSignosVitales.setOHistOdonto(selectHistOdonto);
            editSignosVitales.setSviFCardiaca(editSignosVitales.getSviFCardiaca());
            editSignosVitales.setSviFRespiratoria(editSignosVitales.getSviFRespiratoria());
            editSignosVitales.setSviPArtSist(editSignosVitales.getSviPArtSist());
            editSignosVitales.setSviPArtDiast(editSignosVitales.getSviPArtDiast());
            editSignosVitales.setSviTemperatura(editSignosVitales.getSviTemperatura());

            signosVitalesFacade.create(editSignosVitales);
            ponerMensajeInfo("ATENCIÓN", "Signos vitales guardados...");

        } else {
            signosVitalesFacade.edit(editSignosVitales);
            ponerMensajeInfo("ATENCIÓN", "Signos vitales actualizados...");
        }

    }

    // </editor-fold>        
    /**
     * Creates a new instance of Ficha4SignosVitalesJSFManagedBean
     */
    public Ficha4SignosVitalesJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
