/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OOdontograma;
import he1.odonto.sessions.OOdontogramaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean
@ViewScoped
public class RepOdontograma {

    @EJB
    private OOdontogramaFacade odontogramaFacade;

    private List<OOdontograma> listOdontograma;
    
    private OOdontograma selectedOdontograma;

    public List<OOdontograma> getListOdontograma() {
        return listOdontograma;
    }

    public void setListOdontograma(List<OOdontograma> listOdontograma) {
        this.listOdontograma = listOdontograma;
    }

    public OOdontograma getSelectedOdontograma() {
        return selectedOdontograma;
    }

    public void setSelectedOdontograma(OOdontograma selectedOdontograma) {
        this.selectedOdontograma = selectedOdontograma;
    }
    
    
    ///
    
    public void recuperaOdontogramas() {
        listOdontograma = odontogramaFacade.listAll();
    }

    public void onRowSelect(SelectEvent event) {
        selectedOdontograma = (OOdontograma) event.getObject();
    }

    @PostConstruct
    public void init() {
        recuperaOdontogramas();
        selectedOdontograma = new OOdontograma();
    }

    /**
     * Creates a new instance of RepOdontograma
     */
    public RepOdontograma() {
    }

}
