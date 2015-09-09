/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OParametros;
import he1.odonto.sessions.OParametrosFacade;
import he1.sis.entities.Cargos;
import he1.sis.entities.Cuentas;
import he1.sis.entities.CuentasPK;
import he1.sis.entities.DetallesDeExamenes;
import he1.sis.entities.DetallesDeExamenesPK;
import he1.sis.entities.DiagnosticosPaciente;
import he1.sis.entities.Enfermedades;
import he1.sis.entities.Examenes;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.SolicitudesDeExamenes;
import he1.sis.entities.TiposDeExamenes;
import he1.sis.sessions.CargosFacade;
import he1.sis.sessions.CuentasFacade;
import he1.sis.sessions.DetallesDeExamenesFacade;
import he1.sis.sessions.DiagnosticosPacienteFacade;
import he1.sis.sessions.EnfermedadesFacade;
import he1.sis.sessions.ExamenesFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.PromocionesPacientesFacade;
import he1.sis.sessions.SolicitudesDeExamenesFacade;
import he1.utilities.Utilitario;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "pedidoRxSinTurno")
@ViewScoped
public class PedidoRxSinTurnoJSFManagedBean extends Utilitario implements Serializablee {

    private static final Logger log = Logger.getLogger(RepFichaOdonJSFManagedBean.class.getName());

    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private DiagnosticosPacienteFacade diagPacienteFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private SolicitudesDeExamenesFacade solicitudesDeExamenesFacade;
    @EJB
    private ExamenesFacade examenesFacade;
    @EJB
    private DetallesDeExamenesFacade detallesDeExamenesFacade;
    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private CuentasFacade cuentasFacade;
    @EJB
    private EnfermedadesFacade enfermedadesFacade;
    @EJB
    private OParametrosFacade oParametrosFacade;

    private Pacientes editPacientes;
    private DiagnosticosPaciente selectDiagPaciente;
    private SolicitudesDeExamenes editSolicitudesDeExamenes;
    private Personal loginPersonal;
    private Examenes editExamenes;
    private DetallesDeExamenes editDetDeExamenes;
    private DetallesDeExamenesPK editDetDeExamenesPk;
    private Cuentas editCuentas;
    private CuentasPK editCuentasPk;
    private DiagnosticosPaciente editDiagPaciente;
    private OParametros editOParametros;

    private List<DiagnosticosPaciente> listDiagPaciente;

    private List<SelectItem> itemsListRayosX;
    private List<SelectItem> itemsListEnfOdonto;

    private Integer nroHC;

    private BigDecimal valCargoRx;
    private BigDecimal cantRx;
    private BigDecimal valTotalRx;

    private String codCargoRx;
    private String msgWebService;
    private String descripcionRx;
    private String fechaActual;
    private String buscarEnf;
    private String codDiag;
    private final String codMedico;

    private boolean flagPanelDatos;
    private boolean flagBtnGrabarRx;
    private boolean flagBtnNuevoDiag;

    private Date selectFecha;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
    public void setNroHC(Integer nroHC) {
        this.nroHC = nroHC;
    }

    public Integer getNroHC() {
        return nroHC;
    }

    public List<SelectItem> getItemsListEnfOdonto() {
        return itemsListEnfOdonto;
    }

    public void setItemsListEnfOdonto(List<SelectItem> itemsListEnfOdonto) {
        this.itemsListEnfOdonto = itemsListEnfOdonto;
    }

    public String getCodDiag() {
        return codDiag;
    }

    public void setCodDiag(String codDiag) {
        this.codDiag = codDiag;
    }

    public String getBuscarEnf() {
        return buscarEnf;
    }

    public void setBuscarEnf(String buscarEnf) {
        this.buscarEnf = buscarEnf;
    }

    public boolean isFlagBtnNuevoDiag() {
        return flagBtnNuevoDiag;
    }

    public void setFlagBtnNuevoDiag(boolean flagBtnNuevoDiag) {
        this.flagBtnNuevoDiag = flagBtnNuevoDiag;
    }

    public DiagnosticosPaciente getSelectDiagPaciente() {
        return selectDiagPaciente;
    }

    public void setSelectDiagPaciente(DiagnosticosPaciente selectDiagPaciente) {
        this.selectDiagPaciente = selectDiagPaciente;
    }

    public boolean isFlagBtnGrabarRx() {
        return flagBtnGrabarRx;
    }

    public void setFlagBtnGrabarRx(boolean flagBtnGrabarRx) {
        this.flagBtnGrabarRx = flagBtnGrabarRx;
    }

