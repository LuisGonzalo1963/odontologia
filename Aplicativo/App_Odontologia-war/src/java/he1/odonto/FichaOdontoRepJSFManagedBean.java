/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OAntecedentesPf;
import he1.odonto.entities.ODiagnostico;
import he1.odonto.entities.OEnfProbActual;
import he1.odonto.entities.OEnfermedadPeriodontal;
import he1.odonto.entities.OFluorosis;
import he1.odonto.entities.OHigieneOralSimplificada;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OIndicesCpoCeo;
import he1.odonto.entities.OMalOclusion;
import he1.odonto.entities.OMotivoConsulta;
import he1.odonto.entities.OOdontograma;
import he1.odonto.entities.OPlanDxTerEdu;
import he1.odonto.entities.ORecMovilidad;
import he1.odonto.entities.OSignosVitales;
import he1.odonto.entities.OSistemaEstomatognico;
import he1.odonto.entities.OTratamiento;
import he1.odonto.sessions.OAntecedentesPfFacade;
import he1.odonto.sessions.ODiagnosticoFacade;
import he1.odonto.sessions.OEnfProbActualFacade;
import he1.odonto.sessions.OEnfermedadPeriodontalFacade;
import he1.odonto.sessions.OFluorosisFacade;
import he1.odonto.sessions.OHigieneOralSimplificadaFacade;
import he1.odonto.sessions.OIndicesCpoCeoFacade;
import he1.odonto.sessions.OMalOclusionFacade;
import he1.odonto.sessions.OMotivoConsultaFacade;
import he1.odonto.sessions.OOdontogramaFacade;
import he1.odonto.sessions.OPlanDxTerEduFacade;
import he1.odonto.sessions.ORecMovilidadFacade;
import he1.odonto.sessions.OSignosVitalesFacade;
import he1.odonto.sessions.OSistemaEstomatognicoFacade;
import he1.odonto.sessions.OTratamientoFacade;
import he1.sis.sessions.HojasDeEvolucionFacade;
import he1.sis.sessions.PacientesFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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
@ManagedBean(name = "repFichaOdonto")
@ViewScoped
public class FichaOdontoRepJSFManagedBean implements Serializable {

    @EJB
    private OMotivoConsultaFacade motivoConsultaFacade;
    @EJB
    private HojasDeEvolucionFacade hojasDeEvolucionFacade;
    @EJB
    private OOdontogramaFacade odontogramaFacade;
    @EJB
    private ORecMovilidadFacade recMovilidadFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private OSignosVitalesFacade signosVitalesFacade;
    @EJB
    private OSistemaEstomatognicoFacade oSistemaEstomatognicoFacade;
    @EJB
    private OHigieneOralSimplificadaFacade oHigieneOralSimplificadaFacade;
    @EJB
    private OEnfermedadPeriodontalFacade oEnfermedadPeriodontalFacade;
    @EJB
    private OMalOclusionFacade oMalOclusionFacade;
    @EJB
    private OFluorosisFacade oFluorosisFacade;
    @EJB
    private OIndicesCpoCeoFacade oIndicesCpoCeoFacade;
    @EJB
    private OPlanDxTerEduFacade planDxTerEduFacade;

    @EJB
    private ODiagnosticoFacade diagnosticoFacade;
    @EJB
    private OTratamientoFacade tratamientoFacade;
    @EJB
    private OAntecedentesPfFacade antecedentesPfFacade;
    @EJB
    private OEnfProbActualFacade enfProbActualFacade;

    private final OHistOdonto selectHistOdonto;

    private OMotivoConsulta selectedMotivoConsulta;
    private OEnfProbActual selectedEnfProbActual;

    private OOdontograma selectedOdontograma;
    private ORecMovilidad selectedRecMovilidad;
    private OSignosVitales selectedSignosVitales;
    private OEnfermedadPeriodontal selectedEnfermPeriod;
    private OMalOclusion selectedMalOclusion;
    private OFluorosis selectedFluorosis;
    private OIndicesCpoCeo selectedIndicesCpoCeo;

