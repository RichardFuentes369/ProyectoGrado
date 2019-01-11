/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.TransporteFacade;
import Entity.Finca;
import Entity.Transporte;
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
@Named(value = "mbTransporte")
@SessionScoped

public class mbTransporte implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private TransporteFacade transporteFacade;
    private Transporte transporte = new Transporte();
    private Transporte seleccionado;
    private String temperatura, descripcion, idcosecha;
    private mbCosecha obj = new mbCosecha();

    //GETTER Y SETTER AUTOGENERADOS
    public TransporteFacade getTransporteFacade() {
        return transporteFacade;
    }

    public void setTransporteFacade(TransporteFacade transporteFacade) {
        this.transporteFacade = transporteFacade;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Transporte getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Transporte seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public mbTransporte() {
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getCClogueado() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return usr.getCcUsuario();
    }

    //ESTE METODO SIRVE PARA RETORNAR LOS DATOS DEL TRANSPORTE Y PODER LLENAR LA TABLA
    //DEL PERFIL DE CATEGORIA #2
    //TRANSPORTE
    public List<Transporte> RetornaDatosTransporte() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario fin = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return this.transporteFacade.buscarTrasporte(fin);
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
        if (fecha != null) {
            SimpleDateFormat sdfs1
                    = new SimpleDateFormat("hh:mm:ss");
            String fechaSembrado = sdfs1.format(fecha);
            return fechaSembrado;
        } else {
            return "";
        }
    }

    //ESTE METODO SIRVE PARA INSERTAR LOS TRANSPORTES
    public void insertar(String idcosecha, Date x) throws ParseException, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        
        Date fechsalida = transporte.getFechasalida();
        Date horasalida = transporte.getTiemposalida();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs2
                = new SimpleDateFormat("hh:mm:ss");

        String fechaSalida = sdfs.format(fechsalida);

        String horaSalida = sdfs2.format(horasalida);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateSalida = sdf.parse(fechaSalida);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date datehrSalida = sdf2.parse(horaSalida);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }

        //obtener la cantidad de dias de la fecha de creacion y la fecha de transporte
        //150 dias, 5 meses que es la fecha minima de la recoleccion de la mora validado cada mes por 31 dias
        int dias = (int) ((fechsalida.getTime() - x.getTime()) / 86400000);

        transporte.setFechasalida(cal1.getTime());
        if (dias >= 150) {
            transporteFacade.crearTransporte(transporte, fechaSalida, horaSalida, idcosecha);
            contex.getExternalContext().redirect("../../Views/Finca/Transporte.xhtml");
        } else {
            contex.getExternalContext().redirect("../../Views/Finca/ErrorInsertoTransporte.xhtml");
        }
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR LA TRANSPORTE
    public void actualizar(String idtranspo) {
        Transporte seleccionado1 = this.seleccionado;

        Date fechasal = seleccionado1.getFechasalida();
        Date horasalida = seleccionado1.getTiemposalida();

        Date horarecogida = seleccionado1.getTiemporecogida();

        Date fechalle = seleccionado1.getFechallegada();
        Date horallegada = seleccionado1.getTiempollegada();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs1
                = new SimpleDateFormat("hh:mm:ss");

        String fesalida = sdfs.format(fechasal);
        String fellegada = sdfs.format(fechalle);
        String hosalida = sdfs1.format(horasalida);
        String horecogida = sdfs1.format(horarecogida);
        String hollegada = sdfs1.format(horallegada);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        Calendar cal3 = new GregorianCalendar();
        Calendar cal4 = new GregorianCalendar();
        Calendar cal5 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateFesal = sdf.parse(fesalida);
        } catch (ParseException ex) {
            Logger.getLogger(mbTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date datehosal = sdf1.parse(hosalida);
        } catch (ParseException ex) {
            Logger.getLogger(mbTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateFelle = sdf.parse(fellegada);
        } catch (ParseException ex) {
            Logger.getLogger(mbTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date datehoreco = sdf1.parse(horecogida);
        } catch (ParseException ex) {
            Logger.getLogger(mbTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateholle = sdf1.parse(hollegada);
        } catch (ParseException ex) {
            Logger.getLogger(mbTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }

        transporte.setFechasalida(cal1.getTime());
        transporte.setTiemposalida(cal2.getTime());
        transporte.setTiemporecogida(cal3.getTime());
        transporte.setFechallegada(cal4.getTime());
        transporte.setTiempollegada(cal4.getTime());

        String tem = seleccionado1.getTemperatura();
        String des = seleccionado1.getDescripcion();
        String idcose = seleccionado1.getIdcosecha().getIdcosecha().toString();
        this.transporteFacade.Editar(fesalida, tem, des, idcose, fellegada, hosalida, hollegada, horecogida, idtranspo);
    }

    //ESTE METODO SIRVE PARA BORRAR LA COSECHA SELECCIONADA
    public void borrar() {
        Transporte seleccionado1 = this.seleccionado;
        this.transporteFacade.remove(seleccionado1);
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getUserName() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Finca usr = (Finca) contex.getExternalContext().getSessionMap().get("userSes");
        return transporteFacade.getUsuarioFromTransporte(usr.getCcUser());
    }

    //ESTE METODO ME TRAE LA FECHA DE SALIDA
    public Date obtenerFechaSalida(String id) {
        Transporte seleccionado1 = this.seleccionado;
        return this.transporteFacade.obtenerfecha(seleccionado1.toString());
    }

}
