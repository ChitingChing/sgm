package entities;

//import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ArchivoFichaPrenatal {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String rutaarchivo;
    private String nombrearchivo;
    private String extensionarchivo;
    private String descripcion;
    private String rutaarchivoorigen;
    private Boolean copiado;
    private Character estado;
    @ManyToOne
    @JoinColumn(name = "fichaprenatalid", referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchivoFichaPrenatal that = (ArchivoFichaPrenatal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rutaarchivo, that.rutaarchivo) &&
                Objects.equals(nombrearchivo, that.nombrearchivo) &&
                Objects.equals(extensionarchivo, that.extensionarchivo) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(rutaarchivoorigen, that.rutaarchivoorigen) &&
                Objects.equals(copiado, that.copiado) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(fichaPrenatal, that.fichaPrenatal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rutaarchivo, nombrearchivo, extensionarchivo, descripcion, rutaarchivoorigen, copiado, estado, fichaPrenatal);
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRutaarchivo() {
        return rutaarchivo;
    }

    public void setRutaarchivo(String rutaarchivo) {
        this.rutaarchivo = rutaarchivo;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }

    public String getExtensionarchivo() {
        return extensionarchivo;
    }

    public void setExtensionarchivo(String extensionarchivo) {
        this.extensionarchivo = extensionarchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaarchivoorigen() {
        return rutaarchivoorigen;
    }

    public void setRutaarchivoorigen(String rutaarchivoorigen) {
        this.rutaarchivoorigen = rutaarchivoorigen;
    }

    public FichaPrenatal getFichaPrenatal() {
        return fichaPrenatal;
    }

    public void setFichaPrenatal(FichaPrenatal fichaPrenatal) {
        this.fichaPrenatal = fichaPrenatal;
    }

    public Boolean getCopiado() {
        return copiado;
    }

    public void setCopiado(Boolean copiado) {
        this.copiado = copiado;
    }

}