/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.FumigacionFacade;
import Entity.Finca;
import Entity.Fumigacion;
import Entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author richard
 */
@Named(value = "mbFumigacion")
@SessionScoped

public class mbFumigacion implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private FumigacionFacade fumigacionFacade;
    private Fumigacion fumigacion = new Fumigacion();
    private Fumigacion seleccionado;
    private String nombreproducto, idcosecha;
    private mbCosecha obj = new mbCosecha();

    //GETTER Y SETTER AUTOGENERADOS
    public FumigacionFacade getFumigacionFacade() {
        return fumigacionFacade;
    }

    public void setFumigacionFacade(FumigacionFacade fumigacionFacade) {
        this.fumigacionFacade = fumigacionFacade;
    }

    public Fumigacion getFumigacion() {
        return fumigacion;
    }

    public void setFumigacion(Fumigacion fumigacion) {
        this.fumigacion = fumigacion;
    }

    public Fumigacion getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Fumigacion seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getIdcosecha() {
        return idcosecha;
    }

    public void setIdcosecha(String idcosecha) {
        this.idcosecha = idcosecha;
    }

    public mbCosecha getObj() {
        return obj;
    }

    public void setObj(mbCosecha obj) {
        this.obj = obj;
    }

    //METODOS
    //ESTE METODO ES EL COSTRUCTOR
    public mbFumigacion() {
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getCClogueado() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return usr.getCcUsuario();
    }

    //ESTE METODO SIRVE PARA RETORNAR LOS DATOS DE LA COSECHA Y PODER LLENAR LA TABLA
    //DEL PERFIL DE CATEGORIA #2
    //FUMIGACION
    public List<Fumigacion> RetornaDatosFumigacion() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario fin = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return this.fumigacionFacade.buscarFumigaciones(fin);
    }

    //ESTE METODO SIRVE PARA CONVERTIR LA FECHA
    public String convertirFecha(Date fecha) {
        if (fecha != null) {
            java.text.SimpleDateFormat sdfs
                    = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fechaFumigacion = sdfs.format(fecha);
            return fechaFumigacion;
        } else {
            return "";
        }
    }

    //ESTE METODO SIRVE PARA CONVERTIR LA HORA
    public String convertirHora(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdfs1
                    = new SimpleDateFormat("hh:mm:ss");
            String fechaFumigacion = sdfs1.format(fecha);
            return fechaFumigacion;
        } else {
            return "";
        }
    }

    //ESTE METODO SIRVE PARA INSERTAR LAS FUMIGACIONES
    public void insertar(String idcosecha, Date x) throws ParseException, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();

        Date fumife = fumigacion.getFechafumigacion();
        Date horafum = fumigacion.getHorafumigacion();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs2
                = new SimpleDateFormat("hh:mm:ss");

        String fechaFumigacion = sdfs.format(fumife);
        String horaFumigacion = sdfs2.format(horafum);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateFumi = sdf.parse(fechaFumigacion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateTim1 = sdf2.parse(horaFumigacion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }

        //obtener la cantidad de dias de la fecha de creacion y la fecha de fumigacion
        int dias = (int) ((fumife.getTime() - x.getTime()) / 86400000);

        fumigacion.setFechafumigacion(cal1.getTime());
        fumigacion.setHorafumigacion(cal2.getTime());

        if (dias >= 0) {
            fumigacionFacade.crearFumigacion(fumigacion, fechaFumigacion, horaFumigacion, idcosecha);
            contex.getExternalContext().redirect("../../Views/Finca/Fumigacion.xhtml");
        } else {
            contex.getExternalContext().redirect("../../Views/Finca/ErrorInsertoFumigacion.xhtml");
        }
    }

    //ESTE METODO SIRVE PARA BORRAR LA FUMIGACION SELECCIONADA
    public void borrar() {
        Fumigacion seleccionado1 = this.seleccionado;
        this.fumigacionFacade.remove(seleccionado1);
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getUserName() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Finca usr = (Finca) contex.getExternalContext().getSessionMap().get("userSes");
        return fumigacionFacade.getUsuarioFromFumigacion(usr.getCcUser());
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR LA FUMIGACION
    public void actualizar(String idfumigacion) {
        Fumigacion seleccionado1 = this.seleccionado;

        Date fumife = seleccionado1.getFechafumigacion();
        Date horafum = seleccionado1.getHorafumigacion();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs2
                = new SimpleDateFormat("hh:mm:ss");

        String fechaFumigacion = sdfs.format(fumife);
        String horaFumigacion = sdfs2.format(horafum);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateFumi = sdf.parse(fechaFumigacion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateTim1 = sdf2.parse(horaFumigacion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fumigacion.setFechafumigacion(cal1.getTime());
        fumigacion.setHorafumigacion(cal2.getTime());

        String np = seleccionado1.getNombreproducto();
        String ifumi = seleccionado1.getIdfumigacion().toString();
        this.fumigacionFacade.Editar(fechaFumigacion, np, ifumi, horaFumigacion);
    }

}
