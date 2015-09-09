/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OInforInicial;
import he1.odonto.entities.OMotivoConsulta;
import he1.odonto.sessions.OInforInicialFacade;
import he1.odonto.sessions.OMotivoConsultaFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@ManagedBean(name = "fichaMotivo")
@ViewScoped
public class Ficha1MotivoJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private OMotivoConsultaFacade motivoConsultaFacade;
    @EJB
    private OInforInicialFacade inforInicialFacade;

    private OInforInicial inforInicialEdit;
    private OMotivoConsulta editMotivoConsulta;

    private final OHistOdonto selectHistOdonto;

    private String rangoEdad;
    private String embarazo;
    private boolean flagGrabarM;
    private boolean flagCheckboxGenero;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get">
    public OMotivoConsulta getEditMotivoConsulta() {
        return editMotivoConsulta;
    }

    public void setEditMotivoConsulta(OMotivoConsulta editMotivoConsulta) {
        this.editMotivoConsulta = editMotivoConsulta;
    }

    public boolean isFlagCheckboxGenero() {
        return flagCheckboxGenero;
    }

    public void setFlagCheckboxGenero(boolean flagCheckboxGenero) {
        this.flagCheckboxGenero = flagCheckboxGenero;
    }

    public String getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(String rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public String getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar Motivo">
    public void btnGrabaMotivo() {
        System.out.println("rangoEdad " + rangoEdad);
        System.out.println("inforInicialEdit " + inforInicialEdit);
        ///
        if (inforInicialEdit == null) { ///edicion
            inforInicialEdit = new OInforInicial();
            if (rangoEdad.equalsIgnoreCase("1")) {
                inforInicialEdit.setInfMenorA1("S");
            } else {
                inforInicialEdit.setInfMenorA1("N");
            }

            if (rangoEdad.equalsIgnoreCase("2")) {
                inforInicialEdit.setInf1A4("S");
            } else {
                inforInicialEdit.setInf1A4("N");
            }

            if (rangoEdad.equalsIgnoreCase("3")) {
                inforInicialEdit.setInf5A9Prog("S");
            } else {
                inforInicialEdit.setInf5A9Prog("N");
            }
            if (rangoEdad.equalsIgnoreCase("4")) {
                inforInicialEdit.setInf5A9NoProg("S");
            } else {
                inforInicialEdit.setInf5A9NoProg("N");
            }
            if (rangoEdad.equalsIgnoreCase("5")) {
                inforInicialEdit.setInf10A14Prog("S");
            } else {
                inforInicialEdit.setInf10A14Prog("N");
            }
            if (rangoEdad.equalsIgnoreCase("6")) {
                inforInicialEdit.setInf15A19("S");
            } else {
                inforInicialEdit.setInf15A19("N");
            }
            if (rangoEdad.equalsIgnoreCase("7")) {
                inforInicialEdit.setInfMayorA20("S");
            } else {
                inforInicialEdit.setInfMayorA20("N");
            }
            if (flagCheckboxGenero) {
                inforInicialEdit.setInfEmbarazo("S");
            } else {
                inforInicialEdit.setInfEmbarazo("N");
            }
        }
        if (inforInicialEdit.getInfId() == null) {

            inforInicialEdit.setInfId(BigDecimal.ONE);
            inforInicialEdit.setInfFecha(new Date());
            inforInicialEdit.setoHistOdonto(selectHistOdonto);
            inforInicialFacade.create(inforInicialEdit);
        } else {
             if (rangoEdad.equalsIgnoreCase("1")) {
                inforInicialEdit.setInfMenorA1("S");
            } else {
                inforInicialEdit.setInfMenorA1("N");
            }

            if (rangoEdad.equalsIgnoreCase("2")) {
                inforInicialEdit.setInf1A4("S");
            } else {
                inforInicialEdit.setInf1A4("N");
            }

            if (rangoEdad.equalsIgnoreCase("3")) {
                inforInicialEdit.setInf5A9Prog("S");
            } else {
                inforInicialEdit.setInf5A9Prog("N");
            }
            if (rangoEdad.equalsIgnoreCase("4")) {
                inforInicialEdit.setInf5A9NoProg("S");
            } else {
                inforInicialEdit.setInf5A9NoProg("N");
            }
            if (rangoEdad.equalsIgnoreCase("5")) {
                inforInicialEdit.setInf10A14Prog("S");
            } else {
                inforInicialEdit.setInf10A14Prog("N");
            }
            if (rangoEdad.equalsIgnoreCase("6")) {
                inforInicialEdit.setInf15A19("S");
            } else {
                inforInicialEdit.setInf15A19("N");
            }
            if (rangoEdad.equalsIgnoreCase("7")) {
                inforInicialEdit.setInfMayorA20("S");
            } else {
                inforInicialEdit.setInfMayorA20("N");
            }
            inforInicialEdit.setInfFecha(new Date());
            inforInicialEdit.setoHistOdonto(selectHistOdonto);
            inforInicialFacade.edit(inforInicialEdit);
        }

        System.out.println("infor inicial grabado....");

        //
        System.out.println("a grabar.....");
        if (flagGrabarM) {
            editMotivoConsulta.setMotId(BigDecimal.ZERO);
            editMotivoConsulta.setOHistOdonto(selectHistOdonto);
            editMotivoConsulta.setMotMotivo(editMotivoConsulta.getMotMotivo());

            motivoConsultaFacade.create(editMotivoConsulta);
            ponerMensajeInfo("ATENCIÓN", "Motivo de Consulta guardado...");
        } else {
            motivoConsultaFacade.edit(editMotivoConsulta);
            ponerMensajeInfo("ATENCIÓN", "Motivo de Consulta actualizado...");
        }

    }
    // </editor-fold>

    public void recuperaInfoInicial() {
        inforInicialEdit = inforInicialFacade.findByHistOdon(selectHistOdonto);
        System.out.println("recup " + inforInicialEdit);
        if (inforInicialEdit != null) {
            if (inforInicialEdit.getInfMenorA1().equalsIgnoreCase("S")) {
                rangoEdad = "1";
            }
            if (inforInicialEdit.getInf1A4().equalsIgnoreCase("S")) {
                rangoEdad = "2";
            }
            if (inforInicialEdit.getInf5A9Prog().equalsIgnoreCase("S")) {
                rangoEdad = "3";
            }
            if (inforInicialEdit.getInf5A9NoProg().equalsIgnoreCase("S")) {
                rangoEdad = "4";
            }
            if (inforInicialEdit.getInf10A14Prog().equalsIgnoreCase("S")) {
                rangoEdad = "5";
            }
            if (inforInicialEdit.getInf15A19().equalsIgnoreCase("S")) {
                rangoEdad = "6";
            }
            if (inforInicialEdit.getInfMayorA20().equalsIgnoreCase("S")) {
                rangoEdad = "7";
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="/** ...">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        inforInicialEdit = new OInforInicial();
        recuperaInfoInicial();
        if (selectHistOdonto.getPacientes().getSexo() == 'F') {
            flagCheckboxGenero = false;
        } else if (selectHistOdonto.getPacientes().getSexo() == 'M') {
            flagCheckboxGenero = true;
        }
        editMotivoConsulta = motivoConsultaFacade.findByHistOdon(selectHistOdonto);

        if (editMotivoConsulta == null) {
            System.out.println("no existe... ");
            editMotivoConsulta = new OMotivoConsulta();
            flagGrabarM = true;
        } else {
            System.out.println("existe... ");
            flagGrabarM = false;

        }
    }

    // </editor-fold>    
    /**
     * Creates a new instance of FichaMotivoJSFManagedBean
     */
    public Ficha1MotivoJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
