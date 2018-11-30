package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TrabajoParto {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private LocalDateTime fechaHora;
    private String tensionArterial;
    private Integer contraccionesFrecuencia;
    private Integer duracionContraccion;
    private String altura;
    private String posicion;
    private String fcf;
    private String fcmat;
    private String dilatacion;
    private String meconio;
    private String nombreExaminador;
    private Character estado;
    @ManyToOne
    @JoinColumn(name = "fichaprenatalid", referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal;

    public TrabajoParto() {
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTensionArterial() {
        return tensionArterial;
    }

    public void setTensionArterial(String tensionArterial) {
        this.tensionArterial = tensionArterial;
    }

    public Integer getContraccionesFrecuencia() {
        return contraccionesFrecuencia;
    }

    public void setContraccionesFrecuencia(Integer contraccionesFrecuencia) {
        this.contraccionesFrecuencia = contraccionesFrecuencia;
    }

    public Integer getDuracionContraccion() {
        return duracionContraccion;
    }

    public void setDuracionContraccion(Integer duracionContraccion) {
        this.duracionContraccion = duracionContraccion;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getFcf() {
        return fcf;
    }

    public void setFcf(String fcf) {
        this.fcf = fcf;
    }

    public String getFcmat() {
        return fcmat;
    }

    public void setFcmat(String fcmat) {
        this.fcmat = fcmat;
    }

    public String getDilatacion() {
        return dilatacion;
    }

    public void setDilatacion(String dilatacion) {
        this.dilatacion = dilatacion;
    }

    public String getMeconio() {
        return meconio;
    }

    public void setMeconio(String meconio) {
        this.meconio = meconio;
    }

    public String getNombreExaminador() {
        return nombreExaminador;
    }

    public void setNombreExaminador(String nombreExaminador) {
        this.nombreExaminador = nombreExaminador;
    }

    public FichaPrenatal getFichaPrenatal() {
        return fichaPrenatal;
    }

    public void setFichaPrenatal(FichaPrenatal fichaPrenatal) {
        this.fichaPrenatal = fichaPrenatal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrabajoParto that = (TrabajoParto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fechaHora, that.fechaHora) &&
                Objects.equals(tensionArterial, that.tensionArterial) &&
                Objects.equals(contraccionesFrecuencia, that.contraccionesFrecuencia) &&
                Objects.equals(duracionContraccion, that.duracionContraccion) &&
                Objects.equals(altura, that.altura) &&
                Objects.equals(posicion, that.posicion) &&
                Objects.equals(fcf, that.fcf) &&
                Objects.equals(fcmat, that.fcmat) &&
                Objects.equals(dilatacion, that.dilatacion) &&
                Objects.equals(meconio, that.meconio) &&
                Objects.equals(nombreExaminador, that.nombreExaminador) &&
                Objects.equals(fichaPrenatal, that.fichaPrenatal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaHora, tensionArterial, contraccionesFrecuencia, duracionContraccion, altura, posicion, fcf, fcmat, dilatacion, meconio, nombreExaminador, fichaPrenatal);
    }
}
