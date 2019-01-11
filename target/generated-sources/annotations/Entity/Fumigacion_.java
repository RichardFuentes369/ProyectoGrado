package Entity;

import Entity.Cosecha;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Fumigacion.class)
public class Fumigacion_ { 

    public static volatile SingularAttribute<Fumigacion, String> nombreproducto;
    public static volatile SingularAttribute<Fumigacion, Integer> idfumigacion;
    public static volatile SingularAttribute<Fumigacion, Date> fechafumigacion;
    public static volatile SingularAttribute<Fumigacion, Date> horafumigacion;
    public static volatile SingularAttribute<Fumigacion, Cosecha> idcosecha;

}