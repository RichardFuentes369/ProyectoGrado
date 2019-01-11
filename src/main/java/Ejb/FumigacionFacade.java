/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Fumigacion;
import Entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author richard
 */
@Stateless
public class FumigacionFacade extends AbstractFacade<Fumigacion> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FumigacionFacade() {
        super(Fumigacion.class);
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 CREAR LA COSECHA  
    public void crearFumigacion(Fumigacion f, String fecha, String hora, String nitFumi) {
        System.out.println("Creando la cosecha");

        em.createNativeQuery("INSERT INTO Fumigacion (fechafumigacion,"
                + "nombreproducto,idcosecha,horafumigacion) VALUES('" + fecha
                + "','" + f.getNombreproducto() + "','" + nitFumi + "','" + hora + "')").executeUpdate();
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 LLENAR LA TABLA
    //CON LOS DATOS DE SUS FUMIGACIONES
    public List<Fumigacion> buscarFumigaciones(Usuario fin) {

        List<Fumigacion> s = (List<Fumigacion>) em.createQuery("SELECT i FROM "
                + "Fumigacion i,Cosecha c,Finca f,Usuario  u "
                + "WHERE i.idcosecha.idcosecha = c.idcosecha "
                + "and c.nitfinca.nitfinca = f.nitfinca "
                + "and f.ccUser.ccUsuario = u.ccUsuario "
                + "and u.ccUsuario= '" + fin.getCcUsuario() + "' ").getResultList();
        System.out.println(s);

        return s;
    }

    //ESTE METODO PERMITE SABER QUE USUARIO ESTA LOGUEADO
    public String getUsuarioFromFumigacion(Usuario ccUser) {

        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.ccUsuario ='" + ccUser.getCcUsuario() + "'").getResultList();
        return s.get(0).getNombresusuario() + " " + s.get(0).getApellidosusuario();

    }

    //ESTE METODO PERMITE ACTUALIZAR LA FUMIGACION AL USUARIO FINCA
    public void Editar(String fechafumigacion, String nombreproducto, String idfumigacion, String horafumigacion) {
        em.createQuery("Update Fumigacion f set "
                + "f.fechafumigacion = '" + fechafumigacion + "', f.nombreproducto = '" + nombreproducto + "',"
                + "f.horafumigacion = '" + horafumigacion + "' WHERE f.idfumigacion = '" + idfumigacion + "'").executeUpdate();
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 3 LLENAR LA TABLA
    //CON LOS DATOS DE LA FUMIGACION REQUERIDA
    public Fumigacion buscarFumigacionID(String id) {
        List<Fumigacion> s = (List<Fumigacion>) em.createQuery("SELECT f FROM "
                + "Fumigacion f,Cosecha c "
                + "WHERE f.idcosecha.idcosecha = c.idcosecha "
                + "and f.idcosecha.idcosecha = '" + Integer.parseInt(id) + "'"
                + "order by f.idfumigacion desc limit 1").getResultList();
        System.out.println(s);
        return s.get(0);
    }

}
