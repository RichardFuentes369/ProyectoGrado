package Entity;

import Entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Email.class)
public class Email_ { 

    public static volatile SingularAttribute<Email, Date> fecha;
    public static volatile SingularAttribute<Email, Integer> idemail;
    public static volatile SingularAttribute<Email, Date> hora;
    public static volatile SingularAttribute<Email, String> ccUserdestino;
    public static volatile SingularAttribute<Email, String> mensaje;
    public static volatile SingularAttribute<Email, Usuario> ccUserdestinatario;

}