/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OParametros;
import he1.odonto.sessions.OParametrosFacade;
import he1.sis.entities.AtencionSecretarias;
import he1.sis.entities.CalendariosProcedimientos;
import he1.sis.entities.Departamentos;
import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.AtencionSecretariasFacade;
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
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "asignarTurnoReferido")
@ViewScoped
public class AsignarTurnoReferidoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;
    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private DepartamentosFacade departamentosFacade;
    @EJB
    private AtencionSecretariasFacade atencionSecretariasFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;
    @EJB
    private CalendariosProcedimientosFacade calendProcedFacade;
    @EJB
    private OParametrosFacade parametrosFacade;

    private Personal selectConsultorio;
    private AtencionSecretarias selectMedico;
    private Departamentos selectDepartamentos;
    private TurnosCe selectTurnoCe;
    private TurnosCe editTurnoCe;
    private TurnosCe selectTurnoCePrint;
    private final Personal loginPersonal;
    private OParametros editOParametros;

    private List<TurnosCe> listaTurnos;
    private List<Personal> listConsXespecialidad;
    private List<HorariosMedico> listaHorarioMedico;
    private List<CalendariosProcedimientos> listaCalendProcedimientos;

    private Date selectFecha;
    private Date actualFecha;

    private Calendar sistemaFechaCal;

    private BigDecimal edadPaciente;
    private BigDecimal valorPago;
    private short turnosD1vez;
    private short turnosDSub;

    private String param;
    private String codEsp;
    private String msgTotalTurnos;
    private String msgTotalProced;
    private String msgFecha;
    private String msgGrabar;
    private String fechaActual;
    private List<String> listaHoras;

    private boolean flagPanelConsulta;
    private boolean flagBtnAsignar;
    private boolean flagBtnAceptar;
    private boolean flagCalendar;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public Date getSelectFecha() {
        return selectFecha;
    }

    public void setSelectFecha(Date selectFecha) {
        this.selectFecha = selectFecha;
    }

    public String getMsgTotalProced() {
        return msgTotalProced;
    }

    public void setMsgTotalProced(String msgTotalProced) {
        this.msgTotalProced = msgTotalProced;
    }

    public short getTurnosDSub() {
        return turnosDSub;
    }

    public void setTurnosDSub(short turnosDSub) {
        this.turnosDSub = turnosDSub;
    }

    public String getMsgGrabar() {
        return msgGrabar;
    }

    public void setMsgGrabar(String msgGrabar) {
        this.msgGrabar = msgGrabar;
    }

    public List<String> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<String> listaHoras) {
        this.listaHoras = listaHoras;
    }

    public TurnosCe getSelectTurnoCePrint() {
        return selectTurnoCePrint;
    }

    public void setSelectTurnoCePrint(TurnosCe selectTurnoCePrint) {
        this.selectTurnoCePrint = selectTurnoCePrint;
    }

    public boolean isFlagCalendar() {
        return flagCalendar;
    }

    public void setFlagCalendar(boolean flagCalendar) {
        this.flagCalendar = flagCalendar;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isFlagBtnAceptar() {
        return flagBtnAceptar;
    }

    public void setFlagBtnAceptar(boolean flagBtnAceptar) {
        this.flagBtnAceptar = flagBtnAceptar;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public TurnosCe getSelectTurnoCe() {
        return selectTurnoCe;
    }

    public void setSelectTurnoCe(TurnosCe selectTurnoCe) {
        this.selectTurnoCe = selectTurnoCe;
    }

    public short getTurnosD1vez() {
        return turnosD1vez;
    }

    public void setTurnosD1vez(short turnosD1vez) {
        this.turnosD1vez = turnosD1vez;
    }

    public String getMsgTotalTurnos() {
        return msgTotalTurnos;
    }

    public void setMsgTotalTurnos(String msgTotalTurnos) {
        this.msgTotalTurnos = msgTotalTurnos;
    }

    public boolean isFlagBtnAsignar() {
        return flagBtnAsignar;
    }

    public void setFlagBtnAsignar(boolean flagBtnAsignar) {
        this.flagBtnAsignar = flagBtnAsignar;
    }

    public AtencionSecretarias getSelectMedico() {
        return selectMedico;
    }

    public void setSelectMedico(AtencionSecretarias selectMedico) {
        this.selectMedico = selectMedico;
    }

    public List<HorariosMedico> getListaHorarioMedico() {
        return listaHorarioMedico;
    }

    public void setListaHorarioMedico(List<HorariosMedico> listaHorarioMedico) {
        this.listaHorarioMedico = listaHorarioMedico;
    }

    public List<TurnosCe> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<TurnosCe> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public Personal getSelectConsultorio() {
        return selectConsultorio;
    }

    public void setSelectConsultorio(Personal selectConsultorio) {
        this.selectConsultorio = selectConsultorio;
    }

    public boolean isFlagPanelConsulta() {
        return flagPanelConsulta;
    }

    public void setFlagPanelConsulta(boolean flagPanelConsulta) {
        this.flagPanelConsulta = flagPanelConsulta;
    }

    public List<Personal> getListConsXespecialidad() {
        return listConsXespecialidad;
    }

    public void setListConsXespecialidad(List<Personal> listConsXespecialidad) {
        this.listConsXespecialidad = listConsXespecialidad;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** parámetro de especialidad">
    public void param(ActionEvent ae) {
        selectMedico = null;
        listConsXespecialidad = null;
        listaTurnos = null;
        turnosD1vez = 0;
        turnosDSub = 0;
        msgTotalTurnos = null;
        flagPanelConsulta = true;
        flagCalendar = true;
        flagBtnAsignar = true;
        selectFecha = null;
        listaHoras = null;
        especialidadSelec();
        listConsXespecialidad = personalFacade.listByEspecialidad("%" + especialidadSelec() + "%");
        selectDepartamentos = departamentosFacade.findByAreaDpto(codEsp);
    }

    public String especialidadSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        param = params.get("especialidad");
        codEsp = params.get("codEspe");
        return param;
    }

// </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="/** onRowSelect Consultorios">
    public void onRowSelectConsultorio() {
        if (selectConsultorio == null) {
            System.out.println("consultorio nulooooo....");
        } else {
            listaHoras = null;
            msgTotalProced = null;
            msgTotalTurnos = null;
            selectFecha = null;
            flagCalendar = false;
            selectMedico = atencionSecretariasFacade.findByConsultorio(selectConsultorio);
        }

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** onDateSelect">
    public void onDateSelect(SelectEvent event) {
        selectFecha = (Date) (event.getObject());
        sdf.format(event.getObject());
        sistemaFechaCal.setTime(selectFecha);
        listaHorarioMedico = horariosMedicoFacade.listByDiaConsultorio(String.valueOf(sistemaFechaCal.get(Calendar.DAY_OF_WEEK)), selectConsultorio.getCodigo());

        if (!listaHorarioMedico.isEmpty()) {
            flagCalendar = false;
            selectMedico = atencionSecretariasFacade.findByConsultorio(selectConsultorio);
        } else {
            flagCalendar = true;
            selectMedico = new AtencionSecretarias();
        }

        editOParametros = parametrosFacade.parametros(selectConsultorio.getCodigo());
        if (editOParametros == null) {
            ponerMensajeInfo("ATENCIÓN", "Médico no disponible");
        } else {
            listaCalendProcedimientos = calendProcedFacade.listByDiaConsultorio(String.valueOf(sistemaFechaCal.get(Calendar.DAY_OF_WEEK)), editOParametros.getParConsultorio());
            msgTotalProced = Short.toString(totalizaTurnosProced());
        }

        flagBtnAsignar = true;
        flagPanelConsulta = false;
        listaTurnos = null;

        turnosD1vez = 0;
        msgTotalTurnos = null;
        msgFecha = sdf.format(selectFecha);
        //
        if (actualFecha.before(selectFecha)) {
            ponerMensajeInfo("Fecha seleccionada", sdf.format(selectFecha));
        } else if (selectFecha.before(actualFecha)) {
            flagBtnAsignar = true;
            ponerMensajeInfo("ATENCIÓN", "Es una fecha anterior, no puede asignar");
        } else {
        }
        verificaTurnos();

    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** verificaTurnos">    
    public void verificaTurnos() {
        double porc1Vez = selectDepartamentos.getPrcInicial();
        double porcSubs = selectDepartamentos.getPrcSubsecuente();
        double turnostot = totalizaTurnos();
        //double turnosD1vez = Math.round(turnostot * porc1Vez / 100);
        turnosD1vez = (short) Math.round(turnostot * porc1Vez / 100);
        turnosDSub = (short) Math.round(turnostot * porcSubs / 100);

        //listaTurnos = turnosCeFacade.ListByFechaConsultorioDia(selectFecha, selectConsultorio);
        listaTurnos = turnosCeFacade.ListByFechaConsultorioControl(selectFecha, selectConsultorio);

        if (listaTurnos.size() > 0) {
            listaHoras = returnHoras(listaHorarioMedico.get(0).getHoraInicial(), turnosD1vez, listaHorarioMedico.get(0).getTiempo());
        } else {
            listaHoras = null;
        }
        short turnosReg = (short) (listaTurnos.size());
        //short turnosDisp = (short) (turnosD1vez - turnosReg);
        short turnosDisp = (short) (turnosDSub - turnosReg);
        msgTotalTurnos = Short.toString(turnosDisp);
        if (turnosD1vez == listaTurnos.size()) {
            flagBtnAsignar = true;
        } else {
            flagBtnAsignar = listaTurnos.size() > turnosD1vez;
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** totalizaTurnos">
    public short totalizaTurnos() {
        int i = 0;
        short t1 = 0, ttd = 0;
        while (true) {
            if (i == listaHorarioMedico.size()) {
                break;
            }
            t1 = listaHorarioMedico.get(i).getTurnosPosibles();
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
    // <editor-fold defaultstate="collapsed" desc="/** botón Grabar de Dialog">
    public void btnGrabarTurno() {
        if (turnosCeFacade.ListByFechaPacienteConsl(selectFecha, selectTurnoCe.getPacientes(), selectConsultorio).size() > 0) {
            ponerMensajeInfo("ATENCION", "El paciente seleccionado ya se encuentra registrado...");
        } else {

            try {
                short s;
                if (listaTurnos.isEmpty()) {
                    s = 1;
                } else {
                    s = (short) ((short) listaTurnos.size() + 1);
                }
                editTurnoCe = new TurnosCe();
                editTurnoCe.setPacientes(selectTurnoCe.getPacientes());
                editTurnoCe.setPersonal1(selectConsultorio);
                editTurnoCe.setFecha(selectFecha);
                editTurnoCe.setNumero(s);
                editTurnoCe.setTipo("C");
                editTurnoCe.setEstado("R");
                editTurnoCe.setDprAraCodigo("O");
                editTurnoCe.setDprCodigo(codEsp);
                editTurnoCe.setFechaCreacion(new Date());
                editTurnoCe.setSobrecarga('F');
                editTurnoCe.setCreado(new Date());
                editTurnoCe.setCreadoPor("CAJERO_PRUEBAS");
                editTurnoCe.setPcnFuerza(selectTurnoCe.getPacientes().getFuerza());
                editTurnoCe.setPcnGrado(selectTurnoCe.getPacientes().getGrado());
                editTurnoCe.setPcnSituacion(selectTurnoCe.getPacientes().getSituacion());
                editTurnoCe.setObservaciones("Referido OdontoWeb");
                editTurnoCe.setTipoTurno('T');

                turnosCeFacade.create(editTurnoCe);
                System.out.println("creado turnos x |" + loginPersonal.getCodigo() + " | " + new Date());
                //
                BigDecimal idTurno = turnosCeFacade.maxRegIdByPcn(selectTurnoCe.getPacientes());
                //
                editTurnoCe = turnosCeFacade.find(idTurno);
                editTurnoCe.setCreadoPor(loginPersonal.getUsuario());
                turnosCeFacade.edit(editTurnoCe);
                System.out.println("turno actualizado login");
                listaTurnos = turnosCeFacade.ListByFechaConsultorio(selectFecha, selectConsultorio);

            } catch (Exception e) {
                for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                    System.out.println("t.getCause() " + t.getCause());
                    if (t.getMessage().contains("java.sql.SQLException: ORA-20998")) {
                        msgGrabar = "Personal no autorizado a dar turnos para " + selectConsultorio.getCodigo();
                        System.out.println("msgBuscar " + msgGrabar);
                        if (t.getMessage().contains("ORA-20334")) {
                            msgGrabar = " Se ha determinado que existe una excepcion para la atención de pacientes en el " + selectConsultorio.getCodigo();
                            System.out.println("msgBuscar " + msgGrabar);
                        }
                        break;
                    }

                }
                System.out.println("error......." + e.getMessage());
                msgGrabar = "Personal no autorizado a dar turnos para ** " + selectConsultorio.getCodigo();

            }
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        sistemaFechaCal = Calendar.getInstance();
        fechaActual = sdf.format(sistemaFechaCal.getTime());
        try {
            actualFecha = sdf.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(AsignarTurnoReferidoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        edadPaciente = pacientesFacade.edadPaciente(selectTurnoCe.getPacientes());
        flagPanelConsulta = false;
        flagBtnAsignar = true;
        flagCalendar = true;
        selectTurnoCePrint = new TurnosCe();
    }

    // </editor-fold>    
    /**
     * Creates a new instance of AsignarTurnoReferidoJSFManagedBean
     */
    public AsignarTurnoReferidoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectTurnoCe = (TurnosCe) session.getAttribute("turnoCe");
        loginPersonal = (Personal) session.getAttribute("userLogin");
    }

}
