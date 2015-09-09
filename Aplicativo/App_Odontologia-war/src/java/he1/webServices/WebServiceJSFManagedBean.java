/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.webServices;

import he1.issfa.ParametrosAcceso;
import he1.utilities.Utilitario;

import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "webService")
@ViewScoped
@Stateless
public class WebServiceJSFManagedBean extends Utilitario  implements Serializable {

    private ParametrosAcceso paramAcceso;

    private String nroAfiliacion = "1830271400";
    private String fechaAtencion = "21/03/2015";
    private String procedimiento = "200300";
    private String diagnostico = "K02";

    private String nroAtenciones;
    private String mensaje;

    // <editor-fold defaultstate="collapsed" desc="/** Set y Get de Variables">
    public String getNroAfiliacion() {
        return nroAfiliacion;
    }

    public void setNroAfiliacion(String nroAfiliacion) {
        this.nroAfiliacion = nroAfiliacion;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNroAtenciones() {
        return nroAtenciones;
    }

    public void setNroAtenciones(String nroAtenciones) {
        this.nroAtenciones = nroAtenciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="/** callWebService">
    public void callWebService() {
        System.out.println("1. Llamando al webService...");
        mensaje = callWebServiceConsulta(nroAfiliacion, "27/07/2015", diagnostico, procedimiento);
    }
    // </editor-fold>    

    @PostConstruct
    private void init() {
        Calendar Cal = Calendar.getInstance();
        fechaAtencion = Cal.get(Calendar.DATE) + "/" + (Cal.get(Calendar.MONTH) + 1) + "/" + Cal.get(Calendar.YEAR);
    }

    /**
     * Creates a new instance of WebServiceJSFManagedBean
     */
    public WebServiceJSFManagedBean() {
    }

}
