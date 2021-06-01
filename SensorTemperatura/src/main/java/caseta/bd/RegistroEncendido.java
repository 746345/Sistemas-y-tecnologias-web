package caseta.bd;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Entity
public class RegistroEncendido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long fecha;
    private boolean estadoSonoffPrevio;
    private double temperatura;
    
    @ManyToOne
    private Usuario usuario;

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temp) {
        this.temperatura = temp;
    }

    public boolean getEstadoSonoffPrevio() {
        return estadoSonoffPrevio;
    }

    public void setEstadoSonoffPrevio(boolean estadoSonoffPrevio) {
        this.estadoSonoffPrevio = estadoSonoffPrevio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroEncendido)) {
            return false;
        }
        RegistroEncendido other = (RegistroEncendido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caseta.bd.RegistroEncendido[ id=" + id + " ]";
    }
    
}
