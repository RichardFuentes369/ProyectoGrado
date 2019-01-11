/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Transporte;
import Entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author richard
 */
@Stateless
public class TransporteFacade extends AbstractFacade<Transporte> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteFacade() {
        super(Transporte.class);
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 CREAR LA COSECHA
    public void crearTransporte(Transporte t, String fechasalida, String horasalida, String idcosecha) {
        System.out.println("Creando el transporte");

        em.createNativeQuery("INSERT INTO Transporte (fechasalida,"
                + "tiemposalida,idcosecha) VALUES('" + fechasalida
                + "','" + horasalida + "','" + idcosecha + "')").executeUpdate();
    }

    //ESTE METODO PERMITE ACTUALIZAR EL TRANSPORTE AL USUARIO FINCA
    public void Editar(String fesalida, String temp, String des, String idcose,
            String fellegada, String tiemsal, String tiemlle, String tiempreco, String idtran) {

        em.createNativeQuery("Update Transporte set "
                + "fechallegada = '" + fellegada + "', temperatura = '" + temp + "',"
                + " descripcion = '" + des + "', idcosecha = '" + idcose + "',"
                + " fechasalida = '" + fesalida + "', tiemposalida = '" + tiemsal + "',"
                + " tiempollegada = '" + tiemlle + "', tiemporecogida = '" + tiempreco + "' WHERE idtransporte = '" + idtran + "'").executeUpdate();
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 LLENAR LA TABLA
    //CON LOS DATOS DE SU TRANSPORTE
    public List<Transporte> buscarTrasporte(Usuario fin) {

        List<Transporte> s = (List<Transporte>) em.createQuery("SELECT t FROM "
                + "Transporte t,Cosecha c,Finca f,Usuario  u "
                + "WHERE t.idcosecha.idcosecha = c.idcosecha "
                + "and c.nitfinca.nitfinca = f.nitfinca "
                + "and f.ccUser.ccUsuario = u.ccUsuario "
                + "and u.ccUsuario= '" + fin.getCcUsuario() + "' ").getResultList();
        System.out.println(s);
        return s;

    }

    //ESTE METODO PERMITE SABER QUE USUARIO ESTA LOGUEADO
    public String getUsuarioFromTransporte(Usuario ccUser) {

        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.ccUsuario ='" + ccUser.getCcUsuario() + "'").getResultList();
        return s.get(0).getNombresusuario() + " " + s.get(0).getApellidosusuario();

    }

    //ESTE METODO PERMITE AL USUARIO TIPO 3 LLENAR LA TABLA
    //CON LOS DATOS DEL TRANSPORTE REQUERIDA
    public Transporte buscarTemperaturaFumigacion(String id) {
        List<Transporte> s = (List<Transporte>) em.createQuery("SELECT t FROM "
                + "Transporte t,Cosecha c "
                + "WHERE t.idcosecha.idcosecha = c.idcosecha "
                + "and t.idcosecha.idcosecha = '" + Integer.parseInt(id) + "'").getResultList();
        System.out.println(s);
        return s.get(0);
    }

    //ESTE METODO PERMITE BUSCAR LA COSEHCA POR (NIT FINCA - ID COSECHA)
    public Date obtenerfecha(String id) {

        List<Transporte> s = (List<Transporte>) em.createQuery("SELECT t FROM Transporte c WHERE  c.idcosecha = " + Integer.parseInt(id) + " ").getResultList();
        System.out.println(s);
        return s.get(0).getFechasalida();

    }

}
