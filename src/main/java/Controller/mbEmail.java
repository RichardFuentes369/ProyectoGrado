/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.EmailFacade;
import Entity.Email;
import Entity.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author richard
 */
@Named(value = "mbEmail")
@SessionScoped
public class mbEmail implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private EmailFacade emailFacade;
    private Email email = new Email();
    private Email seleccionado;
    private String destino, mensaje, respuesta;

    //GETTER Y SETTER AUTOGENERADOS
    public EmailFacade getEmailFacade() {
        return emailFacade;
    }

    public void setEmailFacade(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Email getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Email seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    //METODOS
    //ESTE METODO ES EL COSTRUCTOR
    public mbEmail() {
    }

    //RETORNAR LAS CORREOS USUARIO 1 Y USUARIO 3
    public List<Email> RetornarCorreos() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userSes");
        return this.emailFacade.buscarCorreos(usr);
    }

    //RETORNAR LAS CORREOS USUARIO 2
    public List<Email> RetornarCorreos2() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return this.emailFacade.buscarCorreos(usr);
    }

    //ESTE METODO SIRVE PARA INSERTAR LOS CORREOS
    public void insertar(String destinatario) {
        emailFacade.crearCorreo(email, destinatario, getDestino(), getMensaje());
    }

    //ESTE METODO SIRVE PARA RESPONDER EL CORREO
    public void insertardos(String destinatario, String destino) { 
        emailFacade.crearCorreo2(email, destinatario, destino, getRespuesta());
    }

    //ESTE METODO SIRVE PARA BORRAR EL CORREO SELECCIONADO
    public void borrar() {
        Email seleccionado1 = this.seleccionado;
        this.emailFacade.remove(seleccionado1);
    }

    //ESTE METODO SIRVE PARA CONVERTIR LA FECHA
    public String convertirFecha(Date fecha) {
        if (fecha != null) {
            java.text.SimpleDateFormat sdfs
                    = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fechaSembrado = sdfs.format(fecha);

            return fechaSembrado;
        } else {
            return "";
        }
    }

    //ESTE METODO SIRVE PARA CONVERTIR LA HORA
    public String convertirHora(Date fecha) {
        System.out.println(fecha);
        if (fecha != null) {
            SimpleDateFormat sdfs1
                    = new SimpleDateFormat("hh:mm:ss");
            String fechaSembrado = sdfs1.format(fecha);
            return fechaSembrado;
        } else {
            return "";
        }
    }
}
