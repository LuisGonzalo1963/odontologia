/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author christian_ruiz
 */
public class DatosDinamicos {

    Connection con;

    public DataSource getDS_PORTAL() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/DS_PORTAL");
    }

    private String moduloPAcientes = "";

    /**
     * @return the moduloPAcientes
     */
    public String getModuloPAcientes() {

        try {
            con = getDS_PORTAL().getConnection();
            ResultSet rs = con.prepareStatement("SELECT ID, URL_DESCRIP, URL FROM SEGURIDADES.SEG_URLS  WHERE ID = 1").executeQuery();
            while (rs.next()) {
                moduloPAcientes = rs.getString("URL");
            }
//            Insert into SEG_URLS                (ID, URL_DESCRIP, URL)              Values                (1, 'Sistema de Pacientes', '"/Paciente/userHE1?xphsumf=" ');

            con.close();
            con = null;
        } catch (Exception e) {
        }

        return moduloPAcientes;
    }

    public String loginPaciente(String usuario, String clave) throws NamingException, SQLException {
        String login = "";
        con = getDS_PORTAL().getConnection();

        CallableStatement prepareCall = con.prepareCall("{call SEGURIDADES.PACK_SEGURIDADES.LOGIN_PACIENTE (?, ?, ?) }");
        prepareCall.registerOutParameter(3, Types.VARCHAR);
        prepareCall.setString(1, usuario);
        prepareCall.setString(2, clave);
        prepareCall.executeQuery();
        login = prepareCall.getString(3);
        con.close();
        return login;
    }

    public String encripta(String usuario) throws NamingException, SQLException {
        String usuarioEncriptado = "";
        con = getDS_PORTAL().getConnection();
        CallableStatement prepareCall1 = con.prepareCall("{call  SEGURIDADES.PACK_SEGURIDADES.P_ENCRIPT_PALABRA (?, ?) }");
        prepareCall1.registerOutParameter(2, Types.VARCHAR);
        prepareCall1.setString(1, usuario);
        prepareCall1.executeQuery();
        usuarioEncriptado = prepareCall1.getString(2);
        con.close();
        return usuarioEncriptado;
    }

    public String pLogin(String usuario, String clave) throws NamingException, SQLException {
        String pIngresar = "";
        con = getDS_PORTAL().getConnection();
        CallableStatement prepareCall1 = con.prepareCall("{call SEGURIDADES.p_login (?,?,?)}");
        prepareCall1.registerOutParameter(3, Types.VARCHAR);
        prepareCall1.setString(1, usuario);
        prepareCall1.setString(2, clave);
        prepareCall1.executeQuery();
        pIngresar = prepareCall1.getString(3);
        con.close();
        return pIngresar;
    }

    public String pLoginUci(String usuario, String clave) throws NamingException, SQLException {
        String pIngresar = "";
        con = getDS_PORTAL().getConnection();
        CallableStatement prepareCall1 = con.prepareCall("{call SEGURIDADES.p_login_uci (?,?,?)}");
        prepareCall1.registerOutParameter(3, Types.VARCHAR);
        prepareCall1.setString(1, usuario);
        prepareCall1.setString(2, clave);
        prepareCall1.executeQuery();
        pIngresar = prepareCall1.getString(3);
        con.close();
        return pIngresar;
    }
    public String getCodigoPersonal(String usuario) {
        String codigoPaciente = "";
        try {

            con = getDS_PORTAL().getConnection();
            String sql = "SELECT CODIGO   FROM SIS.PERSONAL  WHERE USUARIO = '" + usuario.toUpperCase() + "' ";
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                codigoPaciente = rs.getString("CODIGO");
            }
//            Insert into SEG_URLS                (ID, URL_DESCRIP, URL)              Values                (1, 'Sistema de Pacientes', '"/Paciente/userHE1?xphsumf=" ');

            con.close();
            con = null;
        } catch (Exception e) {
        }
        return codigoPaciente;
    }

    public String decryptDinamico(String palabra) throws NamingException, SQLException {
        String decrip = "";
        con = getDS_PORTAL().getConnection();

        CallableStatement prepareCall = con.prepareCall("{call SEGURIDADES.PACK_SEGURIDADES.p_decript_palabra (?, ?) }");
        prepareCall.registerOutParameter(2, Types.VARCHAR);
        prepareCall.setString(1, palabra);
        prepareCall.executeQuery();
        decrip = prepareCall.getString(2);
        con.close();
        return decrip;
    }



}
