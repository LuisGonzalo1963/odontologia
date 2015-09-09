/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OAntecedentesPf;
import he1.odonto.entities.OEnfProbActual;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OMotivoConsulta;
import he1.odonto.entities.OOdontograma;
import he1.odonto.entities.OPlanDxTerEdu;
import he1.odonto.entities.OSistemaEstomatognico;
import he1.odonto.entities.OTratamiento;
import he1.odonto.sessions.OAntecedentesPfFacade;
import he1.odonto.sessions.OEnfProbActualFacade;
import he1.odonto.sessions.OHistOdontoFacade;
import he1.odonto.sessions.OMotivoConsultaFacade;
import he1.odonto.sessions.OOdontogramaFacade;
import he1.odonto.sessions.OPlanDxTerEduFacade;
import he1.odonto.sessions.OSistemaEstomatognicoFacade;
import he1.odonto.sessions.OTratamientoFacade;
import he1.sis.entities.DiagnosticosPaciente;
import he1.sis.entities.Pacientes;
import he1.sis.sessions.DiagnosticosPacienteFacade;
import he1.sis.sessions.PacientesFacade;
import he1.utilities.Utilitario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "repFichaOdon")
@ViewScoped
public class RepFichaOdonJSFManagedBean extends Utilitario implements Serializablee {

    private static final Logger log = Logger.getLogger(RepFichaOdonJSFManagedBean.class.getName());

    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private OHistOdontoFacade histOdontoFacade;
    @EJB
    private OMotivoConsultaFacade motivoConsultaFacade;
    @EJB
    private OEnfProbActualFacade enfProbActualFacade;
    @EJB
    private OAntecedentesPfFacade antecedentesPfFacade;
    @EJB
    private OSistemaEstomatognicoFacade oSistemaEstomatognicoFacade;
    @EJB
    private OPlanDxTerEduFacade planDxTerEduFacade;
    @EJB
    private DiagnosticosPacienteFacade diagPacienteFacade;
    @EJB
    private OTratamientoFacade tratamientoFacade;
    @EJB
    private OOdontogramaFacade odontogramaFacade;

    private Pacientes editPacientes;
    private OHistOdonto histOdonto;
    private OMotivoConsulta motivo;
    private OEnfProbActual enfermedad;
    private OOdontograma odontograma;

    private List<OAntecedentesPf> laPersFam;
    private List<OSistemaEstomatognico> lsistEst;
    private List<OPlanDxTerEdu> lplanDxTed;
    private List<DiagnosticosPaciente> ldxPcte;
    private List<OTratamiento> lttoPcte;
    private List<OOdontograma> lodontos;

    private Integer nroHC ;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get">
    public Integer getNroHC() {
        return nroHC;
    }

    public void setNroHC(Integer nroHC) {
        this.nroHC = nroHC;
    }

    public OOdontograma getOdontograma() {
        return odontograma;
    }

    public void setOdontograma(OOdontograma odontograma) {
        this.odontograma = odontograma;
    }

    public List<OOdontograma> getLodontos() {
        return lodontos;
    }

    public void setLodontos(List<OOdontograma> lodontos) {
        this.lodontos = lodontos;
    }

    public List<OTratamiento> getLttoPcte() {
        return lttoPcte;
    }

    public void setLttoPcte(List<OTratamiento> lttoPcte) {
        this.lttoPcte = lttoPcte;
    }

    public List<DiagnosticosPaciente> getLdxPcte() {
        return ldxPcte;
    }

    public void setLdxPcte(List<DiagnosticosPaciente> ldxPcte) {
        this.ldxPcte = ldxPcte;
    }

    public List<OPlanDxTerEdu> getLplanDxTed() {
        return lplanDxTed;
    }

    public void setLplanDxTed(List<OPlanDxTerEdu> lplanDxTed) {
        this.lplanDxTed = lplanDxTed;
    }

    public List<OSistemaEstomatognico> getLsistEst() {
        return lsistEst;
    }

    public void setLsistEst(List<OSistemaEstomatognico> lsistEst) {
        this.lsistEst = lsistEst;
    }

    public OMotivoConsulta getMotivo() {
        return motivo;
    }

    public void setMotivo(OMotivoConsulta motivo) {
        this.motivo = motivo;
    }

    public OEnfProbActual getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(OEnfProbActual enfermedad) {
        this.enfermedad = enfermedad;
    }

    public List<OAntecedentesPf> getLaPersFam() {
        return laPersFam;
    }

    public void setLaPersFam(List<OAntecedentesPf> laPersFam) {
        this.laPersFam = laPersFam;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** btnBuscarHc">
        public void btnBuscarHc() {

        editPacientes = pacientesFacade.findByHc(nroHC);
        if (editPacientes != null) {
            log.info("1 paciente...");
            histOdonto = histOdontoFacade.findByPaciente(editPacientes);
            if (histOdonto != null) {
                log.info("2 historia...");
                motivo = motivoConsultaFacade.findByHistOdon(histOdonto);
                enfermedad = enfProbActualFacade.findByHistOdon(histOdonto);
                laPersFam = antecedentesPfFacade.listByHistOdon(histOdonto);
                lsistEst = oSistemaEstomatognicoFacade.listByHistOdon(histOdonto);
                lplanDxTed = planDxTerEduFacade.listByHistOdon(histOdonto);
                ldxPcte = diagPacienteFacade.listDiagnosticoPacientes(editPacientes);
                lttoPcte = tratamientoFacade.listByHistOdon(histOdonto);
                lodontos = odontogramaFacade.listByHistOdon(histOdonto);
            }
        } else {
            ponerMensajeInfo("ATENCION", "NO existe el Paciente");
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** onRowSelectFecha">
        public void onRowSelectFecha() {
            log.log(Level.INFO, "fecha ...{0}", odontograma);
        }
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** Sxxxxxx">
    // </editor-fold>    

        

    /**
     * Creates a new instance of RepFichaOdonJSFManagedBean
     */
    public RepFichaOdonJSFManagedBean() {
    }

}
