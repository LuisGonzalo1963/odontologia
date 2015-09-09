/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OTratamiento;
import he1.odonto.sessions.OHistOdontoFacade;
import he1.odonto.sessions.OTratamientoFacade;
import he1.sis.entities.Cuentas;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.CuentasFacade;
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
@ManagedBean(name = "atencionEspTto")
@ViewScoped
public class AtencionEspecTratamientoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private OTratamientoFacade tratamientoFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private OHistOdontoFacade histOdontoFacade;
    @EJB
    private CuentasFacade cuentasFacade;

    private Personal loginPersonal;
    private TurnosCe updateTurnoCe;
    private final TurnosCe sessionTurno;
    private OHistOdonto selectHistOdonto;

    private OTratamiento selectTratamiento;
    private Cuentas selectCuentas;
    private List<OTratamiento> listarTratamientos;
    private List<Cuentas> listarCuentas;

    private final String codMedico;
    private String msgPago;

    private boolean flagBtnRegistrar;
    private boolean flagBtnEdicion;
    private boolean flagMsgTto;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de Variables">
    public OTratamiento getSelectTratamiento() {
        return selectTratamiento;
    }

    public void setSelectTratamiento(OTratamiento selectTratamiento) {
        this.selectTratamiento = selectTratamiento;
    }

    public String getMsgPago() {
        return msgPago;
    }

    public void setMsgPago(String msgPago) {
        this.msgPago = msgPago;
    }

    public boolean isFlagBtnEdicion() {
        return flagBtnEdicion;
    }

    public void setFlagBtnEdicion(boolean flagBtnEdicion) {
        this.flagBtnEdicion = flagBtnEdicion;
    }

    public boolean isFlagBtnRegistrar() {
        return flagBtnRegistrar;
    }

    public void setFlagBtnRegistrar(boolean flagBtnRegistrar) {
        this.flagBtnRegistrar = flagBtnRegistrar;
    }

    public Cuentas getSelectCuentas() {
        return selectCuentas;
    }

    public void setSelectCuentas(Cuentas selectCuentas) {
        this.selectCuentas = selectCuentas;
    }

    public List<OTratamiento> getListarTratamientos() {
        return listarTratamientos;
    }

    public List<Cuentas> getListarCuentas() {
        return listarCuentas;
    }

    public void setListarCuentas(List<Cuentas> listarCuentas) {
        this.listarCuentas = listarCuentas;
    }

    public void setListarTratamientos(List<OTratamiento> listarTratamientos) {
        this.listarTratamientos = listarTratamientos;
    }

    public void newTrtValueChange(ValueChangeEvent vce) {
        selectTratamiento = new OTratamiento();
        System.out.println("msgPago "+selectCuentas);
        if(selectCuentas == null){
            flagMsgTto = false;
           msgPago="* Procedimiento sin Pago" ;
        }else if(selectCuentas != null){
            flagMsgTto=true;
            msgPago="Procedimiento generado con Pago" ;
        }
    }
    // </editor-fold>

    public void onRowSelectCuenta() {
        if (selectCuentas.getEstado().equalsIgnoreCase("FCT")) {
            //verificar que no tenga registro de tratamiento
            tratamientoFacade.findByNroCta(selectCuentas.getCuentasPK().getNumero());
            if(tratamientoFacade.findByNroCta(selectCuentas.getCuentasPK().getNumero()) == null){
              flagBtnRegistrar = false;  
            }else{
                flagBtnRegistrar = true;
            }
            
        } else if (selectCuentas.getEstado().equalsIgnoreCase("PND")) {
            ponerMensajeInfo("ATENCIÓN", "No ha cancelado el procedimiento");
            flagBtnRegistrar = true;
        }
    }

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
                if(flagMsgTto){
                    selectTratamiento.setTrtNroCuenta(selectCuentas.getCuentasPK().getNumero());
                }
                selectTratamiento.setTrtRealizado("N");

                tratamientoFacade.create(selectTratamiento);

                System.out.println("grabado tratam");
                ponerMensajeInfo("Grabación", "Información guardada con éxito");
                listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
            } else if (selectTratamiento.getTrtId() != null) {
                tratamientoFacade.edit(selectTratamiento);
                System.out.println("actualización tratam");
                ponerMensajeInfo("Grabación", "Información actualizada con éxito");
                listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
            }
            //
            updateTurnoCe = sessionTurno;
            updateTurnoCe.setHoraFin(new Date());
            updateTurnoCe.setPersonal(selectHistOdonto.getPersonal());
            turnosCeFacade.edit(updateTurnoCe);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btnGrabarDel">
    public void grabarEliminarDg() {
        selectTratamiento.setTrtRealizado("R");
        tratamientoFacade.edit(selectTratamiento);
        ponerMensajeInfo("Atención", "Tratamiento realizado.");
        listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    public void init() {
        loginPersonal = personalFacade.findByCodigo(codMedico);
        selectHistOdonto = histOdontoFacade.findByPaciente(sessionTurno.getPacientes());
        System.out.println("hist " + selectHistOdonto);
        listarTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);
        listarCuentas = cuentasFacade.listByPacienteProced(sessionTurno.getPacientes());
        flagBtnRegistrar = true;
    }

    // </editor-fold>
    /**
     * Creates a new instance of AtencionEspecTratamientoJSFManagedBean
     */
    public AtencionEspecTratamientoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessionTurno = (TurnosCe) session.getAttribute("turno");
        //sessionTurno = (TurnosProcedimientos) session.getAttribute("turnoPrc");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