    private List<OSistemaEstomatognico> listSistEstom;
    private List<OHigieneOralSimplificada> listHigieneOralSimpl;
    private List<OPlanDxTerEdu> listPlanDxTerEdu;
    private List<ODiagnostico> listDiagnostico;
    private List<OTratamiento> listTratamientos;
    private List<OAntecedentesPf> listAntecedentesPf;

    private BigDecimal edad;
    private BigDecimal totPlaca;
    private BigDecimal totCalc;
    private BigDecimal totGing;

    // <editor-fold defaultstate="collapsed" desc="/** set y get variables">
    public List<OAntecedentesPf> getListAntecedentesPf() {
        return listAntecedentesPf;
    }

    public void setListAntecedentesPf(List<OAntecedentesPf> listAntecedentesPf) {
        this.listAntecedentesPf = listAntecedentesPf;
    }

    public OMotivoConsulta getSelectedMotivoConsulta() {
        return selectedMotivoConsulta;
    }

    public void setSelectedMotivoConsulta(OMotivoConsulta selectedMotivoConsulta) {
        this.selectedMotivoConsulta = selectedMotivoConsulta;
    }

    public OEnfProbActual getSelectedEnfProbActual() {
        return selectedEnfProbActual;
    }

    public void setSelectedEnfProbActual(OEnfProbActual selectedEnfProbActual) {
        this.selectedEnfProbActual = selectedEnfProbActual;
    }

    public List<OTratamiento> getListTratamientos() {
        return listTratamientos;
    }

    public void setListTratamientos(List<OTratamiento> listTratamientos) {
        this.listTratamientos = listTratamientos;
    }

    public List<ODiagnostico> getListDiagnostico() {
        return listDiagnostico;
    }

    public void setListDiagnostico(List<ODiagnostico> listDiagnostico) {
        this.listDiagnostico = listDiagnostico;
    }

    public List<OPlanDxTerEdu> getListPlanDxTerEdu() {
        return listPlanDxTerEdu;
    }

    public void setListPlanDxTerEdu(List<OPlanDxTerEdu> listPlanDxTerEdu) {
        this.listPlanDxTerEdu = listPlanDxTerEdu;
    }

    public BigDecimal getTotPlaca() {
        return totPlaca;
    }

    public void setTotPlaca(BigDecimal totPlaca) {
        this.totPlaca = totPlaca;
    }

    public BigDecimal getTotCalc() {
        return totCalc;
    }

    public void setTotCalc(BigDecimal totCalc) {
        this.totCalc = totCalc;
    }

    public BigDecimal getTotGing() {
        return totGing;
    }

    public void setTotGing(BigDecimal totGing) {
        this.totGing = totGing;
    }

    public OIndicesCpoCeo getSelectedIndicesCpoCeo() {
        return selectedIndicesCpoCeo;
    }

    public void setSelectedIndicesCpoCeo(OIndicesCpoCeo selectedIndicesCpoCeo) {
        this.selectedIndicesCpoCeo = selectedIndicesCpoCeo;
    }

    public OFluorosis getSelectedFluorosis() {
        return selectedFluorosis;
    }

    public void setSelectedFluorosis(OFluorosis selectedFluorosis) {
        this.selectedFluorosis = selectedFluorosis;
    }

    public OMalOclusion getSelectedMalOclusion() {
        return selectedMalOclusion;
    }

    public void setSelectedMalOclusion(OMalOclusion selectedMalOclusion) {
        this.selectedMalOclusion = selectedMalOclusion;
    }

    public OEnfermedadPeriodontal getSelectedEnfermPeriod() {
        return selectedEnfermPeriod;
    }

    public void setSelectedEnfermPeriod(OEnfermedadPeriodontal selectedEnfermPeriod) {
        this.selectedEnfermPeriod = selectedEnfermPeriod;
    }

    public OOdontograma getSelectedOdontograma() {
        return selectedOdontograma;
    }

    public void setSelectedOdontograma(OOdontograma selectedOdontograma) {
        this.selectedOdontograma = selectedOdontograma;
    }

