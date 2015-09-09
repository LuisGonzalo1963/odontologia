/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.utilities;

import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCamas;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.uci.sessions.UOpcionPacienteFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "acceso")
@ViewScoped
public class AccesoJSFManagedBean implements Serializable {

    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private UOpcionPacienteFacade opcionPacienteFacade;

    @EJB
    private PacientesFacade pacientesFacade;

    private TurnosCamas selectTurnoUci;
    private Personal medicoPersonal;

    private BigDecimal edad;
    private Date fechaActual;
    private String nroHjas;
    private String urlLogin;
    private final String codMedico;

    public TurnosCamas getSelectTurnoUci() {
        return selectTurnoUci;
    }

    public void setSelectTurnoUci(TurnosCamas selectTurnoUci) {
        this.selectTurnoUci = selectTurnoUci;
    }

    public BigDecimal getEdad() {

        return edad;
    }

    public String getUrlLogin() {
        return urlLogin;
    }

    public void setUrlLogin(String urlLogin) {
        this.urlLogin = urlLogin;
    }

    public void setEdad(BigDecimal edad) {

        this.edad = edad;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getNroHjas() {
        return nroHjas;
    }

    public void setNroHjas(String nroHjas) {
        this.nroHjas = nroHjas;
    }

    public Personal getMedicoPersonal() {
        return medicoPersonal;
    }

    public void setMedicoPersonal(Personal medicoPersonal) {
        this.medicoPersonal = medicoPersonal;
    }

    public void redirecPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//        request.getSession().invalidate();
        try {
            System.out.println("ec.getRequestContextPath() " + ec.getRequestContextPath());
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(AccesoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ec = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession().invalidate();
            ec.redirect(ec.getRequestContextPath());
            System.out.println("Sistema cerrado...");
        } catch (IOException ex) {
            //ponerMensajeError("Error", ex.getMessage());
        }
    }


    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sin actividad.", "What are you doing over there?"));

    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Bienvenido de regreso", "Well, that's a long coffee break!"));
        logout();
    }

//    @PostConstruct
//    public void init() {
//        if (selectTurnoUci == null) {
//            logout();
//        } else {
//            edad = pacientesFacade.edadPaciente(selectTurnoUci.getPermanenciasYAtenciones().getPacientes());
//            fechaActual = new Date();
//            nroHjas = opcionPacienteFacade.nroHojaPaciente(selectTurnoUci.getPermanenciasYAtenciones().getPacientes());
//            medicoPersonal = personalFacade.find(codMedico);
//        }
//    }
    public void regresarPagina() {
        System.out.println("regresar............");
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation("atencionTurno?faces-redirect=true");
    }

    /**
     * Creates a new instance of AccesoJSFManagedBean
     */
    public AccesoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectTurnoUci = (TurnosCamas) session.getAttribute("paciente");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
