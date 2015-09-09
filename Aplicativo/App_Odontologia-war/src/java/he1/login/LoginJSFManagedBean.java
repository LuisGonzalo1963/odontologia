/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.login;

import he1.seguridades.sessions.SegUtilitarioFacade;
import he1.sis.sessions.PersonalFacade;
import he1.utilities.Utilitario;
import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginJSFManagedBean extends Utilitario implements Serializable {

    @EJB
    private PersonalFacade personalFacade;

    @EJB
    private SegUtilitarioFacade segUtilitarioFacade;

    DatosDinamicos datosDinamicos;

    private String usuario = "";
    private String password1 = "";
    private String usuarioEncriptado = "";
    private String usuarioClave = "";

    public String getUsuarioEncriptado() {
        return usuarioEncriptado;
    }

    public void setUsuarioEncriptado(String usuarioEncriptado) {
        this.usuarioEncriptado = usuarioEncriptado;
    }

    String urlSiguiente = "";

    public String getUrlSiguiente() {
        return urlSiguiente;
    }

    public void setUrlSiguiente(String urlSiguiente) {
        this.urlSiguiente = urlSiguiente;
    }

    /**
     * Creates a new instance of LoginJSFManagedBean
     */
    public LoginJSFManagedBean() {
        datosDinamicos = new DatosDinamicos();
    }

    public void buttonActionPersonal(ActionEvent actionEvent) throws SQLException {
        try {
            urlSiguiente = "";
            if (segUtilitarioFacade.pLoginUser(usuario, password1).equalsIgnoreCase("1")) {
                addMessage("OK");
                String codPersonal = personalFacade.findByUsuario(usuario).getCodigo();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("usuarioDB", codPersonal);
                if (codPersonal.substring(0, 1).equalsIgnoreCase("F")) {
                    ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                    handler.performNavigation("pages/asignarTurno?faces-redirect=true");

                } else if (codPersonal.substring(0, 1).equalsIgnoreCase("M")) {
                    ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                    handler.performNavigation("pages/atencionTurno?faces-redirect=true");
                }
            } else if (datosDinamicos.pLogin(usuario, password1).equalsIgnoreCase("0")) {
                System.out.println("no......");
                logout();
                //urlSiguiente = "/login.xhtml";
                addMessage("Usuario o Clave Mal Ingresados");
            }

        } catch (NamingException | SQLException e) {
             addMessage("Usuario o Clave Mal Ingresados");
             logout();
            System.out.println("e = " + e.getLocalizedMessage());
        }

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password1
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param password1 the password1 to set
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

}