    public ORecMovilidad getSelectedRecMovilidad() {
        return selectedRecMovilidad;
    }

    public void setSelectedRecMovilidad(ORecMovilidad selectedRecMovilidad) {
        this.selectedRecMovilidad = selectedRecMovilidad;
    }

    public BigDecimal getEdad() {
        return edad;
    }

    public void setEdad(BigDecimal edad) {
        this.edad = edad;
    }

    public OSignosVitales getSelectedSignosVitales() {
        return selectedSignosVitales;
    }

    public void setSelectedSignosVitales(OSignosVitales selectedSignosVitales) {
        this.selectedSignosVitales = selectedSignosVitales;
    }

    public List<OSistemaEstomatognico> getListSistEstom() {
        return listSistEstom;
    }

    public void setListSistEstom(List<OSistemaEstomatognico> listSistEstom) {
        this.listSistEstom = listSistEstom;
    }

    public List<OHigieneOralSimplificada> getListHigieneOralSimpl() {
        return listHigieneOralSimpl;
    }

    public void setListHigieneOralSimpl(List<OHigieneOralSimplificada> listHigieneOralSimpl) {
        this.listHigieneOralSimpl = listHigieneOralSimpl;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct">
    @PostConstruct
    public void init() {
        edad = pacientesFacade.edadPaciente(selectHistOdonto.getPacientes());
        selectedMotivoConsulta = motivoConsultaFacade.findByHistOdon(selectHistOdonto);
        selectedEnfProbActual = enfProbActualFacade.findByHistOdon(selectHistOdonto);
        listAntecedentesPf = antecedentesPfFacade.listByHistOdon(selectHistOdonto);
        selectedSignosVitales = signosVitalesFacade.findByHistOdon(selectHistOdonto);
        listSistEstom = oSistemaEstomatognicoFacade.listByHistOdon(selectHistOdonto);
        selectedOdontograma = odontogramaFacade.findByHistOdon(selectHistOdonto);
        selectedIndicesCpoCeo = oIndicesCpoCeoFacade.findIndice(selectedOdontograma);
        selectedRecMovilidad = recMovilidadFacade.findRecMov(selectedOdontograma);

        listHigieneOralSimpl = oHigieneOralSimplificadaFacade.listByHistOdon(selectHistOdonto);
        totPlaca = new BigDecimal(0);
        totCalc = new BigDecimal(0);
        totGing = new BigDecimal(0);
        if (!listHigieneOralSimpl.isEmpty()) {
            BigDecimal totPlacaX = new BigDecimal(0);
            BigDecimal totCalcX = new BigDecimal(0);
            BigDecimal totGingX = new BigDecimal(0);
            for (OHigieneOralSimplificada ohos : listHigieneOralSimpl) {
                if (ohos.getPbaId() != null) {
                    totPlacaX = totPlacaX.add(ohos.getPbaId());
                }
                if (ohos.getPcaId() != null) {
                    totCalcX = totCalcX.add(ohos.getPcaId());
                }
                if (ohos.getPgiId() != null) {
                    totGingX = totGingX.add(ohos.getPgiId());
                }
            }
            int nroPiezas = listHigieneOralSimpl.size();
            totPlaca = totPlacaX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
            totCalc = totCalcX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
            totGing = totGingX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
        }

        selectedEnfermPeriod = oEnfermedadPeriodontalFacade.findByHistOdon(selectHistOdonto);
        selectedMalOclusion = oMalOclusionFacade.findByHistOdon(selectHistOdonto);
        selectedFluorosis = oFluorosisFacade.findByHistOdon(selectHistOdonto);
        listPlanDxTerEdu = planDxTerEduFacade.listByHistOdon(selectHistOdonto);

        listDiagnostico = diagnosticoFacade.listByHistOdon(selectHistOdonto);
        listTratamientos = tratamientoFacade.listByHistOdon(selectHistOdonto);

    }

    // </editor-fold>
    /**
     * Creates a new instance of FichaOdontoRepJSFManagedBean
     */
    public FichaOdontoRepJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
