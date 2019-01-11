/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t")
    , @NamedQuery(name = "Tipousuario.findByIdtu", query = "SELECT t FROM Tipousuario t WHERE t.idtu = :idtu")
    , @NamedQuery(name = "Tipousuario.findByNombretu", query = "SELECT t FROM Tipousuario t WHERE t.nombretu = :nombretu")
    , @NamedQuery(name = "Tipousuario.findByCaracteristicas", query = "SELECT t FROM Tipousuario t WHERE t.caracteristicas = :caracteristicas")})
public class Tipousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtu")
    private BigDecimal idtu;
    @Size(max = 20)
    @Column(name = "nombretu")
    private String nombretu;
    @Size(max = 50)
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @OneToMany(mappedBy = "idtu", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Tipousuario() {
    }

    public Tipousuario(BigDecimal idtu) {
        this.idtu = idtu;
    }

    public BigDecimal getIdtu() {
        return idtu;
    }

    public void setIdtu(BigDecimal idtu) {
        this.idtu = idtu;
    }

    public String getNombretu() {
        return nombretu;
    }

    public void setNombretu(String nombretu) {
        this.nombretu = nombretu;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtu != null ? idtu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.idtu == null && other.idtu != null) || (this.idtu != null && !this.idtu.equals(other.idtu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tipousuario[ idtu=" + idtu + " ]";
    }
    
}
