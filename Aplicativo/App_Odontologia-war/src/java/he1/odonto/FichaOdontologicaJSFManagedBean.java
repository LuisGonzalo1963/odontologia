/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OMotivoConsulta;
import he1.odonto.sessions.OHistOdontoFacade;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.entities.TurnosProcedimientos;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "fichaOdontologica")
@ViewScoped
public class FichaOdontologicaJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private OHistOdontoFacade histOdontoFacade;
    @EJB
    private PersonalFacade personalFacade;

    private TurnosCe pacienteTurno;
    private final TurnosProcedimientos pacienteProced;
    private OHistOdonto editHistOdonto;
    private OHistOdonto selectHistOdonto;
    private Personal loginPersonal;
    private Pacientes selectPaciente;
    private OMotivoConsulta editMotivoConsulta;

    private OHistOdonto findHistOdonto;

    private BigDecimal edadPaciente;
    private final String codMedico;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get">
    public TurnosCe getPacienteTurno() {
        return pacienteTurno;
    }

    public void setPacienteTurno(TurnosCe pacienteTurno) {
        this.pacienteTurno = pacienteTurno;
    }

    public Pacientes getSelectPaciente() {
        return selectPaciente;
    }

    public void setSelectPaciente(Pacientes selectPaciente) {
        this.selectPaciente = selectPaciente;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** recuperar Historia">
    public void recuperarHistoria() {

        findHistOdonto = histOdontoFacade.findByPaciente(selectPaciente);
        //System.out.println("findHistOdonto " + findHistOdonto);

        if (findHistOdonto == null) {
            editHistOdonto = new OHistOdonto();

            editHistOdonto.setHisId(BigDecimal.ZERO);
            editHistOdonto.setHisFecha(new Date());
            if (pacienteProced != null) {
                //editHistOdonto.setTurnosCe(new TurnosCe(pacienteProced.getId()));
                editHistOdonto.setProcedId(pacienteProced.getId().toBigInteger());
            } else {
                editHistOdonto.setTurnosCe(new TurnosCe(pacienteTurno.getNumeroId()));

            }
            //System.out.println("selectPaciente "+selectPaciente);
            editHistOdonto.setPacientes(selectPaciente);
            //System.out.println("loginPersonal "+loginPersonal);
            editHistOdonto.setPersonal(loginPersonal);
            editHistOdonto.setHisEstado("A");
            histOdontoFacade.create(editHistOdonto);
        } else {
            selectHistOdonto = findHistOdonto;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("fichaOdonto", selectHistOdonto);

        }

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        loginPersonal = personalFacade.findByCodigo(codMedico);
        if (pacienteProced != null) {
            edadPaciente = edadPaciente(pacienteProced.getPacientes());
            selectPaciente = pacienteProced.getPacientes();
        } else {
            edadPaciente = edadPaciente(pacienteTurno.getPacientes());
            selectPaciente = pacienteTurno.getPacientes();
            //System.out.println("FichaIO selectPaciente "+selectPaciente);
            //Actualizo la hora de inicio de la ficha odontològica
            if (pacienteTurno.getFechaAtencion() == null) {
                pacienteTurno.setFechaAtencion(new Date());
                pacienteTurno.setPersonal(loginPersonal);
                turnosCeFacade.edit(pacienteTurno);
                //System.out.println("actualizo fecha...");
            } 
            //System.out.println("no actualizo fecha...");
        }

        
        recuperarHistoria();

    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** ...">
    // </editor-fold>
    /**
     * Creates a new instance of FichaOdontologicaJSFManagedBean
     */
    public FichaOdontologicaJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        pacienteTurno = (TurnosCe) session.getAttribute("turno");
        pacienteProced = (TurnosProcedimientos) session.getAttribute("turnoPrc");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
