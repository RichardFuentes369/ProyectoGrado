/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "cosecha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cosecha.findAll", query = "SELECT c FROM Cosecha c")
    , @NamedQuery(name = "Cosecha.findByIdcosecha", query = "SELECT c FROM Cosecha c WHERE c.idcosecha = :idcosecha")
    , @NamedQuery(name = "Cosecha.findByFechasembrado", query = "SELECT c FROM Cosecha c WHERE c.fechasembrado = :fechasembrado")
    , @NamedQuery(name = "Cosecha.findByFecharecoleccion", query = "SELECT c FROM Cosecha c WHERE c.fecharecoleccion = :fecharecoleccion")
    , @NamedQuery(name = "Cosecha.findByHorasembrado", query = "SELECT c FROM Cosecha c WHERE c.horasembrado = :horasembrado")
    , @NamedQuery(name = "Cosecha.findByHorarecoleccion", query = "SELECT c FROM Cosecha c WHERE c.horarecoleccion = :horarecoleccion")
    , @NamedQuery(name = "Cosecha.findByCantidad", query = "SELECT c FROM Cosecha c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "Cosecha.findByEstado", query = "SELECT c FROM Cosecha c WHERE c.estado = :estado")})
public class Cosecha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcosecha")
    private Integer idcosecha;
    @Column(name = "fechasembrado")
    @Temporal(TemporalType.DATE)
    private Date fechasembrado;
    @Column(name = "fecharecoleccion")
    @Temporal(TemporalType.DATE)
    private Date fecharecoleccion;
    @Column(name = "horasembrado")
    @Temporal(TemporalType.TIME)
    private Date horasembrado;
    @Column(name = "horarecoleccion")
    @Temporal(TemporalType.TIME)
    private Date horarecoleccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idcosecha", fetch = FetchType.LAZY)
    private List<Transporte> transporteList;
    @JoinColumn(name = "nitfinca", referencedColumnName = "nitfinca")
    @ManyToOne(fetch = FetchType.LAZY)
    private Finca nitfinca;
    @OneToMany(mappedBy = "idcosecha", fetch = FetchType.LAZY)
    private List<Fumigacion> fumigacionList;

    public Cosecha() {
    }

    public Cosecha(Integer idcosecha) {
        this.idcosecha = idcosecha;
    }

    public Integer getIdcosecha() {
        return idcosecha;
    }

    public void setIdcosecha(Integer idcosecha) {
        this.idcosecha = idcosecha;
    }

    public Date getFechasembrado() {
        return fechasembrado;
    }

    public void setFechasembrado(Date fechasembrado) {
        this.fechasembrado = fechasembrado;
    }

    public Date getFecharecoleccion() {
        return fecharecoleccion;
    }

    public void setFecharecoleccion(Date fecharecoleccion) {
        this.fecharecoleccion = fecharecoleccion;
    }

    public Date getHorasembrado() {
        return horasembrado;
    }

    public void setHorasembrado(Date horasembrado) {
        this.horasembrado = horasembrado;
    }

    public Date getHorarecoleccion() {
        return horarecoleccion;
    }

    public void setHorarecoleccion(Date horarecoleccion) {
        this.horarecoleccion = horarecoleccion;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Transporte> getTransporteList() {
        return transporteList;
    }

    public void setTransporteList(List<Transporte> transporteList) {
        this.transporteList = transporteList;
    }

    public Finca getNitfinca() {
        return nitfinca;
    }

    public void setNitfinca(Finca nitfinca) {
        this.nitfinca = nitfinca;
    }

    @XmlTransient
    public List<Fumigacion> getFumigacionList() {
        return fumigacionList;
    }

    public void setFumigacionList(List<Fumigacion> fumigacionList) {
        this.fumigacionList = fumigacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcosecha != null ? idcosecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cosecha)) {
            return false;
        }
        Cosecha other = (Cosecha) object;
        if ((this.idcosecha == null && other.idcosecha != null) || (this.idcosecha != null && !this.idcosecha.equals(other.idcosecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Cosecha[ idcosecha=" + idcosecha + " ]";
    }
    
}
