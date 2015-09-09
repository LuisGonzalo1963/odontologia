/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.sessions.OHistOdontoFacade;
import he1.sis.entities.TurnosCe;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "atencionFicha")
@ViewScoped
public class AtencionFichaJSFManagedBean implements Serializable {

    @EJB
    private OHistOdontoFacade histOdontoFacade;

    private List<OHistOdonto> listaHistoria;

    private final TurnosCe sessionTurno;
    private TurnosCe selectedTurno;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public List<OHistOdonto> getListaHistoria() {
        return listaHistoria;
    }

    public void setListaHistoria(List<OHistOdonto> listaHistoria) {
        this.listaHistoria = listaHistoria;
    }

    public TurnosCe getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(TurnosCe selectedTurno) {
        this.selectedTurno = selectedTurno;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** recuperar Historia">
    public void listHistoria() {
        listaHistoria = histOdontoFacade.listByTurno(sessionTurno);
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation("fichaOdontologica?faces-redirect=true");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    // </editor-fold>
    /**
     * Creates a new instance of AtencionFichaJSFManagedBean
     */
    public AtencionFichaJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessionTurno = (TurnosCe) session.getAttribute("turno");
    }

}