    public BigDecimal getCantRx() {
        return cantRx;
    }

    public void setCantRx(BigDecimal cantRx) {
        this.cantRx = cantRx;
    }

    public BigDecimal getValTotalRx() {
        return valTotalRx;
    }

    public void setValTotalRx(BigDecimal valTotalRx) {
        this.valTotalRx = valTotalRx;
    }

    public String getDescripcionRx() {
        return descripcionRx;
    }

    public void setDescripcionRx(String descripcionRx) {
        this.descripcionRx = descripcionRx;
    }

    public BigDecimal getValCargoRx() {
        return valCargoRx;
    }

    public void setValCargoRx(BigDecimal valCargoRx) {
        this.valCargoRx = valCargoRx;
    }

    public String getMsgWebService() {
        return msgWebService;
    }

    public void setMsgWebService(String msgWebService) {
        this.msgWebService = msgWebService;
    }

    public List<SelectItem> getItemsListRayosX() {
        return itemsListRayosX;
    }

    public void setItemsListRayosX(List<SelectItem> itemsListRayosX) {
        this.itemsListRayosX = itemsListRayosX;
    }

    public String getCodCargoRx() {
        return codCargoRx;
    }

    public void setCodCargoRx(String codCargoRx) {
        this.codCargoRx = codCargoRx;
    }

    public Pacientes getEditPacientes() {
        return editPacientes;
    }

    public void setEditPacientes(Pacientes editPacientes) {
        this.editPacientes = editPacientes;
    }

    public List<DiagnosticosPaciente> getListDiagPaciente() {
        return listDiagPaciente;
    }

    public void setListDiagPaciente(List<DiagnosticosPaciente> listDiagPaciente) {
        this.listDiagPaciente = listDiagPaciente;
    }

    public boolean isFlagPanelDatos() {
        return flagPanelDatos;
    }

