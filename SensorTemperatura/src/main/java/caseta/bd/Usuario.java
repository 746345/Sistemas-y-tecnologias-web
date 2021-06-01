package caseta.bd;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id private String usuario;
    private String pwd;
    private String nombre;
    private String apellido;
    
    @OneToMany (mappedBy = "usuario",
                cascade = CascadeType.ALL, 
                orphanRemoval = true)
    private List<RegistroAcceso> registroAccesos;
    
    @OneToMany (mappedBy = "usuario",
                cascade = CascadeType.ALL, 
                orphanRemoval = true)
    private List<RegistroEncendido> registroEncendidos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido1(String apellido1) {
        this.apellido = apellido1;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<RegistroAcceso> getRegistroAccesos() {
        return registroAccesos;
    }

    public void setRegistroAccesos(List<RegistroAcceso> registroAccesos) {
        this.registroAccesos = registroAccesos;
    }

    public List<RegistroEncendido> getRegistroEncendidos() {
        return registroEncendidos;
    }

    public void setRegistroEncendidos(List<RegistroEncendido> registroEncendidos) {
        this.registroEncendidos = registroEncendidos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario;
    }
    
}
