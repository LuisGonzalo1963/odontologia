/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OParametros;
import he1.odonto.sessions.OParametrosFacade;
import he1.sis.entities.CalendariosProcedimientos;
import he1.sis.entities.Cargos;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.Departamentos;
import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.CalendariosProcedimientosFacade;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CuentasFacade;
import he1.sis.sessions.DepartamentosFacade;
import he1.sis.sessions.HorariosMedicoFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.PromocionesPacientesFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "turnoSubsecuente")
@ViewScoped
public class AsignarTurnoSubsecuenteJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;
    @EJB
    private DepartamentosFacade departamentosFacade;
    @EJB
    private OParametrosFacade parametrosFacade;
    @EJB
    private CalendariosProcedimientosFacade calendProcedFacade;

    private List<TurnosCe> listaTurnos;
    private List<HorariosMedico> listaHorario;
    private List<TurnosCe> listaTurnosPaciente;
    private List<CalendariosProcedimientos> listaCalendProcedimientos;

//    private final AtencionSecretarias selectedAtencSecretarias;
    private Departamentos selectDepartamentos;
    private Personal loginPersonal;
    private TurnosCe editTurnoCe;
    private TurnosCe selectTurnoCe;
    private TurnosCe selectTurnoCeEdit;
    private TurnosCe selectTurnoCePrint;
    private Cargos selectCargos;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;
    private Pacientes editPacientes;
    private HorariosMedico selectConsultorio;
    private OParametros editOParametros;

    private Date selectFecha;
    private Date actualFecha;
    private Calendar sistemaFecha;

    private String fechaActual;
    private String diaActual;
    private String msgTurnosDisp;
    private String msgTurnos1Vez;
    private String msgGrabar;
    private String tipoPaciente;
    private String horaTurno;
    private String msgTotalProced;

    private BigDecimal valorPago;
    private BigDecimal edadPaciente;

    private double turnosDisponibles;
    private double porcSubs;

    private short sigTurno;
    private short subTurnos;
     private short turnosD1vez;

    private boolean flagBtnAsignar;
    private boolean flagBtnAceptar;
    private boolean flagPanelTurno;

    private short turnosDSub;

    private final String codMedico;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public Date getSelectFecha() {
        return selectFecha;
    }

    public void setSelectFecha(Date selectFecha) {
        this.selectFecha = selectFecha;
    }

    public String getMsgTurnos1Vez() {
        return msgTurnos1Vez;
    }

    public void setMsgTurnos1Vez(String msgTurnos1Vez) {
        this.msgTurnos1Vez = msgTurnos1Vez;
    }

    public String getMsgTotalProced() {
        return msgTotalProced;
    }

    public void setMsgTotalProced(String msgTotalProced) {
        this.msgTotalProced = msgTotalProced;
    }

    public Pacientes getEditPacientes() {
        return editPacientes;
    }

    public void setEditPacientes(Pacientes editPacientes) {
        this.editPacientes = editPacientes;
    }

    public boolean isFlagPanelTurno() {
        return flagPanelTurno;
    }

    public void setFlagPanelTurno(boolean flagPanelTurno) {
        this.flagPanelTurno = flagPanelTurno;
    }

    public String getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(String horaTurno) {
        this.horaTurno = horaTurno;
    }

    public TurnosCe getSelectTurnoCePrint() {
        return selectTurnoCePrint;
    }

    public void setSelectTurnoCePrint(TurnosCe selectTurnoCePrint) {
        this.selectTurnoCePrint = selectTurnoCePrint;
    }

    public TurnosCe getSelectTurnoCeEdit() {
        return selectTurnoCeEdit;
    }

    public void setSelectTurnoCeEdit(TurnosCe selectTurnoCeEdit) {
        this.selectTurnoCeEdit = selectTurnoCeEdit;
    }

    public TurnosCe getSelectTurnoCe() {
        return selectTurnoCe;
    }

    public void setSelectTurnoCe(TurnosCe selectTurnoCe) {
        this.selectTurnoCe = selectTurnoCe;
    }

    public TurnosCe getEditTurnoCe() {
        return editTurnoCe;
    }

    public void setEditTurnoCe(TurnosCe editTurnoCe) {
        this.editTurnoCe = editTurnoCe;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public String getMsgTurnosDisp() {
        return msgTurnosDisp;
    }

    public void setMsgTurnosDisp(String msgTurnosDisp) {
        this.msgTurnosDisp = msgTurnosDisp;
    }


    public List<TurnosCe> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<TurnosCe> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public boolean isFlagBtnAsignar() {
        return flagBtnAsignar;
    }

    public void setFlagBtnAsignar(boolean flagBtnAsignar) {
        this.flagBtnAsignar = flagBtnAsignar;
    }

    public boolean isFlagBtnAceptar() {
        return flagBtnAceptar;
    }

    public void setFlagBtnAceptar(boolean flagBtnAceptar) {
        this.flagBtnAceptar = flagBtnAceptar;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** onDateSelect">
    public void onDateSelect(SelectEvent event) {
        flagPanelTurno = true;
        selectFecha = (Date) (event.getObject());
        sistemaFecha.setTime(selectFecha);
        diaActual = String.valueOf(sistemaFecha.get(Calendar.DAY_OF_WEEK));

        selectDepartamentos = AreaDptosUtil(loginPersonal, diaActual);
        selectConsultorio = ConsultorioUtil(loginPersonal, diaActual);

        editOParametros = parametrosFacade.parametros(selectConsultorio.getPersonal().getCodigo());
        if (editOParametros == null) {
            ponerMensajeInfo("ATENCIÓN", "Médico no disponible");
        } else {
            listaCalendProcedimientos = calendProcedFacade.listByDiaConsultorio(diaActual, editOParametros.getParConsultorio());
            msgTotalProced = Short.toString(totalizaTurnosProced());
        }
        listaTurnos = turnosCeFacade.ListByFechaConsultorioControl(selectFecha, selectConsultorio.getPersonal());
        listaHorario = horariosMedicoFacade.listByDiaConsultorio(diaActual, selectConsultorio.getPersonal().getCodigo());

        if (actualFecha.before(selectFecha)) {
            flagBtnAsignar = false;
            ponerMensajeInfo("Fecha seleccionada", sdf.format(selectFecha));
            System.out.println("fecha posterior");
        } else if (selectFecha.before(actualFecha)) {
            flagBtnAsignar = true;
            System.out.println("la fecha actual es menor..");
            ponerMensajeInfo("ATENCIÓN", "Es una fecha anterior, no puede asignar");
        } else {
            flagBtnAsignar = false;
            System.out.println("fecha iguales........");
        }
        verificaTurnos();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** verificaTurnos">    
    public void verificaTurnos() {
        double tot1raVez = totalizaTurnos();
        turnosD1vez = (short) Math.round(tot1raVez * selectDepartamentos.getPrcInicial() / 100);
        msgTurnos1Vez= Short.toString(turnosD1vez);
        //porcSubs = selectDepartamentos.getPrcSubsecuente();
        System.out.println("1raVez " + msgTurnos1Vez);
        turnosDisponibles = totalizaTurnosProced();
        System.out.println("totTurnosPrc " + turnosDisponibles);
        //turnosDSub = (short) Math.round(turnosDisponibles * porcSubs / 100);
        short turnosReg = (short) (listaTurnos.size());
        short turnosDisp = (short) (turnosDisponibles - turnosReg);
        msgTurnosDisp = Short.toString(turnosDisp);
        //flagBtnAsignar = msgTotalTurnos.equalsIgnoreCase("0");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** totalizaTurnos">
    public short totalizaTurnos() {
        int i = 0;
        short t1 = 0, ttd = 0;
        while (true) {
            if (i == listaHorario.size()) {
                break;
            }
            t1 = listaHorario.get(i).getTurnosPosibles();
            ttd = (short) (ttd + t1);
            i++;
        }
        return ttd;
    }

    public short totalizaTurnosProced() {
        int i = 0;
        short t1 = 0, ttd = 0;
        while (true) {
            if (i == listaCalendProcedimientos.size()) {
                break;
            }
            t1 = listaCalendProcedimientos.get(i).getNroProcedimientos();
            ttd = (short) (ttd + t1);
            i++;
        }
        return ttd;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** btnBuscarHc">

    public void btnBuscarHc() {
        editPacientes = pacientesFacade.findByHc(editPacientes.getNumeroHc());
        if (editPacientes != null) {
            edadPaciente = pacientesFacade.edadPaciente(editPacientes);
            this.flagBtnAsignar = false;
            this.flagBtnAceptar = false;
        } else {
            this.flagBtnAsignar = true;
            this.flagBtnAceptar = true;
            this.edadPaciente = null;
            ponerMensajeInfo("ATENCION", "No existe el Paciente !!!");
        }
    }

    // </editor-fold>        
    // <editor-fold defaultstate="collapsed" desc="/** btnCancelar">
    public void btnCancelarTurno() {
        this.flagPanelTurno = false;
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="/** botón Grabar">
    public void btnGrabarTurno() {
        //verificar repetido
        if (turnosCeFacade.ListByFechaPacienteConsl(selectFecha, editPacientes, selectConsultorio.getPersonal()).size() > 0) {
            ponerMensajeInfo("ATENCION", "El paciente seleccionado ya se encuentra registrado...");
        } else {

            //subTurnos = totalizaTurnos();
            subTurnos = turnosD1vez;
                    
            System.out.println("turnosDisponib" + subTurnos);
            if (listaTurnos.isEmpty()) {
                sigTurno = (short) (subTurnos + 1);
            } else {
                sigTurno = (short) ((short) listaTurnos.size() + 1 + subTurnos);
            }

            editTurnoCe = new TurnosCe();

            editTurnoCe.setPacientes(editPacientes);
            editTurnoCe.setPersonal1(selectConsultorio.getPersonal());
            editTurnoCe.setFecha(selectFecha);
            editTurnoCe.setNumero(sigTurno);
            editTurnoCe.setTipo("C");
            editTurnoCe.setEstado("R");
            editTurnoCe.setDprAraCodigo("O");
            editTurnoCe.setDprCodigo(selectDepartamentos.getDepartamentosPK().getCodigo());
            editTurnoCe.setFechaCreacion(selectFecha);
            editTurnoCe.setSobrecarga('F');
            editTurnoCe.setCreado(new Date());
            editTurnoCe.setCreadoPor("CAJERO_PRUEBAS");
            editTurnoCe.setPcnFuerza(editPacientes.getFuerza());
            editTurnoCe.setPcnGrado(editPacientes.getGrado());
            editTurnoCe.setPcnSituacion(editPacientes.getSituacion());
            editTurnoCe.setObservaciones("Web Cita control");
            editTurnoCe.setTipoTurno('C');
            try {
                turnosCeFacade.create(editTurnoCe);

                BigDecimal idTurno = turnosCeFacade.maxRegIdByPcn(editPacientes);

                //Actualiza el usuario logueado
                editTurnoCe = turnosCeFacade.find(idTurno);
                editTurnoCe.setCreadoPor(loginPersonal.getUsuario());
                turnosCeFacade.edit(editTurnoCe);
                System.out.println("Cita Médica Creada: " + codMedico + " | " + actualFecha + " | HC: " + editPacientes.getNumeroHc() + " | " + idTurno);
                listaTurnos = turnosCeFacade.ListByFechaConsultorioControl(selectFecha, selectConsultorio.getPersonal());

                flagBtnAceptar = true;
                flagPanelTurno = true;
                editPacientes = null;
                this.btnCancelarTurno();
                verificaTurnos();

            } catch (Exception e) {
                for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                    System.out.println("t.getCause() " + t.getCause());
                    if (t.getMessage().contains("SQLIntegrityConstraintViolationException: ORA-20998")) {
                        msgGrabar = "Personal no autorizado a dar turnos para " + selectConsultorio.getPersonal().getCodigo();
                        System.out.println("msgBuscar " + msgGrabar);
                        break;
                    }
                }
                System.out.println("error......." + e.getMessage());
                msgGrabar = "Personal no autorizado a dar turnos para " + selectConsultorio.getPersonal().getCodigo();

            }
        }
        //

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** botón Asignar">
    public void btnAsignar() {
        msgGrabar = null;
        edadPaciente = null;
        flagBtnAceptar = true;
        flagPanelTurno = true;
        editPacientes = new Pacientes();
        flagBtnAsignar = true;
        //verificaTurnos();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {

        sistemaFecha = Calendar.getInstance();
        fechaActual = sdf.format(sistemaFecha.getTime());
        try {
            actualFecha = sdf.parse(fechaActual);
            //diaActual = String.valueOf(sistemaFecha.get(Calendar.DAY_OF_WEEK));
            loginPersonal = personalFacade.findByCodigo(codMedico);

            flagBtnAsignar = true;
            flagPanelTurno = false;
            flagBtnAceptar = true;
            selectTurnoCePrint = new TurnosCe();
            System.out.println("Cita Médica : " + codMedico + " | " + actualFecha);
        } catch (ParseException ex) {
            Logger.getLogger(AsignarTurnoSubsecuenteJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // </editor-fold>
    public void selectPrint() {
        System.out.println("hola.........." + selectTurnoCePrint);
        if (selectTurnoCePrint != null) {
            horaTurno = turnosCeFacade.pHoraTurno(selectTurnoCePrint.getPersonal1().getCodigo(), selectTurnoCePrint.getNumero(), selectTurnoCePrint.getFecha());
        }
        System.out.println("hola.........." + horaTurno);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        event.getSource();
        System.out.println("11nuevo...." + event.getSource());
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Turno cambiado", "Antes: " + oldValue + ", Actual:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if (newValue != null) {
                //((TurnoCe) event.getObject()).getId());

                System.out.println("nuevo...." + event.getSource());
            }
        }
    }

    /**
     * Creates a new instance of AsignarTurnoSubsecuenteJSFManagedBean
     */
    public AsignarTurnoSubsecuenteJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
