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
@Table(name = "transporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporte.findAll", query = "SELECT t FROM Transporte t")
    , @NamedQuery(name = "Transporte.findByIdtransporte", query = "SELECT t FROM Transporte t WHERE t.idtransporte = :idtransporte")
    , @NamedQuery(name = "Transporte.findByFechasalida", query = "SELECT t FROM Transporte t WHERE t.fechasalida = :fechasalida")
    , @NamedQuery(name = "Transporte.findByTemperatura", query = "SELECT t FROM Transporte t WHERE t.temperatura = :temperatura")
    , @NamedQuery(name = "Transporte.findByDescripcion", query = "SELECT t FROM Transporte t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Transporte.findByFechallegada", query = "SELECT t FROM Transporte t WHERE t.fechallegada = :fechallegada")
    , @NamedQuery(name = "Transporte.findByTiempollegada", query = "SELECT t FROM Transporte t WHERE t.tiempollegada = :tiempollegada")
    , @NamedQuery(name = "Transporte.findByTiemporecogida", query = "SELECT t FROM Transporte t WHERE t.tiemporecogida = :tiemporecogida")
    , @NamedQuery(name = "Transporte.findByTiemposalida", query = "SELECT t FROM Transporte t WHERE t.tiemposalida = :tiemposalida")})
public class Transporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtransporte")
    private Integer idtransporte;
    @Column(name = "fechasalida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Size(max = 3)
    @Column(name = "temperatura")
    private String temperatura;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechallegada")
    @Temporal(TemporalType.DATE)
    private Date fechallegada;
    @Column(name = "tiempollegada")
    @Temporal(TemporalType.TIME)
    private Date tiempollegada;
    @Column(name = "tiemporecogida")
    @Temporal(TemporalType.TIME)
    private Date tiemporecogida;
    @Column(name = "tiemposalida")
    @Temporal(TemporalType.TIME)
    private Date tiemposalida;
    @JoinColumn(name = "idcosecha", referencedColumnName = "idcosecha")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cosecha idcosecha;

    public Transporte() {
    }

    public Transporte(Integer idtransporte) {
        this.idtransporte = idtransporte;
    }

    public Integer getIdtransporte() {
        return idtransporte;
    }

    public void setIdtransporte(Integer idtransporte) {
        this.idtransporte = idtransporte;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(Date fechallegada) {
        this.fechallegada = fechallegada;
    }

    public Date getTiempollegada() {
        return tiempollegada;
    }

    public void setTiempollegada(Date tiempollegada) {
        this.tiempollegada = tiempollegada;
    }

    public Date getTiemporecogida() {
        return tiemporecogida;
    }

    public void setTiemporecogida(Date tiemporecogida) {
        this.tiemporecogida = tiemporecogida;
    }

    public Date getTiemposalida() {
        return tiemposalida;
    }

    public void setTiemposalida(Date tiemposalida) {
        this.tiemposalida = tiemposalida;
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
        hash += (idtransporte != null ? idtransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporte)) {
            return false;
        }
        Transporte other = (Transporte) object;
        if ((this.idtransporte == null && other.idtransporte != null) || (this.idtransporte != null && !this.idtransporte.equals(other.idtransporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Transporte[ idtransporte=" + idtransporte + " ]";
    }
    
}
