/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.sis.entities.HojasDeEvolucion;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.HojasDeEvolucionFacade;
import he1.utilities.Utilitario;
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
@ManagedBean(name = "repHojasDeEvolucion")
@ViewScoped
public class RepHojasDeEvolucionJSFManagedBean extends Utilitario implements Serializablee {

    @EJB
    private HojasDeEvolucionFacade hojasDeEvolucionFacade;

    private List<HojasDeEvolucion> listHojaEvo;
    
    private HojasDeEvolucion selectHojasDeEvolucion;

    private final TurnosCe sessionTurno;

    private final String codMedico;
    

    // <editor-fold defaultstate="collapsed" desc="/** Set y get">
    public List<HojasDeEvolucion> getListHojaEvo() {
        return listHojaEvo;
    }

    public void setListHojaEvo(List<HojasDeEvolucion> listHojaEvo) {
        this.listHojaEvo = listHojaEvo;
    }

    public HojasDeEvolucion getSelectHojasDeEvolucion() {
        return selectHojasDeEvolucion;
    }

    public void setSelectHojasDeEvolucion(HojasDeEvolucion selectHojasDeEvolucion) {
        this.selectHojasDeEvolucion = selectHojasDeEvolucion;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** xxxxxx">
    public void onRowSelectFecha(){
        
    }
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** xxxxxx">
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    public void init() {
       
        listHojaEvo = hojasDeEvolucionFacade.listHojaEvoByHc(sessionTurno.getPacientes());
    }

    // </editor-fold>    
    /**
     * Creates a new instance of RepHojasDeEvolucionJSFManagedBean
     */
    public RepHojasDeEvolucionJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessionTurno = (TurnosCe) session.getAttribute("turno");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
