/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "migraTurnosProced")
@ViewScoped
public class MigraTurnosProcedJSFManagedBean extends Utilitario implements Serializablee {

    private static final Logger LOG = Logger.getLogger(MigraTurnosProcedJSFManagedBean.class.getName());

    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private TurnosCeFacade turnosCeFacade;

    private Pacientes editPacientes;
    private TurnosCe editTurnoCe;

    private Integer nroHC;
    private String consul;
    private Date fecha;
     private Date fechac;
    private Short nroTurno;
    private String espec;
    private String estado;
   
    private String user;

    public Integer getNroHC() {
        return nroHC;
    }

    public void setNroHC(Integer nroHC) {
        this.nroHC = nroHC;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pacientes getEditPacientes() {
        return editPacientes;
    }

    public void setEditPacientes(Pacientes editPacientes) {
        this.editPacientes = editPacientes;
    }

    public String getConsul() {
        return consul;
    }

    public void setConsul(String consul) {
        this.consul = consul;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getNroTurno() {
        return nroTurno;
    }

    public void setNroTurno(Short nroTurno) {
        this.nroTurno = nroTurno;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public Date getFechac() {
        return fechac;
    }

    public void setFechac(Date fechac) {
        this.fechac = fechac;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    public void btnGrabar() {
        editPacientes = pacientesFacade.findByHc(nroHC);
        if (editPacientes != null) {
            LOG.log(Level.INFO, "paciente {0}", editPacientes);
            LOG.log(Level.INFO, "{0} {1} {2} {3} {4} {5}", new Object[]{consul, fecha, nroTurno, espec, fechac, user});
            
            editTurnoCe = new TurnosCe();

            editTurnoCe.setPacientes(editPacientes);
            editTurnoCe.setPersonal1(new Personal(consul));
            editTurnoCe.setFecha(fecha);
            editTurnoCe.setNumero(nroTurno);
            editTurnoCe.setTipo("C");
            editTurnoCe.setEstado(estado);
            editTurnoCe.setDprAraCodigo("O");
            editTurnoCe.setDprCodigo(espec);
            editTurnoCe.setFechaCreacion(fechac);
            editTurnoCe.setCreado(fechac);
            editTurnoCe.setSobrecarga('F');
            editTurnoCe.setCreadoPor("odontoWeb");
            editTurnoCe.setPcnFuerza(editPacientes.getFuerza());
            editTurnoCe.setPcnGrado(editPacientes.getGrado());
            editTurnoCe.setPcnSituacion(editPacientes.getSituacion());
            editTurnoCe.setObservaciones("Migrado de Turnos de Procedimientos");

            turnosCeFacade.create(editTurnoCe);
            System.out.println("turnos creado........");
            BigDecimal idTurno = turnosCeFacade.maxRegIdByPcn(editPacientes);
            //
            editTurnoCe = turnosCeFacade.find(idTurno);
            editTurnoCe.setCreadoPor(user);
            turnosCeFacade.edit(editTurnoCe);
            System.out.println("turno actualizado login");
            editPacientes=null;
            nroHC=null;
            consul=null;

        } else {
            ponerMensajeInfo("ATENCION", "NO existe el Paciente");
        }
    }

    /**
     * Creates a new instance of MigraTurnosProcedJSFManagedBean
     */
    public MigraTurnosProcedJSFManagedBean() {
    }

}
