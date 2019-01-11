/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "finca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finca.findAll", query = "SELECT f FROM Finca f")
    , @NamedQuery(name = "Finca.findByIdfinca", query = "SELECT f FROM Finca f WHERE f.idfinca = :idfinca")
    , @NamedQuery(name = "Finca.findByNombrefinca", query = "SELECT f FROM Finca f WHERE f.nombrefinca = :nombrefinca")
    , @NamedQuery(name = "Finca.findByNitfinca", query = "SELECT f FROM Finca f WHERE f.nitfinca = :nitfinca")
    , @NamedQuery(name = "Finca.findByDepartamento", query = "SELECT f FROM Finca f WHERE f.departamento = :departamento")
    , @NamedQuery(name = "Finca.findByProvincia", query = "SELECT f FROM Finca f WHERE f.provincia = :provincia")
    , @NamedQuery(name = "Finca.findByVereda", query = "SELECT f FROM Finca f WHERE f.vereda = :vereda")
    , @NamedQuery(name = "Finca.findByDireccion", query = "SELECT f FROM Finca f WHERE f.direccion = :direccion")
    , @NamedQuery(name = "Finca.findByNombredueno", query = "SELECT f FROM Finca f WHERE f.nombredueno = :nombredueno")
    , @NamedQuery(name = "Finca.findByTelefono", query = "SELECT f FROM Finca f WHERE f.telefono = :telefono")
    , @NamedQuery(name = "Finca.findByMunicipio", query = "SELECT f FROM Finca f WHERE f.municipio = :municipio")})
public class Finca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idfinca")
    private int idfinca;
    @Size(max = 50)
    @Column(name = "nombrefinca")
    private String nombrefinca;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nitfinca")
    private String nitfinca;
    @Size(max = 15)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 15)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 15)
    @Column(name = "vereda")
    private String vereda;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "nombredueno")
    private String nombredueno;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 15)
    @Column(name = "municipio")
    private String municipio;
    @JoinColumn(name = "cc_user", referencedColumnName = "cc_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario ccUser;
    @OneToMany(mappedBy = "nitfinca", fetch = FetchType.LAZY)
    private List<Cosecha> cosechaList;

    public Finca() {
    }

    public Finca(String nitfinca) {
        this.nitfinca = nitfinca;
    }

    public Finca(String nitfinca, int idfinca) {
        this.nitfinca = nitfinca;
        this.idfinca = idfinca;
    }

    public int getIdfinca() {
        return idfinca;
    }

    public void setIdfinca(int idfinca) {
        this.idfinca = idfinca;
    }

    public String getNombrefinca() {
        return nombrefinca;
    }

    public void setNombrefinca(String nombrefinca) {
        this.nombrefinca = nombrefinca;
    }

    public String getNitfinca() {
        return nitfinca;
    }

    public void setNitfinca(String nitfinca) {
        this.nitfinca = nitfinca;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getVereda() {
        return vereda;
    }

    public void setVereda(String vereda) {
        this.vereda = vereda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombredueno() {
        return nombredueno;
    }

    public void setNombredueno(String nombredueno) {
        this.nombredueno = nombredueno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Usuario getCcUser() {
        return ccUser;
    }

    public void setCcUser(Usuario ccUser) {
        this.ccUser = ccUser;
    }

    @XmlTransient
    public List<Cosecha> getCosechaList() {
        return cosechaList;
    }

    public void setCosechaList(List<Cosecha> cosechaList) {
        this.cosechaList = cosechaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nitfinca != null ? nitfinca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finca)) {
            return false;
        }
        Finca other = (Finca) object;
        if ((this.nitfinca == null && other.nitfinca != null) || (this.nitfinca != null && !this.nitfinca.equals(other.nitfinca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Finca[ nitfinca=" + nitfinca + " ]";
    }
    
}
