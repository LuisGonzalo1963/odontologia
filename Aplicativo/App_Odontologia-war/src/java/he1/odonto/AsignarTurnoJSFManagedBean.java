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
import he1.sis.entities.CopagosPacientes;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.Departamentos;
import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.AtencionSecretariasFacade;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CopagosPacientesFacade;
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
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "asignarTurno")
@ViewScoped
public class AsignarTurnoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OTurnosCoberturaFacade turnosCoberturaFacade;

    @EJB
    private OPcnDerivadosFacade pcnDerivadosFacade;

    @EJB
    private OCopagosFacade oCopagosFacade;
    @EJB
    private CopagosPacientesFacade copagosPacientesFacade;

    @EJB
    private CuentasFacade cuentasFacade;

    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;

    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private AtencionSecretariasFacade atencionSecretariasFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private DepartamentosFacade departamentosFacade;
    @EJB
    private TurnosCeFacade turnosCeFacade;
    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;
    @EJB
    private CargosFacade cargosFacade;

    private List<Personal> listConsXespecialidad;
    private List<TurnosCe> listaTurnos;
    private List<TurnosCe> listaTurnosPaciente;
    private List<HorariosMedico> listaHorarioMedico;
    private List<String> listaHoras;

    private Personal selectConsultorio;
    private Personal loginPersonal;
    private AtencionSecretarias selectMedico;
    private Pacientes editPaciente;
    private TurnosCe editTurnoCe;
    private TurnosCe selectTurnoCe;
    private Cargos selectCargos;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;
    private Departamentos selectDepartamentos;
    private CopagosPacientes selectCoPagosPcn;
    private OCopagos selectOcopagos;
    private OPcnDerivados editPcnDerivados;
    private OTurnosCobertura editTurnosCobertura;

    private String param;
    private String codEsp;
    private String fechaActual;
    private String diaActual;

    private String buscarPor;
    private String nroHc;
    private String msgBuscar;
    private String msgGrabar;

    private String tipoPaciente;
    private String prmPaciente;

    private String codProced;
    private String msgTotalTurnos;
    private String crgCodigo;
    private String pcnDerivado;
    private String pcnDerivDet;
    private String exitoTurno;

    private boolean flagBtnAsignar;
    private boolean flagClicEspec;
    private boolean flagBtnAceptar;
    private boolean flagBtnPago;
    private boolean flagInpText;
    private boolean flagOutText;

    private BigDecimal edadPaciente;
    private BigDecimal valorTotal;
    private BigDecimal valorDscto;
    private BigDecimal valorPago;
    private BigDecimal porcDscto;

    private Long nroAtenciones;

    private Date selectFecha;
    //private Date actualFecha;
    //private java.sql.Date actualFecha1;
    //private Calendar sistemaFecha;
    private String[] horaTurno;
    private final String codMedico;
    private short turnosD1vez;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public short getTurnosD1vez() {
        return turnosD1vez;
    }

    public void setTurnosD1vez(short turnosD1vez) {
        this.turnosD1vez = turnosD1vez;
    }

    public List<String> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<String> listaHoras) {
        this.listaHoras = listaHoras;
    }

    public Long getNroAtenciones() {
        return nroAtenciones;
    }

    public void setNroAtenciones(Long nroAtenciones) {
        this.nroAtenciones = nroAtenciones;
    }

    public boolean isFlagOutText() {
        return flagOutText;
    }

    public void setFlagOutText(boolean flagOutText) {
        this.flagOutText = flagOutText;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Personal getLoginPersonal() {
        return loginPersonal;
    }

    public void setLoginPersonal(Personal loginPersonal) {
        this.loginPersonal = loginPersonal;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public boolean isFlagBtnPago() {
        return flagBtnPago;
    }

    public void setFlagBtnPago(boolean flagBtnPago) {
        this.flagBtnPago = flagBtnPago;
    }

    public TurnosCe getSelectTurnoCe() {
        return selectTurnoCe;
    }

    public void setSelectTurnoCe(TurnosCe selectTurnoCe) {
        this.selectTurnoCe = selectTurnoCe;
    }

    public List<TurnosCe> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<TurnosCe> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    public boolean isFlagBtnAceptar() {
        return flagBtnAceptar;
    }

    public void setFlagBtnAceptar(boolean flagBtnAceptar) {
        this.flagBtnAceptar = flagBtnAceptar;
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

    public Personal getSelectConsultorio() {
        return selectConsultorio;
    }

    public void setSelectConsultorio(Personal selectConsultorio) {
        this.selectConsultorio = selectConsultorio;
    }

    public List<Personal> getListConsXespecialidad() {
        return listConsXespecialidad;
    }

    public void setListConsXespecialidad(List<Personal> listConsXespecialidad) {
        this.listConsXespecialidad = listConsXespecialidad;
    }

    public List<HorariosMedico> getListaHorarioMedico() {
        return listaHorarioMedico;
    }

    public void setListaHorarioMedico(List<HorariosMedico> listaHorarioMedico) {
        this.listaHorarioMedico = listaHorarioMedico;
    }

    public AtencionSecretarias getSelectMedico() {
        return selectMedico;
    }

    public void setSelectMedico(AtencionSecretarias selectMedico) {
        this.selectMedico = selectMedico;
    }

    public boolean isFlagClicEspec() {
        return flagClicEspec;
    }

    public void setFlagClicEspec(boolean flagClicEspec) {
        this.flagClicEspec = flagClicEspec;
    }

    public boolean isFlagBtnAsignar() {
        return flagBtnAsignar;
    }

    public void setFlagBtnAsignar(boolean flagBtnAsignar) {
        this.flagBtnAsignar = flagBtnAsignar;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getMsgTotalTurnos() {
        return msgTotalTurnos;
    }

    public void setMsgTotalTurnos(String msgTotalTurnos) {
        this.msgTotalTurnos = msgTotalTurnos;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** parámetro de especialidad">
    public void param(ActionEvent ae) {
        msgGrabar = null;
        listaTurnos = null;
        turnosD1vez = 0;
        msgTotalTurnos = null;
        especialidadSelec();
        listConsXespecialidad = personalFacade.listByEspecialidad("%" + especialidadSelec() + "%");
        selectDepartamentos = departamentosFacade.findByAreaDpto(codEsp);

        flagClicEspec = false;
        flagBtnAsignar = true;
        flagBtnPago = true;
        selectMedico = new AtencionSecretarias();
        listaHorarioMedico = new ArrayList<>();
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
        msgGrabar = null;
        listaHorarioMedico = horariosMedicoFacade.listByDiaConsultorio(diaActual, selectConsultorio.getCodigo());
        //System.out.println("reg..." + listaHorarioMedico.size());
        if (!listaHorarioMedico.isEmpty()) {
            flagClicEspec = true;
            flagBtnAsignar = false;
            selectMedico = atencionSecretariasFacade.findByConsultorio(selectConsultorio);
        } else {
            flagBtnAsignar = true;
            selectMedico = new AtencionSecretarias();
        }
        //verifica turnos
        verificaTurnos();

        //recupera el valor
        tarifario();
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
        System.out.println("selectFecha " + selectFecha);
        System.out.println("selectConsultorio " + selectConsultorio);
        double porc1Vez = selectDepartamentos.getPrcInicial();
        //double porcSubs = selectDepartamentos.getPrcSubsecuente();
        double turnostot = totalizaTurnos();
        //double turnosD1vez = Math.round(turnostot * porc1Vez / 100);
        turnosD1vez = (short) Math.round(turnostot * porc1Vez / 100);
        //double turnosDSub = Math.round(turnostot * porcSubs / 100);
        listaTurnos = turnosCeFacade.ListByFechaConsultorioDia(selectFecha, selectConsultorio);
        System.out.println("turnosDispo " + turnosD1vez);
        System.out.println("turnosRegis " + listaTurnos.size());
        short turnosReg = (short) (listaTurnos.size());
        short turnosDisp = (short) (turnosD1vez - turnosReg);
        msgTotalTurnos = Short.toString(turnosDisp);
        if (turnosD1vez == listaTurnos.size()) {
            flagBtnAsignar = true;
        } else {
            flagBtnAsignar = listaTurnos.size() > turnosD1vez;

        }
    }

    // </editor-fold>      
    // <editor-fold defaultstate="collapsed" desc="/** botón Asignar">
    public void btnAsignar() {
        nroHc = null;
        msgBuscar = null;
        msgGrabar = null;
        buscarPor = null;
        edadPaciente = null;
        valorTotal = null;
        pcnDerivado = null;
        editPaciente = new Pacientes();
        flagBtnAceptar = true;
        flagInpText = true;

        verificaTurnos();

        Calendar Cal = Calendar.getInstance();
        SimpleDateFormat formatHoraMin = new SimpleDateFormat("hh:mm");
        //
        horaTurno = new String[totalizaTurnos()];
        Date fechaT = listaHorarioMedico.get(0).getHoraInicial();
        System.out.println("hora 1: " + formatHoraMin.format(fechaT));
        horaTurno[0] = formatHoraMin.format(fechaT);
        int i = 1;
        while (true) {
            if (i == totalizaTurnos()) {
                break;
            }
            fechaT = addMinutesToDate(fechaT, listaHorarioMedico.get(0).getTiempo());
            horaTurno[i] = formatHoraMin.format(fechaT);
            System.out.println("hora " + i + ": " + horaTurno[i]);
            i++;
        }
        listaHoras = Arrays.asList(horaTurno);
        System.out.println("listaHoras " + listaHoras.size());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** onRowSelect turnos">
    public void onRowSelectTurno() {
        flagBtnPago = false;
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="/** btnPago">
    public void btnPago() {
        tipoPaciente = null;
        valorPago = null;
        valorTotal = null;
        procesoCuentas(selectTurnoCe.getPacientes());
    }

    public void tarifario() {

        if (codEsp.equalsIgnoreCase("D") || codEsp.equalsIgnoreCase("T")) {
            selectCargos = cargosFacade.findByCodCrg("200001");
        } else {
            selectCargos = cargosFacade.findByCodCrg("200005");
        }
        valorPago = selectCargos.getCosto();
    }

    public void procesoCuentas(Pacientes paciente) {
        tarifario();
        if (paciente.getIdIssfa() != null) {
//            if (copagosPacientesFacade.maxCoPagosPcn(paciente) != null) {
//                selectCoPagosPcn = copagosPacientesFacade.find(copagosPacientesFacade.maxCoPagosPcn(paciente));
//                selectOcopagos = oCopagosFacade.findByCodigo(selectCoPagosPcn.getCopagos().getCodigo());
            selectOcopagos = oCopagosFacade.findByCodigo("02");
//            } else {
//                flagBtnAceptar = true;
//                System.out.println("no hay en copago.....");
//                ponerMensajeInfo("ATENCIÓN", "No tiene registro en Copago!!!!!...");
//            }

        }
    }

    public String tipoPaciente(Pacientes paciente) {
        procesoCuentas(paciente);
        prmPaciente = promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(paciente)).getPromociones().getCodigo();
        System.out.println("valorPago " + valorPago);
        //System.out.println("selectOcopagos.getCpgPorcentaje(); "+selectOcopagos.getCpgPorcentaje());
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
                System.out.println("nroAtenciones " + nroAtenciones);
                if (nroAtenciones <= 2) {
                    msgBuscar = "Paciente no existe. Verifique...";
                }

            }
        }
        return tipoPaciente;
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChange">
    public void selectedAjaxChange() {
        nroHc = null;
        editPaciente = new Pacientes();
        edadPaciente = null;
        msgBuscar = null;
        msgGrabar = null;
        flagBtnAceptar = true;
        if (buscarPor.equalsIgnoreCase("nhc")) {
            System.out.println("hist....");
            flagOutText = false;
        }
        if (buscarPor.equalsIgnoreCase("ced")) {
            System.out.println("cedula.......");
            flagOutText = true;
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeDerivado">
    public void selectedAjaxChangeDerv() {
        flagInpText = !pcnDerivado.equalsIgnoreCase("S");
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
                    System.out.println("buscar....ape..");
                }
            }

            if (editPaciente == null) {
                msgBuscar = "Paciente no existe. Verifique...";
                flagBtnAceptar = true;
            } else {
                msgBuscar = null;
                flagBtnAceptar = false;
                tipoPaciente = tipoPaciente(editPaciente);
                listaTurnosPaciente = turnosCeFacade.ListByFechaPacienteConsl(selectFecha, editPaciente, selectConsultorio);
                edadPaciente = pacientesFacade.edadPaciente(editPaciente);
                if (listaTurnosPaciente.size() > 0) {
                    msgBuscar = "No. de Historia clínica ya registrado. Verifique...en " + listaTurnosPaciente.get(0).getPersonal1().getNombres();
                    flagBtnAceptar = true;
                } else {
                    msgBuscar = null;
                    flagBtnAceptar = false;
                }
                //procesoCuentas();
//                if (editPaciente.getIdIssfa() != null) {
//                    if (copagosPacientesFacade.maxCoPagosPcn(editPaciente) != null) {
//                        selectCoPagosPcn = copagosPacientesFacade.find(copagosPacientesFacade.maxCoPagosPcn(editPaciente));
//                        selectOcopagos = oCopagosFacade.findByCodigo(selectCoPagosPcn.getCopagos().getCodigo());
//                    } else {
//                        ponerMensajeInfo("ATENCIÓN", "No tiene registro en Copago!!!...");
//                        flagBtnAceptar = true;
//                    }
//
//                }
                //
            }

        } else {
            System.out.println("nulo buscar por.....");
            msgGrabar = null;
            msgBuscar = "Seleccione el tipo de búsqueda...";
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** botón Grabar de Dialog">
    public void btnGrabarTurno() {
        try {
            short s;
            if (listaTurnos.isEmpty()) {
                s = 1;
            } else {
                s = (short) ((short) listaTurnos.size() + 1);
            }
            System.out.println("selectConsultorio " + selectConsultorio);
            System.out.println("fecha: " + selectFecha);
            System.out.println("turno: " + s);
            editTurnoCe = new TurnosCe();

            editTurnoCe.setPacientes(editPaciente);
            editTurnoCe.setPersonal1(selectConsultorio);
            editTurnoCe.setFecha(selectFecha);
            editTurnoCe.setNumero(s);
            editTurnoCe.setTipo("P");
            editTurnoCe.setEstado("R");
            editTurnoCe.setDprAraCodigo("O");
            editTurnoCe.setDprCodigo(listaHorarioMedico.get(0).getDepartamentos().getDepartamentosPK().getCodigo());
            editTurnoCe.setFechaCreacion(selectFecha);
            editTurnoCe.setSobrecarga('F');
            editTurnoCe.setCreado(new Date());
            editTurnoCe.setCreadoPor("CAJERO_PRUEBAS");
            editTurnoCe.setPcnFuerza(editPaciente.getFuerza());
            editTurnoCe.setPcnGrado(editPaciente.getGrado());
            editTurnoCe.setPcnSituacion(editPaciente.getSituacion());
            editTurnoCe.setTipoTurno('T');

            turnosCeFacade.create(editTurnoCe);
            System.out.println("1. turno creado...");
            //
            editTurnosCobertura = new OTurnosCobertura();
            editTurnosCobertura.setTcbId(BigDecimal.ONE);
            editTurnosCobertura.setPacientes(editPaciente);
            editTurnosCobertura.setPrmCodigo(prmPaciente);
            editTurnosCobertura.setTcbFecha(new Date());
            editTurnosCobertura.setTcbLogin(loginPersonal.getCodigo());
            editTurnosCobertura.setTcbTipo(codEsp);
            turnosCoberturaFacade.create(editTurnosCobertura);
            System.out.println("2. turnocobertura creado...");
            //
            procesoCuentas(editPaciente);
            BigDecimal idTurno = turnosCeFacade.maxRegIdByPcn(editPaciente);
            //Actualiza el usuario logueado
            editTurnoCe = turnosCeFacade.find(idTurno);
            editTurnoCe.setCreadoPor(loginPersonal.getUsuario());
            turnosCeFacade.edit(editTurnoCe);
            //graba en derivados
            editPcnDerivados = new OPcnDerivados();
            editPcnDerivados.setPcdId(idTurno);
            editPcnDerivados.setPcdFecha(selectFecha);
            editPcnDerivados.setTurnosCe(new TurnosCe(idTurno));
            editPcnDerivados.setPcdCreadoPor(loginPersonal.getUsuario());
            editPcnDerivados.setPcdDerivado(pcnDerivado);
            editPcnDerivados.setPcdDetalle(pcnDerivDet);
            pcnDerivadosFacade.create(editPcnDerivados);
            System.out.println("3. creado derivado pcn..");
            //grabar datos en cuenta
            editCuentasPk = new CuentasPK();
            editCuentasPk.setDocumento("J");
            editCuentasPk.setNumero(idTurno.longValue());
            editCuentasPk.setDetalle(1); //1 para turno

            editCuentas = new Cuentas();
            editCuentas.setCuentasPK(editCuentasPk);

            editCuentas.setSeguro('F');
            editCuentas.setDprAraCodigo("C");
            editCuentas.setDprCodigo("Z");
            editCuentas.setDprAraCodigoPertenecienteA("O");
            editCuentas.setDprCodigoPertenecienteA(codEsp);
            editCuentas.setPacientes(editPaciente);
            editCuentas.setEstado("PND");
            editCuentas.setFecha(new Date());
            editCuentas.setCantidad(BigDecimal.ONE);
            editCuentas.setMonedaDeTrabajo("DLAR");
            editCuentas.setValor(valorTotal);/////*******
            editCuentas.setCrgTipo('S');
            editCuentas.setCrgCodigo(selectCargos.getCargosPK().getCodigo());
            editCuentas.setPorcentajePromocion(BigDecimal.ONE);
            editCuentas.setDescuentoOtorgado(BigDecimal.ZERO);
            editCuentas.setIva(BigDecimal.ZERO);
            editCuentas.setCreadoPor(loginPersonal.getUsuario()); //usuario logueado
            editCuentas.setPrmCodigo("01"); //codigo de promoción ********
            editCuentas.setValore(BigDecimal.ZERO);
            editCuentas.setIvae(BigDecimal.valueOf(0, 1));
            editCuentas.setObservacion("Plataforma Web");
            editCuentas.setIvaExcento('F');
            editCuentas.setServicio("CEX");
            editCuentas.setConfirmado('F');
            editCuentas.setAuditado('F');

            cuentasFacade.create(editCuentas);
            System.out.println("4. creado en Cuentas.....");
            //
            editCuentas = cuentasFacade.findByCodigo(idTurno.longValue());
            editCuentas.setCreadoPor(loginPersonal.getUsuario());
            cuentasFacade.edit(editCuentas);
            //
            listaTurnos = turnosCeFacade.ListByFechaConsultorio(selectFecha, selectConsultorio);

            btnAsignar();

        } catch (Exception e) {
            for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                System.out.println("t.getCause(): " + t.getCause());
                if (t.getMessage().contains("java.sql.SQLException: ORA-20998")) {

                    if (t.getMessage().contains("ORA-20334")) {
                        msgGrabar = " Se ha determinado que existe una excepcion para la atención de pacientes en el " + selectConsultorio.getCodigo();
                        System.out.println("msgBuscar " + msgGrabar);
                    } else {
                        msgGrabar = "Personal no autorizado a dar turnos para " + selectConsultorio.getCodigo();
                        System.out.println("msgBuscar " + msgGrabar);
                    }

                    break;
                }
            }
            System.out.println("error.......");
            msgGrabar = "Personal no autorizado a dar turnos para " + selectConsultorio.getCodigo();

        }

    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar Cal = Calendar.getInstance();
        fechaActual = dateFormat.format(Cal.getTime());
        try {
            selectFecha = dateFormat.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(AsignarTurnoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        diaActual = String.valueOf(Cal.get(Calendar.DAY_OF_WEEK));
        selectConsultorio = new Personal();
        flagBtnAsignar = true;
        flagClicEspec = false;
        flagBtnPago = true;

        loginPersonal = personalFacade.findByCodigo(codMedico);

    }
// </editor-fold>

    /**
     * Creates a new instance of AsignarTurnoReferidoJSFManagedBean
     */
    public AsignarTurnoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
