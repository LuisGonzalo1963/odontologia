/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OPatologias;
import he1.odonto.entities.OSistemaEstomatognico;
import he1.odonto.sessions.OSistemaEstomatognicoFacade;
import he1.utilities.Utilitario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "examSistEstomat")
@ViewScoped
public class ExamSistEstomatJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OSistemaEstomatognicoFacade sistemaEstomatognicoFacade;

    private final OHistOdonto selectHistOdonto;
    private OSistemaEstomatognico editSistemaEstomatognico;
    private OSistemaEstomatognico selectSistemaEstomatognico;
    private List<OSistemaEstomatognico> listSistEstomatognico;

    private String nroPat;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get">
    public String getNroPat() {
        return nroPat;
    }

    public void setNroPat(String nroPat) {
        this.nroPat = nroPat;
    }

    public OSistemaEstomatognico getEditSistemaEstomatognico() {
        return editSistemaEstomatognico;
    }

    public void setEditSistemaEstomatognico(OSistemaEstomatognico editSistemaEstomatognico) {
        this.editSistemaEstomatognico = editSistemaEstomatognico;
    }

    public List<OSistemaEstomatognico> getListSistEstomatognico() {
        return listSistEstomatognico;
    }

    public void setListSistEstomatognico(List<OSistemaEstomatognico> listSistEstomatognico) {
        this.listSistEstomatognico = listSistEstomatognico;
    }

    public OSistemaEstomatognico getSelectSistemaEstomatognico() {
        return selectSistemaEstomatognico;
    }

    public void setSelectSistemaEstomatognico(OSistemaEstomatognico selectSistemaEstomatognico) {
        this.selectSistemaEstomatognico = selectSistemaEstomatognico;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** Parámetros">
    public void paramPat(ActionEvent ae) {
        nroPat = nroAntecSelec();
        editSistemaEstomatognico = new OSistemaEstomatognico();
        if (nroPat.equalsIgnoreCase("13")) {
            editSistemaEstomatognico.setSieDescripcion("Sin Patología Aparente");
        }
    }

    public String nroAntecSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("pat");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btnGrabar">
    public void btnGrabar() {
        System.out.println("editSistemaEstomatognico " + editSistemaEstomatognico);
        System.out.println("selectSistemaEstomatognico " + selectSistemaEstomatognico);
        if (editSistemaEstomatognico != null) {
            if (editSistemaEstomatognico.getSieId() == null) {
                editSistemaEstomatognico.setSieId(BigDecimal.ZERO);
                editSistemaEstomatognico.setPacientes(selectHistOdonto.getPacientes());
                editSistemaEstomatognico.setOPatologias(new OPatologias(new BigDecimal(nroPat)));
                editSistemaEstomatognico.setSieFecha(new Date());
                editSistemaEstomatognico.setoHistOdonto(selectHistOdonto);
                if (nroPat.equalsIgnoreCase("13")) {
                    editSistemaEstomatognico.setSieDescripcion("SIN PATOLOGÍA APARENTE");
                } else {
                    editSistemaEstomatognico.setSieDescripcion(editSistemaEstomatognico.getSieDescripcion());
                }

                sistemaEstomatognicoFacade.create(editSistemaEstomatognico);
                listSistEstomatognico = sistemaEstomatognicoFacade.listByHistOdon(selectHistOdonto);

                ponerMensajeInfo("ATENCIÓN", "Patología guardado...");

            }
        } else if (selectSistemaEstomatognico.getSieId() != null) {
            sistemaEstomatognicoFacade.edit(selectSistemaEstomatognico);
            listSistEstomatognico = sistemaEstomatognicoFacade.listByHistOdon(selectHistOdonto);

            ponerMensajeInfo("ATENCIÓN", "Patología actualizado...");

        }

    }

    public void btnGrabarDel() {
        sistemaEstomatognicoFacade.remove(selectSistemaEstomatognico);
        listSistEstomatognico = sistemaEstomatognicoFacade.listByHistOdon(selectHistOdonto);
        ponerMensajeInfo("ATENCIÓN", "Patología eliminado...");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        listSistEstomatognico = sistemaEstomatognicoFacade.listByHistOdon(selectHistOdonto);
    }

    // </editor-fold>
    /**
     * Creates a new instance of ExamSistEstomatJSFManagedBean
     */
    public ExamSistEstomatJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
