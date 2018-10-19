package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Provincia {
    private String id;
    private String descripcion;
    private String estado;
    private Collection<Canton> cantonsById;

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
        Provincia provincia = (Provincia) o;
        return Objects.equals(id, provincia.id) &&
                Objects.equals(descripcion, provincia.descripcion) &&
                Objects.equals(estado, provincia.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, estado);
    }

    @OneToMany(mappedBy = "provinciaByIdprovincia")
    public Collection<Canton> getCantonsById() {
        return cantonsById;
    }

    public void setCantonsById(Collection<Canton> cantonsById) {
        this.cantonsById = cantonsById;
    }
}
