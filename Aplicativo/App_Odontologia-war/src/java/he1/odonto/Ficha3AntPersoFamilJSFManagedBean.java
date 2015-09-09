/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OAntecedentes;
import he1.odonto.entities.OAntecedentesPf;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.sessions.OAntecedentesPfFacade;
import he1.sis.entities.AntecedentesFamiliares;
import he1.sis.entities.AntecedentesPersonales;
import he1.sis.sessions.AntecedentesFamiliaresFacade;
import he1.sis.sessions.AntecedentesPersonalesFacade;
import he1.utilities.Utilitario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
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
@ManagedBean(name = "antPersoFamil")
@ViewScoped
public class Ficha3AntPersoFamilJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OAntecedentesPfFacade antecedentesPfFacade;
    @EJB
    private AntecedentesFamiliaresFacade antecedentesFamiliaresFacade;

    @EJB
    private AntecedentesPersonalesFacade antecedentesPersonalesFacade;

    private final OHistOdonto selectHistOdonto;
    private OAntecedentes editAntecedentes;
    private OAntecedentesPf editAntecedentesPf;
    private OAntecedentesPf selectAntecedentesPf;

    private List<OAntecedentesPf> listAntecedentesPf;
    private List<AntecedentesPersonales> listAntPer;
    private List<AntecedentesFamiliares> listAntFam;

    private String nroAnt;
    private String tipoAnt;

    // <editor-fold defaultstate="collapsed" desc="/** ">    
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** Set y get">
    public String getNroAnt() {
        return nroAnt;
    }

    public void setNroAnt(String nroAnt) {
        this.nroAnt = nroAnt;
    }

    public String getTipoAnt() {
        return tipoAnt;
    }

    public void setTipoAnt(String tipoAnt) {
        this.tipoAnt = tipoAnt;
    }

    public OAntecedentesPf getEditAntecedentesPf() {
        return editAntecedentesPf;
    }

    public void setEditAntecedentesPf(OAntecedentesPf editAntecedentesPf) {
        this.editAntecedentesPf = editAntecedentesPf;
    }

    public List<OAntecedentesPf> getListAntecedentesPf() {
        return listAntecedentesPf;
    }

    public void setListAntecedentesPf(List<OAntecedentesPf> listAntecedentesPf) {
        this.listAntecedentesPf = listAntecedentesPf;
    }

    public OAntecedentesPf getSelectAntecedentesPf() {
        return selectAntecedentesPf;
    }

    public void setSelectAntecedentesPf(OAntecedentesPf selectAntecedentesPf) {
        this.selectAntecedentesPf = selectAntecedentesPf;
    }

    public List<AntecedentesPersonales> getListAntPer() {
        return listAntPer;
    }

    public void setListAntPer(List<AntecedentesPersonales> listAntPer) {
        this.listAntPer = listAntPer;
    }

    public List<AntecedentesFamiliares> getListAntFam() {
        return listAntFam;
    }

    public void setListAntFam(List<AntecedentesFamiliares> listAntFam) {
        this.listAntFam = listAntFam;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** Parametro">    
    public void paramAFP(ActionEvent ae) {
        nroAnt = nroAntecSelec();
        editAntecedentesPf = new OAntecedentesPf();
        tipoAnt = null;

    }

    public String nroAntecSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("antFP");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar antecedente"> 
    public void btnGrabar() {
        if (editAntecedentesPf != null) {
            editAntecedentesPf.setApfId(BigDecimal.ZERO);
            editAntecedentesPf.setApfTipo(tipoAnt);
            editAntecedentesPf.setOAntecedentes(new OAntecedentes(new BigDecimal(nroAnt)));
            editAntecedentesPf.setOHistOdonto(selectHistOdonto);
            editAntecedentesPf.setApfDescripcion(editAntecedentesPf.getApfDescripcion());

            antecedentesPfFacade.create(editAntecedentesPf);

            ponerMensajeInfo("ATENCIÓN", "Síntomas guardados...");
        } else if (selectAntecedentesPf.getApfId() != null) {
            antecedentesPfFacade.edit(selectAntecedentesPf);
            ponerMensajeInfo("ATENCIÓN", "Síntoma actualizado...");
        }
        listAntecedentesPf = antecedentesPfFacade.listByHistOdon(selectHistOdonto);
    }

    public void btnGrabarDel() {
        antecedentesPfFacade.remove(selectAntecedentesPf);
        listAntecedentesPf = antecedentesPfFacade.listByHistOdon(selectHistOdonto);
        ponerMensajeInfo("ATENCIÓN", "Síntoma eliminado...");
    }

    // </editor-fold>       
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        listAntecedentesPf = antecedentesPfFacade.listByHistOdon(selectHistOdonto);
        listAntPer = antecedentesPersonalesFacade.listAntPerByHC(selectHistOdonto.getPacientes());
        listAntFam = antecedentesFamiliaresFacade.listAntFamByHC(selectHistOdonto.getPacientes());
    }

    // </editor-fold>
    /**
     * Creates a new instance of AntPersoFamilJSFManagedBean
     */
    public Ficha3AntPersoFamilJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
