package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ConsultaEmbarazo {
    @Id
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;
    private LocalDateTime fechaConsulta;
    private Integer semanasAmenorreas;
    private BigDecimal peso;
    private String presionArterial;
    private Integer alturaUterina;
    private String presentacion;
    private String fcf;
    private String movFetal;
    private String edema;
    private String albuminuria;
    private String sangradoGenital;
    private String nombreExaminador;
    private Character estado;
    @ManyToOne
    @JoinColumn(name = "fichaprenatalid" ,referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal;

    public ConsultaEmbarazo() {
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

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public Integer getSemanasAmenorreas() {
        return semanasAmenorreas;
    }

    public void setSemanasAmenorreas(Integer semanasAmenorreas) {
        this.semanasAmenorreas = semanasAmenorreas;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public Integer getAlturaUterina() {
        return alturaUterina;
    }

    public void setAlturaUterina(Integer alturaUterina) {
        this.alturaUterina = alturaUterina;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getFcf() {
        return fcf;
    }

    public void setFcf(String fcf) {
        this.fcf = fcf;
    }

    public String getMovFetal() {
        return movFetal;
    }

    public void setMovFetal(String movFetal) {
        this.movFetal = movFetal;
    }

    public String getEdema() {
        return edema;
    }

    public void setEdema(String edema) {
        this.edema = edema;
    }

    public String getAlbuminuria() {
        return albuminuria;
    }

    public void setAlbuminuria(String albuminuria) {
        this.albuminuria = albuminuria;
    }

    public String getSangradoGenital() {
        return sangradoGenital;
    }

    public void setSangradoGenital(String sangradoGenital) {
        this.sangradoGenital = sangradoGenital;
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
        ConsultaEmbarazo that = (ConsultaEmbarazo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fechaConsulta, that.fechaConsulta) &&
                Objects.equals(semanasAmenorreas, that.semanasAmenorreas) &&
                Objects.equals(peso, that.peso) &&
                Objects.equals(presionArterial, that.presionArterial) &&
                Objects.equals(alturaUterina, that.alturaUterina) &&
                Objects.equals(presentacion, that.presentacion) &&
                Objects.equals(fcf, that.fcf) &&
                Objects.equals(movFetal, that.movFetal) &&
                Objects.equals(edema, that.edema) &&
                Objects.equals(albuminuria, that.albuminuria) &&
                Objects.equals(sangradoGenital, that.sangradoGenital) &&
                Objects.equals(nombreExaminador, that.nombreExaminador) &&
                Objects.equals(fichaPrenatal, that.fichaPrenatal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaConsulta, semanasAmenorreas, peso, presionArterial, alturaUterina, presentacion, fcf, movFetal, edema, albuminuria, sangradoGenital, nombreExaminador, fichaPrenatal);
    }
}
