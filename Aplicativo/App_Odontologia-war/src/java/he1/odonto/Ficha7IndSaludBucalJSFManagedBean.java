/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OEnfermedadPeriodontal;
import he1.odonto.entities.OFluorosis;
import he1.odonto.entities.OHigieneOralSimplificada;
import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OMalOclusion;
import he1.odonto.sessions.OEnfermedadPeriodontalFacade;
import he1.odonto.sessions.OFluorosisFacade;
import he1.odonto.sessions.OHigieneOralSimplificadaFacade;
import he1.odonto.sessions.OMalOclusionFacade;
import he1.sis.entities.HojasDeEvolucion;
import he1.sis.sessions.HojasDeEvolucionFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "indSaludBucal")
@ViewScoped
public class Ficha7IndSaludBucalJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private HojasDeEvolucionFacade hojasDeEvolucionFacade;
    @EJB
    private OFluorosisFacade fluorosisFacade;
    @EJB
    private OMalOclusionFacade malOclusionFacade;
    @EJB
    private OEnfermedadPeriodontalFacade enfermPeriodontalFacade;
    @EJB
    private OHigieneOralSimplificadaFacade higOralSimpFacade;

    private final OHistOdonto selectHistOdonto;
    private OHigieneOralSimplificada higOralSimpNew;
    private OHigieneOralSimplificada higOralSimpSelect;
    private OEnfermedadPeriodontal enfPeriodNew;
    private HojasDeEvolucion hojaDeEvolucionSelect;
    private OMalOclusion malOclusionNew;
    private OFluorosis fluorosisNEw;

    private List<OHigieneOralSimplificada> listIndSaludBucal;
    private List<OEnfermedadPeriodontal> listEnfermPeriod;
    private List<OMalOclusion> listMalOclusión;
    private List<OFluorosis> listFluorosis;
    private BigDecimal codPd;
    private BigDecimal hoja;
    
    private Date fechaActual;
    private String FechaHoy;

    private boolean countPat;
    private boolean flagSave;
    private boolean value16;
    private boolean value17;
    private boolean value55;
    private boolean value11;
    private boolean value21;
    private boolean value51;
    private boolean value26;
    private boolean value27;
    private boolean value65;
    private boolean value36;
    private boolean value37;
    private boolean value75;
    private boolean value31;
    private boolean value41;
    private boolean value71;
    private boolean value46;
    private boolean value47;
    private boolean value85;

    private BigDecimal valuePla1;
    private BigDecimal valuePla2;
    private BigDecimal valuePla3;
    private BigDecimal valuePla4;
    private BigDecimal valuePla5;
    private BigDecimal valuePla6;

    private BigDecimal valueCal1;
    private BigDecimal valueCal2;
    private BigDecimal valueCal3;
    private BigDecimal valueCal4;
    private BigDecimal valueCal5;
    private BigDecimal valueCal6;

    private BigDecimal valueGin1;
    private BigDecimal valueGin2;
    private BigDecimal valueGin3;
    private BigDecimal valueGin4;
    private BigDecimal valueGin5;
    private BigDecimal valueGin6;

    private BigDecimal totPlaca;
    private BigDecimal totCalc;
    private BigDecimal totGing;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables">
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

    public HojasDeEvolucion getHojaDeEvolucionSelect() {
        return hojaDeEvolucionSelect;
    }

    //set y get
    public void setHojaDeEvolucionSelect(HojasDeEvolucion hojaDeEvolucionSelect) {
        this.hojaDeEvolucionSelect = hojaDeEvolucionSelect;
    }

    public OHigieneOralSimplificadaFacade getHigOralSimpFacade() {
        return higOralSimpFacade;
    }

    public void setHigOralSimpFacade(OHigieneOralSimplificadaFacade higOralSimpFacade) {
        this.higOralSimpFacade = higOralSimpFacade;
    }

    public List<OHigieneOralSimplificada> getListIndSaludBucal() {
        return listIndSaludBucal;
    }

    public void setListIndSaludBucal(List<OHigieneOralSimplificada> listIndSaludBucal) {
        this.listIndSaludBucal = listIndSaludBucal;
    }

    public BigDecimal getCodPd() {
        return codPd;
    }

    public void setCodPd(BigDecimal codPd) {
        this.codPd = codPd;
    }

    public List<OEnfermedadPeriodontal> getListEnfermPeriod() {
        return listEnfermPeriod;
    }

    public void setListEnfermPeriod(List<OEnfermedadPeriodontal> listEnfermPeriod) {
        this.listEnfermPeriod = listEnfermPeriod;
    }

    public OEnfermedadPeriodontal getEnfPeriodNew() {
        return enfPeriodNew;
    }

    public void setEnfPeriodNew(OEnfermedadPeriodontal enfPeriodNew) {
        this.enfPeriodNew = enfPeriodNew;
    }

    public List<OMalOclusion> getListMalOclusión() {
        return listMalOclusión;
    }

    public void setListMalOclusión(List<OMalOclusion> listMalOclusión) {
        this.listMalOclusión = listMalOclusión;
    }

    public List<OFluorosis> getListFluorosis() {
        return listFluorosis;
    }

    public void setListFluorosis(List<OFluorosis> listFluorosis) {
        this.listFluorosis = listFluorosis;
    }

    public OMalOclusion getMalOclusionNew() {
        return malOclusionNew;
    }

    public void setMalOclusionNew(OMalOclusion malOclusionNew) {
        this.malOclusionNew = malOclusionNew;
    }

    public OFluorosis getFluorosisNEw() {
        return fluorosisNEw;
    }

    public void setFluorosisNEw(OFluorosis fluorosisNEw) {
        this.fluorosisNEw = fluorosisNEw;
    }

    public boolean isFlagSave() {
        return flagSave;
    }

    public void setFlagSave(boolean flagSave) {
        this.flagSave = flagSave;
    }

    public OHigieneOralSimplificada getHigOralSimpSelect() {
        return higOralSimpSelect;
    }

    public void setHigOralSimpSelect(OHigieneOralSimplificada higOralSimpSelect) {
        this.higOralSimpSelect = higOralSimpSelect;
    }

    //
    public void recuperaEnfermPeriod() {
        listEnfermPeriod = enfermPeriodontalFacade.listByHistOdon(selectHistOdonto);
    }

    public void recuperaMalOclusion() {
        listMalOclusión = malOclusionFacade.listByHistOdon(selectHistOdonto);
    }

    public void recuperaFluorosis() {
        listFluorosis = fluorosisFacade.listByHistOdon(selectHistOdonto);
    }

    public boolean isValue16() {
        return value16;
    }

    public void setValue16(boolean value16) {
        this.value16 = value16;
    }

    public boolean isValue17() {
        return value17;
    }

    public void setValue17(boolean value17) {
        this.value17 = value17;
    }

    public boolean isValue55() {
        return value55;
    }

    public void setValue55(boolean value55) {
        this.value55 = value55;
    }

    public boolean isValue11() {
        return value11;
    }

    public void setValue11(boolean value11) {
        this.value11 = value11;
    }

    public boolean isValue21() {
        return value21;
    }

    public void setValue21(boolean value21) {
        this.value21 = value21;
    }

    public boolean isValue51() {
        return value51;
    }

    public void setValue51(boolean value51) {
        this.value51 = value51;
    }

    public boolean isValue26() {
        return value26;
    }

    public void setValue26(boolean value26) {
        this.value26 = value26;
    }

    public boolean isValue27() {
        return value27;
    }

    public void setValue27(boolean value27) {
        this.value27 = value27;
    }

    public boolean isValue65() {
        return value65;
    }

    public void setValue65(boolean value65) {
        this.value65 = value65;
    }

    public boolean isValue36() {
        return value36;
    }

    public void setValue36(boolean value36) {
        this.value36 = value36;
    }

    public boolean isValue37() {
        return value37;
    }

    public void setValue37(boolean value37) {
        this.value37 = value37;
    }

    public boolean isValue75() {
        return value75;
    }

    public void setValue75(boolean value75) {
        this.value75 = value75;
    }

    public boolean isValue31() {
        return value31;
    }

    public void setValue31(boolean value31) {
        this.value31 = value31;
    }

    public boolean isValue41() {
        return value41;
    }

    public void setValue41(boolean value41) {
        this.value41 = value41;
    }

    public boolean isValue71() {
        return value71;
    }

    public void setValue71(boolean value71) {
        this.value71 = value71;
    }

    public boolean isValue46() {
        return value46;
    }

    public void setValue46(boolean value46) {
        this.value46 = value46;
    }

    public boolean isValue47() {
        return value47;
    }

    public void setValue47(boolean value47) {
        this.value47 = value47;
    }

    public boolean isValue85() {
        return value85;
    }

    public void setValue85(boolean value85) {
        this.value85 = value85;
    }

    public BigDecimal getValuePla1() {
        return valuePla1;
    }

    public void setValuePla1(BigDecimal valuePla1) {
        this.valuePla1 = valuePla1;
    }

    public BigDecimal getValuePla2() {
        return valuePla2;
    }

    public void setValuePla2(BigDecimal valuePla2) {
        this.valuePla2 = valuePla2;
    }

    public BigDecimal getValuePla3() {
        return valuePla3;
    }

    public void setValuePla3(BigDecimal valuePla3) {
        this.valuePla3 = valuePla3;
    }

    public BigDecimal getValuePla4() {
        return valuePla4;
    }

    public void setValuePla4(BigDecimal valuePla4) {
        this.valuePla4 = valuePla4;
    }

    public BigDecimal getValuePla5() {
        return valuePla5;
    }

    public void setValuePla5(BigDecimal valuePla5) {
        this.valuePla5 = valuePla5;
    }

    public BigDecimal getValuePla6() {
        return valuePla6;
    }

    public void setValuePla6(BigDecimal valuePla6) {
        this.valuePla6 = valuePla6;
    }

    public BigDecimal getValueCal1() {
        return valueCal1;
    }

    public void setValueCal1(BigDecimal valueCal1) {
        this.valueCal1 = valueCal1;
    }

    public BigDecimal getValueCal2() {
        return valueCal2;
    }

    public void setValueCal2(BigDecimal valueCal2) {
        this.valueCal2 = valueCal2;
    }

    public BigDecimal getValueCal3() {
        return valueCal3;
    }

    public void setValueCal3(BigDecimal valueCal3) {
        this.valueCal3 = valueCal3;
    }

    public BigDecimal getValueCal4() {
        return valueCal4;
    }

    public void setValueCal4(BigDecimal valueCal4) {
        this.valueCal4 = valueCal4;
    }

    public BigDecimal getValueCal5() {
        return valueCal5;
    }

    public void setValueCal5(BigDecimal valueCal5) {
        this.valueCal5 = valueCal5;
    }

    public BigDecimal getValueCal6() {
        return valueCal6;
    }

    public void setValueCal6(BigDecimal valueCal6) {
        this.valueCal6 = valueCal6;
    }

    public BigDecimal getValueGin1() {
        return valueGin1;
    }

    public void setValueGin1(BigDecimal valueGin1) {
        this.valueGin1 = valueGin1;
    }

    public BigDecimal getValueGin2() {
        return valueGin2;
    }

    public void setValueGin2(BigDecimal valueGin2) {
        this.valueGin2 = valueGin2;
    }

    public BigDecimal getValueGin3() {
        return valueGin3;
    }

    public void setValueGin3(BigDecimal valueGin3) {
        this.valueGin3 = valueGin3;
    }

    public BigDecimal getValueGin4() {
        return valueGin4;
    }

    public void setValueGin4(BigDecimal valueGin4) {
        this.valueGin4 = valueGin4;
    }

    public BigDecimal getValueGin5() {
        return valueGin5;
    }

    public void setValueGin5(BigDecimal valueGin5) {
        this.valueGin5 = valueGin5;
    }

    public BigDecimal getValueGin6() {
        return valueGin6;
    }

    public void setValueGin6(BigDecimal valueGin6) {
        this.valueGin6 = valueGin6;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    // </editor-fold>
    //
    public void recuperaHigOralSimp() {
        System.out.println("selectHistOdonto "+selectHistOdonto);
        listIndSaludBucal = higOralSimpFacade.listByHistOdon(selectHistOdonto);
        totPlaca = new BigDecimal(0);
        totCalc = new BigDecimal(0);
        totGing = new BigDecimal(0);
        if (listIndSaludBucal.size() > 0) {
            BigDecimal totPlacaX = new BigDecimal(0);
            BigDecimal totCalcX = new BigDecimal(0);
            BigDecimal totGingX = new BigDecimal(0);
            for (OHigieneOralSimplificada ohos : listIndSaludBucal) {
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
            int nroPiezas = listIndSaludBucal.size();
            totPlaca = totPlacaX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
            totCalc = totCalcX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
            totGing = totGingX.divide(new BigDecimal(nroPiezas), 2, RoundingMode.HALF_UP);
        }

    }

    public void newHoSValueChange(ValueChangeEvent vce) {
        value16 = false;
        value17 = false;
        value55 = false;
        value11 = false;
        value21 = false;
        value51 = false;
        value26 = false;
        value27 = false;
        value65 = false;
        value36 = false;
        value37 = false;
        value75 = false;
        value31 = false;
        value41 = false;
        value71 = false;
        value46 = false;
        value47 = false;
        value85 = false;

        valuePla1 = null;
        valuePla2 = null;
        valuePla3 = null;
        valuePla4 = null;
        valuePla5 = null;
        valuePla6 = null;

        valueCal1 = null;
        valueCal2 = null;
        valueCal3 = null;
        valueCal4 = null;
        valueCal5 = null;
        valueCal6 = null;

        valueGin1 = null;
        valueGin2 = null;
        valueGin3 = null;
        valueGin4 = null;
        valueGin5 = null;
        valueGin6 = null;
    }

    public void newEnfPeriodValueChange(ValueChangeEvent vce) {
        enfPeriodNew = new OEnfermedadPeriodontal();
    }

    public void newMalOclusionValueChange(ValueChangeEvent vce) {
        malOclusionNew = new OMalOclusion();
    }

    public void newFluorosisValueChange(ValueChangeEvent vce) {
        fluorosisNEw = new OFluorosis();
    }

    //
    public void validaPiezaDental() {
        String sqlValidaPiezaDental = "SELECT count(*) FROM O_HIGIENE_ORAL_SIMPLIFICADA WHERE O_HIGIENE_ORAL_SIMPLIFICADA.PCN_NUMERO_HC = " + hojaDeEvolucionSelect.getPacientes().getNumeroHc() + " AND HPD_ID = " + codPd + " AND TRUNC(O_HIGIENE_ORAL_SIMPLIFICADA.HOS_FECHA) = " + "TO_DATE('" + FechaHoy + "','DD/MM/YYYY')";
        countPat = higOralSimpFacade.DienteExiste(sqlValidaPiezaDental);
        if (countPat) {
            flagSave = true;
            ponerMensajeInfo("ATENCIÓN", "Diente ya seleccionado!!!");
        } else {
            flagSave = false;
        }
    }

    public void cabHigiene() {
        higOralSimpNew.setHosId(BigDecimal.ZERO);
        higOralSimpNew.setoHistOdonto(selectHistOdonto);
        higOralSimpNew.setHosFecha(fechaActual);
        higOralSimpFacade.create(higOralSimpNew);
    }

    //
    public void grabarEdicionHigieneOs() {
        higOralSimpFacade.edit(higOralSimpSelect);
        recuperaHigOralSimp();
        ponerMensajeInfo("Grabación", "Información actualizada");
    }

    public void grabarEliminarHigienOs() {
        higOralSimpFacade.remove(higOralSimpSelect);
        recuperaHigOralSimp();
        ponerMensajeInfo("Grabación", "Información eliminada");
    }

    public void grabarHigieneOs() {
        if (value16 || value17 || value55) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value16) {
                higOralSimpNew.setHpdId(new BigDecimal(16));
            }
            if (value17) {
                higOralSimpNew.setHpdId(new BigDecimal(17));
            }
            if (value55) {
                higOralSimpNew.setHpdId(new BigDecimal(55));
            }
            higOralSimpNew.setPbaId(valuePla1);
            higOralSimpNew.setPcaId(valueCal1);
            higOralSimpNew.setPgiId(valueGin1);
            cabHigiene();
        }
        if (value11 || value21 || value51) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value11) {
                higOralSimpNew.setHpdId(new BigDecimal(11));
            }
            if (value21) {
                higOralSimpNew.setHpdId(new BigDecimal(21));
            }
            if (value51) {
                higOralSimpNew.setHpdId(new BigDecimal(51));
            }
            higOralSimpNew.setPbaId(valuePla2);
            higOralSimpNew.setPcaId(valueCal2);
            higOralSimpNew.setPgiId(valueGin2);
            cabHigiene();
        }
        if (value26 || value27 || value65) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value26) {
                higOralSimpNew.setHpdId(new BigDecimal(26));
            }
            if (value27) {
                higOralSimpNew.setHpdId(new BigDecimal(27));
            }
            if (value65) {
                higOralSimpNew.setHpdId(new BigDecimal(65));
            }
            higOralSimpNew.setPbaId(valuePla3);
            higOralSimpNew.setPcaId(valueCal3);
            higOralSimpNew.setPgiId(valueGin3);
            cabHigiene();
        }
        if (value36 || value37 || value75) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value36) {
                higOralSimpNew.setHpdId(new BigDecimal(36));
            }
            if (value37) {
                higOralSimpNew.setHpdId(new BigDecimal(37));
            }
            if (value75) {
                higOralSimpNew.setHpdId(new BigDecimal(75));
            }
            higOralSimpNew.setPbaId(valuePla4);
            higOralSimpNew.setPcaId(valueCal4);
            higOralSimpNew.setPgiId(valueGin4);
            cabHigiene();
        }
        if (value31 || value41 || value71) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value31) {
                higOralSimpNew.setHpdId(new BigDecimal(31));
            }
            if (value41) {
                higOralSimpNew.setHpdId(new BigDecimal(41));
            }
            if (value71) {
                higOralSimpNew.setHpdId(new BigDecimal(71));
            }
            higOralSimpNew.setPbaId(valuePla5);
            higOralSimpNew.setPcaId(valueCal5);
            higOralSimpNew.setPgiId(valueGin5);
            cabHigiene();
        }
        if (value46 || value47 || value85) {
            higOralSimpNew = new OHigieneOralSimplificada();
            if (value46) {
                higOralSimpNew.setHpdId(new BigDecimal(46));
            }
            if (value47) {
                higOralSimpNew.setHpdId(new BigDecimal(47));
            }
            if (value85) {
                higOralSimpNew.setHpdId(new BigDecimal(85));
            }
            higOralSimpNew.setPbaId(valuePla6);
            higOralSimpNew.setPcaId(valueCal6);
            higOralSimpNew.setPgiId(valueGin6);
            cabHigiene();
        }
        recuperaHigOralSimp();
        ponerMensajeInfo("Grabación", "Información registrada");
    }

    public void grabarEnfPeriod() {
        if (enfPeriodNew != null) {
            if (enfPeriodNew.getEpeId() == null) {
                enfPeriodNew.setEpeId(BigDecimal.ZERO);
                enfPeriodNew.setoHistOdonto(selectHistOdonto);
                enfPeriodNew.setEpeFecha(fechaActual);
                enfPeriodNew.setEpeClasificacion(enfPeriodNew.getEpeClasificacion());
                enfermPeriodontalFacade.create(enfPeriodNew);
                recuperaEnfermPeriod();
                System.out.println("grabación enferm periodoantal");
                ponerMensajeInfo("Grabación", "Información guardada");

            }
        }
    }

    public void grabarMalOclusion() {
        if (malOclusionNew != null) {
            if (malOclusionNew.getMocId() == null) {
                malOclusionNew.setMocId(BigDecimal.ZERO);
                malOclusionNew.setoHistOdonto(selectHistOdonto);
                malOclusionNew.setMocFecha(fechaActual);
                malOclusionNew.setMocClasificacion(malOclusionNew.getMocClasificacion());
                malOclusionFacade.create(malOclusionNew);

                recuperaMalOclusion();
                System.out.println("grabación mal oclucision");
                ponerMensajeInfo("Grabación", "Información guardada");

            }
        }
    }

    public void grabarFluorosis() {
        if (fluorosisNEw != null) {
            if (fluorosisNEw.getFluId() == null) {
                fluorosisNEw.setFluId(BigDecimal.ZERO);
                fluorosisNEw.setoHistOdonto(selectHistOdonto);
                fluorosisNEw.setFluFecha(fechaActual);
                fluorosisNEw.setFluClasificacion(fluorosisNEw.getFluClasificacion());
                fluorosisFacade.create(fluorosisNEw);
                recuperaFluorosis();
                System.out.println("grabación fluorosis");
                ponerMensajeInfo("Grabación", "Información guardada");

            }
        }
    }

    //
    @PostConstruct
    public void init() {

        fechaActual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FechaHoy = dateFormat.format(fechaActual);
        listIndSaludBucal = new ArrayList<>();
        listEnfermPeriod = new ArrayList<>();
        listMalOclusión = new ArrayList<>();
        listFluorosis = new ArrayList<>();
        higOralSimpSelect = new OHigieneOralSimplificada();

        recuperaHigOralSimp();
        recuperaEnfermPeriod();
        recuperaMalOclusion();
        recuperaFluorosis();

    }

    /**
     * Creates a new instance of Ficha7IndSaludBucalJSFManagedBean
     */
    public Ficha7IndSaludBucalJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
    }

}
