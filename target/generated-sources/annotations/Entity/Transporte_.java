package Entity;

import Entity.Cosecha;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Transporte.class)
public class Transporte_ { 

    public static volatile SingularAttribute<Transporte, String> descripcion;
    public static volatile SingularAttribute<Transporte, Date> tiemporecogida;
    public static volatile SingularAttribute<Transporte, Date> tiemposalida;
    public static volatile SingularAttribute<Transporte, Date> fechasalida;
    public static volatile SingularAttribute<Transporte, Integer> idtransporte;
    public static volatile SingularAttribute<Transporte, Date> tiempollegada;
    public static volatile SingularAttribute<Transporte, Date> fechallegada;
    public static volatile SingularAttribute<Transporte, String> temperatura;
    public static volatile SingularAttribute<Transporte, Cosecha> idcosecha;

}