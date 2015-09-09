/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OIndicesCpoCeo;
import he1.odonto.entities.OOdontograma;
import he1.odonto.sessions.OIndicesCpoCeoFacade;
import he1.odonto.sessions.OOdontogramaFacade;
import he1.sis.entities.HojasDeEvolucion;
import he1.sis.sessions.HojasDeEvolucionFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "indCpoCeo")
@ViewScoped
public class Ficha8IndCpoCeoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OIndicesCpoCeoFacade indicesCpoCeoFacade;
    @EJB
    private HojasDeEvolucionFacade hojasDeEvolucionFacade;
    @EJB
    private OOdontogramaFacade odontogramaFacade;

    private final OHistOdonto selectHistOdonto;
    private HojasDeEvolucion hojaDeEvolucionSelect;
    private OOdontograma odontograma;
    private OIndicesCpoCeo selectIndCpoCeo;

    private List<OIndicesCpoCeo> listaIndices;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public HojasDeEvolucion getHojaDeEvolucionSelect() {
        return hojaDeEvolucionSelect;
    }

    public void setHojaDeEvolucionSelect(HojasDeEvolucion hojaDeEvolucionSelect) {
        this.hojaDeEvolucionSelect = hojaDeEvolucionSelect;
    }

    public List<OIndicesCpoCeo> getListaIndices() {
        return listaIndices;
    }

    public void setListaIndices(List<OIndicesCpoCeo> listaIndices) {
        this.listaIndices = listaIndices;
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** recupera odontograma">
    public void recuperaOdontograma() {
        odontograma = odontogramaFacade.findByHistOdon(selectHistOdonto);
        if (odontograma != null) {
            selectIndCpoCeo = new OIndicesCpoCeo();
            listaIndices = indicesCpoCeoFacade.listByOdontograma(odontograma);
        } 
    }

    // </editor-fold> 

    @PostConstruct
    public void init() {
        listaIndices = new ArrayList<>();
        recuperaOdontograma();
    }

    /**
     * Creates a new instance of Ficha8IndCpoCeoJSFManagedBean
     */
    public Ficha8IndCpoCeoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
