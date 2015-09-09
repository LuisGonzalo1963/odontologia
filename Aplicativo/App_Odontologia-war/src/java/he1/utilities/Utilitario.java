/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.utilities;

import he1.issfa.DatosOdontologiaError;
import he1.issfa.ParametrosAcceso;
import he1.issfa.ResultadoBusqueda;
import he1.sis.entities.AtencionSecretarias;
import he1.sis.entities.Departamentos;
import he1.sis.entities.HorariosMedico;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Personal;
import he1.sis.sessions.AtencionSecretariasFacade;
import he1.sis.sessions.HorariosMedicoFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.PromocionesPacientesFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis Guanoluiza
 * @version $Revision: 1.0 $
 */
public class Utilitario implements Serializable {

    @EJB
    private PromocionesPacientesFacade promocionesPacientesFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private AtencionSecretariasFacade atencionSecretariasFacade;
    @EJB
    private HorariosMedicoFacade horariosMedicoFacade;

    private ParametrosAcceso paramAcceso;
    private AtencionSecretarias atencionSecretarias;
    private HorariosMedico horariosMedico;
    private Departamentos departamentos;

    private List<HorariosMedico> lisHorariosMedico;

    private String nroAtenciones;
    private String mensaje;

    public static final String PATRON_FECHA = "dd/MM/yyyy";
    public static final SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES"));

    public static String deDateAString(Date fecha) {
        return sdf1.format(fecha);
    }

