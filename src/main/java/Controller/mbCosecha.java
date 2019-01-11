
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Ejb.CosechaFacade;
import Ejb.FumigacionFacade;
import Ejb.TransporteFacade;
import Entity.Cosecha;
import Entity.Finca;
import Entity.Fumigacion;
import Entity.Transporte;
import Entity.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author richard
 */
@Named(value = "mbCosecha")
@SessionScoped

public class mbCosecha implements Serializable {

    @EJB

    //ATRIBUTOS NECESARIOS PARA LA CLASE
    private CosechaFacade cosechaFacade;
    private Cosecha cosecha = new Cosecha();
    private Cosecha cosechaBusqueda = new Cosecha();
    private String cantidad;
    private Boolean activada;
    private FumigacionFacade fumigacionFacade;
    private Fumigacion fumigacionBusquedatwo = new Fumigacion();
    private TransporteFacade transporteFacade;
    private Transporte transporteBusquedathree = new Transporte();
    private Cosecha seleccionado;
    private int tipo;
    private String buscar;
    private String nitfinca;

    //GETTER Y SETTER AUTOGENERADOS
    public CosechaFacade getCosechaFacade() {
        return cosechaFacade;
    }

    public void setCosechaFacade(CosechaFacade cosechaFacade) {
        this.cosechaFacade = cosechaFacade;
    }

    public Cosecha getCosecha() {
        return cosecha;
    }

    public void setCosecha(Cosecha cosecha) {
        this.cosecha = cosecha;
    }

    public Cosecha getCosechaBusqueda() {
        return cosechaBusqueda;
    }

    public void setCosechaBusqueda(Cosecha cosechaBusqueda) {
        this.cosechaBusqueda = cosechaBusqueda;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getActivada() {
        return activada;
    }

    public void setActivada(Boolean activada) {
        this.activada = activada;
    }

    public FumigacionFacade getFumigacionFacade() {
        return fumigacionFacade;
    }

    public void setFumigacionFacade(FumigacionFacade fumigacionFacade) {
        this.fumigacionFacade = fumigacionFacade;
    }

    public Fumigacion getFumigacionBusquedatwo() {
        return fumigacionBusquedatwo;
    }

    public void setFumigacionBusquedatwo(Fumigacion fumigacionBusquedatwo) {
        this.fumigacionBusquedatwo = fumigacionBusquedatwo;
    }

    public TransporteFacade getTransporteFacade() {
        return transporteFacade;
    }

    public void setTransporteFacade(TransporteFacade transporteFacade) {
        this.transporteFacade = transporteFacade;
    }

    public Transporte getTransporteBusquedathree() {
        return transporteBusquedathree;
    }

    public void setTransporteBusquedathree(Transporte transporteBusquedathree) {
        this.transporteBusquedathree = transporteBusquedathree;
    }

    public Cosecha getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cosecha seleccionado) {
        this.seleccionado = seleccionado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getNitfinca() {
        return nitfinca;
    }

    public void setNitfinca(String nitfinca) {
        this.nitfinca = nitfinca;
    }

    //METODOS
    //ESTE METODO ES EL COSTRUCTOR
    public mbCosecha() {
    }

    //ESTE METODO SIRVE PARA RETORNAR LOS DATOS DE LA COSECHA Y PODER LLENAR LA TABLA
    //DEL PERFIL DE CATEGORIA #2
    //COSECHAS
    public List<Cosecha> RetornaDatosCosecha() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userFinCC");
        return this.cosechaFacade.buscarCosechas(usr);
    }

    //ESTE METODO SIRVE PARA SEPARAR EL FILTRO IDFINCA - IDCOSECHA
    public void getCosechaByFactura() {
        String[] exploded = this.buscar.split("-");
        String x = exploded[0];
        String y = exploded[1];
        this.cosechaBusqueda = (Cosecha) this.cosechaFacade.buscarCosechasPorId(x, y);
        this.fumigacionBusquedatwo = (Fumigacion) this.fumigacionFacade.buscarFumigacionID(y);
        this.transporteBusquedathree = (Transporte) this.transporteFacade.buscarTemperaturaFumigacion(y);
    }

    public String getDatosCosecha() {
        String x = "";
        return x;
    }

