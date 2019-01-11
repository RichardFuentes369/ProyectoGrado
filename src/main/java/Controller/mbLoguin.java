/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.UsuarioFacade;
import Entity.Finca;
import Entity.Usuario;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author richard
 */
@ManagedBean
@ViewScoped
public class mbLoguin implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private UsuarioFacade usuarioFacade;
    private String username;
    private String password;

    //ESTE ES EL METODO CONSTRUCTOS
    public mbLoguin() {
    }

    //GETTER Y SETTER AUTOGENERADOS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    //METODOS
    //ESTE METODO ES EL COSTRUCTOR
    //ESTE ES EL METODO LOGUIN
    //SE LE PASA UN USUARIO Y CONTRASEÑA EL CUAL VA A BUSCAR EN LA BASE DE DATOS 
    //POR MEDIO DEL CONTROLADOR PARA VERIFICAR SI EXISTE Y LA CATEGORIA DE DICHO USUARIO
    //PARA ASI PODER ENVIARLO A LA SESSION CORRECTA
    public void login(ActionEvent event) throws IOException {
        System.out.println("Voy a loguear");
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        if (this.usuarioFacade.IngresoLosDatosCorrectos(username, password) == true) {
            switch (this.usuarioFacade.VerificarCategoria(username, password)) {
                case 1:
                    {
                        //user logueado
                        Usuario user = this.usuarioFacade.getUserByUser(username);
                        contex.getExternalContext().getSessionMap().put("userSes", user);
                        contex.getExternalContext().redirect("../../Views/Administrador/Administrador.xhtml");
                        break;
                    }
                case 2:
                    {
                        //user logueado
                        Usuario userna = this.usuarioFacade.getCosechasByFinca(username);
                        contex.getExternalContext().getSessionMap().put("userFin", userna);
                        //user cc
                        Usuario cc = this.usuarioFacade.getCosechasByFinca(username);
                        contex.getExternalContext().getSessionMap().put("userFinCC", cc);
                        //nitfinca
                        Finca user = this.usuarioFacade.getFincaByUser(username);
                        contex.getExternalContext().getSessionMap().put("userSes", user);
                        contex.getExternalContext().redirect("../../Views/Finca/Finca.xhtml");
                        break;
                    }
                case 3:
                    {
                        //user logueado
                        Usuario user = this.usuarioFacade.getUserByUser(username);
                        contex.getExternalContext().getSessionMap().put("userSes", user);
                        contex.getExternalContext().redirect("../../Views/Comprador/Comprador.xhtml");
                        break;
                    }
                default:
                    break;
            }
        } else {
            contex.getExternalContext().redirect("../../Views/Home/Error.xhtml");
        }

    }

    //ESTE METODO SIRVE PARA CERRAR LA SESSION
    public void salir() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("/ProyectoGrado/faces/Views/Home/index.xhtml/faces/Views/Home/Ingresar.xhtml");
    }

}
