package Entity;

import Entity.Finca;
import Entity.Fumigacion;
import Entity.Transporte;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Cosecha.class)
public class Cosecha_ { 

    public static volatile SingularAttribute<Cosecha, Finca> nitfinca;
    public static volatile SingularAttribute<Cosecha, Boolean> estado;
    public static volatile ListAttribute<Cosecha, Fumigacion> fumigacionList;
    public static volatile ListAttribute<Cosecha, Transporte> transporteList;
    public static volatile SingularAttribute<Cosecha, Date> fecharecoleccion;
    public static volatile SingularAttribute<Cosecha, Date> horarecoleccion;
    public static volatile SingularAttribute<Cosecha, Date> fechasembrado;
    public static volatile SingularAttribute<Cosecha, Date> horasembrado;
    public static volatile SingularAttribute<Cosecha, Float> cantidad;
    public static volatile SingularAttribute<Cosecha, Integer> idcosecha;

}