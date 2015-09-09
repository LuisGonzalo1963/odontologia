/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.entities.OIndicesCpoCeo;
import he1.odonto.entities.OOdontoRealizado;
import he1.odonto.entities.OOdontograma;
import he1.odonto.entities.ORecMovilidad;
import he1.odonto.entities.OSimbologiaOdonto;
import he1.odonto.sessions.OIndicesCpoCeoFacade;
import he1.odonto.sessions.OOdontoRealizadoFacade;
import he1.odonto.sessions.OOdontogramaFacade;
import he1.odonto.sessions.ORecMovilidadFacade;
import he1.odonto.sessions.OSimbologiaOdontoFacade;
import he1.odonto.sessions.VPermanentesFacade;
import he1.odonto.sessions.VTemporalesFacade;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "odonto")
@ViewScoped
public class Ficha6OdontogramaJSFManagedBean extends Utilitario implements Serializable {

    private static final Logger LOG = Logger.getLogger(Ficha6OdontogramaJSFManagedBean.class.getName());
    @EJB
    private OOdontoRealizadoFacade odontoRealizadoFacade;

    @EJB
    private ORecMovilidadFacade recMovilidadFacade;

    @EJB
    private OSimbologiaOdontoFacade oSimbologiaOdontoFacade;
    @EJB
    private OOdontogramaFacade odontogramaFacade;
    @EJB
    private VTemporalesFacade vTemporalesFacade;
    @EJB
    private VPermanentesFacade vPermanentesFacade;
    @EJB
    private OIndicesCpoCeoFacade indicesCpoCeoFacade;
    @EJB
    private TurnosCeFacade turnosCeFacade;

    private List<SelectItem> itemListSimbologia;

    private List<OOdontoRealizado> listOdontoRealizados;
    private List<OOdontoRealizado> listOdontoRealizadosOdonto;
    private List<OOdontoRealizado> listOdontoRealizadosVerif;
    private List<OOdontograma> listOdontograma;

    private OSimbologiaOdonto selectSimbologia;
    private ORecMovilidad selectRecMovilidad;
    private OOdontograma odontograma;
    private OOdontograma editOdontograma;
    private OIndicesCpoCeo selectIndCpoCeo;
    private OOdontoRealizado editOdontoRealizado;
    private TurnosCe updateTurnoCe;

    private final OHistOdonto selectHistOdonto;

    private static final String FILE_PATH = "/resources/imagenSistema/";

    private String piezaDental;
    private String oclusal;
    private String vestibular;
    private String palatino;
    private String mesial;
    private String distal;
    private String obsCara1;
    private String obsCara2;
    private String obsCara3;
    private String obsCara4;
    private String obsCara5;
    private String infPDentalAnt;

    private String protesisDesde;
    private String protesisHasta;
    private String protesisFija;
    private String protesisRemovible;

    private String siNoProt;

    private String indexTabView;

    private String tipoMarcar;
    private final String codMedico;

    private StringBuilder path;
    private StringBuilder pathEdit;
    private StringBuilder nomGraficoPd;

    private BigDecimal codSimbologia;
    private BigDecimal nroOdoId;

    private boolean flagTodo;
    private boolean renderSimb0;
    private boolean renderSimb1;
    private boolean renderPtSup;
    private boolean renderPtInf;

    private boolean flagGraficar;
    private boolean flagBtnGrabarRec;
    private boolean flagPanelTodo;
    private boolean flagPanelPartes;
    private boolean flagBtnGrabarEdit;

    private int indexTab;
    // <editor-fold defaultstate="collapsed" desc="/** Variables Piezas dentales">
    private String diente = null;
    private String foto18 = null;
    private String foto17 = null;
    private String foto16 = null;
    private String foto15 = null;
    private String foto14 = null;
    private String foto13 = null;
    private String foto12 = null;
    private String foto11 = null;

    private String foto21 = null;
    private String foto22 = null;
    private String foto23 = null;
    private String foto24 = null;
    private String foto25 = null;
    private String foto26 = null;
    private String foto27 = null;
    private String foto28 = null;

    private String foto48 = null;
    private String foto47 = null;
    private String foto46 = null;
    private String foto45 = null;
    private String foto44 = null;
    private String foto43 = null;
    private String foto42 = null;
    private String foto41 = null;

    private String foto31 = null;
    private String foto32 = null;
    private String foto33 = null;
    private String foto34 = null;
    private String foto35 = null;
    private String foto36 = null;
    private String foto37 = null;
    private String foto38 = null;

    private String foto55 = null;
    private String foto54 = null;
    private String foto53 = null;
    private String foto52 = null;
    private String foto51 = null;

    private String foto61 = null;
    private String foto62 = null;
    private String foto63 = null;
    private String foto64 = null;
    private String foto65 = null;

    private String foto85 = null;
    private String foto84 = null;
    private String foto83 = null;
    private String foto82 = null;
    private String foto81 = null;

    private String foto71 = null;
    private String foto72 = null;
    private String foto73 = null;
    private String foto74 = null;
    private String foto75 = null;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de variables PDental">
    public String getDiente() {
        return diente;
    }

    public void setDiente(String diente) {
        this.diente = diente;
    }

    public String getFoto18() {
        return foto18;
    }

    public void setFoto18(String foto18) {
        this.foto18 = foto18;
    }

    public String getFoto17() {
        return foto17;
    }

    public void setFoto17(String foto17) {
        this.foto17 = foto17;
    }

    public String getFoto16() {
        return foto16;
    }

    public void setFoto16(String foto16) {
        this.foto16 = foto16;
    }

    public String getFoto15() {
        return foto15;
    }

    public void setFoto15(String foto15) {
        this.foto15 = foto15;
    }

    public String getFoto14() {
        return foto14;
    }

    public void setFoto14(String foto14) {
        this.foto14 = foto14;
    }

    public String getFoto13() {
        return foto13;
    }

    public void setFoto13(String foto13) {
        this.foto13 = foto13;
    }

    public String getFoto12() {
        return foto12;
    }

    public void setFoto12(String foto12) {
        this.foto12 = foto12;
    }

    public String getFoto11() {
        return foto11;
    }

    public void setFoto11(String foto11) {
        this.foto11 = foto11;
    }

    public String getFoto21() {
        return foto21;
    }

    public void setFoto21(String foto21) {
        this.foto21 = foto21;
    }

    public String getFoto22() {
        return foto22;
    }

    public void setFoto22(String foto22) {
        this.foto22 = foto22;
    }

    public String getFoto23() {
        return foto23;
    }

    public void setFoto23(String foto23) {
        this.foto23 = foto23;
    }

    public String getFoto24() {
        return foto24;
    }

    public void setFoto24(String foto24) {
        this.foto24 = foto24;
    }

    public String getFoto25() {
        return foto25;
    }

    public void setFoto25(String foto25) {
        this.foto25 = foto25;
    }

    public String getFoto26() {
        return foto26;
    }

    public void setFoto26(String foto26) {
        this.foto26 = foto26;
    }

    public String getFoto27() {
        return foto27;
    }

    public void setFoto27(String foto27) {
        this.foto27 = foto27;
    }

    public String getFoto28() {
        return foto28;
    }

    public void setFoto28(String foto28) {
        this.foto28 = foto28;
    }

    public String getFoto48() {
        return foto48;
    }

    public void setFoto48(String foto48) {
        this.foto48 = foto48;
    }

    public String getFoto47() {
        return foto47;
    }

    public void setFoto47(String foto47) {
        this.foto47 = foto47;
    }

    public String getFoto46() {
        return foto46;
    }

    public void setFoto46(String foto46) {
        this.foto46 = foto46;
    }

    public String getFoto45() {
        return foto45;
    }

    public void setFoto45(String foto45) {
        this.foto45 = foto45;
    }

    public String getFoto44() {
        return foto44;
    }

    public void setFoto44(String foto44) {
        this.foto44 = foto44;
    }

    public String getFoto43() {
        return foto43;
    }

    public void setFoto43(String foto43) {
        this.foto43 = foto43;
    }

    public String getFoto42() {
        return foto42;
    }

    public void setFoto42(String foto42) {
        this.foto42 = foto42;
    }

    public String getFoto41() {
        return foto41;
    }

