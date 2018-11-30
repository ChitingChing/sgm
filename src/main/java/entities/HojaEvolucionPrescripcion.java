package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class HojaEvolucionPrescripcion {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private LocalDate fecha;
    private String hora;
    private String evolucion;
    private String prescripcion;
    private String medicamentos;
    private Character estado;
    @ManyToOne
    @JoinColumn(name = "fichaprenatalid",referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal;

    public HojaEvolucionPrescripcion() {
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public String getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(String prescripcion) {
        this.prescripcion = prescripcion;
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
        HojaEvolucionPrescripcion that = (HojaEvolucionPrescripcion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(hora, that.hora) &&
                Objects.equals(evolucion, that.evolucion) &&
                Objects.equals(prescripcion, that.prescripcion) &&
                Objects.equals(medicamentos, that.medicamentos) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(fichaPrenatal, that.fichaPrenatal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, hora, evolucion, prescripcion, medicamentos, estado, fichaPrenatal);
    }
}
