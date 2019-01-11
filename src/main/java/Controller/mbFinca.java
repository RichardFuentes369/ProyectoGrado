/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.FincaFacade;
import Entity.Finca;
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
@Named(value = "mbFinca")
@SessionScoped
public class mbFinca implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private FincaFacade fincaFacade;
    private Finca finca = new Finca();
    private Finca seleccionado;
    private String ccCrear = "";

    //GETTER Y SETTER AUTOGENERADOS
    public String getCcCrear() {
        return ccCrear;
    }

    public void setCcCrear(String ccCrear) {
        this.ccCrear = ccCrear;
    }

    public FincaFacade getFincaFacade() {
        return fincaFacade;
    }

    public void setFincaFacade(FincaFacade fincaFacade) {
        this.fincaFacade = fincaFacade;
    }

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    public Finca getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Finca seleccionado) {
        this.seleccionado = seleccionado;
    }

    //METODOS
    //ESTE METODO ES EL COSTRUCTOR
    public mbFinca() {
    }

    //ESTE METODO SIRVE PARA RETORNAR LOS DATOS DE LA COSECHA Y PODER LLENAR LA TABLA
    public List<Finca> RetornaDatosFincas() {
        return this.fincaFacade.findAll();
    }

    //ESTE METODO SIRVE PARA INSERTAR DATOS DE LA FINCA
    public void insertar(String nombre) {
        this.fincaFacade.crearFinca(finca, ccCrear, nombre);
        this.finca = new Finca();
    }

    //ESTE METODO SIRVE PARA BORRAR LA FINCA SELECCIONADA
    public void borrar() {
        Finca seleccionado1 = this.seleccionado;
        this.fincaFacade.remove(seleccionado1);
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR DATOS DE LA FINCA
    public void actualizar(String nitFinca) {
        Finca seleccionado1 = this.seleccionado;
        String nombreFinca = seleccionado1.getNombrefinca(),
                dpt = seleccionado1.getDepartamento(),
                pro = seleccionado1.getProvincia(),
                mun = seleccionado1.getMunicipio(),
                vereda = seleccionado1.getVereda(),
                direccion = seleccionado1.getDireccion(),
                tel = seleccionado1.getTelefono();
        this.fincaFacade.Editar(nombreFinca, dpt, pro, mun, vereda, direccion, tel, nitFinca);
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getCClogueado() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return usr.getCcUsuario();
    }

    //ESTE METODO ME TRAE EL USUARIO DE LA FINCA LOGUEADA
    public String getUserFinca() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFin");
        return usr.getUsernamelogin();
    }

    //RETORNAR LAS FINCAS
    public List<Finca> RetornarNombres() {
        return this.fincaFacade.all(this.getCClogueado());
    }

}
