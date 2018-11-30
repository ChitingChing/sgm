package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Puerperio {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private LocalDate fechaPostParto;
    private String horaPostParto;
    private BigDecimal temperatura;
    private String pulso;
    private String presionArterial;
    private String uterina;
    private Character estado;
    private String caracteristicasLoquios;
    @ManyToOne
    @JoinColumn(name = "fichaprenatalid", referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal;

    public Puerperio() {
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

    public LocalDate getFechaPostParto() {
        return fechaPostParto;
    }

    public void setFechaPostParto(LocalDate fechaPostParto) {
        this.fechaPostParto = fechaPostParto;
    }

    public String getHoraPostParto() {
        return horaPostParto;
    }

    public void setHoraPostParto(String horaPostParto) {
        this.horaPostParto = horaPostParto;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public String getUterina() {
        return uterina;
    }

    public void setUterina(String uterina) {
        this.uterina = uterina;
    }

    public String getCaracteristicasLoquios() {
        return caracteristicasLoquios;
    }

    public void setCaracteristicasLoquios(String caracteristicasLoquios) {
        this.caracteristicasLoquios = caracteristicasLoquios;
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
        Puerperio puerperio = (Puerperio) o;
        return Objects.equals(id, puerperio.id) &&
                Objects.equals(fechaPostParto, puerperio.fechaPostParto) &&
                Objects.equals(horaPostParto, puerperio.horaPostParto) &&
                Objects.equals(temperatura, puerperio.temperatura) &&
                Objects.equals(pulso, puerperio.pulso) &&
                Objects.equals(presionArterial, puerperio.presionArterial) &&
                Objects.equals(uterina, puerperio.uterina) &&
                Objects.equals(caracteristicasLoquios, puerperio.caracteristicasLoquios) &&
                Objects.equals(fichaPrenatal, puerperio.fichaPrenatal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaPostParto, horaPostParto, temperatura, pulso, presionArterial, uterina, caracteristicasLoquios, fichaPrenatal);
    }
}
