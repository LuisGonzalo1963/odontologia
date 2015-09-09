/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OTratamiento;
import he1.odonto.sessions.OTratamientoFacade;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "tratamiento")
@ViewScoped
public class Ficha12TratamientoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private OTratamientoFacade tratamientoFacade;
    @EJB
    private PersonalFacade personalFacade;
    private final OHistOdonto selectHistOdonto;

    private Personal loginPersonal;
    private OTratamiento selectTratamiento;
    private TurnosCe updateTurnoCe;
    private final TurnosCe selectTurnoCe;

    private final String codMedico;

    private List<OTratamiento> listarTratamientos;
    private boolean flagBtnEditar;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de Variables">
    public OTratamiento getSelectTratamiento() {
        return selectTratamiento;
    }

    public void setSelectTratamiento(OTratamiento selectTratamiento) {
        this.selectTratamiento = selectTratamiento;
    }

    public boolean isFlagBtnEditar() {
        return flagBtnEditar;
    }

    public void setFlagBtnEditar(boolean flagBtnEditar) {
        this.flagBtnEditar = flagBtnEditar;
    }

    public List<OTratamiento> getListarTratamientos() {
        return listarTratamientos;
    }

    public void setListarTratamientos(List<OTratamiento> listarTratamientos) {
        this.listarTratamientos = listarTratamientos;
    }

    public void newTrtValueChange(ValueChangeEvent vce) {
        selectTratamiento = new OTratamiento();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btnGrabar">
    public void grabarTratamiento() {
        if (selectTratamiento != null) {
            if (selectTratamiento.getTrtId() == null) {
                selectTratamiento.setTrtId(BigDecimal.ZERO);
                selectTratamiento.setOHistOdonto(selectHistOdonto);
                selectTratamiento.setPersonal(loginPersonal);
                selectTratamiento.setTrtFecha(new Date());
                selectTratamiento.setTrtDiagnosCompl(selectTratamiento.getTrtDiagnosCompl());
                selectTratamiento.setTrtProcedimientos(selectTratamiento.getTrtProcedimientos());
                selectTratamiento.setTrtPrescripciones(selectTratamiento.getTrtPrescripciones());

                tratamientoFacade.create(selectTratamiento);

                System.out.println("grabado tratam");
                ponerMensajeInfo("Grabación", "Información guardada con éxito");
                listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
            } else if (selectTratamiento.getTrtId() != null) {
                selectTratamiento.setPersonal(loginPersonal);
                tratamientoFacade.edit(selectTratamiento);
                System.out.println("actualización tratam");
                ponerMensajeInfo("Grabación", "Información actualizada con éxito");
                listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
            }
            //
            if (updateTurnoCe.getFechaAtencion() == null) {
                updateTurnoCe.setFechaAtencion(new Date());
                updateTurnoCe.setHoraFin(new Date());
                updateTurnoCe.setPersonal(new Personal(codMedico));
                turnosCeFacade.edit(updateTurnoCe);
            } else {
                updateTurnoCe.setHoraFin(new Date());
                updateTurnoCe.setPersonal(new Personal(codMedico));
                turnosCeFacade.edit(updateTurnoCe);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btnGrabarDel">
    public void grabarEliminarDg() {
        tratamientoFacade.remove(selectTratamiento);
        ponerMensajeInfo("Atención", "Eliminación permitida.");
        listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
    }
    // </editor-fold>

    public void ajaxValidar() {

        if (selectTratamiento != null) {
            if (selectTratamiento.getPersonal().equals(loginPersonal)) {
                System.out.println("son iguales");
                flagBtnEditar = false;

            } else {
                System.out.println("son diferentes");
                flagBtnEditar = true;
            }
        } else {
            flagBtnEditar = true;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    public void init() {
        loginPersonal = personalFacade.findByCodigo(codMedico);
        listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
        updateTurnoCe = turnosCeFacade.find(selectTurnoCe.getNumeroId());
       
    }

    // </editor-fold>
    /**
     * Creates a new instance of Ficha12TratamientoJSFManagedBean
     */
    public Ficha12TratamientoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
        selectTurnoCe = (TurnosCe) session.getAttribute("turno");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