    public void setFoto41(String foto41) {
        this.foto41 = foto41;
    }

    public String getFoto31() {
        return foto31;
    }

    public void setFoto31(String foto31) {
        this.foto31 = foto31;
    }

    public String getFoto32() {
        return foto32;
    }

    public void setFoto32(String foto32) {
        this.foto32 = foto32;
    }

    public String getFoto33() {
        return foto33;
    }

    public void setFoto33(String foto33) {
        this.foto33 = foto33;
    }

    public String getFoto34() {
        return foto34;
    }

    public void setFoto34(String foto34) {
        this.foto34 = foto34;
    }

    public String getFoto35() {
        return foto35;
    }

    public void setFoto35(String foto35) {
        this.foto35 = foto35;
    }

    public String getFoto36() {
        return foto36;
    }

    public void setFoto36(String foto36) {
        this.foto36 = foto36;
    }

    public String getFoto37() {
        return foto37;
    }

    public void setFoto37(String foto37) {
        this.foto37 = foto37;
    }

    public String getFoto38() {
        return foto38;
    }

    public void setFoto38(String foto38) {
        this.foto38 = foto38;
    }

    public String getFoto55() {
        return foto55;
    }

    public void setFoto55(String foto55) {
        this.foto55 = foto55;
    }

    public String getFoto54() {
        return foto54;
    }

    public void setFoto54(String foto54) {
        this.foto54 = foto54;
    }

    public String getFoto53() {
        return foto53;
    }

    public void setFoto53(String foto53) {
        this.foto53 = foto53;
    }

    public String getFoto52() {
        return foto52;
    }

    public void setFoto52(String foto52) {
        this.foto52 = foto52;
    }

    public String getFoto51() {
        return foto51;
    }

    public void setFoto51(String foto51) {
        this.foto51 = foto51;
    }

    public String getFoto61() {
        return foto61;
    }

    public void setFoto61(String foto61) {
        this.foto61 = foto61;
    }

    public String getFoto62() {
        return foto62;
    }

    public void setFoto62(String foto62) {
        this.foto62 = foto62;
    }

    public String getFoto63() {
        return foto63;
    }

    public void setFoto63(String foto63) {
        this.foto63 = foto63;
    }

    public String getFoto64() {
        return foto64;
    }

    public void setFoto64(String foto64) {
        this.foto64 = foto64;
    }

    public String getFoto65() {
        return foto65;
    }

    public void setFoto65(String foto65) {
        this.foto65 = foto65;
    }

    public String getFoto85() {
        return foto85;
    }

    public void setFoto85(String foto85) {
        this.foto85 = foto85;
    }

    public String getFoto84() {
        return foto84;
    }

    public void setFoto84(String foto84) {
        this.foto84 = foto84;
    }

    public String getFoto83() {
        return foto83;
    }

    public void setFoto83(String foto83) {
        this.foto83 = foto83;
    }

    public String getFoto82() {
        return foto82;
    }

    public void setFoto82(String foto82) {
        this.foto82 = foto82;
    }

    public String getFoto81() {
        return foto81;
    }

    public void setFoto81(String foto81) {
        this.foto81 = foto81;
    }

    public String getFoto71() {
        return foto71;
    }

    public void setFoto71(String foto71) {
        this.foto71 = foto71;
    }

    public String getFoto72() {
        return foto72;
    }

    public void setFoto72(String foto72) {
        this.foto72 = foto72;
    }

    public String getFoto73() {
        return foto73;
    }

    public void setFoto73(String foto73) {
        this.foto73 = foto73;
    }

    public String getFoto74() {
        return foto74;
    }

    public void setFoto74(String foto74) {
        this.foto74 = foto74;
    }

    public String getFoto75() {
        return foto75;
    }

    public void setFoto75(String foto75) {
        this.foto75 = foto75;
    }

    public String getPiezaDental() {
        return piezaDental;
    }

    public void setPiezaDental(String piezaDental) {
        this.piezaDental = piezaDental;
    }

    public BigDecimal getCodSimbologia() {
        return codSimbologia;
    }

    public void setCodSimbologia(BigDecimal codSimbologia) {
        this.codSimbologia = codSimbologia;
    }

    public List<SelectItem> getItemListSimbologia() {
        return itemListSimbologia;
    }

