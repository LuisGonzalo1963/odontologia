/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OCopagos;
import he1.odonto.entities.OPcnDerivados;
import he1.odonto.entities.OTurnosCobertura;
import he1.odonto.sessions.OCopagosFacade;
import he1.odonto.sessions.OPcnDerivadosFacade;
import he1.odonto.sessions.OTurnosCoberturaFacade;
import he1.sis.entities.AtencionSecretarias;
import he1.sis.entities.Cargos;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.Departamentos;
import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.entities.TurnosProcedimientos;
import he1.sis.sessions.AtencionSecretariasFacade;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CuentasFacade;
import he1.sis.sessions.DepartamentosFacade;
import he1.sis.sessions.HorariosMedicoFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.PromocionesPacientesFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.sis.sessions.TurnosProcedimientosFacade;
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
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "atencionTurno")
@ViewScoped
public class AtencionTurnoJSFManagedBean extends Utilitario implements Serializable {

    private static final Logger LOG = Logger.getLogger(AtencionTurnoJSFManagedBean.class.getName());
    @EJB
    private OPcnDerivadosFacade pcnDerivadosFacade;
    @EJB
    private OCopagosFacade oCopagosFacade;
    @EJB
    private TurnosProcedimientosFacade turnosProcedimientosFacade;
    @EJB
    private OTurnosCoberturaFacade turnosCoberturaFacade;
    @EJB
    private DepartamentosFacade departamentosFacade;

    @EJB
    private AtencionSecretariasFacade atencionSecretariasFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;

    private AtencionSecretarias selectedAtencSecretarias;
    private Personal selectedConsultorio;
    private Personal loginPersonal;
    private TurnosCe selectedTurno;
    private Pacientes editPaciente;
    private Pacientes editPacienteConsulta;
    private TurnosCe editTurnoCe;
    private TurnosCe updateTurnoCe;
    private Departamentos selectDepartamentos;
    private Cargos selectCargos;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;
    private Cuentas selectCuentas;
    private TurnosProcedimientos selectedTurnoProced;
    private OCopagos selectOcopagos;
    private OTurnosCobertura editTurnosCobertura;
    private OPcnDerivados editPcnDerivados;

    private List<AtencionSecretarias> listAtencionSecretarias;
    private List<TurnosCe> listaTurnos;
    private List<TurnosCe> listaTurnosDia;
    private List<TurnosCe> listaTurnosControl;
    private List<TurnosCe> listaTurnosExtras;

    private List<TurnosCe> listaTurnosConsulta;
    private List<HorariosMedico> listaHorarioMedico;
    private List<TurnosCe> listaTurnosPaciente;
    private List<TurnosProcedimientos> listaTurnosProc;

    private String fechaActual;
    private String diaActual;
    private String buscarPor;
    private String nroHc;
    private String msgBuscar;
    private String msgGrabar;
    private String tipoTurno;
    private String msgTotalTurnos;
    private String msgTurnosExtras;
    private String msgTurnosProced;
    private String tipoPaciente;
    private String prmPaciente;
    private String pcnDerivado;
    private String pcnDerivDet;

    private List<String> listaHoras;

    private boolean flagBtnAsignar;
    private boolean flagBtnAceptar;
    private boolean flagBtnReferir;
    private boolean flagBtnSubsec;
    private boolean flagBtnFichaO;
    private boolean flagBtnFichaP;
    private boolean flagPnlSubsec;
    private boolean flagInpText;
    private boolean flagCosto;

    private BigDecimal edadPaciente;
    private BigDecimal valorPago;
    private BigDecimal valorTotal;
    private BigDecimal porcDscto;

    private Date fechaAtencion;
    private Date fechaSelect;

    private Long nroAtenciones;

    private short turnosD1vez;
    private short turnosDSub;

    private final String codMedico;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private java.util.Date date;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public String getFechaActual() {
        return fechaActual;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<String> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<String> listaHoras) {
        this.listaHoras = listaHoras;
    }

    public String getMsgTurnosProced() {
        return msgTurnosProced;
    }

    public void setMsgTurnosProced(String msgTurnosProced) {
        this.msgTurnosProced = msgTurnosProced;
    }

    public boolean isFlagCosto() {
        return flagCosto;
    }

