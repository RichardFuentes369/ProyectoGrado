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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
    , @NamedQuery(name = "Email.findByCcUserdestino", query = "SELECT e FROM Email e WHERE e.ccUserdestino = :ccUserdestino")
    , @NamedQuery(name = "Email.findByMensaje", query = "SELECT e FROM Email e WHERE e.mensaje = :mensaje")
    , @NamedQuery(name = "Email.findByIdemail", query = "SELECT e FROM Email e WHERE e.idemail = :idemail")
    , @NamedQuery(name = "Email.findByFecha", query = "SELECT e FROM Email e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Email.findByHora", query = "SELECT e FROM Email e WHERE e.hora = :hora")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "cc_userdestino")
    private String ccUserdestino;
    @Size(max = 900)
    @Column(name = "mensaje")
    private String mensaje;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemail")
    private Integer idemail;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "cc_userdestinatario", referencedColumnName = "cc_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario ccUserdestinatario;

    public Email() {
    }

    public Email(Integer idemail) {
        this.idemail = idemail;
    }

    public Email(Integer idemail, String ccUserdestino) {
        this.idemail = idemail;
        this.ccUserdestino = ccUserdestino;
    }

    public String getCcUserdestino() {
        return ccUserdestino;
    }

    public void setCcUserdestino(String ccUserdestino) {
        this.ccUserdestino = ccUserdestino;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIdemail() {
        return idemail;
    }

    public void setIdemail(Integer idemail) {
        this.idemail = idemail;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Usuario getCcUserdestinatario() {
        return ccUserdestinatario;
    }

    public void setCcUserdestinatario(Usuario ccUserdestinatario) {
        this.ccUserdestinatario = ccUserdestinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemail != null ? idemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.idemail == null && other.idemail != null) || (this.idemail != null && !this.idemail.equals(other.idemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Email[ idemail=" + idemail + " ]";
    }
    
}
