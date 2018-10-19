package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Canton {
    private String id;
    private String descripcion;
    private String estado;
    private Provincia provinciaByIdprovincia;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Canton canton = (Canton) o;
        return Objects.equals(id, canton.id) &&
                Objects.equals(descripcion, canton.descripcion) &&
                Objects.equals(estado, canton.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, estado);
    }

    @ManyToOne
    @JoinColumn(name = "idprovincia", referencedColumnName = "id")
    public Provincia getProvinciaByIdprovincia() {
        return provinciaByIdprovincia;
    }

    public void setProvinciaByIdprovincia(Provincia provinciaByIdprovincia) {
        this.provinciaByIdprovincia = provinciaByIdprovincia;
    }
}
