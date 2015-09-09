/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.ODiagnostico;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.sessions.ODiagnosticoFacade;
import he1.sis.entities.DiagnosticosPaciente;
import he1.sis.entities.Enfermedades;
import he1.sis.entities.Personal;
import he1.sis.sessions.DiagnosticosPacienteFacade;
import he1.sis.sessions.EnfermedadesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
@ManagedBean(name = "diagnostico")
@ViewScoped
public class Ficha11DiagnosticoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private DiagnosticosPacienteFacade diagPacienteFacade;

    @EJB
    private ODiagnosticoFacade diagnosticoFacade;

    @EJB
    private EnfermedadesFacade enfermedadesFacade;
    @EJB
    private PersonalFacade personalFacade;
    @EJB
    private DiagnosticosPacienteFacade diagnosticosPacienteFacade;

    private List<ODiagnostico> listDiagnostico;
    private List<DiagnosticosPaciente> listDiagPaciente;

    private List<SelectItem> itemsListEnfOdonto;

    private Personal loginPersonal;
    private ODiagnostico selectDiagnostico;
    private ODiagnostico editDiagnostico;
    private DiagnosticosPaciente editDiagPaciente;

    private final OHistOdonto selectHistOdonto;

    private String codEnf;
    private String buscarEnf;
    private String tipoBuscar;
    private String codMedico;

    // <editor-fold defaultstate="collapsed" desc="/**Set y Get de variables ">
    public void setListDiagnostico(List<ODiagnostico> listDiagnostico) {
        this.listDiagnostico = listDiagnostico;
    }

    public List<ODiagnostico> getListDiagnostico() {
        return listDiagnostico;
    }

    public String getTipoBuscar() {
        return tipoBuscar;
    }

    public void setTipoBuscar(String tipoBuscar) {
        this.tipoBuscar = tipoBuscar;
    }

    public String getBuscarEnf() {
        return buscarEnf;
    }

    public void setBuscarEnf(String buscarEnf) {
        this.buscarEnf = buscarEnf;
    }

    public List<DiagnosticosPaciente> getListDiagPaciente() {
        return listDiagPaciente;
    }

    public void setListDiagPaciente(List<DiagnosticosPaciente> listDiagPaciente) {
        this.listDiagPaciente = listDiagPaciente;
    }

    public ODiagnostico getSelectDiagnostico() {
        return selectDiagnostico;
    }

    public void setSelectDiagnostico(ODiagnostico selectDiagnostico) {
        this.selectDiagnostico = selectDiagnostico;
    }

    public String getCodEnf() {
        return codEnf;
    }

    public void setCodEnf(String codEnf) {
        this.codEnf = codEnf;
    }

    public List<SelectItem> getItemsListEnfOdonto() {
        return itemsListEnfOdonto;
    }

    public void setItemsListEnfOdonto(List<SelectItem> itemsListEnfOdonto) {
        this.itemsListEnfOdonto = itemsListEnfOdonto;
    }

    public ODiagnostico getEditDiagnostico() {
        return editDiagnostico;
    }

    public void setEditDiagnostico(ODiagnostico editDiagnostico) {
        this.editDiagnostico = editDiagnostico;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/**recuperaItemsEnfermedad ">
    public void selectedAjaxChangeTipo() {
        buscarEnf = null;
        itemsListEnfOdonto= new ArrayList<>();
        recuperarItems();
    }

    public void recuperarItems() {
        List<Enfermedades> listEnf = new ArrayList<>();
        //listEnf = enfermedadesFacade.itemsEnfermOdonto();
        if (tipoBuscar != null) {
            if (buscarEnf == null) {
                buscarEnf = "";
            }
            System.out.println("buscarEnf " + buscarEnf);
            if (tipoBuscar.equalsIgnoreCase("D")) {
                listEnf = enfermedadesFacade.itemsEnfermByNombre("%" + buscarEnf.toUpperCase() + "%");
            } else if (tipoBuscar.equalsIgnoreCase("C")) {
                listEnf = enfermedadesFacade.itemsEnfermByCodigo(buscarEnf.toUpperCase() + "%");
            }

            if (listEnf != null) {
                this.itemsListEnfOdonto = new ArrayList<>();
                for (Enfermedades enf : listEnf) {
                    this.itemsListEnfOdonto.add(new SelectItem(enf.getCodigo(), enf.getCodigo() + " " + enf.getEnfermedad()));
                }
            }
        }
    }

    public void selectedAjaxChangeBuscarEnf() {
        recuperarItems();
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/**btnActionListener ">
    public void btnActionListener(ValueChangeEvent vce) {

        editDiagnostico = new ODiagnostico();
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/**btnGrabar ">
    public void btnGrabar() {
        if (editDiagnostico.getDgtId() == null) {

            try {
                //graba en DiagnosticoPacientes del SGHE
                editDiagPaciente = new DiagnosticosPaciente();
                //editDiagPaciente.setDgnpcnId(diagPacienteFacade.maxDiagId() + 1);/////******
                editDiagPaciente.setPacientes(selectHistOdonto.getPacientes());
                editDiagPaciente.setEnfermedades(new Enfermedades(codEnf));
                editDiagPaciente.setFechaInicio(new Date());
                editDiagPaciente.setTipo("PRE");
                editDiagPaciente.setPersonal(loginPersonal);
                editDiagPaciente.setEstadoValidez('V');
                editDiagPaciente.setObservacion("OdontoWeb");
                diagPacienteFacade.create(editDiagPaciente);
                System.out.println("creado Diag. en SGH");
                listDiagPaciente = diagnosticosPacienteFacade.listDiagnosticoPacientes(selectHistOdonto.getPacientes());

                editDiagnostico.setDgtId(BigDecimal.ZERO);
                //System.out.println("selectHistOdonto " + selectHistOdonto);
                editDiagnostico.setOHistOdonto(selectHistOdonto);
                editDiagnostico.setDgtFechaApertura(new Date());
                editDiagnostico.setDgtNumero(BigInteger.ONE);
                //System.out.println("editDiagnostico.getDgtTipo() " + editDiagnostico.getDgtTipo());
                editDiagnostico.setDgtTipo(editDiagnostico.getDgtTipo());
                //System.out.println("new Enfermedades(codEnf) " + new Enfermedades(codEnf));
                editDiagnostico.setEnfermedades(new Enfermedades(codEnf));
                editDiagnostico.setPersonal(loginPersonal);

                diagnosticoFacade.create(editDiagnostico);

                listDiagnostico = diagnosticoFacade.listByHistOdon(selectHistOdonto);

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
            }

        } else if (selectDiagnostico.getDgtId() != null) {
            diagnosticoFacade.edit(selectDiagnostico);
            ponerMensajeInfo("ATENCIÓN", "Información actualizada");
        }
    }

    public void btnGrabarDel() {
        diagnosticoFacade.remove(selectDiagnostico);

        listDiagnostico = diagnosticoFacade.listByHistOdon(selectHistOdonto);

        ponerMensajeInfo("ATENCIÓN", "Diagnóstico eliminado...");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    public void init() {
        System.out.println("iniciaDiagnostico....");
        loginPersonal = personalFacade.findByCodigo(codMedico);
        listDiagnostico = diagnosticoFacade.listByHistOdon(selectHistOdonto);
        listDiagPaciente = diagnosticosPacienteFacade.listDiagnosticoPacientes(selectHistOdonto.getPacientes());
        System.out.println("listDiag " + listDiagPaciente.size());
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** ">
    // </editor-fold>
    /**
     * Creates a new instance of Ficha11DiagnosticoJSFManagedBean
     */
    public Ficha11DiagnosticoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
