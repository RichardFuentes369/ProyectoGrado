/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Email;
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
public class EmailFacade extends AbstractFacade<Email> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailFacade() {
        super(Email.class);
    }

    //METODOS
    //ESTE METODO PERMITE AL USUARIO LA TABLA CORREOS
    //CON LOS DATOS DE SUS COSECHAS
    public List<Email> buscarCorreos(Usuario us) {

        List<Email> s = (List<Email>) em.createQuery("SELECT e FROM Email e, Usuario u WHERE e.ccUserdestinatario.ccUsuario = u.ccUsuario "
                + "and e.ccUserdestino = '" + us.getCcUsuario() + "'").getResultList();
        return s;
    }

    //ESTE METODO PERMITE AL USUARIO INSERTAR CORREOS
    public void crearCorreo(Email e, String destinatario, String destino, String mensaje) {
        System.out.println("Creando el correo");

        em.createNativeQuery("INSERT INTO Email (cc_userdestinatario,"
                + "cc_userdestino,mensaje,fecha,hora) VALUES('" + destinatario + "','"
                + destino + "','" + mensaje + "',Current_Date,Current_Time)").executeUpdate();
    }

    //ESTE METODO PERMITE AL USUARIO RESPONDER CORREOS
    public void crearCorreo2(Email e, String destinatario, String destino, String mensaje) {
        System.out.println("Creando el correo respuesta");
        
        em.createNativeQuery("INSERT INTO Email (cc_userdestinatario,"
                + "cc_userdestino,mensaje,fecha,hora) VALUES('" + destinatario + "','"
                + destino + "','" + mensaje + "',Current_Date,Current_Time)").executeUpdate();
    }

}
