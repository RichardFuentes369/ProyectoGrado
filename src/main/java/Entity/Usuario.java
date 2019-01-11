/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCcUsuario", query = "SELECT u FROM Usuario u WHERE u.ccUsuario = :ccUsuario")
    , @NamedQuery(name = "Usuario.findByNombresusuario", query = "SELECT u FROM Usuario u WHERE u.nombresusuario = :nombresusuario")
    , @NamedQuery(name = "Usuario.findByApellidosusuario", query = "SELECT u FROM Usuario u WHERE u.apellidosusuario = :apellidosusuario")
    , @NamedQuery(name = "Usuario.findByUsernamelogin", query = "SELECT u FROM Usuario u WHERE u.usernamelogin = :usernamelogin")
    , @NamedQuery(name = "Usuario.findByPasswordlogin", query = "SELECT u FROM Usuario u WHERE u.passwordlogin = :passwordlogin")
    , @NamedQuery(name = "Usuario.findByTelefonousuario", query = "SELECT u FROM Usuario u WHERE u.telefonousuario = :telefonousuario")
    , @NamedQuery(name = "Usuario.findByCelularusuario", query = "SELECT u FROM Usuario u WHERE u.celularusuario = :celularusuario")
    , @NamedQuery(name = "Usuario.findByCorreoelectronico", query = "SELECT u FROM Usuario u WHERE u.correoelectronico = :correoelectronico")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "cc_usuario")
    private String ccUsuario;
    @Size(max = 50)
    @Column(name = "nombresusuario")
    private String nombresusuario;
    @Size(max = 50)
    @Column(name = "apellidosusuario")
    private String apellidosusuario;
    @Size(max = 20)
    @Column(name = "usernamelogin")
    private String usernamelogin;
    @Size(max = 30)
    @Column(name = "passwordlogin")
    private String passwordlogin;
    @Size(max = 20)
    @Column(name = "telefonousuario")
    private String telefonousuario;
    @Size(max = 20)
    @Column(name = "celularusuario")
    private String celularusuario;
    @Size(max = 80)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccUser", fetch = FetchType.LAZY)
    private List<Finca> fincaList;
    @JoinColumn(name = "idtu", referencedColumnName = "idtu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipousuario idtu;
    @OneToMany(mappedBy = "ccUserdestinatario", fetch = FetchType.LAZY)
    private List<Email> emailList;

    public Usuario() {
    }

    public Usuario(String ccUsuario) {
        this.ccUsuario = ccUsuario;
    }

    public String getCcUsuario() {
        return ccUsuario;
    }

    public void setCcUsuario(String ccUsuario) {
        this.ccUsuario = ccUsuario;
    }

    public String getNombresusuario() {
        return nombresusuario;
    }

    public void setNombresusuario(String nombresusuario) {
        this.nombresusuario = nombresusuario;
    }

    public String getApellidosusuario() {
        return apellidosusuario;
    }

    public void setApellidosusuario(String apellidosusuario) {
        this.apellidosusuario = apellidosusuario;
    }

    public String getUsernamelogin() {
        return usernamelogin;
    }

    public void setUsernamelogin(String usernamelogin) {
        this.usernamelogin = usernamelogin;
    }

    public String getPasswordlogin() {
        return passwordlogin;
    }

    public void setPasswordlogin(String passwordlogin) {
        this.passwordlogin = passwordlogin;
    }

    public String getTelefonousuario() {
        return telefonousuario;
    }

    public void setTelefonousuario(String telefonousuario) {
        this.telefonousuario = telefonousuario;
    }

    public String getCelularusuario() {
        return celularusuario;
    }

    public void setCelularusuario(String celularusuario) {
        this.celularusuario = celularusuario;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    @XmlTransient
    public List<Finca> getFincaList() {
        return fincaList;
    }

    public void setFincaList(List<Finca> fincaList) {
        this.fincaList = fincaList;
    }

    public Tipousuario getIdtu() {
        return idtu;
    }

    public void setIdtu(Tipousuario idtu) {
        this.idtu = idtu;
    }

    @XmlTransient
    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccUsuario != null ? ccUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.ccUsuario == null && other.ccUsuario != null) || (this.ccUsuario != null && !this.ccUsuario.equals(other.ccUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Usuario[ ccUsuario=" + ccUsuario + " ]";
    }
    
}
