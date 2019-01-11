/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Finca;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    //ESTE METODO VALIDA EL INGRESO DE EL USERNAME Y LA CONTRASEÑA SEAN CORRECTAS
    public boolean IngresoLosDatosCorrectos(String nombre, String cotrasena) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "' and u.passwordlogin='" + cotrasena + "'").getResultList();
        if (!s.isEmpty()) {
            System.out.println("Son Correctas");
            return Boolean.TRUE;
        } else {
            System.out.println("Son Incorrectas");
            return Boolean.FALSE;
        }
    }

    //ESTE METODO PERMITE AL SISTEMA SABER SI EXISTE EL USUARIO
    public boolean ExisteElUsuario(String nombre) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "'").getResultList();
        if (!s.isEmpty()) {
            System.out.println("Si existe el usuario");
            return Boolean.TRUE;
        } else {
            System.out.println("No existe el usuario");
            return Boolean.FALSE;
        }
    }

    //ESTE METODO PERMITE SABER QUE USUARIO ESTA LOGUEADO 
    public Usuario getUserByUser(String nombre) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "'").getResultList();
        return s.get(0);
    }

    //ESTE METODO PERMITE SABER EL NIT DE LA FINCA DEL USUARIO FINCA LOGUEADO
    public Finca getFincaByUser(String nombre) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "'").getResultList();
        List<Finca> f = (List<Finca>) em.createQuery("SELECT f FROM Finca f WHERE f.ccUser.ccUsuario = '" + s.get(0).getCcUsuario() + "'").getResultList();
        System.out.println(f.get(0));
        return f.get(0);
    }

    //ESTE METODO PERMITE SABER LA CC DEL DUEÑO DE LA FINCA PARA VER QUE FINCAS TIENE
    public Usuario getCosechasByFinca(String nombre) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "'").getResultList();
        System.out.println(s.get(0));
        return s.get(0);
    }

    //ESTE METODO PERMITE SABER EL NIT DE LA FINCA DEL USUARIO FINCA LOGUEADO
    public Finca getFincaByNit(String nombre) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "'").getResultList();
        List<Finca> f = (List<Finca>) em.createQuery("SELECT f FROM Finca f WHERE f.ccUser.ccUsuario = '" + s.get(0).getCcUsuario() + "'").getResultList();

        return f.get(0);
    }

    //ESTE METODO VERIFICA LA CATEGORIA A LA CUAL PERTENECE EL USUARIO
    public int VerificarCategoria(String nombre, String cotrasena) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.usernamelogin='" + nombre + "' and u.passwordlogin='" + cotrasena + "'").getResultList();
        int tipoUsuario = 0;

        System.out.println("Usuario " + s.get(0).getUsernamelogin() + " tipo usuario: " + s.get(0).getIdtu().getIdtu().toString());
        tipoUsuario = Integer.parseInt(s.get(0).getIdtu().getIdtu().toString());
        return tipoUsuario;
    }

    //ESTE METODO VALIDA SI EL USUARIO ya existe (PERFIL ADMIN/COMPRADOR)
    public boolean ExisteElUsuarioPADMIN(String usernameloguin) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u where u.usernamelogin = '" + usernameloguin + "'").getResultList();

        if (s.get(0).getUsernamelogin().isEmpty()) {
            System.out.println("Creando el usuario");
            return Boolean.TRUE;
        } else {
            if (s.get(0).getUsernamelogin() == usernameloguin) {
                return Boolean.FALSE;
            }else{
            System.out.println("El usuariologuin: " + usernameloguin + " ya existe");
            return Boolean.FALSE;
            }
        }
    }

    //ESTE METODO VALIDA SI EL USUARIO O LA CC YA EXISTEN (PERFIL ADMIN/COMPRADOR)
    public boolean ExisteLACCPADMIN(String cc) {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u where u.ccUsuario = '" + cc + "'").getResultList();

        if (s.get(0).getCcUsuario().isEmpty()) {
            System.out.println("Creando el usuario");
            return Boolean.TRUE;
        } else {
            if (s.get(0).getCcUsuario() == cc) {
                return Boolean.FALSE;
            }else{
            System.out.println("El usuariologuin: " + cc + " ya existe");
            return Boolean.FALSE;
            }
        }
    }

    //ESTE METODO PERMITE CREAR EL USUARIO DESDE LA VISTA DEL ADMINISTRADOR
    public void crearUsuario(Usuario u, int tipo) {
        em.createNativeQuery("INSERT INTO Usuario VALUES('" + u.getCcUsuario() + "','"
                + u.getNombresusuario() + "','" + u.getApellidosusuario()
                + "','" + u.getUsernamelogin() + "','" + u.getPasswordlogin()
                + "','" + u.getTelefonousuario() + "','" + u.getCelularusuario()
                + "','" + u.getCorreoelectronico() + "'," + tipo + ")").executeUpdate();
    }

    //ESTE METODO PERMITE ACTUALIZAR EL USUARIO DESDE LA VISTA DEL ADMINISTRADOR
    public void Editar(String nombreUsuario, String apellidoUsuario, String telefonoUsuario, String celularUsuario,
            String correoUsuario, String ccusuario) {
        em.createQuery("Update Usuario u set "
                + "u.nombresusuario = '" + nombreUsuario + "', u.apellidosusuario = '" + apellidoUsuario + "',"
                + "u.telefonousuario = '" + telefonoUsuario + "',u.celularusuario = '" + celularUsuario + "',"
                + "u.correoelectronico = '" + correoUsuario + "' WHERE u.ccUsuario = '" + ccusuario + "'").executeUpdate();
    }

    //ESTE METODO PERMITE ACTUALIZAR LA CONTRASEÑA DEL USUARIO DESDE LA VISTA LOGUIN
    public void EditarContrasena(String username, String password, String correoElectronico) {
        em.createQuery("Update Usuario u set "
                + "u.passwordlogin = '" + password + "' WHERE u.usernamelogin = '" + username
                + "' and u.correoelectronico = '" + correoElectronico + "'").executeUpdate();
    }

    //ESTE METODO PERMITE ACTUALIZAR LA CONTRASEÑA DEL USUARIO DESDE LA VISTA LOGUIN
    public void EditarContrasena2(String username, String password) {
        em.createQuery("Update Usuario u set "
                + "u.passwordlogin = '" + password + "' WHERE u.usernamelogin = '" + username + "'").executeUpdate();
    }

    //ESTE METODO ME SIRVE PARA TRAERME TODOS LOS DATOS DE LOS USUARIOS TIPO 2
    public List<Usuario> RetoranarCCUsuarios() {
        List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.idtu.idtu = '" + 2 + "'").getResultList();
        return s;
    }

    //ESTE METODO ME SIRVE PARA TRAERME TODOS LOS DATOS DE LOS USUARIOS TIPO 2
    public String RetoranarNombreUsuarios(String cc) {
        if (!"".equals(cc)) {
            List<Usuario> s = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u WHERE u.ccUsuario = '" + cc + "'").getResultList();
            return " " + s.get(0).getNombresusuario() + " " + s.get(0).getApellidosusuario();
        } else {
            return "****";
        }
    }

}
