package Entity;

import Entity.Email;
import Entity.Finca;
import Entity.Tipousuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-15T03:21:35")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, Finca> fincaList;
    public static volatile SingularAttribute<Usuario, String> passwordlogin;
    public static volatile ListAttribute<Usuario, Email> emailList;
    public static volatile SingularAttribute<Usuario, String> ccUsuario;
    public static volatile SingularAttribute<Usuario, String> nombresusuario;
    public static volatile SingularAttribute<Usuario, String> telefonousuario;
    public static volatile SingularAttribute<Usuario, String> apellidosusuario;
    public static volatile SingularAttribute<Usuario, String> correoelectronico;
    public static volatile SingularAttribute<Usuario, String> usernamelogin;
    public static volatile SingularAttribute<Usuario, Tipousuario> idtu;
    public static volatile SingularAttribute<Usuario, String> celularusuario;

}