    public void setFlagCosto(boolean flagCosto) {
        this.flagCosto = flagCosto;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public boolean isFlagInpText() {
        return flagInpText;
    }

    public void setFlagInpText(boolean flagInpText) {
        this.flagInpText = flagInpText;
    }

    public String getPcnDerivDet() {
        return pcnDerivDet;
    }

    public void setPcnDerivDet(String pcnDerivDet) {
        this.pcnDerivDet = pcnDerivDet;
    }

    public String getPcnDerivado() {
        return pcnDerivado;
    }

    public void setPcnDerivado(String pcnDerivado) {
        this.pcnDerivado = pcnDerivado;
    }

    public Long getNroAtenciones() {
        return nroAtenciones;
    }

    public void setNroAtenciones(Long nroAtenciones) {
        this.nroAtenciones = nroAtenciones;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public boolean isFlagBtnFichaP() {
        return flagBtnFichaP;
    }

    public void setFlagBtnFichaP(boolean flagBtnFichaP) {
        this.flagBtnFichaP = flagBtnFichaP;
    }

    public TurnosProcedimientos getSelectedTurnoProced() {
        return selectedTurnoProced;
    }

    public void setSelectedTurnoProced(TurnosProcedimientos selectedTurnoProced) {
        this.selectedTurnoProced = selectedTurnoProced;
    }

    public List<TurnosProcedimientos> getListaTurnosProc() {
        return listaTurnosProc;
    }

    public void setListaTurnosProc(List<TurnosProcedimientos> listaTurnosProc) {
        this.listaTurnosProc = listaTurnosProc;
    }

    public Pacientes getEditPacienteConsulta() {
        return editPacienteConsulta;
    }

    public void setEditPacienteConsulta(Pacientes editPacienteConsulta) {
        this.editPacienteConsulta = editPacienteConsulta;
    }

    public List<TurnosCe> getListaTurnosConsulta() {
        return listaTurnosConsulta;
    }

    public void setListaTurnosConsulta(List<TurnosCe> listaTurnosConsulta) {
        this.listaTurnosConsulta = listaTurnosConsulta;
    }

    public String getMsgTurnosExtras() {
        return msgTurnosExtras;
    }

    public void setMsgTurnosExtras(String msgTurnosExtras) {
        this.msgTurnosExtras = msgTurnosExtras;
    }

    public boolean isFlagBtnFichaO() {
        return flagBtnFichaO;
    }

    public boolean isFlagPnlSubsec() {
        return flagPnlSubsec;
    }

    public void setFlagPnlSubsec(boolean flagPnlSubsec) {
        this.flagPnlSubsec = flagPnlSubsec;
    }

    public void setFlagBtnFichaO(boolean flagBtnFichaO) {
        this.flagBtnFichaO = flagBtnFichaO;
    }

    public boolean isFlagBtnSubsec() {
        return flagBtnSubsec;
    }

    public void setFlagBtnSubsec(boolean flagBtnSubsec) {
        this.flagBtnSubsec = flagBtnSubsec;
    }

    public String getMsgTotalTurnos() {
        return msgTotalTurnos;
    }

    public void setMsgTotalTurnos(String msgTotalTurnos) {
        this.msgTotalTurnos = msgTotalTurnos;
    }

    public short getTurnosD1vez() {
        return turnosD1vez;
    }

    public void setTurnosD1vez(short turnosD1vez) {
        this.turnosD1vez = turnosD1vez;
    }

    public String getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    public boolean isFlagBtnAceptar() {
        return flagBtnAceptar;
    }

    public void setFlagBtnAceptar(boolean flagBtnAceptar) {
        this.flagBtnAceptar = flagBtnAceptar;
    }

    public String getMsgBuscar() {
        return msgBuscar;
    }

    public void setMsgBuscar(String msgBuscar) {
        this.msgBuscar = msgBuscar;
    }

    public String getMsgGrabar() {
        return msgGrabar;
    }

    public void setMsgGrabar(String msgGrabar) {
        this.msgGrabar = msgGrabar;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public Pacientes getEditPaciente() {
        return editPaciente;
    }

    public void setEditPaciente(Pacientes editPaciente) {
        this.editPaciente = editPaciente;
    }

    public String getNroHc() {
        return nroHc;
    }

    public void setNroHc(String nroHc) {
        this.nroHc = nroHc;
    }

    public List<TurnosCe> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<TurnosCe> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public List<AtencionSecretarias> getListAtencionSecretarias() {
        return listAtencionSecretarias;
    }

    public void setListAtencionSecretarias(List<AtencionSecretarias> listAtencionSecretarias) {
        this.listAtencionSecretarias = listAtencionSecretarias;
    }

    public AtencionSecretarias getSelectedAtencSecretarias() {
        return selectedAtencSecretarias;
    }

    public void setSelectedAtencSecretarias(AtencionSecretarias selectedAtencSecretarias) {
        this.selectedAtencSecretarias = selectedAtencSecretarias;
    }

    public TurnosCe getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(TurnosCe selectedTurno) {
        this.selectedTurno = selectedTurno;
    }

    public Personal getLoginPersonal() {
        return loginPersonal;
    }

    public void setLoginPersonal(Personal loginPersonal) {
        this.loginPersonal = loginPersonal;
    }

    public boolean isFlagBtnAsignar() {
        return flagBtnAsignar;
    }

    public void setFlagBtnAsignar(boolean flagBtnAsignar) {
        this.flagBtnAsignar = flagBtnAsignar;
    }

    public boolean isFlagBtnReferir() {
        return flagBtnReferir;
    }

    public void setFlagBtnReferir(boolean flagBtnReferir) {
        this.flagBtnReferir = flagBtnReferir;
    }

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxEventTipoTurno">
    public void selectedAjaxEventTipoTurno() {
        System.out.println("tipoTurno " + tipoTurno);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** recuperarTurnos">
    public void recuperarTurnos() {
        actualizarTurnos();

        msgTotalTurnos = Short.toString(totalizaTurnos());
        msgTurnosExtras = Integer.toString(listaTurnosExtras.size());
        msgTurnosProced = Integer.toString(listaTurnosControl.size());
        turnosD1vez = (short) (listaTurnosDia.size());
        turnosD1vez = (short) (totalizaTurnos() - turnosD1vez);
        if (listaTurnos.size() > 0) {
            flagBtnAsignar = false;
        } else {
        }
        //
        actualizarTurnos();
        if (!listaHorarioMedico.isEmpty()) {
            //llamada a las horas desde el utilitario
            listaHoras = returnHoras(listaHorarioMedico.get(0).getHoraInicial(), totalizaTurnos(), listaHorarioMedico.get(0).getTiempo());
        } else {
            ponerMensajeInfo("ATENCIÓN", "No tiene horario definido, para consultas por 1ra- Vez");
        }

    }

    public void actualizarTurnos() {
        listAtencionSecretarias = atencionSecretariasFacade.listByPersonal(loginPersonal);
        if (listAtencionSecretarias.isEmpty()) {
        } else {
            int i = 0;
            while (true) {
                try {
                    if (i == listAtencionSecretarias.size()) {
                        break;
                    }
                    if (listAtencionSecretarias.get(i).getConsultorio() == null) {
                    } else {
                        if (listAtencionSecretarias.get(i).getConsultorio().equalsIgnoreCase("C")) {
                            listaTurnos = turnosCeFacade.ListByFechaConsultorio(fechaSelect, listAtencionSecretarias.get(i).getPersonal());
                            listaTurnosControl = turnosCeFacade.ListByFechaConsultorioControl(fechaSelect, listAtencionSecretarias.get(i).getPersonal());
                            listaTurnosExtras = turnosCeFacade.ListByFechaConsultorioExtras(fechaSelect, listAtencionSecretarias.get(i).getPersonal());
                            listaTurnosDia = turnosCeFacade.ListByFechaConsultorioDia(fechaSelect, listAtencionSecretarias.get(i).getPersonal());
                            listaHorarioMedico = horariosMedicoFacade.listByDiaConsultorio(diaActual, listAtencionSecretarias.get(i).getPersonal().getCodigo());
                        }
                    }
                } catch (Exception e) {
                }
                i++;
            }
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

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** verificaTurnos">    
    public void verificaTurnos() {
        selectDepartamentos = AreaDptosUtil(loginPersonal, diaActual);
        if (selectDepartamentos == null) {
            ponerMensajeInfo("ATENCIÓN", "No tiene horario definido, para consultas por 1ra- Vez");
            
        } else {
            double porc1Vez = selectDepartamentos.getPrcInicial();
            double porcSubs = selectDepartamentos.getPrcSubsecuente();
            double turnostot = totalizaTurnos();
            turnosD1vez = (short) Math.round(turnostot * porc1Vez / 100);
            turnosDSub = (short) Math.round(turnostot * porcSubs / 100);
            System.out.println("1ra vez " + turnosD1vez);
            System.out.println("subSec " + turnosDSub);

            short turnosReg = (short) (listaTurnosDia.size());
            short turnosDisp = (short) (turnosD1vez - turnosReg);
            short turnosExt = (short) (turnosReg - turnosD1vez);

            msgTotalTurnos = Short.toString(turnosDisp);

            if (turnosDisp <= 0) {
                msgTotalTurnos = "0";
                flagBtnAsignar = false;
            } else {
                flagBtnAsignar = true;
            }
            msgTurnosExtras = Short.toString(turnosExt);
            if (turnosExt <= 0) {
                msgTurnosExtras = "0";
            }
        }
    }

    // </editor-fold>          
    // <editor-fold defaultstate="collapsed" desc="/** onRowSelect turnos">
    public void onRowSelectTurnos(SelectEvent event) {
        flagBtnFichaP = true;

        selectedTurno = ((TurnosCe) event.getObject());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("turnoCe", selectedTurno);

        if (selectedTurno != null) {
            //recupera registro cuenta del paciente seleccionado
            selectCuentas = cuentasFacade.findByCodigo(selectedTurno.getNumeroId().longValue());
            if (selectCuentas == null) {
                flagBtnFichaP = false;
                flagBtnSubsec = false;
            } else {
                flagBtnFichaO = true;
                if (selectCuentas.getEstado().equalsIgnoreCase("FCT")) {
                    if (selectedTurno.getEstado().equalsIgnoreCase("A")) {

                    } else {
                        selectedTurno.setEstado("P");
                        turnosCeFacade.edit(selectedTurno);
                    }
                }

                if (selectedTurno.getEstado().equalsIgnoreCase("P") || selectedTurno.getEstado().equalsIgnoreCase("A")) {
                    flagBtnFichaO = false;
                    flagBtnSubsec = false;
                    flagBtnReferir = false;
                    flagBtnFichaP = false;
                } else {
                    flagBtnFichaO = true;
                    flagBtnSubsec = true;
                    flagBtnReferir = true;
                    flagBtnFichaP = true;
                    if (selectedTurno.getEstado().equalsIgnoreCase("R")) {
                        ponerMensajeInfo("ATENCIÓN", "El turno no está Cancelado...");
                    } else {
                        ponerMensajeInfo("ATENCIÓN", "El turno no está habilitado...Revise el estado");
                    }
                }

            }

        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** botón btnBuscarHc">
    public void btnBuscarHc() {
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation("repFichaOdonByHc?faces-redirect=true");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** botón btnRxPedido">
    public void btnRxPedido() {
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation("pedidoRxSinTurno?faces-redirect=true");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** botón Asignar">
    public void btnAsignar() {
        nroHc = null;
        msgBuscar = null;
        msgGrabar = null;
        buscarPor = null;
        edadPaciente = null;
        tipoTurno = null;
        editPaciente = new Pacientes();
        flagBtnAceptar = true;

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** btn Estado turno">
    public void btnEstadoTurno() {
        selectedTurno.setEstado(selectedTurno.getEstado());
        turnosCeFacade.edit(selectedTurno);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn Estado Procedimiento">
    public void btnEstadoProced() {
        selectedTurno.setEstado(selectedTurno.getEstado());
        turnosCeFacade.edit(selectedTurno);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn Referidos">
    public void btnReferidos() {
        System.out.println("selectedTurno " + selectedTurno);
        if (selectedTurno != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("turno", selectedTurno);
            httpSession.setAttribute("userLogin", loginPersonal);

            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            handler.performNavigation("asignarTurnoReferido?faces-redirect=true");

        } else if (selectedTurno == null) {
            ponerMensajeInfo("ATENCIÓN", "Seleccione un paciente");
        }

    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** btn Subsecuente">
    public void btnSubsecuente() {

        System.out.println("turnosDSub " + turnosDSub);
        flagPnlSubsec = true;
//        if (turnosDSub == 0) {
//            ponerMensajeInfo("ATENCIÓN", "No tiene parametrizado Subsecuentes");
//        } else {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("turnoCe", selectedTurno);

        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation("asignarTurnoSubsecuente?faces-redirect=true");
//        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn btnRefresh">    
    public void btnRefresh() {
        recuperarTurnos();
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** btn Listar turnos">
    public void btnListarTurnos() {
        actualizarTurnos();
        try {
            fechaSelect = dateFormat.parse(fechaActual);
            listaTurnos = turnosCeFacade.ListByFechaConsultorio(fechaSelect, listaTurnos.get(0).getPersonal1());
        } catch (ParseException ex) {
            Logger.getLogger(AtencionTurnoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btn FichaO">
    public void btnFichaO() {
        System.out.println("selectedTurnoFicha " + selectedTurno);

        if (selectedTurno != null) {
            if (selectedTurno.getEstado().equalsIgnoreCase("P") || selectedTurno.getEstado().equalsIgnoreCase("A")) {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                HttpSession httpSession = request.getSession(false);

                httpSession.setAttribute("userLogin", loginPersonal);
                httpSession.setAttribute("turno", selectedTurno);

                //determinar el código del departamento para la especialidad
                if (selectedTurno.getDprCodigo().equalsIgnoreCase("D") || selectedTurno.getDprCodigo().equalsIgnoreCase("T")) {
                    //registra la hora de inicio de atención
                    updateTurnoCe = selectedTurno;
                    if (updateTurnoCe.getFechaAtencion() == null) {
                        updateTurnoCe = selectedTurno;
                        updateTurnoCe.setFechaAtencion(new Date());
                        updateTurnoCe.setHoraInicio(new Date());
                        turnosCeFacade.edit(updateTurnoCe);

                    }

                    ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                    handler.performNavigation("atencionFicha?faces-redirect=true");
                } else {

                    ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                    handler.performNavigation("atencionEspecialidad?faces-redirect=true");
                }

            } else {
                //System.out.println("estado " + selectedTurno.getEstado());
                if (selectedTurno.getEstado().equalsIgnoreCase("R")) {
                    ponerMensajeInfo("ATENCIÓN", "El turno no está Cancelado...");
                } else {
                    ponerMensajeInfo("ATENCIÓN", "El turno no está habilitado...Revise el estado");
                }

            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn Procedimientos">
    public void btnProcedimientos() {
        System.out.println("selectedTurnoProc " + selectedTurno);
        if (selectedTurno != null) {
            if (selectedTurno.getTipo().equalsIgnoreCase("C") || selectedTurno.getEstado().equalsIgnoreCase("P") || selectedTurno.getEstado().equalsIgnoreCase("A")) {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                HttpSession httpSession = request.getSession(false);

                httpSession.setAttribute("userLogin", loginPersonal);

                httpSession.setAttribute("turno", selectedTurno);
                updateTurnoCe = selectedTurno;
                updateTurnoCe.setFechaAtencion(fechaAtencion);
                updateTurnoCe.setHoraInicio(new Date());
                turnosCeFacade.edit(updateTurnoCe);
                ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                handler.performNavigation("atencionEspecialidad?faces-redirect=true");

            } else {
                //System.out.println("estado " + selectedTurno.getEstado());
                if (selectedTurno.getEstado().equalsIgnoreCase("R")) {
                    ponerMensajeInfo("ATENCIÓN", "El turno no está Cancelado...");
                } else {
                    ponerMensajeInfo("ATENCIÓN", "El turno no está habilitado...Revise el estado");
                }

            }
        }

    }

    // </editor-fold>      
    // <editor-fold defaultstate="collapsed" desc="/** Tipo de búsqueda">
    public void selectedAjaxChange() {
        if (tipoTurno == null) {
            ponerMensajeInfo("ATENCIÓN", "Seleccione el tipo de cita médica...");
        }
        nroHc = null;
        editPaciente = new Pacientes();
        edadPaciente = null;
        msgBuscar = null;
        msgGrabar = null;
        flagBtnAceptar = true;
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** buscarAjaxBlurTurnos">    
    public void buscarAjaxBlurTurnos() {
        editPacienteConsulta = pacientesFacade.findByHc(Integer.valueOf(nroHc));
        listaTurnosConsulta = turnosCeFacade.ListByTurnosPaciente(editPacienteConsulta);
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** buscar por tipo de documento">
    public void buscarAjaxBlur() {
        if (buscarPor != null) {
            msgBuscar = null;
            if (nroHc != null) {
                if (buscarPor.equalsIgnoreCase("nhc")) {
                    editPaciente = pacientesFacade.findByHc(Integer.valueOf(nroHc));
                }
                if (buscarPor.equalsIgnoreCase("ced")) {
                    editPaciente = pacientesFacade.findByNroCed(nroHc);
                }
                if (buscarPor.equalsIgnoreCase("ape")) {

                }
            }
            if (editPaciente == null) {
                msgBuscar = "Paciente no existe. Verifique...";
                flagBtnAceptar = true;
            } else {
                msgBuscar = null;
                flagBtnAceptar = false;
                tipoPaciente = tipoPaciente(editPaciente);
                //listaTurnosPaciente = turnosCeFacade.ListByFechaPaciente(fechaSelect, editPaciente);
                listaTurnosPaciente = turnosCeFacade.ListByFechaPacienteConsl(fechaSelect, editPaciente, listaTurnos.get(0).getPersonal1());
                edadPaciente = pacientesFacade.edadPaciente(editPaciente);
                if (listaTurnosPaciente.size() > 0) {
                    msgBuscar = "No. de Historia clínica ya registrado. Verifique...en " + listaTurnosPaciente.get(0).getPersonal1().getNombres();
                    flagBtnAceptar = true;
                } else {
                    msgBuscar = null;
                    flagBtnAceptar = false;
                }
            }

        } else {
            msgGrabar = null;
            msgBuscar = "Seleccione el tipo de búsqueda...";
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** tarifario">
    public void tarifario() {
        if (listaTurnos.get(0).getDprCodigo().equalsIgnoreCase("D") || listaTurnos.get(0).getDprCodigo().equalsIgnoreCase("T")) {
            selectCargos = cargosFacade.findByCodCrg("200001");
        } else {
            selectCargos = cargosFacade.findByCodCrg("200005");
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeDerivado">
    public void selectedAjaxChangeDerv() {
        flagInpText = !pcnDerivado.equalsIgnoreCase("S");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** procesoCuentas">
    public void procesoCuentas(Pacientes paciente) {
        tarifario();
        if (paciente.getIdIssfa() != null) {
            selectOcopagos = oCopagosFacade.findByCodigo("02");

        }
        valorPago = selectCargos.getCosto();
    }

    public String tipoPaciente(Pacientes paciente) {
        procesoCuentas(paciente);
        prmPaciente = promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(paciente)).getPromociones().getCodigo();
        if (prmPaciente.equalsIgnoreCase("01")) {
            //civil 100%%
            tipoPaciente = "CIVIL 100%";
            valorTotal = valorPago.setScale(2, BigDecimal.ROUND_UP);
        } else if (prmPaciente.equalsIgnoreCase("02")) {
            //issfa validar padreISsfa paga 100%
            if (paciente.getSituacion().equalsIgnoreCase("3")) {
                tipoPaciente = "ISSFA padre";
                porcDscto = selectOcopagos.getCpgPorcentaje();
                valorTotal = valorPago.multiply(porcDscto).setScale(2, BigDecimal.ROUND_UP);
            } else {
                tipoPaciente = "ISSFA";
                porcDscto = selectOcopagos.getCpgPorcentaje();
                //valorTotal = valorPago.multiply(porcDscto).setScale(2, BigDecimal.ROUND_UP);
                valorTotal = new BigDecimal("6.38");
                //verifica numero de atenciones en odontologia
                nroAtenciones = turnosCoberturaFacade.countTurnos(paciente);
                if (nroAtenciones <= 2) {
                    msgBuscar = "Paciente no existe. Verifique...";
                }

            }
        }
        return tipoPaciente;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** botón Grabar de Dialog">
    public void btnGrabarTurno() {
        try {
            date = dateFormat.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(AtencionTurnoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            short c = 0, e = 0, t = 0;
            if (listaTurnosControl.isEmpty()) {
                //c++;
            } else {
                c = (short) ((short) listaTurnosControl.size());
            }
            if (listaTurnosExtras.isEmpty()) {
                //e++;
            } else {
                e = (short) ((short) listaTurnosExtras.size());
            }
            t = (short) (totalizaTurnos() + c + e + 1);
            //1. grabar en turnos
            editTurnoCe = new TurnosCe();

            editTurnoCe.setPacientes(editPaciente);
            editTurnoCe.setPersonal1(listaTurnos.get(0).getPersonal1());
            editTurnoCe.setFecha(date);
            editTurnoCe.setNumero(t);
            editTurnoCe.setTipo(tipoTurno);
            if (tipoTurno.equalsIgnoreCase("P")) {
                editTurnoCe.setEstado("R");
            } else {
                editTurnoCe.setEstado("R");
            }
            editTurnoCe.setDprAraCodigo("O");
            editTurnoCe.setDprCodigo(listaTurnos.get(0).getDprCodigo());
            editTurnoCe.setFechaCreacion(fechaSelect);
            if (tipoTurno.equalsIgnoreCase("P")) {
                editTurnoCe.setSobrecarga('V');
            } else {
                editTurnoCe.setSobrecarga('F');
            }
            editTurnoCe.setCreado(new Date());
            editTurnoCe.setCreadoPor("CAJERO_PRUEBAS");
            editTurnoCe.setPcnFuerza(editPaciente.getFuerza());
            editTurnoCe.setPcnGrado(editPaciente.getGrado());
            editTurnoCe.setPcnSituacion(editPaciente.getSituacion());
            if (tipoTurno.equalsIgnoreCase("P")) {
                //editTurnoCe.setTipoTurno('T');
            } else {
                editTurnoCe.setTipoTurno('C');
            }

            turnosCeFacade.create(editTurnoCe);
            System.out.println("turnos creado........");
            //
            procesoCuentas(editPaciente);
            BigDecimal idTurno = turnosCeFacade.maxRegIdByPcn(editPaciente);
            //
            editTurnoCe = turnosCeFacade.find(idTurno);
            editTurnoCe.setCreadoPor(loginPersonal.getUsuario());
            turnosCeFacade.edit(editTurnoCe);
            System.out.println("turno actualizado login");
            //graba en derivados
            editPcnDerivados = new OPcnDerivados();
            editPcnDerivados.setPcdId(idTurno);
            editPcnDerivados.setPcdFecha(fechaSelect);
            editPcnDerivados.setTurnosCe(new TurnosCe(idTurno));
            editPcnDerivados.setPcdCreadoPor(loginPersonal.getUsuario());
            editPcnDerivados.setPcdDerivado(pcnDerivado);
            editPcnDerivados.setPcdDetalle(pcnDerivDet);
            pcnDerivadosFacade.create(editPcnDerivados);
            System.out.println("creado derivado pcn..");

            //Validación de turno de control
            if (tipoTurno.equalsIgnoreCase("P")) {
                System.out.println("registra pago turno extra 1ra. vez...");
                //2.1 registra turno en la tabla cobertura
                editTurnosCobertura = new OTurnosCobertura();
                editTurnosCobertura.setTcbId(BigDecimal.ONE);
                editTurnosCobertura.setPacientes(editPaciente);
                editTurnosCobertura.setPrmCodigo(prmPaciente);
                editTurnosCobertura.setTcbFecha(new Date());
                editTurnosCobertura.setTcbLogin(loginPersonal.getCodigo());
                editTurnosCobertura.setTcbTipo(listaTurnos.get(0).getDprCodigo());
                turnosCoberturaFacade.create(editTurnosCobertura);
                System.out.println("turnocobertura creado...");

                //2.2 grabar datos en cuenta
                procesoCuentas(editPaciente);
                editCuentasPk = new CuentasPK();
                editCuentasPk.setDocumento("J");
                editCuentasPk.setNumero(idTurno.longValue()); //nro. secuencial del turno del paciente
                editCuentasPk.setDetalle(1); //1 para turno

                editCuentas = new Cuentas();
                editCuentas.setCuentasPK(editCuentasPk);

                editCuentas.setSeguro('F');
                editCuentas.setDprAraCodigo("C");
                editCuentas.setDprCodigo("Z");
                editCuentas.setDprAraCodigoPertenecienteA("O");
                editCuentas.setDprCodigoPertenecienteA(listaTurnos.get(0).getDprCodigo());
                editCuentas.setPacientes(editPaciente);
                editCuentas.setEstado("PND");
                editCuentas.setFecha(fechaSelect);
                editCuentas.setCantidad(BigDecimal.ONE);
                editCuentas.setMonedaDeTrabajo("DLAR");
                editCuentas.setValor(valorTotal);/////*******
                editCuentas.setCrgTipo('S');
                editCuentas.setCrgCodigo(selectCargos.getCargosPK().getCodigo());
                editCuentas.setPorcentajePromocion(BigDecimal.ONE);
                editCuentas.setDescuentoOtorgado(BigDecimal.ZERO);
                editCuentas.setIva(BigDecimal.ZERO);
                editCuentas.setCreadoPor(loginPersonal.getUsuario()); //usuario logueado
                editCuentas.setPrmCodigo("01"); //codigo de promoción para generar pago
                editCuentas.setValore(BigDecimal.ZERO);
                editCuentas.setIvae(BigDecimal.valueOf(0, 1));
                editCuentas.setObservacion("Plataforma Web");
                editCuentas.setIvaExcento('F');
                editCuentas.setServicio("CEX");
                editCuentas.setConfirmado('F');
                editCuentas.setAuditado('F');

                cuentasFacade.create(editCuentas);
                System.out.println("creado en Cuentas.....");
                //
                System.out.println("idTurno " + idTurno);
                editCuentas = cuentasFacade.findByCodigo(idTurno.longValue());
                //System.out.println("editCuentasAct " + editCuentas);
                editCuentas.setCreadoPor(loginPersonal.getUsuario());
                cuentasFacade.edit(editCuentas);
                //
                System.out.println("actualizado login....");
            }
            //
            listaTurnos = turnosCeFacade.ListByFechaConsultorio(fechaSelect, listaTurnos.get(0).getPersonal1());

            recuperarTurnos();
            totalizaTurnos();

        } catch (Exception e) {
            for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                System.out.println("t.getCause() " + t.getCause());
                if (t.getMessage().contains("SQLIntegrityConstraintViolationException: ORA-20998")) {
                    msgGrabar = "Personal no autorizado a dar turnos para " + selectedConsultorio.getCodigo();
                    System.out.println("msgBuscar " + msgGrabar);
                    break;
                }
            }
            System.out.println("error.......");
            msgGrabar = "Personal no autorizado a dar turnos para " + selectedConsultorio.getCodigo();

        }

    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        Calendar Cal = Calendar.getInstance();
        fechaActual = dateFormat.format(Cal.getTime());
        diaActual = String.valueOf(Cal.get(Calendar.DAY_OF_WEEK));
        try {
            fechaSelect = dateFormat.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(AtencionTurnoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Atención, CodMedico: " + codMedico + " fecha: " + new Date());
        if (codMedico != null) {
            if (codMedico.substring(0, 1).equalsIgnoreCase("M")) {
                loginPersonal = personalFacade.findByCodigo(codMedico);
                listAtencionSecretarias = atencionSecretariasFacade.listByPersonal(loginPersonal);
                flagBtnAsignar = true;
                flagBtnReferir = true;
            } else {
                logout();
            }
        } else {
            logout();
        }
        flagBtnFichaO = true;
        flagBtnSubsec = true;
        flagBtnReferir = true;
        flagPnlSubsec = false;
        flagBtnFichaP = true;
        //recuperar listados de pacientes

        //recuperarTurnos();
        actualizarTurnos();
        verificaTurnos();
    }

    // </editor-fold>
    /**
     * Creates a new instance of AtencionTurnoJSFManagedBean
     */
    public AtencionTurnoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
