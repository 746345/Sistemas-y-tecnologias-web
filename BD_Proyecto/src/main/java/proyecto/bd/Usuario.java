/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.bd;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author inaki
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String usuario;
    private String pwd;
    
    @OneToMany (mappedBy = "usuario",
                cascade = CascadeType.ALL, 
                orphanRemoval = true)
    private List<RegistroAcceso> registroAccesos;
    private List<RegistroEncendido> registroEncendidos;

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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.bd.Usuario[ id=" + id + " ]";
    }
    
}