    /**
     * Metódo que retorna el httpServletRequest
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    /**
     * Metódo que retorna el xxternalContext
     *
     * @return ExternalContext
     */
    protected ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    /**
     *
     * @return
     */
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     *
     * @param summary
     * @param detail
     */
    protected void ponerMensajeInfo(final String summary, final String detail) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(summary);
        infoMessage.setDetail(detail);
        infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("messages", infoMessage);
    }

    /**
     *
     * @param summary
     * @param detail
     */
    protected void ponerMensajeError(final String summary, final String detail) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(summary);
        errorMessage.setDetail(detail);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("messages", errorMessage);
    }

    /**
     * Metódo que retorna la fecha de String a Date
     *
     * @param fecha
     * @return fecha
     */
    public Date deStringADate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * *
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }

    /**
     * *
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     *
     * @param message texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     * SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public static String getStringMessageDigest(String message, String algorithm) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    }

    public void logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ec = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            request.getSession().invalidate();
            ec.redirect(ec.getRequestContextPath());
            System.out.println("Sistema cerrado...");
        } catch (IOException ex) {
            //ponerMensajeError("Error", ex.getMessage());
        }
    }

    public void noAcceso(String url) {
        System.out.println("salir...");
        try {

            FacesContext facesCtx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesCtx.getExternalContext().getSession(false);
            session.invalidate();

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(AccesoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return null;
    }

    /**
     * Agrega o quita minutos a una fecha dada. Para quitar minutos hay que
     * sumarle valores negativos.
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutesToDate(Date date, int minutes) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.add(Calendar.MINUTE, minutes);
        return calendarDate.getTime();
    }

    public List<String> returnHoras(Date horaInicial, short turnos, short tiempo) {
        String[] horaTurno;
        List<String> listaHoras;
        SimpleDateFormat formatHoraMin = new SimpleDateFormat("hh:mm");

        horaTurno = new String[turnos];
        Date fechaT = horaInicial;
        //System.out.println("hora 1: " + formatHoraMin.format(fechaT));
        horaTurno[0] = formatHoraMin.format(fechaT);
        int i = 1;
        while (true) {
            if (i == turnos) {
                break;
            }
            fechaT = addMinutesToDate(fechaT, tiempo);
            horaTurno[i] = formatHoraMin.format(fechaT);
            //System.out.println("hora " + i + ": " + horaTurno[i]);
            i++;
        }
        listaHoras = Arrays.asList(horaTurno);
        //System.out.println("listaHoras " + listaHoras.size());
        return listaHoras;
    }

    public String promPcn(Pacientes pcn) {
        String tipoPaciente = null;
        if (promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(pcn)).getPromociones().getCodigo().equalsIgnoreCase("01")) {
            //civil 100%%
            tipoPaciente = "CIVIL";
        } else if (promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(pcn)).getPromociones().getCodigo().equalsIgnoreCase("02")) {
            //issfa validar padreISsfa paga 100%
            pacientesFacade.find(pcn.getNumeroHc()).getSituacion();
            System.out.println("sit.." + pacientesFacade.find(pcn.getNumeroHc()).getSituacion());
            if (pacientesFacade.find(pcn.getNumeroHc()).getSituacion().equalsIgnoreCase("3")) {
                tipoPaciente = "ISSFA padre";
            } else {
                tipoPaciente = "ISSFA dscto";
            }

        } else {
            tipoPaciente = "Considerado CIVIL";
        }
        return tipoPaciente;
    }

    public String promCodPcn(Pacientes pcn) {
        return promocionesPacientesFacade.find(promocionesPacientesFacade.maxOdoId(pcn)).getPromociones().getCodigo();
    }

    public BigDecimal edadPaciente(Pacientes pcn) {
        return pacientesFacade.edadPaciente(pcn);
    }

    public Departamentos AreaDptosUtil(Personal medico, String dia) {
        System.out.println("medico "+medico+" dia "+dia);
        departamentos = new Departamentos();
        atencionSecretarias = atencionSecretariasFacade.findByCodMedico(medico);
        if (atencionSecretarias != null) {
            System.out.println("atencionSecretarias.getPersonal().getCodigo() "+atencionSecretarias.getPersonal().getCodigo());
            lisHorariosMedico = horariosMedicoFacade.listByDiaConsultorio(dia, atencionSecretarias.getPersonal().getCodigo());
            if (lisHorariosMedico.size() > 0) {
                departamentos = lisHorariosMedico.get(0).getDepartamentos();
            } else {
                System.out.println("horarios medico nulo...");
                departamentos = null;
            }
        } else {
            System.out.println("atencionSecretarias es nulo...");
            departamentos = null;
        }
        return departamentos;
    }

    public HorariosMedico ConsultorioUtil(Personal medico, String dia) {
        atencionSecretarias = atencionSecretariasFacade.findByCodMedico(medico);
        if (atencionSecretarias != null) {
            lisHorariosMedico = horariosMedicoFacade.listByDiaConsultorio(dia, atencionSecretarias.getPersonal().getCodigo());
            if (lisHorariosMedico.size() > 0) {
                return horariosMedico = lisHorariosMedico.get(0);
            } else {
                System.out.println("horarios medico nulo...");
            }
        } else {
            System.out.println("atencionSecretarias es nulo...");
        }
        return null;
    }

    public int existeCodCie(String codigosCie, String codigoDx) {
        // Contador de ocurrencias 
        int contador = 0;
        while (codigosCie.contains(codigoDx)) {
            codigosCie = codigosCie.substring(codigosCie.indexOf(
                    codigoDx) + codigoDx.length(), codigosCie.length());
            contador++;
        }
        return contador;
    }

    public int existeTexto(String texto, String buscar) {
        // Contador de ocurrencias 
        int contador = 0;
        while (texto.contains(buscar)) {
            texto = texto.substring(texto.indexOf(buscar) + buscar.length(), texto.length());
            contador++;
        }
        return contador;
    }

    // <editor-fold defaultstate="collapsed" desc="/** callWebService">
    public String callWebServiceConsulta(String nroAfil, String fecha, String codDiag, String codServ) {
        System.out.println("2 ingresando al webService");
        paramAcceso = new ParametrosAcceso();
        paramAcceso.setOperacion("0");
        paramAcceso.setNoAfiliacionPaciente(nroAfil);
        paramAcceso.setFechaAtencion(fecha);
        paramAcceso.setDiagnosticoPrimario(codDiag);
        paramAcceso.setNoServicio(codServ);
        paramAcceso.setUsuario("HE1");
        paramAcceso.setClave("A");

        try {
            System.out.println("3. conn " + datosOdontologia(paramAcceso).getResultadoConexion());
            nroAtenciones = datosOdontologia(paramAcceso).getMensaje();
            //System.out.println("res.." + datosOdontologia(paramAcceso).getMensaje());
            mensaje = datosOdontologia(paramAcceso).getMensaje();
        } catch (DatosOdontologiaError ex) {
            nroAtenciones = "0";
            Logger.getLogger(Utilitario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mensaje;
    }

    // </editor-fold>      
    // <editor-fold defaultstate="collapsed" desc="/** callWebServiceInserta">
    public String callWebServiceInserta(String plan, String afilTit, String afilPcn, String nomPcn, String fechAte, String fechSal, String diagPri, String diagSec, String fechTari, String serv, String cant, String valTot, String valPcn, String modif) {
        paramAcceso = new ParametrosAcceso();
        paramAcceso.setOperacion("1");
        paramAcceso.setNoAfiliacionPaciente(plan);
        paramAcceso.setFechaAtencion(afilTit);
        paramAcceso.setDiagnosticoPrimario(afilPcn);
        paramAcceso.setNoServicio(nomPcn);
        paramAcceso.setNoAfiliacionPaciente(fechAte);
        paramAcceso.setFechaAtencion(fechSal);
        paramAcceso.setDiagnosticoPrimario(diagPri);
        paramAcceso.setNoServicio(diagSec);
        paramAcceso.setLMuerto("0");
        paramAcceso.setFechaTarifario("");
        paramAcceso.setNoServicio(diagSec);  //
        paramAcceso.setCantidad(valPcn);  //
        paramAcceso.setValorTotal(valPcn);  //
        paramAcceso.setValorPaciente(valPcn); //
        paramAcceso.setModificador(valPcn); //
        paramAcceso.setUsuario("HE1");
        paramAcceso.setClave("A");

        try {
            System.out.println("conn " + datosOdontologia(paramAcceso).getResultadoConexion());
            nroAtenciones = datosOdontologia(paramAcceso).getMensaje();
            //System.out.println("res.." + datosOdontologia(paramAcceso).getMensaje());
            mensaje = datosOdontologia(paramAcceso).getMensaje();
        } catch (DatosOdontologiaError ex) {
            Logger.getLogger(Utilitario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mensaje;
    }

    // </editor-fold> 
    private static ResultadoBusqueda datosOdontologia(he1.issfa.ParametrosAcceso datosAcceso) throws DatosOdontologiaError {
        he1.issfa.WsdlOdontologiaV2Definitions service = new he1.issfa.WsdlOdontologiaV2Definitions();
        he1.issfa.WsdlOdontologiaV2 port = service.getWsdlOdontologiaV2Port();
        return port.datosOdontologia(datosAcceso);
    }

}
