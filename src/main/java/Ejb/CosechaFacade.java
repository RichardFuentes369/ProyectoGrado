/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Cosecha;
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
public class CosechaFacade extends AbstractFacade<Cosecha> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CosechaFacade() {
        super(Cosecha.class);
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 CREAR LA COSECHA
    public void crearCosecha(Cosecha c, String fecha, String hora, String nit) {
        System.out.println("Creando la cosecha");

        em.createNativeQuery("INSERT INTO Cosecha (cantidad, estado,fechasembrado,"
                + "horasembrado,nitfinca) VALUES('" + c.getCantidad() + "','" + c.getEstado() + "','"+ fecha
                + "','" + hora + "','" + nit + "')").executeUpdate();
    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 ACTUALIZAR LA COSECHA
    public void Editar(String cant, Boolean est, String fesem, String horsem, String fere, String horre, int id) {
        System.out.println("Actualizando la cosecha");

        em.createQuery("UPDATE Cosecha c SET "
                + "c.fechasembrado = '" + fesem + "',c.cantidad = '"+ cant +"',c.fecharecoleccion = '"+ fere +"',"
                + "c.horasembrado = '" + horsem + "',c.horarecoleccion = '" + horre + "',"
                + "c.estado = '" + est + "' WHERE c.idcosecha = '" + id + "'").executeUpdate();
    }

    //ESTE METODO PERMITE SABER QUE USUARIO ESTA LOGUEADO
    public String getUsuarioFromCosecha(Usuario ccUser) {

        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.ccUsuario ='" + ccUser.getCcUsuario() + "'").getResultList();
        return s.get(0).getNombresusuario() + " " + s.get(0).getApellidosusuario();

    }

    //ESTE METODO PERMITE SABER QUE CC DE USUARIO ESTA LOGUEADO
    public String getUsuarioFromCosechacc(Usuario ccUser) {

        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.ccUsuario ='" + ccUser.getCcUsuario() + "'").getResultList();
        return s.get(0).getCcUsuario();

    }

    //ESTE METODO PERMITE AL USUARIO TIPO 2 LLENAR LA TABLA
    //CON LOS DATOS DE SUS COSECHAS
    public List<Cosecha> buscarCosechas(Usuario us) {

        List<Cosecha> s = (List<Cosecha>) em.createQuery("SELECT c FROM Cosecha c, Finca f, Usuario u WHERE c.nitfinca.nitfinca = f.nitfinca "
                + "and f.ccUser.ccUsuario = u.ccUsuario "
                + "and u.ccUsuario = '" + us.getCcUsuario() + "'").getResultList();
        return s;

    }
    
    //ESTE METODO PERMITE BUSCAR LA COSEHCA POR (ID COSECHA)
    public Cosecha buscarCosechasPorIdtwo(String id) {

        List<Cosecha> s = (List<Cosecha>) em.createQuery("SELECT c FROM Cosecha c WHERE c.idcosecha = '" + Integer.parseInt(id) + "' ").getResultList();
        System.out.println(s);
        return s.get(0);

    }

    //ESTE METODO PERMITE BUSCAR LA COSEHCA POR (NIT FINCA - ID COSECHA)
    public Cosecha buscarCosechasPorId(String nit, String id) {

        List<Cosecha> s = (List<Cosecha>) em.createQuery("SELECT c FROM Cosecha c WHERE c.nitfinca.nitfinca ='" + nit + "' AND c.idcosecha = " + Integer.parseInt(id) + " ").getResultList();
        System.out.println(s);
        return s.get(0);

    }
    
    //ESTE METODO PERMITE BUSCAR LA COSEHCA POR (NIT FINCA - ID COSECHA)
    public Date obtenerfecha(String id) {

        List<Cosecha> s = (List<Cosecha>) em.createNativeQuery("SELECT c FROM Cosecha c WHERE  c.idcosecha = " + Integer.parseInt(id) + " ").getResultList();
        System.out.println(s);
        return s.get(0).getFechasembrado();

    }

}
