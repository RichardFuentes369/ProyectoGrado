package Entity;

import Entity.Cosecha;
import Entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Finca.class)
public class Finca_ { 

    public static volatile SingularAttribute<Finca, String> nitfinca;
    public static volatile SingularAttribute<Finca, Usuario> ccUser;
    public static volatile SingularAttribute<Finca, String> nombrefinca;
    public static volatile ListAttribute<Finca, Cosecha> cosechaList;
    public static volatile SingularAttribute<Finca, String> municipio;
    public static volatile SingularAttribute<Finca, String> nombredueno;
    public static volatile SingularAttribute<Finca, String> direccion;
    public static volatile SingularAttribute<Finca, String> departamento;
    public static volatile SingularAttribute<Finca, String> provincia;
    public static volatile SingularAttribute<Finca, String> telefono;
    public static volatile SingularAttribute<Finca, Integer> idfinca;
    public static volatile SingularAttribute<Finca, String> vereda;

}