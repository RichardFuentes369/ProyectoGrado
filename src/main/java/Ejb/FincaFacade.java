/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entity.Finca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author richard
 */
@Stateless
public class FincaFacade extends AbstractFacade<Finca> {

    @PersistenceContext(unitName = "com.mycompany_ProyectoGrado_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FincaFacade() {
        super(Finca.class);
    }

    //ESTE METODO PERMITE AL USUARIO ADMINISTRADOR CREAR LA FINCA PARA UN USUARIO TIPO 2 YA REGISTRADO
    public void crearFinca(Finca finca, String ccCrear, String nombrecompleto) {

        em.createNativeQuery("INSERT INTO Finca (nombrefinca,nitfinca,departamento,provincia,municipio,"
                + "vereda,direccion,nombredueno,telefono,cc_user) VALUES('" + finca.getNombrefinca()
                + "','" + finca.getNitfinca() + "','" + finca.getDepartamento() + "','" + finca.getProvincia()
                + "','" + finca.getMunicipio() + "','" + finca.getVereda()
                + "','" + finca.getDireccion() + "','" + nombrecompleto
                + "','" + finca.getTelefono() + "','" + ccCrear + "')").executeUpdate();

    }

    //ESTE METODO PERMITE AL USUARIO ADMINISTRADOR ACTUALIZAR LOS DATOS DE LA FINCA
    public void Editar(String nombreFinca, String dpt, String pro, String mun, String vereda, String direccion, String tel, String nitFinca) {

        em.createQuery("Update Finca  set "
                + "nombrefinca = '" + nombreFinca + "', departamento = '" + dpt + "',"
                + "provincia = '" + pro + "' , municipio = '" + mun + "' , vereda = '" + vereda + "',direccion = '" + direccion + "',"
                + "direccion = '" + direccion + "',"
                + "telefono = '" + tel + "' WHERE nitfinca = '" + nitFinca + "'").executeUpdate();

    }

    //ESTE METODO PERMITE CONSULTAR LOS DATOS DE LA FINCA QUE LE PERTENECE AL USUARIO
    public List<Finca> all(String cc) {
        List<Finca> f = (List<Finca>) em.createQuery("SELECT f FROM Finca f where f.ccUser.ccUsuario = '" + cc + "'").getResultList();
        return f;
    }

    //ESTE METODO VALIDA SI EL NIT DE LA FINCA YA EXISTE (PERFIL ADMIN)
    public boolean ExisteElnitFinca(String nit) {
        List<Finca> s = (List<Finca>) em.createQuery("SELECT f FROM Finca f where f.nitfinca = '" + nit + "'").getResultList();

        if (s.get(0).getNitfinca().isEmpty()) {
            System.out.println("Creando el usuario");
            return Boolean.FALSE;
        } else {
            System.out.println("El nit: " + nit + " ya existe");
            return Boolean.TRUE;
        }
    }

}
