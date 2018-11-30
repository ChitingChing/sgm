package entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
public class RecordOperatorio {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String establecimiento ;
    private String localidad ;
    private String servicio ;
    private String sala ;
    private String cama ;
    private String preoperatorio ;
    private String postoperatorio ;
    private String proyectada  ;
    private Boolean efectiva ;
    private Boolean emergencia ;
    private Bool paleativa ;
    private String realizada ;
    private String cirujano ;
    private String primerAyudante ;
    private String segundoAyudante ;
    private String tercerAyudante ;
    private String instrumentista ;
    private String circulante ;
    private String anestesia ;
    private String ayudanteAnestesia ;
    private LocalDate fechaOperacion ;
    private String horaInicio ;
    private String horaTerminacion ;
    private String tipoAnestesia ;
    private String dieresis ;
    private String exposicion ;
    private String exploHallazgosQui ;
    private String procedimientoOperatorio ;
   @ManyToOne
   @JoinColumn(name = "fichaprenatalid",referencedColumnName = "id")
    private FichaPrenatal fichaPrenatal ;
   @OneToMany(mappedBy = "recordOperatorio",cascade = CascadeType.ALL)
    private Collection<HojaEvolucionPrescripcion> hojaEvolucionPrescripcionCollection;
}