    public void setItemListSimbologia(List<SelectItem> itemListSimbologia) {
        this.itemListSimbologia = itemListSimbologia;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de Variables">
    public boolean isFlagTodo() {
        return flagTodo;
    }

    public void setFlagTodo(boolean flagTodo) {
        this.flagTodo = flagTodo;
    }

    public boolean isFlagBtnGrabarEdit() {
        return flagBtnGrabarEdit;
    }

    public void setFlagBtnGrabarEdit(boolean flagBtnGrabarEdit) {
        this.flagBtnGrabarEdit = flagBtnGrabarEdit;
    }

    public boolean isFlagPanelTodo() {
        return flagPanelTodo;
    }

    public void setFlagPanelTodo(boolean flagPanelTodo) {
        this.flagPanelTodo = flagPanelTodo;
    }

    public boolean isFlagPanelPartes() {
        return flagPanelPartes;
    }

    public void setFlagPanelPartes(boolean flagPanelPartes) {
        this.flagPanelPartes = flagPanelPartes;
    }

    public String getTipoMarcar() {
        return tipoMarcar;
    }

    public void setTipoMarcar(String tipoMarcar) {
        this.tipoMarcar = tipoMarcar;
    }

    public List<OOdontoRealizado> getListOdontoRealizadosOdonto() {
        return listOdontoRealizadosOdonto;
    }

    public void setListOdontoRealizadosOdonto(List<OOdontoRealizado> listOdontoRealizadosOdonto) {
        this.listOdontoRealizadosOdonto = listOdontoRealizadosOdonto;
    }

    public String getObsCara1() {
        return obsCara1;
    }

    public List<OOdontoRealizado> getListOdontoRealizados() {
        return listOdontoRealizados;
    }

    public void setListOdontoRealizados(List<OOdontoRealizado> listOdontoRealizados) {
        this.listOdontoRealizados = listOdontoRealizados;
    }

    public void setObsCara1(String obsCara1) {
        this.obsCara1 = obsCara1;
    }

    public String getObsCara2() {
        return obsCara2;
    }

    public void setObsCara2(String obsCara2) {
        this.obsCara2 = obsCara2;
    }

    public String getObsCara3() {
        return obsCara3;
    }

    public void setObsCara3(String obsCara3) {
        this.obsCara3 = obsCara3;
    }

    public String getObsCara4() {
        return obsCara4;
    }

    public void setObsCara4(String obsCara4) {
        this.obsCara4 = obsCara4;
    }

    public String getObsCara5() {
        return obsCara5;
    }

    public void setObsCara5(String obsCara5) {
        this.obsCara5 = obsCara5;
    }

    public boolean isFlagBtnGrabarRec() {
        return flagBtnGrabarRec;
    }

    public void setFlagBtnGrabarRec(boolean flagBtnGrabarRec) {
        this.flagBtnGrabarRec = flagBtnGrabarRec;
    }

    public OOdontograma getOdontograma() {
        return odontograma;
    }

    public void setOdontograma(OOdontograma odontograma) {
        this.odontograma = odontograma;
    }

    public ORecMovilidad getSelectRecMovilidad() {
        return selectRecMovilidad;
    }

    public void setSelectRecMovilidad(ORecMovilidad selectRecMovilidad) {
        this.selectRecMovilidad = selectRecMovilidad;
    }

    public int getIndexTab() {
        return indexTab;
    }

    public void setIndexTab(int indexTab) {
        this.indexTab = indexTab;
    }

    public boolean isRenderSimb0() {
        return renderSimb0;
    }

    public void setRenderSimb0(boolean renderSimb0) {
        this.renderSimb0 = renderSimb0;
    }

    public boolean isRenderSimb1() {
        return renderSimb1;
    }

    public void setRenderSimb1(boolean renderSimb1) {
        this.renderSimb1 = renderSimb1;
    }

    public String getOclusal() {
        return oclusal;
    }

    public void setOclusal(String oclusal) {
        this.oclusal = oclusal;
    }

    public String getVestibular() {
        return vestibular;
    }

    public void setVestibular(String vestibular) {
        this.vestibular = vestibular;
    }

    public String getPalatino() {
        return palatino;
    }

    public void setPalatino(String palatino) {
        this.palatino = palatino;
    }

    public String getMesial() {
        return mesial;
    }

    public void setMesial(String mesial) {
        this.mesial = mesial;
    }

    public String getDistal() {
        return distal;
    }

    public void setDistal(String distal) {
        this.distal = distal;
    }

    public String getSiNoProt() {
        return siNoProt;
    }

    public void setSiNoProt(String siNoProt) {
        this.siNoProt = siNoProt;
    }

    public boolean isRenderPtSup() {
        return renderPtSup;
    }

    public void setRenderPtSup(boolean renderPtSup) {
        this.renderPtSup = renderPtSup;
    }

    public boolean isRenderPtInf() {
        return renderPtInf;
    }

    public void setRenderPtInf(boolean renderPtInf) {
        this.renderPtInf = renderPtInf;
    }

    public String getProtesisDesde() {
        return protesisDesde;
    }

    public void setProtesisDesde(String protesisDesde) {
        this.protesisDesde = protesisDesde;
    }

    public String getProtesisHasta() {
        return protesisHasta;
    }

    public void setProtesisHasta(String protesisHasta) {
        this.protesisHasta = protesisHasta;
    }

    public String getProtesisFija() {
        return protesisFija;
    }

    public void setProtesisFija(String protesisFija) {
        this.protesisFija = protesisFija;
    }

    public String getProtesisRemovible() {
        return protesisRemovible;
    }

    public void setProtesisRemovible(String protesisRemovible) {
        this.protesisRemovible = protesisRemovible;
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** Parámetros">
    public String piezaDentalSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("diente");
    }

    //Selecciona pieza dental
    public void param(ActionEvent ae) {
        diente = piezaDentalSelec();
        piezaDental = piezaDentalSelec();
        listaSimbologia();
        System.out.println("pieza dental: " + piezaDental);
    }

    //Selecciona pieza dental
    public void paramEdit(ActionEvent ae) {
        obsCara1 = null;
        obsCara2 = null;
        obsCara3 = null;
        obsCara4 = null;
        obsCara5 = null;
        diente = piezaDentalSelec();
        piezaDental = piezaDentalSelec();
        listOdontoRealizados = odontoRealizadoFacade.listByHisPdental(selectHistOdonto, piezaDental);
        recuperaPartesGraficadas();
        listaSimbologia();

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** recuperaPartesGraficadas">
    public void recuperaPartesGraficadas() {
        if (piezaDental.substring(0, 1).equalsIgnoreCase("1") || piezaDental.substring(0, 1).equalsIgnoreCase("2") || piezaDental.substring(0, 1).equalsIgnoreCase("3") || piezaDental.substring(0, 1).equalsIgnoreCase("4")) {
            diente = "ODO_PDENTALV_".concat(piezaDental);
            String infPieza = odontogramaFacade.infPiezaDental(diente, odontograma);
            infPDentalAnt = odontogramaFacade.infPiezaDental(diente, odontograma);

            if (infPieza.equalsIgnoreCase("diente.png")) {
                oclusal = "s";
                vestibular = "s";
                palatino = "s";
                mesial = "s";
                distal = "s";
                flagGraficar = false;
            }

            if (infPieza.substring(0, 2).equalsIgnoreCase("d_")) {
                oclusal = infPieza.substring(2, 3);
                vestibular = infPieza.substring(3, 4);
                palatino = infPieza.substring(4, 5);
                mesial = infPieza.substring(5, 6);
                distal = infPieza.substring(6, 7);
            }
        }
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** selectSimbValueChange()">
    public void selectSimbValueChange() {
        selectSimbologia = oSimbologiaOdontoFacade.find(codSimbologia);
        if (selectSimbologia.getSodParametro().equalsIgnoreCase("0")) {
            //System.out.println("pieza dental por partes " + infPDentalAnt);
            flagTodo = false;
            setRenderSimb0(false);
            setRenderSimb1(true);
        }

        if (selectSimbologia.getSodParametro().equalsIgnoreCase("1")) {
            //System.out.println("pieza dental total " + infPDentalAnt);
            flagTodo = true;
            setRenderSimb0(false);
            setRenderSimb1(false);
        }
    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** listaSimbologia">
    public void listaSimbologia() {
        //itemListSimbologia.clear();
        List<OSimbologiaOdonto> listarSimbologia = new ArrayList<>();
        listarSimbologia = oSimbologiaOdontoFacade.listSimbologia();
        if (listarSimbologia != null) {
            this.itemListSimbologia = new ArrayList<>();
            for (OSimbologiaOdonto simbol : listarSimbologia) {
                this.itemListSimbologia.add(new SelectItem(simbol.getSodId(), simbol.getSodDescripcion()));
            }
        }
    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** graficar Odontograma">
    public void btnGraficarOdonto() {
        path = new StringBuilder();
        path.append(FILE_PATH);

        if (selectSimbologia.getSodParametro().equalsIgnoreCase("0")) {
            path.append("d_").append(oclusal.concat(vestibular).concat(palatino).concat(mesial).concat(distal)).append(".png");
            //System.out.println("path 0 " + path.toString());

        } else if (selectSimbologia.getSodParametro().equalsIgnoreCase("1")) {
            path.append(selectSimbologia.getSodAbreviatura()).append(".png");
            //System.out.println("path 1 " + path.toString());
        }
        //Grupo 1
        if (piezaDental.equalsIgnoreCase("18")) {
            foto18 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("17")) {
            foto17 = path.toString();

        }
        if (piezaDental.equalsIgnoreCase("16")) {
            foto16 = path.toString();

        }
        if (piezaDental.equalsIgnoreCase("15")) {
            foto15 = path.toString();

        }
        if (piezaDental.equalsIgnoreCase("14")) {
            foto14 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("13")) {
            foto13 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("12")) {
            foto12 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("11")) {
            foto11 = path.toString();
        }
        //Grupo 2
        if (piezaDental.equalsIgnoreCase("21")) {
            foto21 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("22")) {
            foto22 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("23")) {
            foto23 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("24")) {
            foto24 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("25")) {
            foto25 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("26")) {
            foto26 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("27")) {
            foto27 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("28")) {
            foto28 = path.toString();
        }
        //Grupo 3
        if (piezaDental.equalsIgnoreCase("48")) {
            foto48 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("47")) {
            foto47 = path.toString();
            //selectedOdontograma.setOdoPdentalv47(path.toString());
        }
        if (piezaDental.equalsIgnoreCase("46")) {
            foto46 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("45")) {
            foto45 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("44")) {
            foto44 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("43")) {
            foto43 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("42")) {
            foto42 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("41")) {
            foto41 = path.toString();
        }

        //Grupo 4
        if (piezaDental.equalsIgnoreCase("31")) {
            foto31 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("32")) {
            foto32 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("33")) {
            foto33 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("34")) {
            foto34 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("35")) {
            foto35 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("36")) {
            foto36 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("37")) {
            foto37 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("38")) {
            foto38 = path.toString();
        }
        //Lingual
        if (piezaDental.equalsIgnoreCase("55")) {
            foto55 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("54")) {
            foto54 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("53")) {
            foto53 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("52")) {
            foto52 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("51")) {
            foto51 = path.toString();
        }
        //
        if (piezaDental.equalsIgnoreCase("61")) {
            foto61 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("62")) {
            foto62 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("63")) {
            foto63 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("64")) {
            foto64 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("65")) {
            foto65 = path.toString();
        }
        //
        if (piezaDental.equalsIgnoreCase("85")) {
            foto85 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("84")) {
            foto84 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("83")) {
            foto83 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("82")) {
            foto82 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("81")) {
            foto81 = path.toString();
        }
        //
        if (piezaDental.equalsIgnoreCase("71")) {
            foto71 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("72")) {
            foto72 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("73")) {
            foto73 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("74")) {
            foto74 = path.toString();
        }
        if (piezaDental.equalsIgnoreCase("75")) {
            foto75 = path.toString();
        }

        if (indexTabView.equalsIgnoreCase("tab11")) {
            indexTab = 0;
        }
        if (indexTabView.equalsIgnoreCase("tab12")) {
            indexTab = 1;
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** graficar Protesis Total">
    public void buttonAction(ActionEvent actionEvent) {
        //System.out.println("hola............preuba");
    }

    public void btnGraficarPTotal() {
        //System.out.println("hola....");
        String protAzul = "diente.png";
        if (siNoProt.equalsIgnoreCase("S")) {
            protAzul = "d_prttr.png";
        } else if (siNoProt.equalsIgnoreCase("N")) {
            protAzul = "d_prtta.png";
        }
        if (renderPtSup) {
            //System.out.println("pintar todo superior");
            foto18 = FILE_PATH.concat(protAzul);
            foto17 = FILE_PATH.concat(protAzul);
            foto16 = FILE_PATH.concat(protAzul);
            foto15 = FILE_PATH.concat(protAzul);
            foto14 = FILE_PATH.concat(protAzul);
            foto13 = FILE_PATH.concat(protAzul);
            foto12 = FILE_PATH.concat(protAzul);
            foto11 = FILE_PATH.concat(protAzul);

            foto21 = FILE_PATH.concat(protAzul);
            foto22 = FILE_PATH.concat(protAzul);
            foto23 = FILE_PATH.concat(protAzul);
            foto24 = FILE_PATH.concat(protAzul);
            foto25 = FILE_PATH.concat(protAzul);
            foto26 = FILE_PATH.concat(protAzul);
            foto27 = FILE_PATH.concat(protAzul);
            foto28 = FILE_PATH.concat(protAzul);
        }
        if (renderPtInf) {
            //System.out.println("pintar todo inferior");
            foto48 = FILE_PATH.concat(protAzul);
            foto47 = FILE_PATH.concat(protAzul);
            foto46 = FILE_PATH.concat(protAzul);
            foto45 = FILE_PATH.concat(protAzul);
            foto44 = FILE_PATH.concat(protAzul);
            foto43 = FILE_PATH.concat(protAzul);
            foto42 = FILE_PATH.concat(protAzul);
            foto41 = FILE_PATH.concat(protAzul);

            foto31 = FILE_PATH.concat(protAzul);
            foto32 = FILE_PATH.concat(protAzul);
            foto33 = FILE_PATH.concat(protAzul);
            foto34 = FILE_PATH.concat(protAzul);
            foto35 = FILE_PATH.concat(protAzul);
            foto36 = FILE_PATH.concat(protAzul);
            foto37 = FILE_PATH.concat(protAzul);
            foto38 = FILE_PATH.concat(protAzul);
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** MarcarOdontoProtesisFija">
    public void btnGraficarPFija() {
        //System.out.println("prot fija.............");
        int xd = Integer.parseInt(protesisDesde);
        int xh = Integer.parseInt(protesisHasta);
        //
        if (protesisDesde.substring(0, 1).equalsIgnoreCase(protesisHasta.substring(0, 1))) {
            //System.out.println("en el mismo cuadrante....");
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("1")) {
                marcarProtFija_1(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("2")) {
                marcarProtFija_2(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("3")) {
                marcarProtFija_3(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("4")) {
                marcarProtFija_4(xd, xh);
            }
        } else {
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("1") || protesisHasta.substring(0, 1).equalsIgnoreCase("2")) {
                //System.out.println("cuadrante...I y II....");
                marcarProtFija_1_2(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("4") || protesisHasta.substring(0, 1).equalsIgnoreCase("3")) {
                //System.out.println("cuadrante...IV y III....");
                marcarProtFija_4_3(xd, xh);
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija_1">
    public void marcarProtFija_1(int desde, int hasta) {

        int x1, x2;
        for (x1 = desde; x1 >= hasta; x1--) {
            if (x1 == 17) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto17 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto17 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto17 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto17 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto17 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //16
            if (x1 == 16) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto16 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto16 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto16 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto16 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto16 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //15
            if (x1 == 15) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto15 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto15 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto15 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto15 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto15 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //14
            if (x1 == 14) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto14 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto14 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto14 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto14 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto14 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //13
            if (x1 == 13) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto13 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto13 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto13 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto13 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto13 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //12
            if (x1 == 12) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto12 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto12 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto12 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto12 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto12 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //11
            if (x1 == 11) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto11 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto11 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto11 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto11 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto11 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija_2">
    public void marcarProtFija_2(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 <= hasta; x1++) {
            if (x1 == 21) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto21 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto21 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto21 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto21 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto21 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //22
            if (x1 == 22) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto22 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto22 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto22 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto22 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto22 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //23
            if (x1 == 23) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto23 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto23 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto23 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto23 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto23 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //24
            if (x1 == 24) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto24 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto24 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto24 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto24 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto24 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //25
            if (x1 == 25) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto25 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto25 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto25 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto25 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto25 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //26
            if (x1 == 26) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto26 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto26 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto26 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto26 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto26 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //27
            if (x1 == 27) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto27 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto27 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto27 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto27 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto27 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija_3">
    public void marcarProtFija_3(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 <= hasta; x1++) {
            //System.out.println("x1 " + x1);
            if (x1 == 31) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto31 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto31 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto31 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto31 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto31 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //32
            if (x1 == 32) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto32 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto32 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto32 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto32 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto32 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //33
            if (x1 == 33) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto33 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto33 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto33 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto33 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto33 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //34
            if (x1 == 34) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto34 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto34 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto34 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto34 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto34 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //35
            if (x1 == 35) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto35 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto35 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto35 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto35 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto35 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //36
            if (x1 == 36) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto36 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto36 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto36 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto36 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto36 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //37
            if (x1 == 37) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto37 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto37 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto37 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto37 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto37 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija_4">
    public void marcarProtFija_4(int desde, int hasta) {

        int x1, x2;
        for (x1 = desde; x1 >= hasta; x1--) {
            if (x1 == 47) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto47 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto47 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto47 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto47 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto47 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //16
            if (x1 == 46) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto46 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto46 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto46 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto46 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto46 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //15
            if (x1 == 45) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto45 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto45 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto45 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto45 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto45 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //14
            if (x1 == 44) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto44 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto44 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto44 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto44 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto44 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //13
            if (x1 == 43) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto43 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto43 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto43 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto43 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto43 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //12
            if (x1 == 42) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto42 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto42 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto42 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto42 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto42 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
            //11
            if (x1 == 41) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto41 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto41 = FILE_PATH + "d_pfa_2.png";
                        } else {
                            foto41 = FILE_PATH + "d_pfr_2.png";
                        }
                    } else {
                        if (protesisFija.equalsIgnoreCase("R")) {
                            foto41 = FILE_PATH + "d_pfa.png";
                        } else {
                            foto41 = FILE_PATH + "d_pfr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija 1 y 2">
    public void marcarProtFija_1_2(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 >= 11; x1--) {
            if (x1 == 17) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto17 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto17 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //16
            if (x1 == 16) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto16 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto16 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //15
            if (x1 == 15) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto15 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto15 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //14
            if (x1 == 14) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto14 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto14 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //13
            if (x1 == 13) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto13 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto13 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //12
            if (x1 == 12) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto12 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto12 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //11
            if (x1 == 11) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto11 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto11 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
        }
        //cuadrante II
        for (x1 = 21; x1 <= hasta; x1++) {
            if (x1 == 21) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto21 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto21 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //22
            if (x1 == 22) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto22 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto22 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //23
            if (x1 == 23) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto23 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto23 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto23 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto23 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //24
            if (x1 == 24) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto24 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto24 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //25
            if (x1 == 25) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto25 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto25 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //26
            if (x1 == 26) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto26 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto26 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //27
            if (x1 == 27) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto27 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto27 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
        }

    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtFija 2 y 4">
    public void marcarProtFija_4_3(int desde, int hasta) {
        int x1, x2;
        //cuadrante IV
        for (x1 = desde; x1 >= 41; x1--) {
            if (x1 == 47) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto47 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto47 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //16
            if (x1 == 46) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto46 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto46 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //15
            if (x1 == 45) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto45 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto45 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //14
            if (x1 == 44) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto44 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {

                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto44 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //13
            if (x1 == 43) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto43 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto43 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //42
            if (x1 == 42) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto42 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto42 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //41
            if (x1 == 41) {
                if (x1 == desde) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pfa_1.png";
                    } else {
                        foto41 = FILE_PATH + "d_pfr_1.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto41 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
        }
        //cuadrante III
        for (x1 = 31; x1 <= hasta; x1++) {
            //System.out.println("x1 " + x1);
            if (x1 == 31) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto31 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto31 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //32
            if (x1 == 32) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto32 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto32 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //33
            if (x1 == 33) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto33 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto33 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //34
            if (x1 == 34) {

                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto34 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto34 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //35
            if (x1 == 35) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto35 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto35 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //36
            if (x1 == 36) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto36 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto36 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
            //37
            if (x1 == 37) {
                if (x1 == hasta) {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pfa_2.png";
                    } else {
                        foto37 = FILE_PATH + "d_pfr_2.png";
                    }
                } else {
                    if (protesisFija.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pfa.png";
                    } else {
                        foto37 = FILE_PATH + "d_pfr.png";
                    }
                }
            }
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** marcar Prótesis Removible">
    public void btnGraficarPRemovible() {
        int xd = Integer.parseInt(protesisDesde);
        int xh = Integer.parseInt(protesisHasta);
        if (protesisDesde.substring(0, 1).equalsIgnoreCase(protesisHasta.substring(0, 1))) {
            //System.out.println("en el mismo cuadrante...removible...");
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("1")) {
                marcarProtRemovible_1(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("2")) {
                marcarProtRemovible_2(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("3")) {
                marcarProtRemovible_3(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("4")) {
                marcarProtRemovible_4(xd, xh);
            }
        } else {
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("1") || protesisHasta.substring(0, 1).equalsIgnoreCase("2")) {
                //System.out.println("cuadrante...I y II....");
                marcarProtRemovible_1_2(xd, xh);
            }
            if (protesisDesde.substring(0, 1).equalsIgnoreCase("4") || protesisHasta.substring(0, 1).equalsIgnoreCase("3")) {
                //System.out.println("cuadrante...IV y III....");
                marcarProtRemovible_4_3(xd, xh);
            }
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible_1">
    public void marcarProtRemovible_1(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 >= hasta; x1--) {
            if (x1 == 17) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto17 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto17 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto17 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto17 = FILE_PATH + "d_pra.png";
                        } else {
                            foto17 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //16
            if (x1 == 16) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto16 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto16 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto16 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto16 = FILE_PATH + "d_pra.png";
                        } else {
                            foto16 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //15
            if (x1 == 15) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto15 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto15 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto15 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto15 = FILE_PATH + "d_pra.png";
                        } else {
                            foto15 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //14
            if (x1 == 14) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto14 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto14 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto14 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto14 = FILE_PATH + "d_pra.png";
                        } else {
                            foto14 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //13
            if (x1 == 13) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto13 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto13 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto13 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto13 = FILE_PATH + "d_pra.png";
                        } else {
                            foto13 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //12
            if (x1 == 12) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto12 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto12 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto12 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto12 = FILE_PATH + "d_pra.png";
                        } else {
                            foto12 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //11
            if (x1 == 11) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto11 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto11 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto11 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto11 = FILE_PATH + "d_pra.png";
                        } else {
                            foto11 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible_2">
    public void marcarProtRemovible_2(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 <= hasta; x1++) {
            if (x1 == 21) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto21 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto21 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto21 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto21 = FILE_PATH + "d_pra.png";
                        } else {
                            foto21 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //22
            if (x1 == 22) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto22 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto22 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto22 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto22 = FILE_PATH + "d_pra.png";
                        } else {
                            foto22 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //23
            if (x1 == 23) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto23 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto23 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto23 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto23 = FILE_PATH + "d_pra.png";
                        } else {
                            foto23 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //24
            if (x1 == 24) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto34 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto24 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto24 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto24 = FILE_PATH + "d_pra.png";
                        } else {
                            foto24 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //25
            if (x1 == 25) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto25 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto25 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto25 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto25 = FILE_PATH + "d_pra.png";
                        } else {
                            foto25 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //26
            if (x1 == 26) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto26 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto26 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto26 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto26 = FILE_PATH + "d_pra.png";
                        } else {
                            foto26 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //27
            if (x1 == 27) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto27 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto27 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto27 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto27 = FILE_PATH + "d_pra.png";
                        } else {
                            foto27 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible_3">
    public void marcarProtRemovible_3(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 <= hasta; x1++) {
            //System.out.println("x1 " + x1);
            if (x1 == 31) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto31 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto31 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto31 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto31 = FILE_PATH + "d_pra.png";
                        } else {
                            foto31 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //32
            if (x1 == 32) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto32 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto32 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto32 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto32 = FILE_PATH + "d_pra.png";
                        } else {
                            foto32 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //33
            if (x1 == 33) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto33 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto33 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto33 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto33 = FILE_PATH + "d_pra.png";
                        } else {
                            foto33 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //34
            if (x1 == 34) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto34 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto34 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto34 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto34 = FILE_PATH + "d_pra.png";
                        } else {
                            foto34 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //35
            if (x1 == 35) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto35 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto35 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto35 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto35 = FILE_PATH + "d_pra.png";
                        } else {
                            foto35 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //36
            if (x1 == 36) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto36 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto36 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto36 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto36 = FILE_PATH + "d_pra.png";
                        } else {
                            foto36 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //37
            if (x1 == 37) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto37 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto37 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto37 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto37 = FILE_PATH + "d_pra.png";
                        } else {
                            foto37 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible_4">
    public void marcarProtRemovible_4(int desde, int hasta) {

        int x1, x2;
        for (x1 = desde; x1 >= hasta; x1--) {
            if (x1 == 47) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto47 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto47 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto47 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto47 = FILE_PATH + "d_pra.png";
                        } else {
                            foto47 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //16
            if (x1 == 46) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto46 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto46 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto46 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto46 = FILE_PATH + "d_pra.png";
                        } else {
                            foto46 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //15
            if (x1 == 45) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto45 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto45 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto45 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto45 = FILE_PATH + "d_pra.png";
                        } else {
                            foto45 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //14
            if (x1 == 44) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto44 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto44 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto44 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto44 = FILE_PATH + "d_pra.png";
                        } else {
                            foto44 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //13
            if (x1 == 43) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto43 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto43 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto43 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto43 = FILE_PATH + "d_pra.png";
                        } else {
                            foto43 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //12
            if (x1 == 42) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto42 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto42 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto42 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto42 = FILE_PATH + "d_pra.png";
                        } else {
                            foto42 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
            //11
            if (x1 == 41) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto41 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (x1 == hasta) {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto41 = FILE_PATH + "d_pra_2.png";
                        } else {
                            foto41 = FILE_PATH + "d_prr_2.png";
                        }
                    } else {
                        if (protesisRemovible.equalsIgnoreCase("R")) {
                            foto41 = FILE_PATH + "d_pra.png";
                        } else {
                            foto41 = FILE_PATH + "d_prr.png";
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible 1 y 2">
    public void marcarProtRemovible_1_2(int desde, int hasta) {
        int x1, x2;
        for (x1 = desde; x1 >= 11; x1--) {
            if (x1 == 17) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto17 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto17 = FILE_PATH + "d_pra.png";
                    } else {
                        foto17 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //16
            if (x1 == 16) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto16 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto16 = FILE_PATH + "d_pra.png";
                    } else {
                        foto16 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //15
            if (x1 == 15) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto15 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto15 = FILE_PATH + "d_pra.png";
                    } else {
                        foto15 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //14
            if (x1 == 14) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto14 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto14 = FILE_PATH + "d_pra.png";
                    } else {
                        foto14 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //13
            if (x1 == 13) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto13 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto13 = FILE_PATH + "d_pra.png";
                    } else {
                        foto13 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //12
            if (x1 == 12) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto12 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto12 = FILE_PATH + "d_pra.png";
                    } else {
                        foto12 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //11
            if (x1 == 11) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto11 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto11 = FILE_PATH + "d_pra.png";
                    } else {
                        foto11 = FILE_PATH + "d_prr.png";
                    }
                }
            }
        }
        //cuadrante II
        for (x1 = 21; x1 <= hasta; x1++) {
            if (x1 == 21) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto21 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto21 = FILE_PATH + "d_pra.png";
                    } else {
                        foto21 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //22
            if (x1 == 22) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto22 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto22 = FILE_PATH + "d_pra.png";
                    } else {
                        foto22 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //23
            if (x1 == 23) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto23 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto23 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto23 = FILE_PATH + "d_pra.png";
                    } else {
                        foto23 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //24
            if (x1 == 24) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto24 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto24 = FILE_PATH + "d_pra.png";
                    } else {
                        foto24 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //25
            if (x1 == 25) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto25 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto25 = FILE_PATH + "d_pra.png";
                    } else {
                        foto25 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //26
            if (x1 == 26) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto26 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto26 = FILE_PATH + "d_pra.png";
                    } else {
                        foto26 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //27
            if (x1 == 27) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto27 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto27 = FILE_PATH + "d_pra.png";
                    } else {
                        foto27 = FILE_PATH + "d_prr.png";
                    }
                }
            }
        }

    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** marcarProtRemovible 2 y 4">
    public void marcarProtRemovible_4_3(int desde, int hasta) {
        int x1, x2;
        //cuadrante IV
        for (x1 = desde; x1 >= 41; x1--) {
            if (x1 == 47) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto47 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto47 = FILE_PATH + "d_pra.png";
                    } else {
                        foto47 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //16
            if (x1 == 46) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto46 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto46 = FILE_PATH + "d_pra.png";
                    } else {
                        foto46 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //15
            if (x1 == 45) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto45 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto45 = FILE_PATH + "d_pra.png";
                    } else {
                        foto45 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //14
            if (x1 == 44) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto44 = FILE_PATH + "d_prr_1.png";
                    }
                } else {

                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto44 = FILE_PATH + "d_pra.png";
                    } else {
                        foto44 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //13
            if (x1 == 43) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto43 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto43 = FILE_PATH + "d_pra.png";
                    } else {
                        foto43 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //42
            if (x1 == 42) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto42 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto42 = FILE_PATH + "d_pra.png";
                    } else {
                        foto42 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //41
            if (x1 == 41) {
                if (x1 == desde) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pra_1.png";
                    } else {
                        foto41 = FILE_PATH + "d_prr_1.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto41 = FILE_PATH + "d_pra.png";
                    } else {
                        foto41 = FILE_PATH + "d_prr.png";
                    }
                }
            }
        }
        //cuadrante III
        for (x1 = 31; x1 <= hasta; x1++) {
            //System.out.println("x1 " + x1);
            if (x1 == 31) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto31 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto31 = FILE_PATH + "d_pra.png";
                    } else {
                        foto31 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //32
            if (x1 == 32) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto32 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto32 = FILE_PATH + "d_pra.png";
                    } else {
                        foto32 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //33
            if (x1 == 33) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto33 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto33 = FILE_PATH + "d_pra.png";
                    } else {
                        foto33 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //34
            if (x1 == 34) {

                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto34 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto34 = FILE_PATH + "d_pra.png";
                    } else {
                        foto34 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //35
            if (x1 == 35) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto35 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto35 = FILE_PATH + "d_pra.png";
                    } else {
                        foto35 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //36
            if (x1 == 36) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto36 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto36 = FILE_PATH + "d_pra.png";
                    } else {
                        foto36 = FILE_PATH + "d_prr.png";
                    }
                }
            }
            //37
            if (x1 == 37) {
                if (x1 == hasta) {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pra_2.png";
                    } else {
                        foto37 = FILE_PATH + "d_prr_2.png";
                    }
                } else {
                    if (protesisRemovible.equalsIgnoreCase("R")) {
                        foto37 = FILE_PATH + "d_pra.png";
                    } else {
                        foto37 = FILE_PATH + "d_prr.png";
                    }
                }
            }
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** selectedAjaxChangeTipo()">
    public void selectedAjaxChangeTipo() {
        if (tipoMarcar.equalsIgnoreCase("C")) {
            flagPanelTodo = true;
            flagPanelPartes = false;
        } else {
            flagPanelTodo = false;
            flagPanelPartes = true;
        }
    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** onTabChange">
    public void onTabChange(TabChangeEvent event) {
        indexTabView = event.getTab().getId();
        if (indexTabView.equalsIgnoreCase("tab11")) {
            indexTab = 0;
        }
        if (indexTabView.equalsIgnoreCase("tab12")) {
            indexTab = 1;
        }
        if (indexTabView.equalsIgnoreCase("tab13")) {
            if (odontograma.getOdoId() == null) {
                ponerMensajeInfo("ATENCIÓN", "Es necesario registrar el Odontograma del Paciente!!!");
                indexTab = 0;
                flagBtnGrabarRec = true;
            } else {
                flagBtnGrabarRec = false;
                indexTab = 2;
            }
        }
    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** btnGtrabarMovilidad">
    public void btnGrabarRecMovilidad() {
        if (odontograma.getOdoId() == null) {
            ponerMensajeInfo("ATENCIÓN", "No tiene información el Odontograma");
        } else {
            if (selectRecMovilidad.getRmoId() == null) {
                try {
                    //System.out.println("(odontograma.getOdoId() " + odontograma.getOdoId());
                    selectRecMovilidad.setRmoId(BigDecimal.ZERO);
                    selectRecMovilidad.setOOdontograma(odontograma);
                    selectRecMovilidad.setRmoFecha(new Date());
                    recMovilidadFacade.create(selectRecMovilidad);
                    ponerMensajeInfo("ATENCIÓN", "Información guardada!!!");
                } catch (Exception e) {
                    for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                        System.out.println("t.getCause() " + t.getCause());
                        if (t.getMessage().contains("SQLIntegrityConstraintViolationException: ORA-00001")) {
                            ponerMensajeInfo("ATENCIÓN", "Registrado!!!");
                            break;
                        }
                    }
                }
            } else if (selectRecMovilidad.getRmoId() != null) {
                recMovilidadFacade.edit(selectRecMovilidad);
                ponerMensajeInfo("ATENCIÓN", "Información actualizado!!!");
            }

        }
    }

    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="/** proceso grabar">
    public void procesoGrabar() {
        odontograma.setOdoId(BigDecimal.ZERO);
        odontograma.setoHistOdonto(selectHistOdonto);
        odontograma.setOdoFecha(new Date());

        odontograma.setOdoPdentalv18(foto18.substring(25));
        odontograma.setOdoPdentalv17(foto17.substring(25));
        odontograma.setOdoPdentalv16(foto16.substring(25));
        odontograma.setOdoPdentalv15(foto15.substring(25));
        odontograma.setOdoPdentalv14(foto14.substring(25));
        odontograma.setOdoPdentalv13(foto13.substring(25));
        odontograma.setOdoPdentalv12(foto12.substring(25));
        odontograma.setOdoPdentalv11(foto11.substring(25));
        //
        odontograma.setOdoPdentalv21(foto21.substring(25));
        odontograma.setOdoPdentalv22(foto22.substring(25));
        odontograma.setOdoPdentalv23(foto23.substring(25));
        odontograma.setOdoPdentalv24(foto24.substring(25));
        odontograma.setOdoPdentalv25(foto25.substring(25));
        odontograma.setOdoPdentalv26(foto26.substring(25));
        odontograma.setOdoPdentalv27(foto27.substring(25));
        odontograma.setOdoPdentalv28(foto28.substring(25));
        //
        odontograma.setOdoPdentalv48(foto48.substring(25));
        odontograma.setOdoPdentalv47(foto47.substring(25));
        odontograma.setOdoPdentalv46(foto46.substring(25));
        odontograma.setOdoPdentalv45(foto45.substring(25));
        odontograma.setOdoPdentalv44(foto44.substring(25));
        odontograma.setOdoPdentalv43(foto43.substring(25));
        odontograma.setOdoPdentalv42(foto42.substring(25));
        odontograma.setOdoPdentalv41(foto41.substring(25));
        //
        odontograma.setOdoPdentalv31(foto31.substring(25));
        odontograma.setOdoPdentalv32(foto32.substring(25));
        odontograma.setOdoPdentalv33(foto33.substring(25));
        odontograma.setOdoPdentalv34(foto34.substring(25));
        odontograma.setOdoPdentalv35(foto35.substring(25));
        odontograma.setOdoPdentalv36(foto36.substring(25));
        odontograma.setOdoPdentalv37(foto37.substring(25));
        odontograma.setOdoPdentalv38(foto38.substring(25));
        //
        odontograma.setOdoPdentall55(foto55.substring(25));
        odontograma.setOdoPdentall54(foto54.substring(25));
        odontograma.setOdoPdentall53(foto53.substring(25));
        odontograma.setOdoPdentall52(foto52.substring(25));
        odontograma.setOdoPdentall51(foto51.substring(25));

        odontograma.setOdoPdentall61(foto61.substring(25));
        odontograma.setOdoPdentall62(foto62.substring(25));
        odontograma.setOdoPdentall63(foto63.substring(25));
        odontograma.setOdoPdentall64(foto64.substring(25));
        odontograma.setOdoPdentall65(foto65.substring(25));

        odontograma.setOdoPdentall85(foto85.substring(25));
        odontograma.setOdoPdentall84(foto84.substring(25));
        odontograma.setOdoPdentall83(foto83.substring(25));
        odontograma.setOdoPdentall82(foto82.substring(25));
        odontograma.setOdoPdentall81(foto81.substring(25));

        odontograma.setOdoPdentall71(foto71.substring(25));
        odontograma.setOdoPdentall72(foto72.substring(25));
        odontograma.setOdoPdentall73(foto73.substring(25));
        odontograma.setOdoPdentall74(foto74.substring(25));
        odontograma.setOdoPdentall75(foto75.substring(25));
        //

        odontogramaFacade.create(odontograma);
        //recuperaListOdonto();
        System.out.println("grabado odonto...");
        //Recupera odoID
        nroOdoId = odontogramaFacade.maxOdoId(selectHistOdonto);
        selectIndCpoCeo.setIccId(BigDecimal.ZERO);
        selectIndCpoCeo.setOOdontograma(new OOdontograma(nroOdoId));
        selectIndCpoCeo.setIccFecha(new Date());
        selectIndCpoCeo.setIccCpoC(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "CARIES")));
        selectIndCpoCeo.setIccCpoO(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "OBTURADAS")));
        selectIndCpoCeo.setIccCpoP(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "PERDIDAS")));
        selectIndCpoCeo.setIccCeoC(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "CARIES")));
        selectIndCpoCeo.setIccCeoO(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "OBTURADAS")));
        selectIndCpoCeo.setIccCeoE(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "PERDIDAS")));
        indicesCpoCeoFacade.create(selectIndCpoCeo);
        //ponerMensajeInfo("Odontograma", "Información guardada");
        System.out.println("grabado cpo-ceo...");
        //
        updateTurnoCe.setFechaAtencion(new Date());
        updateTurnoCe.setHoraFin(new Date());
        updateTurnoCe.setPersonal(selectHistOdonto.getTurnosCe().getPersonal1());
        turnosCeFacade.edit(updateTurnoCe);

    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** btnEditarOdonto">
    public void grabarOdonRealizado() {
        pathEdit = new StringBuilder();
        StringBuilder textEdit = new StringBuilder();
        if (tipoMarcar.equalsIgnoreCase("C")) {
            pathEdit = new StringBuilder(selectSimbologia.getSodAbreviatura()).append(".png");
            textEdit = new StringBuilder("Pieza dental completa");
        } else {

            if (!obsCara1.equalsIgnoreCase("")) {
                textEdit.append(" Cara 1, ").append(obsCara1).append(" / ");
            }
            if (!obsCara2.equalsIgnoreCase("")) {
                textEdit.append(" Cara 2, ").append(obsCara2).append(" / ");
            }
            if (!obsCara3.equalsIgnoreCase("")) {
                textEdit.append(" Cara 3, ").append(obsCara3).append(" / ");
            }
            if (!obsCara4.equalsIgnoreCase("")) {
                textEdit.append(" Cara 4, ").append(obsCara4).append(" / ");
            }
            if (!obsCara5.equalsIgnoreCase("")) {
                textEdit.append(" Cara 5, ").append(obsCara5).append(" / ");
            }
            pathEdit.append("d_").append(oclusal.concat(vestibular).concat(palatino).concat(mesial).concat(distal)).append(".png");

        }
        //a grabar detalle de realizado
        listOdontoRealizadosVerif = odontoRealizadoFacade.listByOdonPdental(odontograma, piezaDental);
        if (listOdontoRealizadosVerif.isEmpty()) {
            editOdontoRealizado = new OOdontoRealizado();
            editOdontoRealizado.setOreId(BigDecimal.ONE);
            editOdontoRealizado.setOOdontograma(odontograma);
            editOdontoRealizado.setOreFecha(new Date());
            editOdontoRealizado.setOrePdental(piezaDental);
            editOdontoRealizado.setOreGraficoAnt(infPDentalAnt);
            editOdontoRealizado.setOreGraficoAct(pathEdit.toString());
            editOdontoRealizado.setOreObserv(textEdit.toString());
            odontoRealizadoFacade.create(editOdontoRealizado);
            //ponerMensajeInfo("ATENCIÓN", "Información actualizada");
        } else {
            editOdontoRealizado = listOdontoRealizadosVerif.get(0);
            odontoRealizadoFacade.edit(editOdontoRealizado);
        }
        flagBtnGrabarEdit = false;
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** recupera odontograma">
    public void recuperaOdonto() {
        listOdontograma = odontogramaFacade.listByHistOdon(selectHistOdonto);
        if (listOdontograma.isEmpty()) {
            odontograma = new OOdontograma();
            selectIndCpoCeo = new OIndicesCpoCeo();
            inicializarPiezasDentales();
        } else if (listOdontograma.size() > 0) {
            odontograma = odontogramaFacade.find(listOdontograma.get(listOdontograma.size() - 1).getOdoId());
            listOdontoRealizadosOdonto = odontoRealizadoFacade.listByHisOdo(selectHistOdonto);
        }
    }
    // </editor-fold>        

    // <editor-fold defaultstate="collapsed" desc="/** recupera RecMovilidad">
    public void recuperaRecMovilidad() {
        selectRecMovilidad = recMovilidadFacade.findRecMov(odontograma);
        if (selectRecMovilidad == null) {
            selectRecMovilidad = new ORecMovilidad();
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** Inicialización Piezas dentales">
    public void inicializarPiezasDentales() {
        //inicializa pieza dental
        foto18 = FILE_PATH + "diente.png";
        foto17 = FILE_PATH + "diente.png";
        foto16 = FILE_PATH + "diente.png";
        foto15 = FILE_PATH + "diente.png";
        foto14 = FILE_PATH + "diente.png";
        foto13 = FILE_PATH + "diente.png";
        foto12 = FILE_PATH + "diente.png";
        foto11 = FILE_PATH + "diente.png";

        foto21 = FILE_PATH + "diente.png";
        foto22 = FILE_PATH + "diente.png";
        foto23 = FILE_PATH + "diente.png";
        foto24 = FILE_PATH + "diente.png";
        foto25 = FILE_PATH + "diente.png";
        foto26 = FILE_PATH + "diente.png";
        foto27 = FILE_PATH + "diente.png";
        foto28 = FILE_PATH + "diente.png";

        foto48 = FILE_PATH + "diente.png";
        foto47 = FILE_PATH + "diente.png";
        foto46 = FILE_PATH + "diente.png";
        foto45 = FILE_PATH + "diente.png";
        foto44 = FILE_PATH + "diente.png";
        foto43 = FILE_PATH + "diente.png";
        foto42 = FILE_PATH + "diente.png";
        foto41 = FILE_PATH + "diente.png";

        foto31 = FILE_PATH + "diente.png";
        foto32 = FILE_PATH + "diente.png";
        foto33 = FILE_PATH + "diente.png";
        foto34 = FILE_PATH + "diente.png";
        foto35 = FILE_PATH + "diente.png";
        foto36 = FILE_PATH + "diente.png";
        foto37 = FILE_PATH + "diente.png";
        foto38 = FILE_PATH + "diente.png";

        foto55 = FILE_PATH + "diente.png";
        foto54 = FILE_PATH + "diente.png";
        foto53 = FILE_PATH + "diente.png";
        foto52 = FILE_PATH + "diente.png";
        foto51 = FILE_PATH + "diente.png";

        foto61 = FILE_PATH + "diente.png";
        foto62 = FILE_PATH + "diente.png";
        foto63 = FILE_PATH + "diente.png";
        foto64 = FILE_PATH + "diente.png";
        foto65 = FILE_PATH + "diente.png";

        foto85 = FILE_PATH + "diente.png";
        foto84 = FILE_PATH + "diente.png";
        foto83 = FILE_PATH + "diente.png";
        foto82 = FILE_PATH + "diente.png";
        foto81 = FILE_PATH + "diente.png";

        foto71 = FILE_PATH + "diente.png";
        foto72 = FILE_PATH + "diente.png";
        foto73 = FILE_PATH + "diente.png";
        foto74 = FILE_PATH + "diente.png";
        foto75 = FILE_PATH + "diente.png";
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="/** Graficar ODONTOGRAMA EDICION">
    public void btnGraficarOdontoEdit() {
        nomGraficoPd = new StringBuilder();
        if (tipoMarcar.equalsIgnoreCase("C")) {
            nomGraficoPd.append(selectSimbologia.getSodAbreviatura()).append(".png");
        } else {
            nomGraficoPd.append("d_").append(oclusal.concat(vestibular).concat(palatino).concat(mesial).concat(distal)).append(".png");
        }
        //grabar trabajo realizado por pieza dental
        //Grupo 1
        if (piezaDental.equalsIgnoreCase("18")) {
            odontograma.setOdoPdentalv18(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("17")) {
            odontograma.setOdoPdentalv17(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("16")) {
            odontograma.setOdoPdentalv16(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("15")) {
            odontograma.setOdoPdentalv15(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("14")) {
            odontograma.setOdoPdentalv14(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("13")) {
            odontograma.setOdoPdentalv13(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("12")) {
            odontograma.setOdoPdentalv12(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("11")) {
            odontograma.setOdoPdentalv11(nomGraficoPd.toString());
        }
        //Grupo 2
        if (piezaDental.equalsIgnoreCase("21")) {
            odontograma.setOdoPdentalv21(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("22")) {
            odontograma.setOdoPdentalv22(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("23")) {
            odontograma.setOdoPdentalv23(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("24")) {
            odontograma.setOdoPdentalv24(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("25")) {
            odontograma.setOdoPdentalv25(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("26")) {
            odontograma.setOdoPdentalv26(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("27")) {
            odontograma.setOdoPdentalv27(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("28")) {
            odontograma.setOdoPdentalv28(nomGraficoPd.toString());
        }
        //Grupo 3
        if (piezaDental.equalsIgnoreCase("38")) {
            odontograma.setOdoPdentalv38(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("37")) {
            odontograma.setOdoPdentalv37(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("36")) {
            odontograma.setOdoPdentalv36(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("35")) {
            odontograma.setOdoPdentalv35(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("34")) {
            odontograma.setOdoPdentalv34(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("33")) {
            odontograma.setOdoPdentalv33(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("32")) {
            odontograma.setOdoPdentalv32(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("31")) {
            odontograma.setOdoPdentalv31(nomGraficoPd.toString());
        }
        //Grupo 4
        if (piezaDental.equalsIgnoreCase("41")) {
            odontograma.setOdoPdentalv41(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("42")) {
            odontograma.setOdoPdentalv42(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("43")) {
            odontograma.setOdoPdentalv43(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("44")) {
            odontograma.setOdoPdentalv44(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("45")) {
            odontograma.setOdoPdentalv45(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("46")) {
            odontograma.setOdoPdentalv46(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("47")) {
            odontograma.setOdoPdentalv47(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("48")) {
            odontograma.setOdoPdentalv48(nomGraficoPd.toString());
        }
        //Detinción Temporal
        if (piezaDental.equalsIgnoreCase("55")) {
            odontograma.setOdoPdentall55(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("54")) {
            odontograma.setOdoPdentall54(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("53")) {
            odontograma.setOdoPdentall53(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("52")) {
            odontograma.setOdoPdentall52(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("51")) {
            odontograma.setOdoPdentall51(nomGraficoPd.toString());
        }
        //2
        if (piezaDental.equalsIgnoreCase("61")) {
            odontograma.setOdoPdentall61(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("62")) {
            odontograma.setOdoPdentall62(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("63")) {
            odontograma.setOdoPdentall63(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("64")) {
            odontograma.setOdoPdentall64(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("65")) {
            odontograma.setOdoPdentall65(nomGraficoPd.toString());
        }
        //3
        if (piezaDental.equalsIgnoreCase("71")) {
            odontograma.setOdoPdentall71(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("72")) {
            odontograma.setOdoPdentall72(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("73")) {
            odontograma.setOdoPdentall73(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("74")) {
            odontograma.setOdoPdentall74(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("75")) {
            odontograma.setOdoPdentall75(nomGraficoPd.toString());
        }
        //4
        if (piezaDental.equalsIgnoreCase("85")) {
            odontograma.setOdoPdentall85(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("84")) {
            odontograma.setOdoPdentall84(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("83")) {
            odontograma.setOdoPdentall83(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("82")) {
            odontograma.setOdoPdentall82(nomGraficoPd.toString());
        }
        if (piezaDental.equalsIgnoreCase("81")) {
            odontograma.setOdoPdentall81(nomGraficoPd.toString());
        }
        grabarOdonRealizado();
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar Edicion">
    public void btnGrabarEdicion() {
        //odontograma = new OOdontograma();
        odontograma.setOdoId(BigDecimal.ZERO);
        odontograma.setoHistOdonto(selectHistOdonto);
        odontograma.setOdoFecha(new Date());
        odontograma.setOdoObservacion(String.valueOf(updateTurnoCe.getNumeroId()));
        odontogramaFacade.create(odontograma);

        nroOdoId = odontogramaFacade.maxOdoId(selectHistOdonto);

        //grabar numérico de odontograma para el CPO-ceo
        selectIndCpoCeo = new OIndicesCpoCeo();
        selectIndCpoCeo.setIccId(BigDecimal.ZERO);
        selectIndCpoCeo.setOOdontograma(new OOdontograma(nroOdoId));
        selectIndCpoCeo.setIccFecha(new Date());
        selectIndCpoCeo.setIccCpoC(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "CARIES")));
        selectIndCpoCeo.setIccCpoO(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "OBTURADAS")));
        selectIndCpoCeo.setIccCpoP(BigInteger.valueOf(vPermanentesFacade.countIndicePermanente(nroOdoId, "PERDIDAS")));
        selectIndCpoCeo.setIccCeoC(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "CARIES")));
        selectIndCpoCeo.setIccCeoO(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "OBTURADAS")));
        selectIndCpoCeo.setIccCeoE(BigInteger.valueOf(vTemporalesFacade.countIndiceTemporal(nroOdoId, "PERDIDAS")));
        indicesCpoCeoFacade.create(selectIndCpoCeo);
        //
        if (updateTurnoCe.getFechaAtencion() == null) {
            updateTurnoCe.setFechaAtencion(new Date());
            updateTurnoCe.setHoraFin(new Date());
            updateTurnoCe.setPersonal(new Personal(codMedico));
            turnosCeFacade.edit(updateTurnoCe);
        } else {
            updateTurnoCe.setHoraFin(new Date());
            updateTurnoCe.setPersonal(new Personal(codMedico));
            turnosCeFacade.edit(updateTurnoCe);
        }
        flagBtnGrabarEdit = true;
    }
    // </editor-fold>      

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    public void init() {
        indexTab = 0;
        indexTabView = "tab11";
        recuperaOdonto();
        recuperaRecMovilidad();
        flagGraficar = true;
        flagBtnGrabarRec = true;
        flagBtnGrabarEdit = true;
        //System.out.println("selectHistOdonto.getTurnosCe().getNumeroId() "+selectHistOdonto.getTurnosCe().getNumeroId());
        //updateTurnoCe = turnosCeFacade.find(selectHistOdonto.getTurnosCe().getNumeroId());
    }

    // </editor-fold>
    /**
     * Creates a new instance of Ficha6OdontogramaJSFManagedBean
     */
    public Ficha6OdontogramaJSFManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        selectHistOdonto = (OHistOdonto) session.getAttribute("fichaOdonto");
        updateTurnoCe = (TurnosCe) session.getAttribute("turno");
        codMedico = (String) session.getAttribute("usuarioDB");
    }

}
