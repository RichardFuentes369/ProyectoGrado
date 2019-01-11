/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.UsuarioFacade;
import Entity.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author richard
 */
@Named(value = "mbUsuarios")
@SessionScoped

public class mbUsuarios implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private UsuarioFacade usuarioFacade;
    private Usuario usuario = new Usuario();
    private Usuario seleccionado;
    private String username, password, correoElectronico, newcontrasena;
    private int tipo;

    //ESTE ES EL METODO CONSTRUCTOS
    public mbUsuarios() {
    }

    //GETTER Y SETTER AUTOGENERADOS
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }

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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNewcontrasena() {
        return newcontrasena;
    }

    public void setNewcontrasena(String newcontrasena) {
        this.newcontrasena = newcontrasena;
    }

    //METODOS
    //ESTE METODO SIRVE PARA RETORNAR LOS DATOS DE LOS USUARIOS Y PODER LLENAR LA TABLA
    //DEL PERFIL DE CATEGORIA #1
    public List<Usuario> RetornaDatosUsuarios() {
        return this.usuarioFacade.findAll();
    }
    
//    //Validacion Para insertar (no funcióno)
//    public void insertar() throws IOException {
//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesContext contex = FacesContext.getCurrentInstance();
//        System.out.println("Insertando usuario desde el admin");
//        if (this.usuarioFacade.ExisteElUsuarioPADMIN(usuario.getUsernamelogin()) == false) {
//            System.out.println("paila ya existe");
//            contex.getExternalContext().redirect("../../Views/Administrador/ErrorInsertarUsuario.xhtml");
//        } else {
//            if (this.usuarioFacade.ExisteLACCPADMIN(usuario.getCcUsuario()) == false) {
//                System.out.println("paila ya existe");
//                contex.getExternalContext().redirect("../../Views/Administrador/ErrorInsertarUsuario.xhtml");
//            } else {
//                this.usuarioFacade.crearUsuario(usuario, this.tipo);
//                System.out.println("Insertando el usuario");
//                contex.getExternalContext().redirect("../../Views/Administrador/InsertoUsuarioCorrectamente.xhtml");
//            }
//        }
//    }

    //ESTE METODO SIRVE PARA INSERTAR LOS USUARIOS POR MEDIO DEL ADMINISTRADOR
    public void insertar() {
        this.usuarioFacade.crearUsuario(usuario, this.tipo);
    }

    //ESTE METODO SIRVE PARA INSERTAR LOS USUARIOS TIPO 3
    public void insertar2() {
        this.usuarioFacade.crearUsuario(usuario, this.tipo = 3);
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR AL USUARIOS POR MEDIO DEL ADMINISTRADOR
    public void actualizar(String ccusuario) {
        Usuario seleccionado1 = this.seleccionado;
        String nombreUsuario = seleccionado1.getNombresusuario(),
                apellidoUsuario = seleccionado1.getApellidosusuario(),
                telefonoUsuario = seleccionado1.getTelefonousuario(),
                celularUsuario = seleccionado1.getCelularusuario(),
                correoUsuario = seleccionado1.getCorreoelectronico();
        this.usuarioFacade.Editar(nombreUsuario, apellidoUsuario, telefonoUsuario, celularUsuario, correoUsuario, ccusuario);
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR LA CONTRASEÑA SI ESTA SE A OLVIDADO AL MOMENTO DE INGRESAR
    //BOTON OLVIDE MI CONTRASEÑA
    public void cambiarContrasena() {
        this.usuarioFacade.EditarContrasena(this.username, this.password, this.correoElectronico);
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR LA CONTRASEÑA DEL USUARIO 1 Y 3 LOGUEADO
    public void cambiarContrasena2(String usernamei) throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        this.usuarioFacade.EditarContrasena2(usernamei, this.newcontrasena);
        contex.getExternalContext().redirect("../../Views/Home/Ingresar.xhtml");
    }

    //ESTE METODO SIRVE PARA BORRAR EL USUARIO SELECCIONADO POR MEDIO DEL ADMINISTRADOR
    public void borrar() {
        Usuario seleccionado1 = this.seleccionado;
        this.usuarioFacade.remove(seleccionado1);
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getUserName() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userSes");
        return usr.getNombresusuario() + " " + usr.getApellidosusuario();
    }

    //ESTE METODO ME TRAE EL USERNAME DEL USUARIO LOGUEADO
    public String getUserNameU() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userSes");
        return usr.getUsernamelogin();
    }

    //ESTE METODO ME TRAE LA CC DEL USUARIO LOGUEADO
    public String getCC() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userSes");
        return usr.getCcUsuario();
    }

    //ESTE METODO ME RETORNA LA CEDULA DE CIUDADANIA DE LOS USUARIOS DE LAS FINCAS
    public List<Usuario> RetornaCCDatosUsuariosFinca() {
        return this.usuarioFacade.RetoranarCCUsuarios();
    }

    //ESTE METODO ME RETORNA EL NOMBRE DEL DUEÑO DE LA FINCA
    public String RetornaNombreDuenoFinca(String cc) {
        return this.usuarioFacade.RetoranarNombreUsuarios(cc);
    }

    //ESTE METODO ME GENERA EL REPORTE DE LOS USUARIOS DEL SISTEMA POR MEDIO DEL ADMINISTRADOR
    public void generarReporte() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("/ProyectoGrado/faces/Views/Home/index.xhtml/UserReport");
    }

}
