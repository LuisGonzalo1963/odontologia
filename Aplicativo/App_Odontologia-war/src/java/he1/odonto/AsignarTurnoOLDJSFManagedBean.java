/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.entities.TurnosCe;
import he1.sis.sessions.HorariosMedicoFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PersonalFacade;
import he1.sis.sessions.TurnosCeFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "asignarTurno_old")
@ViewScoped
public class AsignarTurnoOLDJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private PersonalFacade personalFacade;

    @EJB
    private PacientesFacade pacientesFacade;

    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;

    @EJB
    private TurnosCeFacade turnosCeFacade;

    private List<TurnosCe> listaTurnos;
    private List<TurnosCe> listaTurnosPaciente;
    private List<HorariosMedico> listaHorario;

    private TurnosCe editTurnoCe;
    private TurnosCe selectTurnoCe;
    private Pacientes editPaciente;
    private Personal selectPersonal;

    private String fechaActual;
    private String diaActual;
    private String codConsultorio;
    private String nroHc;
    private String msgBuscar;
    private String msgGrabar;
    private String buscarPor;
    private String ape1;

    private BigDecimal edadPaciente;

    private boolean flagBtnAceptar;
    private boolean flagBtnAsignar;

    private final short tt = 0;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de Variables">
    public List<TurnosCe> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<TurnosCe> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public Personal getSelectPersonal() {
        return selectPersonal;
    }

    public void setSelectPersonal(Personal selectPersonal) {
        this.selectPersonal = selectPersonal;
    }

    public String getMsgBuscar() {
        return msgBuscar;
    }

    public void setMsgBuscar(String msgBuscar) {
        this.msgBuscar = msgBuscar;
    }

    public String getMsgGrabar() {
        return msgGrabar;
    }

    public void setMsgGrabar(String msgGrabar) {
        this.msgGrabar = msgGrabar;
    }

    public boolean isFlagBtnAceptar() {
        return flagBtnAceptar;
    }

    public void setFlagBtnAceptar(boolean flagBtnAceptar) {
        this.flagBtnAceptar = flagBtnAceptar;
    }

    public boolean isFlagBtnAsignar() {
        return flagBtnAsignar;
    }

    public void setFlagBtnAsignar(boolean flagBtnAsignar) {
        this.flagBtnAsignar = flagBtnAsignar;
    }

    public String getNroHc() {
        return nroHc;
    }

    public void setNroHc(String nroHc) {
        this.nroHc = nroHc;
    }

    public Pacientes getEditPaciente() {
        return editPaciente;
    }

    public void setEditPaciente(Pacientes editPaciente) {
        this.editPaciente = editPaciente;
    }

    public TurnosCe getEditTurnoCe() {
        return editTurnoCe;
    }

    public void setEditTurnoCe(TurnosCe editTurnoCe) {
        this.editTurnoCe = editTurnoCe;
    }

    public List<HorariosMedico> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<HorariosMedico> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getCodConsultorio() {
        return codConsultorio;
    }

    public void setCodConsultorio(String codConsultorio) {
        this.codConsultorio = codConsultorio;
    }

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    public BigDecimal getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(BigDecimal edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public TurnosCe getSelectTurnoCe() {
        return selectTurnoCe;
    }

    public void setSelectTurnoCe(TurnosCe selectTurnoCe) {
        this.selectTurnoCe = selectTurnoCe;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** ajaxChangeConsultorio">
    public void ajaxChangeConsultorio() {
        //////try catch
        msgGrabar = null;
        selectPersonal = personalFacade.findByCodigo(codConsultorio);
        listaHorario = horariosMedicoFacade.listByDiaConsultorio(diaActual, codConsultorio);
        System.out.println("turnosDispo " + totalizaTurnos());
         System.out.println("selecFecha "+deStringADate(fechaActual));
        listaTurnos = turnosCeFacade.ListByFechaConsultorio(deStringADate(fechaActual), new Personal(codConsultorio));
        System.out.println("turnosRegis "+listaTurnos.size());
        if(listaTurnos.size() < totalizaTurnos()){
            flagBtnAsignar = false;
        } else if(listaTurnos.size() > totalizaTurnos()){
            flagBtnAsignar = true;
        }
    }

    public short totalizaTurnos() {
        int i = 0;
        short t1 = 0, ttd = 0;
        while (true) {
            if (i == listaHorario.size()) {
                break;
            }
            t1 = listaHorario.get(i).getTurnosPosibles();
            ttd = (short) (ttd + t1);
            i++;
        }
        return ttd;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** botón Asignar">
    public void btnAsignar() {
        nroHc = null;
        msgBuscar = null;
        msgGrabar = null;
        buscarPor = null;
        edadPaciente = null;
        editPaciente = new Pacientes();
        flagBtnAceptar = true;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** botón Referidos">
    public void btnReferidos() {
 

    }
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="/** ajaxBlurHc">
    public void ajaxBlurHc() {
        if (!"".equals(nroHc)) {
            editPaciente = pacientesFacade.findByHc(Integer.valueOf(nroHc));
            System.out.println("editPaciente " + editPaciente);
            if (editPaciente == null) {
                msgBuscar = "No. de Historia clínica no existe. Verifique...";
                flagBtnAceptar = true;
            } else {
                msgBuscar = null;
                flagBtnAceptar = false;
                listaTurnosPaciente = turnosCeFacade.ListByFechaPaciente(deStringADate(fechaActual), editPaciente);
                if (listaTurnosPaciente.size() > 0) {
                    msgBuscar = "No. de Historia clínica ya registrado. Verifique...en " + listaTurnosPaciente.get(0).getPersonal1().getNombres();
                    flagBtnAceptar = true;
                } else {
                    msgBuscar = null;
                    flagBtnAceptar = false;
                }
            }
        } else {
            System.out.println("sin nhc " + nroHc);
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** Tipo de búsqueda">
    public void selectedAjaxChange() {
        nroHc = null;
        editPaciente = new Pacientes();
        edadPaciente = null;
        msgBuscar = null;
        msgGrabar = null;
        flagBtnAceptar = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** buscar por tipo de documento">
    public void buscarAjaxBlur() {
        if (buscarPor != null) {
            msgBuscar = null;
            if (nroHc != null) {
                if (buscarPor.equalsIgnoreCase("nhc")) {
                    editPaciente = pacientesFacade.findByHc(Integer.valueOf(nroHc));
                }
                if (buscarPor.equalsIgnoreCase("ced")) {
                    editPaciente = pacientesFacade.findByNroCed(nroHc);
                }
                if (buscarPor.equalsIgnoreCase("ape")) {

                    System.out.println("buscar....ape..");
                }
            }
            System.out.println("editPaciente " + editPaciente);
//            if (editPaciente.getNumeroHc() != null) {
//                edadPaciente = pacientesFacade.edadPaciente(editPaciente);
//            } else {
//                edadPaciente = null;
//            }
            if (editPaciente == null) {
                msgBuscar = "Paciente no existe. Verifique...";
                flagBtnAceptar = true;
            } else {
                msgBuscar = null;
                flagBtnAceptar = false;
                listaTurnosPaciente = turnosCeFacade.ListByFechaPaciente(deStringADate(fechaActual), editPaciente);
                edadPaciente = pacientesFacade.edadPaciente(editPaciente);
                if (listaTurnosPaciente.size() > 0) {
                    msgBuscar = "No. de Historia clínica ya registrado. Verifique...en " + listaTurnosPaciente.get(0).getPersonal1().getNombres();
                    flagBtnAceptar = true;
                } else {
                    msgBuscar = null;
                    flagBtnAceptar = false;
                }
            }

        } else {
            System.out.println("nulo buscar por.....");
            msgGrabar = null;
            msgBuscar = "Seleccione el tipo de búsqueda...";
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** botón Grabar de Dialog">
    public void btnGrabarTurno() {
        System.out.println("listaTurnos " + listaTurnos.size());
        try {
            short s;
            if (listaTurnos.isEmpty()) {
                s = 1;
            } else {
                s = (short) ((short) listaTurnos.size() + 1);
            }
            BigDecimal idTurno = turnosCeFacade.maxRegId();
            idTurno = idTurno.add(new BigDecimal(1));
            editTurnoCe = new TurnosCe();

            editTurnoCe.setNumeroId(idTurno);
            editTurnoCe.setPacientes(editPaciente);
            editTurnoCe.setPersonal1(new Personal(codConsultorio));
            editTurnoCe.setFecha(deStringADate(fechaActual));
            editTurnoCe.setNumero(s);
            editTurnoCe.setTipo("P");
            editTurnoCe.setEstado("R");
            editTurnoCe.setDprAraCodigo("O");
            editTurnoCe.setDprCodigo("T");
            editTurnoCe.setFechaCreacion(deStringADate(fechaActual));
            editTurnoCe.setSobrecarga('F');
            editTurnoCe.setCreado(new Date());
            editTurnoCe.setCreadoPor("CAJERO_PRUEBAS");
            turnosCeFacade.create(editTurnoCe);
            listaTurnos = turnosCeFacade.ListByFechaConsultorio(deStringADate(fechaActual), new Personal(codConsultorio));
        } catch (Exception e) {
            for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                System.out.println("t.getCause() " + t.getCause());
                if (t.getMessage().contains("SQLIntegrityConstraintViolationException: ORA-20998")) {
                    msgGrabar = "Personal no autorizado a dar turnos para " + codConsultorio;
                    System.out.println("msgBuscar " + msgGrabar);
                    break;
                }
            }
            System.out.println("error.......");
            msgGrabar = "Personal no autorizado a dar turnos para " + codConsultorio;

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** btn Grabar Pago">
    public void btnPagarTurno() {
        System.out.println("pago...");
        selectTurnoCe.setEstado("P");
        turnosCeFacade.edit(selectTurnoCe);
        listarTurnos();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** listar turnos">
    public void listarTurnos() {
        listaTurnos = turnosCeFacade.ListByFechaConsultorio(deStringADate(fechaActual), new Personal(codConsultorio));
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    @PostConstruct
    private void init() {
        Calendar Cal = Calendar.getInstance();
        fechaActual = Cal.get(Calendar.DATE) + "-" + (Cal.get(Calendar.MONTH) + 1) + "-" + Cal.get(Calendar.YEAR);
        String fechaActualHora = Cal.get(Calendar.DATE) + "/" + (Cal.get(Calendar.MONTH) + 1) + "/" + Cal.get(Calendar.YEAR) + " : 07:00";
        diaActual = String.valueOf(Cal.get(Calendar.DAY_OF_WEEK));
        nroHc = null;
        flagBtnAsignar = true;
    }
    // </editor-fold>

    /**
     * Creates a new instance of AsignarTurnoJSFManagedBean
     */
    public AsignarTurnoOLDJSFManagedBean() {
    }

}
