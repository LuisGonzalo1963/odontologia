/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.ODiagnostico;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OPlanDxTerEdu;
import he1.odonto.sessions.ODiagnosticoFacade;
import he1.odonto.sessions.OPlanDxTerEduFacade;
import he1.sis.entities.Cargos;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.DetallesDeExamenes;
import he1.sis.entities.DetallesDeExamenesPK;
import he1.sis.entities.DiagnosticosPaciente;
import he1.sis.entities.Examenes;
import he1.sis.entities.Personal;
import he1.sis.entities.SolicitudesDeExamenes;
import he1.sis.entities.TiposDeExamenes;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CuentasFacade;
import he1.sis.sessions.DetallesDeExamenesFacade;
import he1.sis.sessions.DiagnosticosPacienteFacade;
import he1.sis.sessions.ExamenesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.PromocionesPacientesFacade;
import he1.sis.sessions.SolicitudesDeExamenesFacade;
import he1.utilities.Utilitario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "PlanDxTEd")
@ViewScoped
public class Ficha10PlanDxTerEduJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private ODiagnosticoFacade diagnosticoFacade;
    @EJB
    private OPlanDxTerEduFacade planDxTerEduFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private DiagnosticosPacienteFacade diagnosticosPacienteFacade;
    @EJB
    private SolicitudesDeExamenesFacade solicitudesDeExamenesFacade;
    @EJB
    private DetallesDeExamenesFacade detallesDeExamenesFacade;
    @EJB
    private ExamenesFacade examenesFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;
    @EJB
    private DiagnosticosPacienteFacade diagPacienteFacade;

    private OPlanDxTerEdu editPlanDxTerEdu;
    private OPlanDxTerEdu selectPlanDxTerEdu;
    private Personal loginPersonal;
    private DiagnosticosPaciente selectDiagPacientes;
    private ODiagnostico selectDiagnostico;
    private SolicitudesDeExamenes editSolicitudesDeExamenes;
    private Examenes editExamenes;
    private DetallesDeExamenes editDetDeExamenes;
    private DetallesDeExamenesPK editDetDeExamenesPk;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;

    private List<OPlanDxTerEdu> listPlanDxTerEdu;
    private List<ODiagnostico> listDiagnostico;

    private List<SelectItem> itemsListTarifario;
    private List<SelectItem> itemsListCargos;
    private List<SelectItem> itemsListDiagPcn;

    private String nroTipo;
    private String msgDiag;
    private String codDiag;
    private String codDiagWs;
    private String nroAtenciones;
    private String msgWebService;
    private String codCargo;
    private String prmPcn;
    private String fechaActual;
    private String codMedico;

    private OHistOdonto selectHistOdonto;

    private BigDecimal valCargo;
    private BigDecimal cantRx;
    private BigDecimal valTotal;

    private boolean flagBtnDlg;

    // <editor-fold defaultstate="collapsed" desc="/**Set y Get de Variables ">  
    public String getNroTipo() {
        return nroTipo;
    }

    public String getMsgDiag() {
        return msgDiag;
    }

    public BigDecimal getValTotal() {
        return valTotal;
    }

    public void setValTotal(BigDecimal valTotal) {
        this.valTotal = valTotal;
    }

    public String getCodDiag() {
        return codDiag;
    }

    public void setCodDiag(String codDiag) {
        this.codDiag = codDiag;
    }

    public List<SelectItem> getItemsListDiagPcn() {
        return itemsListDiagPcn;
    }

    public void setItemsListDiagPcn(List<SelectItem> itemsListDiagPcn) {
        this.itemsListDiagPcn = itemsListDiagPcn;
    }

    public String getPrmPcn() {
        return prmPcn;
    }

    public void setPrmPcn(String prmPcn) {
        this.prmPcn = prmPcn;
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

    public String getNroAtenciones() {
        return nroAtenciones;
    }

    public void setNroAtenciones(String nroAtenciones) {
        this.nroAtenciones = nroAtenciones;
    }

    public String getMsgWebService() {
        return msgWebService;
    }

    public void setMsgWebService(String msgWebService) {
        this.msgWebService = msgWebService;
    }

    public OHistOdonto getSelectHistOdonto() {
        return selectHistOdonto;
    }

    public void setSelectHistOdonto(OHistOdonto selectHistOdonto) {
        this.selectHistOdonto = selectHistOdonto;
    }

    public boolean isFlagBtnDlg() {
        return flagBtnDlg;
    }

    public void setFlagBtnDlg(boolean flagBtnDlg) {
        this.flagBtnDlg = flagBtnDlg;
    }

    public void setMsgDiag(String msgDiag) {
        this.msgDiag = msgDiag;
    }

    public void setNroTipo(String nroTipo) {
        this.nroTipo = nroTipo;
    }

    public BigDecimal getCantRx() {
        return cantRx;
    }

    public void setCantRx(BigDecimal cantRx) {
        this.cantRx = cantRx;
    }

    public BigDecimal getValCargo() {
        return valCargo;
    }

    public void setValCargo(BigDecimal valCargo) {
        this.valCargo = valCargo;
    }

    public List<SelectItem> getItemsListTarifario() {
        return itemsListTarifario;
    }

    public void setItemsListTarifario(List<SelectItem> itemsListTarifario) {
        this.itemsListTarifario = itemsListTarifario;
    }

    public OPlanDxTerEdu getEditPlanDxTerEdu() {
        return editPlanDxTerEdu;
    }

    public void setEditPlanDxTerEdu(OPlanDxTerEdu editPlanDxTerEdu) {
        this.editPlanDxTerEdu = editPlanDxTerEdu;
    }

    public OPlanDxTerEdu getSelectPlanDxTerEdu() {
        return selectPlanDxTerEdu;
    }

    public void setSelectPlanDxTerEdu(OPlanDxTerEdu selectPlanDxTerEdu) {
        this.selectPlanDxTerEdu = selectPlanDxTerEdu;
    }

    public List<OPlanDxTerEdu> getListPlanDxTerEdu() {
        return listPlanDxTerEdu;
    }

    public void setListPlanDxTerEdu(List<OPlanDxTerEdu> listPlanDxTerEdu) {
        this.listPlanDxTerEdu = listPlanDxTerEdu;
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** ">    
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/**selectedAjaxChangeDx">
    public void selectedAjaxChangeDx() {
        selectDiagPacientes = diagPacienteFacade.find(Long.valueOf(codDiag));
        codDiagWs = selectDiagPacientes.getEnfermedades().getCodigo().replace(".", "");
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsTarifario ">
    public void recuperaItemsTarifario() {
        //itemsListEnfOdonto.clear();
//        List<TarifarioServicios> listEnf = new ArrayList<>();
//        listEnf = tarifarioServiciosFacade.itemsTarifario();
//        if (listEnf != null) {
//            this.itemsListTarifario = new ArrayList<>();
//            for (TarifarioServicios tar : listEnf) {
//                this.itemsListTarifario.add(new SelectItem(tar.getTsvId(), tar.getTsvCodigo() + " " + tar.getTsvDescripcion()));
//            }
//        }

    }

    public void recuperaItemsCargos() {
        List<Cargos> lc = new ArrayList<>();
        lc = cargosFacade.listAllRx();
        if (lc != null) {
            this.itemsListCargos = new ArrayList<>();
            for (Cargos car : lc) {
                this.itemsListCargos.add(new SelectItem(car.getCargosPK().getCodigo(), car.getCargosPK().getCodigo() + " " + car.getDescripcion()));
            }
        }
    }

    // </editor-fold>      
    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsDiagPcn ">
    public void recuperaItemsDiagPcn() {
        List<DiagnosticosPaciente> ldp = new ArrayList<>();
        ldp = diagnosticosPacienteFacade.listDiagnosticoPacientes(selectHistOdonto.getPacientes());
        if (ldp != null) {
            this.itemsListDiagPcn = new ArrayList<>();
            for (DiagnosticosPaciente dp : ldp) {
                this.itemsListDiagPcn.add(new SelectItem(dp.getDgnpcnId(), dp.getEnfermedades().getCodigo() + " " + dp.getEnfermedades().getEnfermedad()));
            }
        }
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="/** Parametro">    
    public void paramPlanDx(ActionEvent ae) {
        codCargo = null;
        msgWebService = null;
        prmPcn = null;
        valCargo = null;
        cantRx = null;

        nroTipo = nroTipoSelec();
        if (nroTipo.equalsIgnoreCase("3")) {
            flagBtnDlg = true;
        } else {
            flagBtnDlg = false;
        }
        editPlanDxTerEdu = new OPlanDxTerEdu();
        recuperaItemsCargos();
//        if (!msgDiag.equalsIgnoreCase(null)) {
//            ponerMensajeInfo("ATENCIÓN", "Registre un diagnóstico...");
//        }

    }

    public String nroTipoSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("tipo");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChange">
    public void selectedAjaxChange() {
        System.out.println("codCargo " + codCargo);
        System.out.println("codDiag " + codDiagWs);

        //msgWebService = callWebServiceConsulta(selectHistOdonto.getPacientes().getIdIssfa(), "02/07/2015", codDiagWs, codCargo);
        System.out.println("msgWebService " + msgWebService);
        valCargo = cargosFacade.findByCod(codCargo);
        System.out.println("valCargo " + valCargo);
        prmPcn = promPcn(selectHistOdonto.getPacientes());
        System.out.println("prom " + promPcn(selectHistOdonto.getPacientes()));
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeCant">
    public void selectedAjaxChangeCant() {
        valTotal = cantRx.multiply(valCargo);
        if (cantRx.compareTo(BigDecimal.ZERO) > 0) {
            flagBtnDlg = false;
        } else {
            flagBtnDlg = true;
        }

    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/**btnGrabar dialogo ">    
    public void btnGrabar() {
        System.out.println("editPlanDxTerEdu " + editPlanDxTerEdu);
        if (editPlanDxTerEdu != null) {
            editPlanDxTerEdu.setPdxId(BigDecimal.ZERO);
            editPlanDxTerEdu.setOHistOdonto(selectHistOdonto);
            editPlanDxTerEdu.setPdxTipo(new BigInteger(nroTipo));
            editPlanDxTerEdu.setPdxDescripcion(editPlanDxTerEdu.getPdxDescripcion());
            editPlanDxTerEdu.setPdxFecha(new Date());
            editPlanDxTerEdu.setPersonal(loginPersonal);
            if (nroTipo.equalsIgnoreCase("3")) {
                editPlanDxTerEdu.setPdxCantidad(cantRx);
                editPlanDxTerEdu.setPdxProced(Integer.parseInt(codCargo));
                editPlanDxTerEdu.setPdxDiagnostico(selectDiagPacientes.getEnfermedades().getCodigo());
                editPlanDxTerEdu.setPdxRestante(nroAtenciones);
            }
            System.out.println("1. grabado PlanDxTerEduc..."+codMedico+" "+fechaActual);

            planDxTerEduFacade.create(editPlanDxTerEdu);

            listPlanDxTerEdu = planDxTerEduFacade.listByHistOdon(selectHistOdonto);
            if (nroTipo.equalsIgnoreCase("3")) {

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
                editExamenes.setDiagnosticosPaciente(selectDiagPacientes);
                editExamenes.setDatosClinicos(editPlanDxTerEdu.getPdxDescripcion());
                editExamenes.setTomado('F');
                examenesFacade.create(editExamenes);
                System.out.println("3. grabado Exam...");

                //graba en detalles de examenes
                editDetDeExamenesPk = new DetallesDeExamenesPK();
                editDetDeExamenesPk.setExmNumero(examenesFacade.listByPaciente(selectHistOdonto.getPacientes(), loginPersonal).get(0).getNumero());
                editDetDeExamenesPk.setVrbexmCodigo(Integer.parseInt(codCargo));
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
                editCuentasPk.setDetalle(Integer.parseInt(codCargo));

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
                editCuentas.setValor(valCargo);/////*******
                editCuentas.setCrgTipo('P');
                editCuentas.setCrgCodigo(codCargo);
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
            }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar Cal = Calendar.getInstance();
        fechaActual = dateFormat.format(Cal.getTime());
        loginPersonal = personalFacade.findByCodigo(codMedico);
        selectPlanDxTerEdu = new OPlanDxTerEdu();
        listPlanDxTerEdu = planDxTerEduFacade.listByHistOdon(selectHistOdonto);
        listDiagnostico = diagnosticoFacade.listByHistOdon(selectHistOdonto);
        recuperaItemsDiagPcn();
        if (listDiagnostico.size() == 0) {
            flagBtnDlg = true;
            msgDiag = "Registre un Diagnóstico...";
        } else {
            flagBtnDlg = false;
            //codDiag = listDiagnostico.get(0).getEnfermedades().getCodigo().replace(".", "");
            //msgDiag = "Diagnóstico: " + codDiag + " " + listDiagnostico.get(0).getEnfermedades().getEnfermedad();

            selectDiagnostico = listDiagnostico.get(0);
            System.out.println("pac " + selectHistOdonto.getPacientes());
            selectDiagPacientes = diagnosticosPacienteFacade.listByDiagPcn(selectHistOdonto.getPacientes()).get(0);

        }

    }

    // </editor-fold>       
    /**
     * Creates a new instance of Ficha10PlanDxTerEduJSFManagedBean
     */
    public Ficha10PlanDxTerEduJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
