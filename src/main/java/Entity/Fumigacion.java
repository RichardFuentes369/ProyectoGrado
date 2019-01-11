/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "fumigacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fumigacion.findAll", query = "SELECT f FROM Fumigacion f")
    , @NamedQuery(name = "Fumigacion.findByIdfumigacion", query = "SELECT f FROM Fumigacion f WHERE f.idfumigacion = :idfumigacion")
    , @NamedQuery(name = "Fumigacion.findByFechafumigacion", query = "SELECT f FROM Fumigacion f WHERE f.fechafumigacion = :fechafumigacion")
    , @NamedQuery(name = "Fumigacion.findByNombreproducto", query = "SELECT f FROM Fumigacion f WHERE f.nombreproducto = :nombreproducto")
    , @NamedQuery(name = "Fumigacion.findByHorafumigacion", query = "SELECT f FROM Fumigacion f WHERE f.horafumigacion = :horafumigacion")})
public class Fumigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfumigacion")
    private Integer idfumigacion;
    @Column(name = "fechafumigacion")
    @Temporal(TemporalType.DATE)
    private Date fechafumigacion;
    @Size(max = 200)
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Column(name = "horafumigacion")
    @Temporal(TemporalType.TIME)
    private Date horafumigacion;
    @JoinColumn(name = "idcosecha", referencedColumnName = "idcosecha")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cosecha idcosecha;

    public Fumigacion() {
    }

    public Fumigacion(Integer idfumigacion) {
        this.idfumigacion = idfumigacion;
    }

    public Integer getIdfumigacion() {
        return idfumigacion;
    }

    public void setIdfumigacion(Integer idfumigacion) {
        this.idfumigacion = idfumigacion;
    }

    public Date getFechafumigacion() {
        return fechafumigacion;
    }

    public void setFechafumigacion(Date fechafumigacion) {
        this.fechafumigacion = fechafumigacion;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Date getHorafumigacion() {
        return horafumigacion;
    }

    public void setHorafumigacion(Date horafumigacion) {
        this.horafumigacion = horafumigacion;
    }

    public Cosecha getIdcosecha() {
        return idcosecha;
    }

    public void setIdcosecha(Cosecha idcosecha) {
        this.idcosecha = idcosecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfumigacion != null ? idfumigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fumigacion)) {
            return false;
        }
        Fumigacion other = (Fumigacion) object;
        if ((this.idfumigacion == null && other.idfumigacion != null) || (this.idfumigacion != null && !this.idfumigacion.equals(other.idfumigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Fumigacion[ idfumigacion=" + idfumigacion + " ]";
    }
    
}
