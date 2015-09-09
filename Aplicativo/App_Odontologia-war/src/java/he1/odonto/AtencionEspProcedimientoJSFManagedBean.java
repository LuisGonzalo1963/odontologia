/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OCoberturaIssfa;
import he1.odonto.entities.ODiagnostico;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OPcnDerivados;
import he1.odonto.entities.OPlanDxTerEdu;
import he1.odonto.entities.OProcedimientos;
import he1.odonto.entities.OvProcedEspecialidad;
import he1.odonto.sessions.OCoberturaIssfaFacade;
import he1.odonto.sessions.ODiagnosticoFacade;
import he1.odonto.sessions.OHistOdontoFacade;
import he1.odonto.sessions.OPcnDerivadosFacade;
import he1.odonto.sessions.OPlanDxTerEduFacade;
import he1.odonto.sessions.OProcedimientosFacade;
import he1.odonto.sessions.OvProcedEspecialidadFacade;
import he1.sis.entities.Cargos;
import he1.sis.entities.CgCodeControls;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.Departamentos;
import he1.sis.entities.DetallesDeExamenes;
import he1.sis.entities.DetallesDeExamenesPK;
import he1.sis.entities.DiagnosticosPaciente;
import he1.sis.entities.Enfermedades;
import he1.sis.entities.Especialidades;
import he1.sis.entities.EspecialidadesMedicos;
import he1.sis.entities.Examenes;
import he1.sis.entities.Personal;
import he1.sis.entities.ProcedimientosEspecialidad;
import he1.sis.entities.ProcedimientosMenores;
import he1.sis.entities.ProcedimientosMenoresPK;
import he1.sis.entities.ProcedimientosTarifarios;
import he1.sis.entities.SolicitudesDeExamenes;
import he1.sis.entities.TiposDeExamenes;
import he1.sis.entities.TurnosCe;

import he1.sis.sessions.CabeceraInsumosFacade;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CgCodeControlsFacade;
import he1.sis.sessions.CuentasFacade;
import he1.sis.sessions.DepartamentosFacade;
import he1.sis.sessions.DetallesDeExamenesFacade;
import he1.sis.sessions.DiagnosticosPacienteFacade;
import he1.sis.sessions.EnfermedadesFacade;
import he1.sis.sessions.EspecialidadesFacade;
import he1.sis.sessions.EspecialidadesMedicosFacade;
import he1.sis.sessions.ExamenesFacade;

import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.ProcedimientosEspecialidadFacade;
import he1.sis.sessions.ProcedimientosMenoresFacade;
import he1.sis.sessions.ProcedimientosTarifariosFacade;

import he1.sis.sessions.PromocionesPacientesFacade;
import he1.sis.sessions.SolicitudesDeExamenesFacade;
import he1.sis.sessions.TarifariosFacade;