    public void setFlagPanelDatos(boolean flagPanelDatos) {
        this.flagPanelDatos = flagPanelDatos;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btnBuscarHc">
    public void btnBuscarHc() {
        editPacientes = pacientesFacade.findByHc(nroHC);
        if (editPacientes != null) {
            flagPanelDatos = true;
            listDiagPaciente = diagPacienteFacade.listByDiagPcn(editPacientes);
            recuperaItemsRx();
            this.flagBtnNuevoDiag = Boolean.FALSE;
            msgWebService = null;
        } else {
            flagPanelDatos = false;
            this.flagBtnNuevoDiag = Boolean.TRUE;
            ponerMensajeInfo("ATENCION", "No existe el Paciente !!!");
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** recuperaItemsRx">
    public void recuperaItemsRx() {
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
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeSelectDiagnostico">
    public void selectedAjaxChangeSelectDg() {
        System.out.println("selectDiagPaciente " + selectDiagPaciente.getEnfermedades().getCodigo().replace(".", ""));
        this.flagPanelDatos = Boolean.FALSE;
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeRxPr">
    public void selectedAjaxChangeRxPr() {
        System.out.println("codCargoRx " + codCargoRx);
        System.out.println("fecha " + fechaActual);
        editOParametros = oParametrosFacade.parametros("WEBSERVICE");
        if (editOParametros != null) {
            System.out.println("");
            System.out.println("editOParametros.getParEstado() "+editOParametros.getParEstado());
            if (editOParametros.getParEstado().equalsIgnoreCase("A")) {
                System.out.println("1 llamando al WebService");
                msgWebService = callWebServiceConsulta(editPacientes.getIdIssfa(), fechaActual, selectDiagPaciente.getEnfermedades().getCodigo().replace(".", ""), codCargoRx);
            }
        }

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
    // <editor-fold defaultstate="collapsed" desc="/** Sxxxxxx">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/**btnGrabarDiagPcn ">
    public void btnGrabarDiagPcn() {
        try {
            //graba en DiagnosticoPacientes del SGHE
            editDiagPaciente = new DiagnosticosPaciente();
            editDiagPaciente.setPacientes(editPacientes);
            editDiagPaciente.setEnfermedades(new Enfermedades(codDiag));
            editDiagPaciente.setFechaInicio(new Date());
            editDiagPaciente.setTipo("PRE");
            editDiagPaciente.setPersonal(loginPersonal);
            editDiagPaciente.setEstadoValidez('V');
            editDiagPaciente.setObservacion("OdontoWeb");
            diagPacienteFacade.create(editDiagPaciente);
            System.out.println("creado Diag. en SGH");

            ponerMensajeInfo("ATENCIÓN", "Información guardada");
            listDiagPaciente = diagPacienteFacade.listByDiagPcn(editPacientes);
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

    // </editor-fold>       
    // <editor-fold defaultstate="collapsed" desc="/**btnGrabar Pedido Rx ">    
    public void btnGrabarRx() {
        //graba em SOLICITUD_DE_EXAMENES
        editSolicitudesDeExamenes = new SolicitudesDeExamenes();
        //editSolicitudesDeExamenes.setNumero(solicitudesDeExamenesFacade.maxDiagId() + 1);
        editSolicitudesDeExamenes.setDprAraCodigo("C");
        editSolicitudesDeExamenes.setDprCodigo("Z");
        editSolicitudesDeExamenes.setPacientes(editPacientes);
        editSolicitudesDeExamenes.setEstado("C");
        editSolicitudesDeExamenes.setFechaDeCreacion(new Date());
        editSolicitudesDeExamenes.setMedico(loginPersonal.getApellidos() + " " + loginPersonal.getNombres());
        editSolicitudesDeExamenes.setPersonal(loginPersonal);
        editSolicitudesDeExamenes.setFechaDePeticion(selectFecha);
        editSolicitudesDeExamenes.setMdcRctId(loginPersonal.getCodigo());
        solicitudesDeExamenesFacade.create(editSolicitudesDeExamenes);
        System.out.println("2. grabado SOL DE EXAM..");

        //graba en examenes
        editExamenes = new Examenes();
        //editExamenes.setNumero(examenesFacade.maxExamId() + 1);
        editExamenes.setPacientes(editPacientes);
        editExamenes.setPersonal(loginPersonal);
        editExamenes.setSolicitudesDeExamenes(solicitudesDeExamenesFacade.listByPaciente(editPacientes, loginPersonal).get(0));
        editExamenes.setTiposDeExamenes(new TiposDeExamenes("OT"));
        editExamenes.setEstadoDeExamen("A");
        editExamenes.setDiagnosticosPaciente(selectDiagPaciente);
        editExamenes.setDatosClinicos(descripcionRx);
        editExamenes.setTomado('F');
        examenesFacade.create(editExamenes);
        System.out.println("3. grabado Exam...");

        //graba en detalles de examenes
        editDetDeExamenesPk = new DetallesDeExamenesPK();
        editDetDeExamenesPk.setExmNumero(examenesFacade.listByPaciente(editPacientes, loginPersonal).get(0).getNumero());
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
        editCuentasPk.setNumero(examenesFacade.listByPaciente(editPacientes, loginPersonal).get(0).getNumero());
        System.out.println("codCargoRx " + codCargoRx);
        editCuentasPk.setDetalle(Integer.parseInt(codCargoRx));

        editCuentas = new Cuentas();
        editCuentas.setCuentasPK(editCuentasPk);

        editCuentas.setDprAraCodigo("C");
        editCuentas.setDprAraCodigoPertenecienteA("O");
        editCuentas.setPacientes(editPacientes);
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
        editCuentas.setPrmCodigo(promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(editPacientes)).getPromociones().getCodigo());
        editCuentas.setValore(BigDecimal.ZERO);
        editCuentas.setIvae(BigDecimal.valueOf(0, 1));
        editCuentas.setObservacion(descripcionRx);
        editCuentas.setIvaExcento('F');
        editCuentas.setServicio("CEX");
        editCuentas.setSeguro('F');
        editCuentas.setConfirmado('F');
        editCuentas.setAuditado('F');
        cuentasFacade.create(editCuentas);
        System.out.println("5. creado en Cuentas.....");
        //
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        //handler.performNavigation("asignarTurno?faces-redirect=true");
        handler.performNavigation("atencionTurno?faces-redirect=true");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/**btnActionListenerPrc ">    
    public void selectedAjaxChangeBuscarEnf() {
        recuperaItemsEnfermedad();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsEnfermedad ">
    public void recuperaItemsEnfermedad() {
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
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar Cal = Calendar.getInstance();
        fechaActual = dateFormat.format(Cal.getTime());
        try {
            selectFecha = dateFormat.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(AsignarTurnoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (codMedico != null) {
            loginPersonal = personalFacade.findByCodigo(codMedico);
        }
        this.flagPanelDatos = Boolean.TRUE;
        this.flagBtnGrabarRx = Boolean.TRUE;
        this.flagBtnNuevoDiag = Boolean.TRUE;

    }

    // </editor-fold>    
    /**
     * Creates a new instance of PedidoRxSinTurnoJSFManagedBean
     */
    public PedidoRxSinTurnoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        codMedico = (String) session.getAttribute("usuarioDB");

    }

}
