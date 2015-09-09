/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.odonto;

import he1.odonto.entities.OHistOdonto;
import he1.odonto.sessions.OHistOdontoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis_guanoluiza
 */
@ManagedBean(name = "menuOdonto")
@ViewScoped
public class MenuOdontoJSFManagedBean implements Serializable{
    @EJB
    private OHistOdontoFacade histOdontoFacade;
    
    private List<OHistOdonto> listaHistoria;

    // <editor-fold defaultstate="collapsed" desc="/** recuperar Historia">
    public void listHistoria(){
        listaHistoria = histOdontoFacade.listByTurno(null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/** PostConstruct init()">
    // </editor-fold>

    /**
     * Creates a new instance of MenuOdontoJSFManagedBean
     */
    public MenuOdontoJSFManagedBean() {
    }
    
}