import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "atencionEspProc")
@ViewScoped
public class AtencionEspProcedimientoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private CgCodeControlsFacade cgCodeControlsFacade;
    @EJB
    private TarifariosFacade tarifariosFacade;
    @EJB
    private OPlanDxTerEduFacade planDxTerEduFacade;
    @EJB
    private SolicitudesDeExamenesFacade solicitudesDeExamenesFacade;
    @EJB
    private ProcedimientosMenoresFacade procedimientosMenoresFacade;
    @EJB
    private OvProcedEspecialidadFacade vProcedEspecialidadFacade;

    @EJB
    private ProcedimientosEspecialidadFacade procedEspecFacade;

    @EJB
    private DepartamentosFacade departamentosFacade;
    @EJB
    private EspecialidadesFacade especialidadesFacade;

    @EJB
    private OProcedimientosFacade procedimientosFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private EnfermedadesFacade enfermedadesFacade;
    @EJB
    private OCoberturaIssfaFacade coberturaIssfaFacade;
    @EJB
    private CabeceraInsumosFacade cabeceraInsumosFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private DiagnosticosPacienteFacade diagnosticosPacienteFacade;
    @EJB
    private OHistOdontoFacade histOdontoFacade;
    @EJB
    private ODiagnosticoFacade diagnosticoFacade;
    @EJB
    private DiagnosticosPacienteFacade diagPacienteFacade;
    @EJB
    private OPcnDerivadosFacade pcnDerivadosFacade;
    @EJB
    private EspecialidadesMedicosFacade especialidadesMedicoFacade;
    @EJB
    private ProcedimientosTarifariosFacade procedimientosTarifariosFacade;
    @EJB
    private ExamenesFacade examenesFacade;
    @EJB
    private DetallesDeExamenesFacade detallesDeExamenesFacade;

    private TurnosCe sessionTurno;
    private OProcedimientos editProcedimientos;
    private OProcedimientos updateProcedimientos;
    private OCoberturaIssfa selectCoberturaIssfa;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;
    private Personal loginPersonal;
    private ODiagnostico editDiagnostico;
    private DiagnosticosPaciente editDiagPaciente;
    private DiagnosticosPaciente selectDiagPaciente;
    private OPcnDerivados selectPcnDerivados;
    private DiagnosticosPaciente selectDiagPacientes;
    private Especialidades selectEspecialidades;
    private Departamentos selectDepartamentos;
    private ProcedimientosEspecialidad selectProcEspec;
    private ProcedimientosMenores editProcedimientosMenores;
    private ProcedimientosMenores updateProcedimientosMenores;
    private ProcedimientosMenoresPK editProcedimientosMenoresPK;
    private CgCodeControls editCgCodeControls;
    private Cargos editCargos;
    private ProcedimientosTarifarios editProcedimientosTarifarios;
    private OPlanDxTerEdu editPlanDxTerEdu;
    private OHistOdonto selectHistOdonto;
    private SolicitudesDeExamenes editSolicitudesDeExamenes;
    private Examenes editExamenes;
    private DetallesDeExamenes editDetDeExamenes;
    private DetallesDeExamenesPK editDetDeExamenesPk;
    private OPlanDxTerEdu selectPlanDxTerEdu;

    private List<OProcedimientos> listaProcedimientos;
    private List<OProcedimientos> listProcedCobertura;
    private List<OProcedimientos> listaProcedRegistrados;
    private List<ProcedimientosEspecialidad> listProcEspec;
    private List<OvProcedEspecialidad> listVProcEspec;
    private List<OPlanDxTerEdu> listPlanDxTerEdu;
    private List<DiagnosticosPaciente> listDiagPaciente;

    private List<SelectItem> itemsListCargos;
    private List<SelectItem> itemsListEnfOdonto;
    private List<SelectItem> itemsListDiagPcn;
    private List<SelectItem> itemsListEspMedicos;
    private List<SelectItem> itemsListRayosX;

    private String codCargo;
    private String codCargoRx;
    private String prmPcn;
    private String fechaActual;
    private String fechaActualWs;
    private String nroAtenciones;
    private String restAtenciones;
    private String msgWebService;
    private String codDiag;
    private String codDiagWs;
    private String prcDescripcion;
    private String codEspMedico;
    private String buscarEnf;
    private String descripcionRx;

    private BigDecimal valCargo;
    private BigDecimal valCargoRx;
    private BigDecimal valTotal;
    private BigDecimal valTotalRx;
    private BigDecimal cantRx;
    private BigDecimal valTotIssfa;
    private BigDecimal valTotPcn;
    private BigDecimal valTotal2;
    private BigDecimal valCopago;
    private BigDecimal cantResto;
    private BigDecimal cantCopago;
    private BigDecimal edadPaciente;
    private BigDecimal porcDscto;

    private int cantAtenciones;
    private int cantProcedimientos;
    private int cantIssfa;
    private int cantPaciente;

    private boolean flagMsgWs;
    private boolean flagbtnReg;
    private boolean flagbtnGraba;
    private boolean flagPanelDatos;
    private boolean flagBtnGrabarRx;

    private final String codMedico;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">    
    public List<OProcedimientos> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setListaProcedimientos(List<OProcedimientos> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public List<DiagnosticosPaciente> getListDiagPaciente() {
        return listDiagPaciente;
    }

    public void setListDiagPaciente(List<DiagnosticosPaciente> listDiagPaciente) {
        this.listDiagPaciente = listDiagPaciente;
    }

    public String getDescripcionRx() {
        return descripcionRx;
    }

    public void setDescripcionRx(String descripcionRx) {
        this.descripcionRx = descripcionRx;
    }

    public String getBuscarEnf() {
        return buscarEnf;
    }

    public void setBuscarEnf(String buscarEnf) {
        this.buscarEnf = buscarEnf;
    }

    public List<OPlanDxTerEdu> getListPlanDxTerEdu() {
        return listPlanDxTerEdu;
    }

    public void setListPlanDxTerEdu(List<OPlanDxTerEdu> listPlanDxTerEdu) {
        this.listPlanDxTerEdu = listPlanDxTerEdu;
    }

    public OPlanDxTerEdu getEditPlanDxTerEdu() {
        return editPlanDxTerEdu;
    }

    public void setEditPlanDxTerEdu(OPlanDxTerEdu editPlanDxTerEdu) {
        this.editPlanDxTerEdu = editPlanDxTerEdu;
    }

    public BigDecimal getCantRx() {
        return cantRx;
    }

    public void setCantRx(BigDecimal cantRx) {
        this.cantRx = cantRx;
    }

    public boolean isFlagBtnGrabarRx() {
        return flagBtnGrabarRx;
    }

    public void setFlagBtnGrabarRx(boolean flagBtnGrabarRx) {
        this.flagBtnGrabarRx = flagBtnGrabarRx;
    }

    public BigDecimal getValTotalRx() {
        return valTotalRx;
    }

    public void setValTotalRx(BigDecimal valTotalRx) {
        this.valTotalRx = valTotalRx;
    }

    public BigDecimal getValCargoRx() {
        return valCargoRx;
    }

    public void setValCargoRx(BigDecimal valCargoRx) {
        this.valCargoRx = valCargoRx;
    }

    public String getCodCargoRx() {
        return codCargoRx;
    }

    public void setCodCargoRx(String codCargoRx) {
        this.codCargoRx = codCargoRx;
    }

    public List<SelectItem> getItemsListRayosX() {
        return itemsListRayosX;
    }

    public void setItemsListRayosX(List<SelectItem> itemsListRayosX) {
        this.itemsListRayosX = itemsListRayosX;
    }

    public int getCantIssfa() {
        return cantIssfa;
    }

    public void setCantIssfa(int cantIssfa) {
        this.cantIssfa = cantIssfa;
    }

    public int getCantPaciente() {
        return cantPaciente;
    }

    public void setCantPaciente(int cantPaciente) {
        this.cantPaciente = cantPaciente;
    }

    public boolean isFlagPanelDatos() {
        return flagPanelDatos;
    }

    public void setFlagPanelDatos(boolean flagPanelDatos) {
        this.flagPanelDatos = flagPanelDatos;
    }

    public String getCodEspMedico() {
        return codEspMedico;
    }

    public void setCodEspMedico(String codEspMedico) {
        this.codEspMedico = codEspMedico;
    }

    public List<SelectItem> getItemsListEspMedicos() {
        return itemsListEspMedicos;
    }

    public void setItemsListEspMedicos(List<SelectItem> itemsListEspMedicos) {
        this.itemsListEspMedicos = itemsListEspMedicos;
    }

    public String getPrcDescripcion() {
        return prcDescripcion;
    }

    public void setPrcDescripcion(String prcDescripcion) {
        this.prcDescripcion = prcDescripcion;
    }

    public ProcedimientosEspecialidad getSelectProcEspec() {
        return selectProcEspec;
    }

    public void setSelectProcEspec(ProcedimientosEspecialidad selectProcEspec) {
        this.selectProcEspec = selectProcEspec;
    }

    public List<ProcedimientosEspecialidad> getListProcEspec() {
        return listProcEspec;
    }

    public void setListProcEspec(List<ProcedimientosEspecialidad> listProcEspec) {
        this.listProcEspec = listProcEspec;
    }

    public TurnosCe getSessionTurno() {
        return sessionTurno;
    }

    public void setSessionTurno(TurnosCe sessionTurno) {
        this.sessionTurno = sessionTurno;
    }

    public BigDecimal getCantResto() {
        return cantResto;
    }

    public void setCantResto(BigDecimal cantResto) {
        this.cantResto = cantResto;
    }

    public BigDecimal getValTotal() {
        return valTotal;
    }

    public void setValTotal(BigDecimal valTotal) {
        this.valTotal = valTotal;
    }

    public OPcnDerivados getSelectPcnDerivados() {
        return selectPcnDerivados;
    }

    public void setSelectPcnDerivados(OPcnDerivados selectPcnDerivados) {
        this.selectPcnDerivados = selectPcnDerivados;
    }

    public List<OProcedimientos> getListaProcedRegistrados() {
        return listaProcedRegistrados;
    }

    public void setListaProcedRegistrados(List<OProcedimientos> listaProcedRegistrados) {
        this.listaProcedRegistrados = listaProcedRegistrados;
    }

    public boolean isFlagbtnGraba() {
        return flagbtnGraba;
    }

    public void setFlagbtnGraba(boolean flagbtnGraba) {
        this.flagbtnGraba = flagbtnGraba;
    }

    public boolean isFlagbtnReg() {
        return flagbtnReg;
    }

    public void setFlagbtnReg(boolean flagbtnReg) {
        this.flagbtnReg = flagbtnReg;
    }

    public List<SelectItem> getItemsListDiagPcn() {
        return itemsListDiagPcn;
    }

    public void setItemsListDiagPcn(List<SelectItem> itemsListDiagPcn) {
        this.itemsListDiagPcn = itemsListDiagPcn;
    }

    public int getCantProcedimientos() {
        return cantProcedimientos;
    }

    public void setCantProcedimientos(int cantProcedimientos) {
        this.cantProcedimientos = cantProcedimientos;
    }

    public boolean isFlagMsgWs() {
        return flagMsgWs;
    }

    public void setFlagMsgWs(boolean flagMsgWs) {
        this.flagMsgWs = flagMsgWs;
    }

    public String getNroAtenciones() {
        return nroAtenciones;
    }

    public void setNroAtenciones(String nroAtenciones) {
        this.nroAtenciones = nroAtenciones;
    }

    public OCoberturaIssfa getSelectCoberturaIssfa() {
        return selectCoberturaIssfa;
    }

    public void setSelectCoberturaIssfa(OCoberturaIssfa selectCoberturaIssfa) {
        this.selectCoberturaIssfa = selectCoberturaIssfa;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public ODiagnostico getEditDiagnostico() {
        return editDiagnostico;
    }

    public void setEditDiagnostico(ODiagnostico editDiagnostico) {
        this.editDiagnostico = editDiagnostico;
    }

    public List<SelectItem> getItemsListCargos() {
        return itemsListCargos;
    }

    public void setItemsListCargos(List<SelectItem> itemsListCargos) {
        this.itemsListCargos = itemsListCargos;
    }

    public String getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(String codCargo) {
        this.codCargo = codCargo;
    }

    public String getCodDiag() {
        return codDiag;
    }

    public void setCodDiag(String codDiag) {
        this.codDiag = codDiag;
    }

    public BigDecimal getValCargo() {
        return valCargo;
    }

    public void setValCargo(BigDecimal valCargo) {
        this.valCargo = valCargo;
    }

    public List<SelectItem> getItemsListEnfOdonto() {
        return itemsListEnfOdonto;
    }

    public void setItemsListEnfOdonto(List<SelectItem> itemsListEnfOdonto) {
        this.itemsListEnfOdonto = itemsListEnfOdonto;
    }

    public String getMsgWebService() {
        return msgWebService;
    }

    public void setMsgWebService(String msgWebService) {
        this.msgWebService = msgWebService;
    }

    public String getPrmPcn() {
        return prmPcn;
    }

    public void setPrmPcn(String prmPcn) {
        this.prmPcn = prmPcn;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsCargos ">
    public void recuperaItemsCargos(String codEsp) {
        listVProcEspec = new ArrayList<>();
        listVProcEspec = vProcedEspecialidadFacade.listByEspecialidad(codEsp);
        if (listVProcEspec != null) {
            this.itemsListCargos = new ArrayList<>();
            for (OvProcedEspecialidad pe : listVProcEspec) {
                this.itemsListCargos.add(new SelectItem(pe.getPrchspCodigo(), pe.getPrchspCodigo() + " " + pe.getDescripcion()));
            }
        }
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsDiagPcn ">
    public void recuperaItemsDiagPcn() {
        List<DiagnosticosPaciente> ldp = new ArrayList<>();
        ldp = diagnosticosPacienteFacade.listDiagnosticoPacientes(sessionTurno.getPacientes());
        if (ldp != null) {
            this.itemsListDiagPcn = new ArrayList<>();
            for (DiagnosticosPaciente dp : ldp) {
                this.itemsListDiagPcn.add(new SelectItem(dp.getDgnpcnId(), dp.getEnfermedades().getCodigo() + " " + dp.getEnfermedades().getEnfermedad()));
            }
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsEspecialidadesMedicos ">
    public void recuperaItemsEspMedico() {
        List<EspecialidadesMedicos> lem;
        System.out.println("login " + loginPersonal);
        lem = especialidadesMedicoFacade.listEspecialidadesMedicos(loginPersonal);
        System.out.println("list " + lem.size());
        if (lem != null) {
            this.itemsListEspMedicos = new ArrayList<>();
            for (EspecialidadesMedicos ep : lem) {
                this.itemsListEspMedicos.add(new SelectItem(ep.getEspecialidades().getCodigo(), ep.getEspecialidades().getEspecialidad()));
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsEnfermedad ">
    public void recuperaItemsEnfermedad() {
        System.out.println("codTipoEnf " + buscarEnf);
        //itemsListEnfOdonto.clear();
        List<Enfermedades> listEnf = new ArrayList<>();
        if (buscarEnf.toUpperCase().substring(0, 1).equalsIgnoreCase("K")) {
            listEnf = enfermedadesFacade.itemsEnfermByCodigo(buscarEnf.toUpperCase() + "%");
        } else {
            listEnf = enfermedadesFacade.itemsEnfermByNombre("%" + buscarEnf.toUpperCase() + "%");
        }
        System.out.println("listEnf " + listEnf.size());
        if (listEnf != null) {
            this.itemsListEnfOdonto = new ArrayList<>();
            for (Enfermedades enf : listEnf) {
                this.itemsListEnfOdonto.add(new SelectItem(enf.getCodigo(), enf.getCodigo() + " " + enf.getEnfermedad()));
            }
        }

    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="/**Recuperar items ">    
    public void recuperarItems() {

        recuperaItemsDiagPcn();
        recuperaItemsEspMedico();

        recuperaItemsRayosX();

        //selectPcnDerivados = pcnDerivadosFacade.findByTurno(sessionTurno);
    }

    public void recuperaItemsRayosX() {
        List<Cargos> lc = new ArrayList<>();
        lc = cargosFacade.listAllRx();
        if (lc != null) {
            this.itemsListRayosX = new ArrayList<>();
            for (Cargos car : lc) {
                this.itemsListRayosX.add(new SelectItem(car.getCargosPK().getCodigo(), car.getCargosPK().getCodigo() + " " + car.getDescripcion()));
            }
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeRxPr">
    public void selectedAjaxChangeRxPr() {
        cantRx = null;
        valTotalRx = null;
        //msgWebService = callWebServiceConsulta(sessionTurno.getPacientes().getIdIssfa(), fechaActualWs, codDiagWs, codCargoRx);
        System.out.println("msgWebService " + msgWebService);
        valCargoRx = cargosFacade.findByCod(codCargoRx);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeCantRx">
    public void selectedAjaxChangeCantRx() {
        valTotalRx = cantRx.multiply(valCargoRx);
        if (cantRx.compareTo(BigDecimal.ZERO) > 0) {
            flagBtnGrabarRx = false;
        } else {
            flagBtnGrabarRx = true;
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/**btnActionListener ">
    public void btnActionListener(ValueChangeEvent vce) {
        editDiagnostico = new ODiagnostico();
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="/**btnActionListenerPrc ">    
    public void selectedAjaxChangeBuscarEnf() {

        recuperaItemsEnfermedad();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/**selectedAjaxChange">
    public void selectedAjaxChangeEspec() {
        System.out.println("codEspMedico " + codEspMedico);
        recuperaItemsCargos(codEspMedico);
        if (listVProcEspec.size() == 0) {
            flagPanelDatos = true;
        } else {
            flagPanelDatos = false;
        }
    }

    public void selectedAjaxChangeDx() {
        selectDiagPaciente = diagPacienteFacade.find(Long.valueOf(codDiag));
        codDiagWs = selectDiagPaciente.getEnfermedades().getCodigo();
        if (codCargo != null) {
            procesoVerificar();

        }

    }

    public void selectedAjaxChangeCr() {
        System.out.println("codCargo " + codCargo);
        editCargos = cargosFacade.findByCodCrg(codCargo);
        System.out.println("editCargos " + editCargos);
        if (editCargos == null) {
            editProcedimientosTarifarios = procedimientosTarifariosFacade.findByCodCrg(codCargo);
            if (editProcedimientosTarifarios == null) {
                valCargo = new BigDecimal(0);
            } else {
                editCargos = cargosFacade.findByCodCrg(editProcedimientosTarifarios.getProcedimientosTarifariosPK().getTrfCodigoItem());
                if (editCargos == null) {
                    valCargo = new BigDecimal(0);
                } else {
                    valCargo = editCargos.getCosto();
                }
            }

        } else {
            valCargo = editCargos.getCosto();
        }
        System.out.println("valCargo " + valCargo);
        if (codDiag != null) {
            procesoVerificar();
        } else {
            ponerMensajeInfo("ATENCIÓN", "Seleccione un Diagnóstico!!!");
        }
    }

    public void procesoVerificar() {
        if (sessionTurno.getPacientes().getIdIssfa() != null) {
            nroAtenciones = "0";
            selectCoberturaIssfa = coberturaIssfaFacade.findByCodCrg(codCargo);

            selectDiagPacientes = diagPacienteFacade.find(Long.valueOf(codDiag));
            codDiagWs = selectDiagPacientes.getEnfermedades().getCodigo().replace(".", "");
            //aqui inicia el webService de odontologia
////////            msgWebService = callWebServiceConsulta(sessionTurno.getPacientes().getIdIssfa(), fechaActualWs, codDiagWs, codCargo);
////////            System.out.println("msgWebService "+msgWebService);
////////            if (msgWebService.length() == 1) {
////////                nroAtenciones = msgWebService;
////////                if (nroAtenciones.equalsIgnoreCase("0")) {
////////                    msgWebService = null;
////////                } else {
////////                    //Valida con el registro de tabla local
////////                    if (existeCodCie(selectCoberturaIssfa.getCbiCodigoCie(), selectDiagPaciente.getEnfermedades().getCodigo() + ",") > 0) {
////////                        if (selectCoberturaIssfa.getCbi3erNivel().equalsIgnoreCase("N")) {
////////                            if (selectPcnDerivados.getPcdDerivado().equalsIgnoreCase("N")) {
////////                                nroAtenciones = "0";
////////                            }
////////
////////                        }
////////////                    listProcedCobertura = procedimientosFacade.listByCobIssfa(selectCoberturaIssfa, sessionTurno.getPacientes());////
////////////                    System.out.println("list " + listProcedCobertura.size());
////////////                    if (listProcedCobertura.isEmpty()) {
////////////
////////////                    } else {
////////////                        BigInteger cant = new BigInteger("0");
////////////                        int i = 0;
////////////                        while (true) {
////////////                            if (i == listProcedCobertura.size()) {
////////////                                break;
////////////                            }
////////////                            cant = cant.add(listProcedCobertura.get(i).getPrcCantidad());
////////////                            i++;
////////////                        }
////////////                        System.out.println("cant " + cant);
////////////                        BigInteger nroA, resA;
////////////                        nroA = new BigInteger(nroAtenciones);
////////////                        resA = nroA.subtract(cant);
////////////                        if (resA.intValue() < 0) {
////////////                            nroAtenciones = "0";
////////////                        } else {
////////////                            nroAtenciones = String.valueOf(resA);
////////////                        }
////////////
////////////                    }
////////////
////////                    }
////////                }
////////            } else {
////////                nroAtenciones = "0";
//////////                msgWebService=null;
//////////                selectPcnDerivados=null;
//////////                selectCoberturaIssfa=null;
//////////                valCargo=null;
////////                
////////            }
            //fin del validación del webService
        } else {
            nroAtenciones = "0";
            msgWebService = "EL PACIENTE CIVIL NO TIENE COBERTURA";
        }
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/**AjaxCantidad ">
    public void selectedAjaxChangeCant() {
        valTotal = valCargo.multiply(new BigDecimal(cantProcedimientos));
        valTotal2 = valTotal;
        cantResto = new BigDecimal(cantProcedimientos).subtract(new BigDecimal(nroAtenciones));
        if (cantResto.compareTo(BigDecimal.ZERO) > 0) {
            //cantCopago = new BigDecimal(prcCantidad).subtract(cantResto);
            cantCopago = cantResto; ///VERIFICARRRRRRRRRRRRRRRRRRRRRRR
            valCopago = cantCopago.multiply(valCargo);
            valTotal = cantResto.multiply(valCargo);

        } else if (cantResto.compareTo(BigDecimal.ZERO) <= 0) {
            valTotal = BigDecimal.ZERO;
            valCopago = new BigDecimal(cantProcedimientos).multiply(valCargo);
        }
        flagbtnReg = cantProcedimientos == 0;
        ///
        procesarDatos();
    }
    // </editor-fold>     

    // <editor-fold defaultstate="collapsed" desc="/**btnGrabarDiagPcn ">
    public void btnGrabarDiagPcn() {
        if (editDiagnostico.getDgtId() == null) {

            try {
                //graba en DiagnosticoPacientes del SGHE
                editDiagPaciente = new DiagnosticosPaciente();
                editDiagPaciente.setPacientes(sessionTurno.getPacientes());
                editDiagPaciente.setEnfermedades(new Enfermedades(codDiag));
                editDiagPaciente.setFechaInicio(new Date());
                editDiagPaciente.setTipo(editDiagnostico.getDgtTipo());
                editDiagPaciente.setPersonal(loginPersonal);
                editDiagPaciente.setEstadoValidez('V');
                editDiagPaciente.setObservacion("OdontoWeb");
                diagPacienteFacade.create(editDiagPaciente);
                recuperaItemsDiagPcn();
                System.out.println("creado Diag. en SGH");

                editDiagnostico.setDgtId(BigDecimal.ZERO);
                editDiagnostico.setOHistOdonto(histOdontoFacade.findByPaciente(sessionTurno.getPacientes()));
                editDiagnostico.setDgtFechaApertura(new Date());
                editDiagnostico.setDgtNumero(BigInteger.ONE);
                editDiagnostico.setDgtTipo(editDiagnostico.getDgtTipo());
                editDiagnostico.setEnfermedades(new Enfermedades(codDiag));
                editDiagnostico.setPersonal(loginPersonal);

                diagnosticoFacade.create(editDiagnostico);
                init();
                ponerMensajeInfo("ATENCIÓN", "Información guardada");
            } catch (Exception e) {
                for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                    System.out.println("t.getCause() " + t.getCause());
                    if (t.getMessage().contains("java.sql.SQLException: ORA-20998")) {
                        System.out.println("Código no autorizado... ");
                        ponerMensajeInfo("ATENCIÓN", "Código no autorizado... ");
                        break;
                    }
                }
                System.out.println("error.......");
            }

        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/**btnGrabarProcedimiento ">
    public void procesarDatos() {
        if (sessionTurno.getPacientes().getIdIssfa() != null) {
            int resA;
            cantAtenciones = Integer.parseInt(nroAtenciones);
            resA = cantAtenciones - cantProcedimientos;
            if (resA < 0) {
                restAtenciones = "0";
                cantPaciente = cantProcedimientos - cantAtenciones;
                cantIssfa = cantProcedimientos - cantPaciente;
            }
            if (resA == 0) {
                restAtenciones = String.valueOf(resA);
                cantPaciente = cantAtenciones - cantProcedimientos;
                cantIssfa = cantAtenciones - cantPaciente;
            }
            if (resA > 0) {
                restAtenciones = String.valueOf(resA);
                cantIssfa = cantAtenciones - cantProcedimientos;
                cantPaciente = cantIssfa - cantProcedimientos;
            }

            System.out.println("rest..." + restAtenciones);
        } else {
            restAtenciones = nroAtenciones;
            cantIssfa = 0;
            cantPaciente = cantProcedimientos;
        }
        valTotIssfa = valCargo.multiply(new BigDecimal(cantIssfa));
        valTotPcn = valCargo.multiply(new BigDecimal(cantPaciente));
        System.out.println("valorIssfa " + valTotIssfa);
        System.out.println("valorPcn " + valTotPcn);
    }

    public void btnGrabarProcedimiento() {
        //1. si cantPaciente es mayor a cero, el Paciente tiene q pagar
        if (cantPaciente > 0) {
            editProcedimientos = new OProcedimientos();
            editProcedimientos.setPrcId(BigDecimal.ONE);
            editProcedimientos.setPacientes(sessionTurno.getPacientes());
            editProcedimientos.setPrmCodigo(promCodPcn(sessionTurno.getPacientes()));
            editProcedimientos.setPrcDiagnostico(codDiag);
            editProcedimientos.setPrcProcedimiento(editCargos.getCargosPK().getCodigo());
            editProcedimientos.setPrcFecha(new Date());
            editProcedimientos.setPrcTotAtenciones(BigInteger.valueOf(Long.valueOf(nroAtenciones))); ///***
            editProcedimientos.setPrcRestAtenciones(BigInteger.valueOf(Long.valueOf(restAtenciones)));
            editProcedimientos.setPrcCantidad(BigInteger.valueOf(cantPaciente));
            editProcedimientos.setPrcValor(valCargo);
            editProcedimientos.setPrcCreadoPor("ODONTOWEB");//*******
            editProcedimientos.setPrsCodigo(loginPersonal.getCodigo());
            editProcedimientos.setoCoberturaIssfa(selectCoberturaIssfa);
            editProcedimientos.setPrcObservaciones(msgWebService);
            editProcedimientos.setPrcDescripcion(prcDescripcion);
            editProcedimientos.setPrcEstado("R");
            editProcedimientos.setPrcTipo("P");

            procedimientosFacade.create(editProcedimientos);
        }
        //2. si cantIssfa es mayor a cero, el Paciente no tiene q pagar
        if (cantIssfa > 0) {
            editProcedimientos = new OProcedimientos();
            editProcedimientos.setPrcId(BigDecimal.ONE);
            editProcedimientos.setPacientes(sessionTurno.getPacientes());
            editProcedimientos.setPrmCodigo(promCodPcn(sessionTurno.getPacientes()));
            editProcedimientos.setPrcDiagnostico(codDiag);
            editProcedimientos.setPrcProcedimiento(editCargos.getCargosPK().getCodigo());
            editProcedimientos.setPrcFecha(new Date());
            editProcedimientos.setPrcTotAtenciones(BigInteger.valueOf(Long.valueOf(nroAtenciones))); ///***
            editProcedimientos.setPrcRestAtenciones(BigInteger.valueOf(Long.valueOf(restAtenciones)));
            editProcedimientos.setPrcCantidad(BigInteger.valueOf(cantIssfa));
            editProcedimientos.setPrcValor(valCargo);
            editProcedimientos.setPrcCreadoPor("ODONTOWEB");//*******
            editProcedimientos.setPrsCodigo(loginPersonal.getCodigo());
            editProcedimientos.setoCoberturaIssfa(selectCoberturaIssfa);
            editProcedimientos.setPrcObservaciones(msgWebService);
            editProcedimientos.setPrcDescripcion(prcDescripcion);
            editProcedimientos.setPrcEstado("R");
            editProcedimientos.setPrcTipo("I");

            procedimientosFacade.create(editProcedimientos);
        }

        listaProcedimientos = procedimientosFacade.listByPaciente(sessionTurno.getPacientes());
        //
        codCargo = null;
        codDiag = null;
        msgWebService = null;
        nroAtenciones = null;
        valCargo = null;
        valTotal = null;
        prcDescripcion = " ";
        cantProcedimientos = 0;
        flagbtnReg = true;
        flagbtnGraba = false;
        ponerMensajeInfo("ATENCION", "Procedimiento grabado...");
        System.out.println("proc grabado.........");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/**btnGrabarCuentas ">    
    public void btnGrabarCuentas() {

        //1 Grabar en procedimientos menores
        System.out.println("grabar..........");
        editProcedimientosMenoresPK = new ProcedimientosMenoresPK();
        editProcedimientosMenoresPK.setNumero((int) cgCodeControlsFacade.nextValuePrcMnr().getCcNextValue());
        editProcedimientosMenoresPK.setPcnNumeroHc(sessionTurno.getPacientes().getNumeroHc());

        editProcedimientosMenores = new ProcedimientosMenores();
        editProcedimientosMenores.setProcedimientosMenoresPK(editProcedimientosMenoresPK);
        editProcedimientosMenores.setPacientes(sessionTurno.getPacientes());
        //editProcedimientosMenores.setHjaevlNumero(procedimientosFacade.maxOprcId(sessionTurno.getPacientes()).toBigInteger());
        editProcedimientosMenores.setHjaevlNumero(BigInteger.valueOf(1810100));
        editProcedimientosMenores.setDiagnosticosPaciente(new DiagnosticosPaciente(Long.parseLong(listaProcedimientos.get(0).getPrcDiagnostico())));
        editProcedimientosMenores.setDescripcion(listaProcedimientos.get(0).getPrcDescripcion());
        editProcedimientosMenores.setFecha(new Date());
        editProcedimientosMenores.setPersonal1(loginPersonal);
        editProcedimientosMenores.setAuditado("V");
        editProcedimientosMenores.setEstado("C");
        procedimientosMenoresFacade.create(editProcedimientosMenores);

        System.out.println("1. creado ProcedimientosMenores....");

        //2. grabar en el tabla cuentas
        int i = 0;
        while (true) {
            if (i == listaProcedimientos.size()) {
                break;
            }
            //
            Long nexValue;
            nexValue = cgCodeControlsFacade.nextValuePrcMnr().getCcNextValue();
            nexValue = nexValue + 1;
            editCgCodeControls = cgCodeControlsFacade.find(cgCodeControlsFacade.nextValuePrcMnr().getCcDomain());
            editCgCodeControls.setCcNextValue(nexValue);
            cgCodeControlsFacade.edit(editCgCodeControls);
            //
            editCuentasPk = new CuentasPK();
            editCuentasPk.setDocumento("G");
            editCuentasPk.setNumero(cgCodeControlsFacade.nextValuePrcMnr().getCcNextValue());
            editCuentasPk.setDetalle(1); //1 para turno

            editCuentas = new Cuentas();
            editCuentas.setCuentasPK(editCuentasPk);
            editCuentas.setDprAraCodigo("C");
            editCuentas.setDprCodigo("Z");
            editCuentas.setDprAraCodigoPertenecienteA("O");
            editCuentas.setDprCodigoPertenecienteA("E");
            editCuentas.setPacientes(sessionTurno.getPacientes());
            editCuentas.setEstado("PND");
            editCuentas.setFecha(new Date());
            editCuentas.setCantidad(new BigDecimal(listaProcedimientos.get(i).getPrcCantidad()));
            editCuentas.setMonedaDeTrabajo("DLAR");
            editCuentas.setValor(listaProcedimientos.get(i).getPrcValor());/////*******
            editCuentas.setCrgTipo('P');
            editCuentas.setCrgCodigo(listaProcedimientos.get(i).getPrcProcedimiento());
            if (listaProcedimientos.get(i).getPrcTipo().equalsIgnoreCase("I")) {
                editCuentas.setPorcentajePromocion(BigDecimal.ZERO);
            } else {
                editCuentas.setPorcentajePromocion(BigDecimal.ONE);
            }
            editCuentas.setDescuentoOtorgado(BigDecimal.ZERO);
            editCuentas.setIva(BigDecimal.ZERO);
            editCuentas.setCreadoPor(loginPersonal.getUsuario()); //usuario logueado*******
            if (listaProcedimientos.get(i).getPrcTipo().equalsIgnoreCase("I")) {
                editCuentas.setPrmCodigo(listaProcedimientos.get(i).getPrmCodigo());
            } else {
                editCuentas.setPrmCodigo("01");
            }
            //editCuentas.setCbcinsNumero(cabeceraInsumosFacade.maxCabInsumosId(sessionTurno.getPacientes()).intValue());
            editCuentas.setValore(BigDecimal.ZERO);
            editCuentas.setIvae(BigDecimal.valueOf(0, 1));
            //editCuentas.setObservacion("HE1 Honorarios Médicos");
            editCuentas.setObservacion(listaProcedimientos.get(0).getPrcDescripcion());
            editCuentas.setIvaExcento('F');
            editCuentas.setServicio("CEX");
            if (listaProcedimientos.get(i).getPrcTipo().equalsIgnoreCase("I")) {
                editCuentas.setSeguro('X');
            } else {
                editCuentas.setSeguro('F');
            }

            editCuentas.setConfirmado('F');
            editCuentas.setAuditado('F');
            editCuentas.setUvr(tarifariosFacade.findByCodCargo(listaProcedimientos.get(0).getPrcProcedimiento()).getUvr3());
            editCuentas.setPrc(tarifariosFacade.findByCodCargo(listaProcedimientos.get(0).getPrcProcedimiento()).getPrc3());

            cuentasFacade.create(editCuentas);
            System.out.println("creado en Cuentas.....");

            updateProcedimientos = listaProcedimientos.get(i);
            updateProcedimientos.setPrcEstado("P");
            procedimientosFacade.edit(updateProcedimientos);
            //

            i++;
        }

        //
        listaProcedimientos = procedimientosFacade.listByPaciente(sessionTurno.getPacientes());
        listaProcedRegistrados = procedimientosFacade.listProcedByPaciente(sessionTurno.getPacientes());
        flagbtnGraba = true;
        ponerMensajeInfo("ATENCION", "Procedimiento grabado...");
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="/**btnGrabar Pedido Rx ">    
    public void btnGrabarRx() {

        editPlanDxTerEdu = new OPlanDxTerEdu();
        if (editPlanDxTerEdu != null) {
            editPlanDxTerEdu.setPdxId(BigDecimal.ZERO);
            editPlanDxTerEdu.setOHistOdonto(selectHistOdonto);
            editPlanDxTerEdu.setPdxTipo(new BigInteger("3"));
            editPlanDxTerEdu.setPdxDescripcion(descripcionRx);
            editPlanDxTerEdu.setPersonal(loginPersonal);
            editPlanDxTerEdu.setPdxCantidad(cantRx);
            editPlanDxTerEdu.setPdxProced(Integer.parseInt(codCargoRx));
            editPlanDxTerEdu.setPdxDiagnostico(selectDiagPaciente.getEnfermedades().getCodigo());
            editPlanDxTerEdu.setPdxRestante(nroAtenciones);

            planDxTerEduFacade.create(editPlanDxTerEdu);
            System.out.println("1. grabado PlanDxTerEduc...");

            listPlanDxTerEdu = planDxTerEduFacade.listByHistOdon(selectHistOdonto);

            //graba em SOLICITUD_DE_EXAMENES
            editSolicitudesDeExamenes = new SolicitudesDeExamenes();
            //editSolicitudesDeExamenes.setNumero(solicitudesDeExamenesFacade.maxDiagId() + 1);
            editSolicitudesDeExamenes.setDprAraCodigo("C");
            editSolicitudesDeExamenes.setDprCodigo("Z");
            editSolicitudesDeExamenes.setPacientes(selectHistOdonto.getPacientes());
            editSolicitudesDeExamenes.setEstado("C");
            editSolicitudesDeExamenes.setFechaDeCreacion(new Date());
            editSolicitudesDeExamenes.setMedico(loginPersonal.getApellidos() + " " + loginPersonal.getNombres());
            editSolicitudesDeExamenes.setPersonal(loginPersonal);
            editSolicitudesDeExamenes.setFechaDePeticion(deStringADate(fechaActual));
            editSolicitudesDeExamenes.setMdcRctId(loginPersonal.getCodigo());
            solicitudesDeExamenesFacade.create(editSolicitudesDeExamenes);
            System.out.println("2. grabado SOL DE EXAM..");

            //graba en examenes
            editExamenes = new Examenes();
            //editExamenes.setNumero(examenesFacade.maxExamId() + 1);
            editExamenes.setPacientes(selectHistOdonto.getPacientes());
            editExamenes.setPersonal(loginPersonal);
            editExamenes.setSolicitudesDeExamenes(solicitudesDeExamenesFacade.listByPaciente(selectHistOdonto.getPacientes(), loginPersonal).get(0));
            editExamenes.setTiposDeExamenes(new TiposDeExamenes("OT"));
            editExamenes.setEstadoDeExamen("A");
            editExamenes.setDiagnosticosPaciente(selectDiagPaciente);
            editExamenes.setDatosClinicos(editPlanDxTerEdu.getPdxDescripcion());
            editExamenes.setTomado('F');
            examenesFacade.create(editExamenes);
            System.out.println("3. grabado Exam...");

            //graba en detalles de examenes
            editDetDeExamenesPk = new DetallesDeExamenesPK();
            editDetDeExamenesPk.setExmNumero(examenesFacade.listByPaciente(selectHistOdonto.getPacientes(), loginPersonal).get(0).getNumero());
            editDetDeExamenesPk.setVrbexmCodigo(Integer.parseInt(codCargoRx));
            editDetDeExamenes = new DetallesDeExamenes();
            editDetDeExamenes.setDetallesDeExamenesPK(editDetDeExamenesPk);
            editDetDeExamenes.setEstadoDeActividad("A");
            editDetDeExamenes.setTomado('F');
            detallesDeExamenesFacade.create(editDetDeExamenes);
            System.out.println("4. grabado detalle de examenes....");

            //graba en cuentas
            editCuentasPk = new CuentasPK();
            editCuentasPk.setDocumento("E");
            editCuentasPk.setNumero(examenesFacade.listByPaciente(selectHistOdonto.getPacientes(), loginPersonal).get(0).getNumero());
            System.out.println("codCargoRx " + codCargoRx);
            editCuentasPk.setDetalle(Integer.parseInt(codCargoRx));

            editCuentas = new Cuentas();
            editCuentas.setCuentasPK(editCuentasPk);

            editCuentas.setDprAraCodigo("C");
            editCuentas.setDprAraCodigoPertenecienteA("O");
            editCuentas.setPacientes(selectHistOdonto.getPacientes());
            editCuentas.setDprCodigo("Z");
            editCuentas.setDprCodigoPertenecienteA("P");
            editCuentas.setEstado("PND");
            editCuentas.setFecha(new Date());
            editCuentas.setCantidad(cantRx);
            editCuentas.setMonedaDeTrabajo("SCRE");
            editCuentas.setValor(valCargoRx);/////*******
            editCuentas.setCrgTipo('P');
            editCuentas.setCrgCodigo(codCargoRx);
            editCuentas.setPorcentajePromocion(BigDecimal.ONE);
            editCuentas.setDescuentoOtorgado(BigDecimal.ZERO);
            editCuentas.setIva(BigDecimal.ZERO);
            editCuentas.setCreadoPor(loginPersonal.getUsuario());
            editCuentas.setPrmCodigo(promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(selectHistOdonto.getPacientes())).getPromociones().getCodigo());
            editCuentas.setValore(BigDecimal.ZERO);
            editCuentas.setIvae(BigDecimal.valueOf(0, 1));
            editCuentas.setObservacion("Plataforma Web");
            editCuentas.setIvaExcento('F');
            editCuentas.setServicio("CEX");
            editCuentas.setSeguro('F');
            editCuentas.setConfirmado('F');
            editCuentas.setAuditado('F');
            cuentasFacade.create(editCuentas);
            System.out.println("5. creado en Cuentas.....");
            //
            ponerMensajeInfo("ATENCIÓN", "Información guardada");

        } else if (selectPlanDxTerEdu.getPdxId() != null) {
            planDxTerEduFacade.edit(selectPlanDxTerEdu);
            ponerMensajeInfo("ATENCIÓN", "Información guardada");
        }
    }

    public void btnGrabarDel() {
        planDxTerEduFacade.remove(selectPlanDxTerEdu);

        listPlanDxTerEdu = planDxTerEduFacade.listByHistOdon(selectHistOdonto);

        ponerMensajeInfo("ATENCIÓN", "Plan de Diagnóstico eliminado...");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        Calendar Cal = Calendar.getInstance();
        fechaActual = Cal.get(Calendar.DATE) + "-" + (Cal.get(Calendar.MONTH) + 1) + "-" + Cal.get(Calendar.YEAR);
        fechaActualWs = Cal.get(Calendar.DATE) + "/" + (Cal.get(Calendar.MONTH) + 1) + "/" + Cal.get(Calendar.YEAR);
//        selectEspecialidades = especialidadesFacade.find(sessionTurno.getEspecialidades().getCodigo());
//        selectDepartamentos = departamentosFacade.findByNombre(selectEspecialidades.getEspecialidad());
        listDiagPaciente = diagPacienteFacade.listByDiagPcn(sessionTurno.getPacientes());
        selectHistOdonto = histOdontoFacade.findByPaciente(sessionTurno.getPacientes());
        listaProcedimientos = procedimientosFacade.listByPaciente(sessionTurno.getPacientes());
        flagbtnGraba = listaProcedimientos.isEmpty();
        cantResto = new BigDecimal(0);
        listaProcedRegistrados = procedimientosFacade.listProcedByPaciente(sessionTurno.getPacientes());
        prmPcn = promPcn(sessionTurno.getPacientes());
        edadPaciente = edadPaciente(sessionTurno.getPacientes());

        flagMsgWs = true;
        flagbtnReg = true;
        if (codMedico != null) {
            loginPersonal = personalFacade.findByCodigo(codMedico);

        }
        recuperarItems();
        flagPanelDatos = true;
    }

    // </editor-fold>    
    /**
     * Creates a new instance of AtencionEspProcedimientoJSFManagedBean
     */
    public AtencionEspProcedimientoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessionTurno = (TurnosCe) session.getAttribute("turno");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