    //ESTE METODO SIRVE PARA BORRAR LA COSECHA SELECCIONADA
    public void borrar() {
        Cosecha seleccionado1 = this.seleccionado;
        this.cosechaFacade.remove(seleccionado1);
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

    //ESTE METODO SIRVE PARA INSERTAR LAS COSECHAS
    public void insertar() throws ParseException {
        Date sembrado = cosecha.getFechasembrado();
        Date horasem = cosecha.getHorasembrado();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs2
                = new SimpleDateFormat("hh:mm:ss");

        String fechaSembrado = sdfs.format(sembrado);
        String horaSembrado = sdfs2.format(horasem);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateSemb = sdf.parse(fechaSembrado);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateTim2 = sdf2.parse(horaSembrado);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }

        cosecha.setFechasembrado(cal1.getTime());
        cosecha.setHorasembrado(cal2.getTime());
        cosechaFacade.crearCosecha(cosecha, fechaSembrado, horaSembrado, getNitfinca());
    }

    //ESTE METODO SIRVE PARA ACTUALIZAR LOS DATOS DE LAS COSECHAS
    public void actualizar(String cosechaSeleccionada) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();

        Cosecha seleccionado1 = this.seleccionado;
        String Cantidad = seleccionado1.getCantidad().toString();
        Boolean Estado = seleccionado1.getEstado();

        Date sembrado = seleccionado1.getFechasembrado();
        Date horasem = seleccionado1.getHorasembrado();
        Date recoleccion = seleccionado1.getFecharecoleccion();
        Date horare = seleccionado1.getHorarecoleccion();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdfs
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfs1
                = new SimpleDateFormat("hh:mm:ss");

        String fechaSembrado = sdfs.format(sembrado);
        String fechaRecoleccion = sdfs.format(recoleccion);
        String horaSembrado = sdfs1.format(horasem);
        String horaRecoleccion = sdfs1.format(horare);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        Calendar cal3 = new GregorianCalendar();
        Calendar cal4 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        try {
            Date dateSemb = sdf.parse(fechaSembrado);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateRec = sdf.parse(fechaRecoleccion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateTim1 = sdf2.parse(horaSembrado);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date dateTim2 = sdf2.parse(horaRecoleccion);
        } catch (ParseException ex) {
            Logger.getLogger(mbCosecha.class.getName()).log(Level.SEVERE, null, ex);
        }

        cosecha.setFecharecoleccion(cal1.getTime());
        cosecha.setFechasembrado(cal1.getTime());
        cosecha.setFecharecoleccion(cal2.getTime());
        cosecha.setHorasembrado(cal3.getTime());
        cosecha.setHorarecoleccion(cal4.getTime());

        //obtener la cantidad de dias de la fecha de creacion y la fecha de transporte
        //150 dias, 5 meses que es la fecha minima de la recoleccion de la mora validado cada mes por 31 dias
        int dias = (int) ((recoleccion.getTime() - sembrado.getTime()) / 86400000);
        System.out.println(dias);
        if (dias >= 150) {
            this.cosechaFacade.Editar(Cantidad, Estado, fechaSembrado, horaSembrado, fechaRecoleccion, horaRecoleccion, Integer.parseInt(cosechaSeleccionada));
        } else {
            contex.getExternalContext().redirect("../../Views/Finca/ErrorInsertoCosecha.xhtml");
        }
    }

    //ESTE METODO ME TRAE EL NOMBRE DEL USUARIO LOGUEADO
    public String getUserName() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Finca usr = (Finca) contex.getExternalContext().getSessionMap().get("userSes");
        return cosechaFacade.getUsuarioFromCosecha(usr.getCcUser());
    }

    //ESTE METODO ME TRAE LA CC DEL USUARIO LOGUEADO
    public String getCClogueado() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Usuario usr = (Usuario) contex.getExternalContext().getSessionMap().get("userSes");
        return usr.getCcUsuario();
    }

    //ESTE METODO ME TRAE LA CC DEL USUARIO FINCA LOGUEADO
    public String getCCFinca() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext contex = FacesContext.getCurrentInstance();
        Finca usr = (Finca) contex.getExternalContext().getSessionMap().get("userSes");
        return cosechaFacade.getUsuarioFromCosechacc(usr.getCcUser());
    }

    //ESTE METODO ME TRAE LA FECHA DE SEMBRADO
    public Date obtenerFechaSembrado(String id) {
        Cosecha seleccionado1 = this.seleccionado;
        return this.cosechaFacade.obtenerfecha(seleccionado1.toString());
    }

    //ESTE METODO ME TRAE LA FECHA DE SEMBRADO PARA EL TRASPORTE Y FUMIGACIÓN
    public Date obtenerFechaSembrado2(String id) {
        return this.cosechaFacade.obtenerfecha(id);
    }

}
