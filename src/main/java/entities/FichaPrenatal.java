package entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
public class FichaPrenatal {
    @Id
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;
    private LocalDateTime fechaRegistro;
    private Character estado;
    //Antecedentes
    private String anteFamiliares;
    private String antePersonales;
    private String anteOtros;
    private Boolean anteNingunoOmasEmbarazos;
    private Boolean anteRnMenor2500;
    private Boolean anteGemelares;
    private Integer anteGestas;
    private Integer antePartos;
    private Integer anteAbortos;
    private Integer anteVaginales;
    private Integer anteCesareas;
    private Integer anteNacidosVivos;
    private Integer anteNacidosMuertos;
    private Integer anteViven;
    private Integer anteMuertos1Semana;
    private Integer anteMuertosmas1Semana;
    private LocalDate anteFechaFinAnteriorEmbarazo;
    private BigDecimal anteRnMayorPeso;
    //Embarazo
    private BigDecimal embPesoAnterior;
    private Integer embTalla;
    private LocalDate embFum;
    private LocalDate embFfp;
    private Boolean embDudas;
    private Boolean embAntitetanicaPrevia;
    private String embAntitetanicaMesGestaActual1;
    private String embAntitetanicaMesGestaActual2;
    private String embGrupoSanguineo;
    private String embRh;
    private Boolean embSensibilidad;
    private Boolean embGrupoSanguineoSensibilidad;
    private Boolean embFuma;
    private Integer embCigarrilosPorDia;
    private Boolean embHospitalizacion;
    private Boolean embTraslado;
    private String embLugarTraslado;
    private Boolean embExClinico;
    private Boolean embExMamas;
    private Boolean embExOdontologico;
    private Boolean embExPelvis;
    private Boolean embExPapanicolao;
    private Boolean embExColposcopia;
    private Boolean embExCervix;
    private String embVdrl;
    private LocalDate embFechaVdrl;
    private Integer embGlucosa;
    private LocalDate embFechaGlucosa;
    private Integer embHb1;
    private LocalDate embFechaHb1;
    private Integer embHb2;
    private LocalDate embFechaHb2;
    //CONSULTA EMBARAZO
    @OneToMany(mappedBy = "fichaPrenatal" ,cascade = CascadeType.ALL)
    private Collection<ConsultaEmbarazo> consultaEmbarazoCollection;
    //Parto
    private Boolean partEsAborto;
    private String partOrigenParto;
    private Integer partConsultaPrenatal;
    private Boolean partEnHospital;
    private Boolean partCarnet;
    private LocalDateTime partFechaIngresoParto;
    private BigDecimal partTemperaturaParto;
    private Integer partEdadGestacion;
    private Boolean partMenor37;
    private Boolean partMayor41;
    private String partPresentacion;
    private Boolean partTamanoFetalAdecuado;
    private LocalDateTime partFechaTerminacion;
    private String partInicio;
    private String partMembranas;
    private LocalDateTime partFechaRuptura;
    private String partPatologias;
    private String partTerminacion;
    private LocalDateTime partFechaNacimiento;
    private String partIndicacionPrincipal;
    private String partMuerteIntegerraUt;
    private Boolean partEpisiotomia;
    private Boolean partDesgarro;
    private Boolean partAlumbEspont;
    private Boolean partPlacentaComp;
    private String partMedicacionParto;
    private String partNivelAtencion;
    private String partAtendioPartoNombre;
    private String partAtendioPartoCargo;
    private String partAtendioNeonatoNombre;
    private String partAtendioNeonatoCargo;
    private String partNombreRn;
    private String partHcRn;
    private Boolean partMultiple;
    private Integer partOrden;
    private String partPatologiaspp;
    //trabajo parto
    @OneToMany(mappedBy = "fichaPrenatal",cascade = CascadeType.ALL)
    private Collection<TrabajoParto> trabajoPartoCollection;
    //Recien Nacido
    private String rnSexo;
    private Integer rnPesoNacer;
    private Boolean rnMenor2500g;
    private Integer rnTalla;
    private Integer rnPerCefalico;
    private Integer rnEdadPorExFisico;
    private Boolean rnMenor37;
    private String rnPesoEdadGestacional;
    private Integer rnApgar1min;
    private Integer rnApgar5min;
    private String rnReaminRespir;
    private String rnVdrl;
    private String rnExamenFisico;
    private String rnPatologias;
    private Boolean rnAlojConjunto;
    private Boolean rnHospitalizado;
    private Boolean rnBcg;
    private Boolean rnPvo;
    private String rnGrupoSanguineo;
    private String rnRhh;
    private String rnObservaciones;
    //Puerperio
    @OneToMany(mappedBy = "fichaPrenatal",cascade = CascadeType.ALL)
    private Collection<Puerperio> puerperioCollection;
    //Egreso
    private LocalDate egrFechaEgresoRn;
    private String egrEstadoRn;
    private String egrAlimento;
    private Integer egrPesoEgresoRn;
    private String egrResponsableEgresoRn;
    private LocalDate egrFechaEgresoMaterno;
    private String egrResponsableEgresoMaterno;
    private String egrAnticoncepcion;
    private String egrEstadoMaterno;
    //Record Operatorio

    private String recOpServicio ;
    private String recOpSala ;
    private String recOpCama ;
    private String recOpPreoperatorio ;
    private String recOpPostoperatorio ;
    private String recOpProyectada ;
    private String recOpTipoOperacion ;
    private String recOpRealizada ;
    private String recOpCirujano ;
    private String recOpPrimerAyudante ;
    private String recOpSegundoAyudante ;
    private String recOpTercerAyudante ;
    private String recOpInstrumentista ;
    private String recOpCirculante ;
    private String recOpAnestesia ;
    private String recOpAyudanteAnestesia ;
    private LocalDate recOpFechaOperacion ;
    private String recOpHoraInicio ;
    private String recOpHoraTerminacion ;
    private String recOpTipoAnestesia ;
    private String recOpDieresis ;
    private String recOpExposicion ;
    private String recOpExploHallazgosQui ;
    private String recOpProcedimientoOperatorio ;
    //Hoja Evolucion
    @OneToMany(mappedBy = "fichaPrenatal",cascade = CascadeType.ALL)
    private  Collection<HojaEvolucionPrescripcion> hojaEvolucionPrescripcionCollection;

/////
    @ManyToOne
    @JoinColumn(name = "pacienteid",referencedColumnName = "id")
    private Paciente paciente;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public FichaPrenatal() {
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public void setPartFechaIngresoParto(LocalDateTime partFechaIngresoParto) {
        this.partFechaIngresoParto = partFechaIngresoParto;
    }

    public LocalDateTime getPartFechaIngresoParto() {
        return partFechaIngresoParto;
    }

    public LocalDateTime getPartFechaTerminacion() {
        return partFechaTerminacion;
    }

    public void setPartFechaTerminacion(LocalDateTime partFechaTerminacion) {
        this.partFechaTerminacion = partFechaTerminacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getAnteFamiliares() {
        return anteFamiliares;
    }

    public void setAnteFamiliares(String anteFamiliares) {
        this.anteFamiliares = anteFamiliares;
    }

    public String getAntePersonales() {
        return antePersonales;
    }

    public void setAntePersonales(String antePersonales) {
        this.antePersonales = antePersonales;
    }

    public String getAnteOtros() {
        return anteOtros;
    }

    public void setAnteOtros(String anteOtros) {
        this.anteOtros = anteOtros;
    }

    public Boolean getAnteNingunoOmasEmbarazos() {
        return anteNingunoOmasEmbarazos;
    }

    public void setAnteNingunoOmasEmbarazos(Boolean anteNingunoOmasEmbarazos) {
        this.anteNingunoOmasEmbarazos = anteNingunoOmasEmbarazos;
    }

    public Boolean getAnteRnMenor2500() {
        return anteRnMenor2500;
    }

    public void setAnteRnMenor2500(Boolean anteRnMenor2500) {
        this.anteRnMenor2500 = anteRnMenor2500;
    }

    public Boolean getAnteGemelares() {
        return anteGemelares;
    }

    public void setAnteGemelares(Boolean anteGemelares) {
        this.anteGemelares = anteGemelares;
    }

    public Integer getAnteGestas() {
        return anteGestas;
    }

    public void setAnteGestas(Integer anteGestas) {
        this.anteGestas = anteGestas;
    }

    public Integer getAntePartos() {
        return antePartos;
    }

    public void setAntePartos(Integer antePartos) {
        this.antePartos = antePartos;
    }

    public Integer getAnteAbortos() {
        return anteAbortos;
    }

    public void setAnteAbortos(Integer anteAbortos) {
        this.anteAbortos = anteAbortos;
    }

    public Integer getAnteVaginales() {
        return anteVaginales;
    }

    public void setAnteVaginales(Integer anteVaginales) {
        this.anteVaginales = anteVaginales;
    }

    public Integer getAnteCesareas() {
        return anteCesareas;
    }

    public void setAnteCesareas(Integer anteCesareas) {
        this.anteCesareas = anteCesareas;
    }

    public Integer getAnteNacidosVivos() {
        return anteNacidosVivos;
    }

    public void setAnteNacidosVivos(Integer anteNacidosVivos) {
        this.anteNacidosVivos = anteNacidosVivos;
    }

    public Integer getAnteNacidosMuertos() {
        return anteNacidosMuertos;
    }

    public void setAnteNacidosMuertos(Integer anteNacidosMuertos) {
        this.anteNacidosMuertos = anteNacidosMuertos;
    }

    public Integer getAnteViven() {
        return anteViven;
    }

    public void setAnteViven(Integer anteViven) {
        this.anteViven = anteViven;
    }

    public Integer getAnteMuertos1Semana() {
        return anteMuertos1Semana;
    }

    public void setAnteMuertos1Semana(Integer anteMuertos1Semana) {
        this.anteMuertos1Semana = anteMuertos1Semana;
    }

    public Integer getAnteMuertosmas1Semana() {
        return anteMuertosmas1Semana;
    }

    public void setAnteMuertosmas1Semana(Integer anteMuertosmas1Semana) {
        this.anteMuertosmas1Semana = anteMuertosmas1Semana;
    }

    public LocalDate getAnteFechaFinAnteriorEmbarazo() {
        return anteFechaFinAnteriorEmbarazo;
    }

    public void setAnteFechaFinAnteriorEmbarazo(LocalDate anteFechaFinAnteriorEmbarazo) {
        this.anteFechaFinAnteriorEmbarazo = anteFechaFinAnteriorEmbarazo;
    }

    public BigDecimal getAnteRnMayorPeso() {
        return anteRnMayorPeso;
    }

    public void setAnteRnMayorPeso(BigDecimal anteRnMayorPeso) {
        this.anteRnMayorPeso = anteRnMayorPeso;
    }

    public BigDecimal getEmbPesoAnterior() {
        return embPesoAnterior;
    }

    public void setEmbPesoAnterior(BigDecimal embPesoAnterior) {
        this.embPesoAnterior = embPesoAnterior;
    }

    public Integer getEmbTalla() {
        return embTalla;
    }

    public void setEmbTalla(Integer embTalla) {
        this.embTalla = embTalla;
    }

    public LocalDate getEmbFum() {
        return embFum;
    }

    public void setEmbFum(LocalDate embFum) {
        this.embFum = embFum;
    }

    public LocalDate getEmbFfp() {
        return embFfp;
    }

    public void setEmbFfp(LocalDate embFfp) {
        this.embFfp = embFfp;
    }

    public Boolean getEmbDudas() {
        return embDudas;
    }

    public void setEmbDudas(Boolean embDudas) {
        this.embDudas = embDudas;
    }

    public Boolean getEmbAntitetanicaPrevia() {
        return embAntitetanicaPrevia;
    }

    public void setEmbAntitetanicaPrevia(Boolean embAntitetanicaPrevia) {
        this.embAntitetanicaPrevia = embAntitetanicaPrevia;
    }

    public String getEmbAntitetanicaMesGestaActual1() {
        return embAntitetanicaMesGestaActual1;
    }

    public void setEmbAntitetanicaMesGestaActual1(String embAntitetanicaMesGestaActual1) {
        this.embAntitetanicaMesGestaActual1 = embAntitetanicaMesGestaActual1;
    }

    public String getEmbAntitetanicaMesGestaActual2() {
        return embAntitetanicaMesGestaActual2;
    }

    public void setEmbAntitetanicaMesGestaActual2(String embAntitetanicaMesGestaActual2) {
        this.embAntitetanicaMesGestaActual2 = embAntitetanicaMesGestaActual2;
    }

    public String getEmbGrupoSanguineo() {
        return embGrupoSanguineo;
    }

    public void setEmbGrupoSanguineo(String embGrupoSanguineo) {
        this.embGrupoSanguineo = embGrupoSanguineo;
    }

    public String getEmbRh() {
        return embRh;
    }

    public void setEmbRh(String embRh) {
        this.embRh = embRh;
    }

    public Boolean getEmbSensibilidad() {
        return embSensibilidad;
    }

    public void setEmbSensibilidad(Boolean embSensibilidad) {
        this.embSensibilidad = embSensibilidad;
    }

    public Boolean getEmbGrupoSanguineoSensibilidad() {
        return embGrupoSanguineoSensibilidad;
    }

    public void setEmbGrupoSanguineoSensibilidad(Boolean embGrupoSanguineoSensibilidad) {
        this.embGrupoSanguineoSensibilidad = embGrupoSanguineoSensibilidad;
    }

    public Boolean getEmbFuma() {
        return embFuma;
    }

    public void setEmbFuma(Boolean embFuma) {
        this.embFuma = embFuma;
    }

    public Integer getEmbCigarrilosPorDia() {
        return embCigarrilosPorDia;
    }

    public void setEmbCigarrilosPorDia(Integer embCigarrilosPorDia) {
        this.embCigarrilosPorDia = embCigarrilosPorDia;
    }

    public Boolean getEmbHospitalizacion() {
        return embHospitalizacion;
    }

    public void setEmbHospitalizacion(Boolean embHospitalizacion) {
        this.embHospitalizacion = embHospitalizacion;
    }

    public Boolean getEmbTraslado() {
        return embTraslado;
    }

    public void setEmbTraslado(Boolean embTraslado) {
        this.embTraslado = embTraslado;
    }

    public String getEmbLugarTraslado() {
        return embLugarTraslado;
    }

    public void setEmbLugarTraslado(String embLugarTraslado) {
        this.embLugarTraslado = embLugarTraslado;
    }

    public Boolean getEmbExClinico() {
        return embExClinico;
    }

    public void setEmbExClinico(Boolean embExClinico) {
        this.embExClinico = embExClinico;
    }

    public Boolean getEmbExMamas() {
        return embExMamas;
    }

    public void setEmbExMamas(Boolean embExMamas) {
        this.embExMamas = embExMamas;
    }

    public Boolean getEmbExOdontologico() {
        return embExOdontologico;
    }

    public void setEmbExOdontologico(Boolean embExOdontologico) {
        this.embExOdontologico = embExOdontologico;
    }

    public Boolean getEmbExPelvis() {
        return embExPelvis;
    }

    public void setEmbExPelvis(Boolean embExPelvis) {
        this.embExPelvis = embExPelvis;
    }

    public Boolean getEmbExPapanicolao() {
        return embExPapanicolao;
    }

    public void setEmbExPapanicolao(Boolean embExPapanicolao) {
        this.embExPapanicolao = embExPapanicolao;
    }

    public Boolean getEmbExColposcopia() {
        return embExColposcopia;
    }

    public void setEmbExColposcopia(Boolean embExColposcopia) {
        this.embExColposcopia = embExColposcopia;
    }

    public Boolean getEmbExCervix() {
        return embExCervix;
    }

    public void setEmbExCervix(Boolean embExCervix) {
        this.embExCervix = embExCervix;
    }

    public String getEmbVdrl() {
        return embVdrl;
    }

    public void setEmbVdrl(String embVdrl) {
        this.embVdrl = embVdrl;
    }

    public LocalDate getEmbFechaVdrl() {
        return embFechaVdrl;
    }

    public void setEmbFechaVdrl(LocalDate embFechaVdrl) {
        this.embFechaVdrl = embFechaVdrl;
    }

    public Integer getEmbGlucosa() {
        return embGlucosa;
    }

    public void setEmbGlucosa(Integer embGlucosa) {
        this.embGlucosa = embGlucosa;
    }

    public LocalDate getEmbFechaGlucosa() {
        return embFechaGlucosa;
    }

    public void setEmbFechaGlucosa(LocalDate embFechaGlucosa) {
        this.embFechaGlucosa = embFechaGlucosa;
    }

    public Integer getEmbHb1() {
        return embHb1;
    }

    public void setEmbHb1(Integer embHb1) {
        this.embHb1 = embHb1;
    }

    public LocalDate getEmbFechaHb1() {
        return embFechaHb1;
    }

    public void setEmbFechaHb1(LocalDate embFechaHb1) {
        this.embFechaHb1 = embFechaHb1;
    }

    public Integer getEmbHb2() {
        return embHb2;
    }

    public void setEmbHb2(Integer embHb2) {
        this.embHb2 = embHb2;
    }

    public LocalDate getEmbFechaHb2() {
        return embFechaHb2;
    }

    public void setEmbFechaHb2(LocalDate embFechaHb2) {
        this.embFechaHb2 = embFechaHb2;
    }

    public Collection<ConsultaEmbarazo> getConsultaEmbarazoCollection() {
        return consultaEmbarazoCollection;
    }

    public void setConsultaEmbarazoCollection(Collection<ConsultaEmbarazo> consultaEmbarazoCollection) {
        this.consultaEmbarazoCollection = consultaEmbarazoCollection;
    }

    public Boolean getPartEsAborto() {
        return partEsAborto;
    }

    public void setPartEsAborto(Boolean partEsAborto) {
        this.partEsAborto = partEsAborto;
    }

    public String getPartOrigenParto() {
        return partOrigenParto;
    }

    public void setPartOrigenParto(String partOrigenParto) {
        this.partOrigenParto = partOrigenParto;
    }

    public Integer getPartConsultaPrenatal() {
        return partConsultaPrenatal;
    }

    public void setPartConsultaPrenatal(Integer partConsultaPrenatal) {
        this.partConsultaPrenatal = partConsultaPrenatal;
    }

    public Boolean getPartEnHospital() {
        return partEnHospital;
    }

    public void setPartEnHospital(Boolean partEnHospital) {
        this.partEnHospital = partEnHospital;
    }

    public Boolean getPartCarnet() {
        return partCarnet;
    }

    public void setPartCarnet(Boolean partCarnet) {
        this.partCarnet = partCarnet;
    }

    public BigDecimal getPartTemperaturaParto() {
        return partTemperaturaParto;
    }

    public void setPartTemperaturaParto(BigDecimal partTemperaturaParto) {
        this.partTemperaturaParto = partTemperaturaParto;
    }

    public Integer getPartEdadGestacion() {
        return partEdadGestacion;
    }

    public void setPartEdadGestacion(Integer partEdadGestacion) {
        this.partEdadGestacion = partEdadGestacion;
    }

    public Boolean getPartMenor37() {
        return partMenor37;
    }

    public void setPartMenor37(Boolean partMenor37) {
        this.partMenor37 = partMenor37;
    }

    public Boolean getPartMayor41() {
        return partMayor41;
    }

    public void setPartMayor41(Boolean partMayor41) {
        this.partMayor41 = partMayor41;
    }

    public String getPartPresentacion() {
        return partPresentacion;
    }

    public void setPartPresentacion(String partPresentacion) {
        this.partPresentacion = partPresentacion;
    }

    public Boolean getPartTamanoFetalAdecuado() {
        return partTamanoFetalAdecuado;
    }

    public void setPartTamanoFetalAdecuado(Boolean partTamanoFetalAdecuado) {
        this.partTamanoFetalAdecuado = partTamanoFetalAdecuado;
    }

    public String getPartInicio() {
        return partInicio;
    }

    public void setPartInicio(String partInicio) {
        this.partInicio = partInicio;
    }

    public String getPartMembranas() {
        return partMembranas;
    }

    public void setPartMembranas(String partMembranas) {
        this.partMembranas = partMembranas;
    }

    public LocalDateTime getPartFechaRuptura() {
        return partFechaRuptura;
    }

    public void setPartFechaRuptura(LocalDateTime partFechaRuptura) {
        this.partFechaRuptura = partFechaRuptura;
    }

    public String getPartPatologias() {
        return partPatologias;
    }

    public void setPartPatologias(String partPatologias) {
        this.partPatologias = partPatologias;
    }

    public String getPartTerminacion() {
        return partTerminacion;
    }

    public void setPartTerminacion(String partTerminacion) {
        this.partTerminacion = partTerminacion;
    }

    public LocalDateTime getPartFechaNacimiento() {
        return partFechaNacimiento;
    }

    public void setPartFechaNacimiento(LocalDateTime partFechaNacimiento) {
        this.partFechaNacimiento = partFechaNacimiento;
    }

    public String getPartIndicacionPrincipal() {
        return partIndicacionPrincipal;
    }

    public void setPartIndicacionPrincipal(String partIndicacionPrincipal) {
        this.partIndicacionPrincipal = partIndicacionPrincipal;
    }

    public String getPartMuerteIntegerraUt() {
        return partMuerteIntegerraUt;
    }

    public void setPartMuerteIntegerraUt(String partMuerteIntegerraUt) {
        this.partMuerteIntegerraUt = partMuerteIntegerraUt;
    }

    public Boolean getPartEpisiotomia() {
        return partEpisiotomia;
    }

    public void setPartEpisiotomia(Boolean partEpisiotomia) {
        this.partEpisiotomia = partEpisiotomia;
    }

    public Boolean getPartDesgarro() {
        return partDesgarro;
    }

    public void setPartDesgarro(Boolean partDesgarro) {
        this.partDesgarro = partDesgarro;
    }

    public Boolean getPartAlumbEspont() {
        return partAlumbEspont;
    }

    public void setPartAlumbEspont(Boolean partAlumbEspont) {
        this.partAlumbEspont = partAlumbEspont;
    }

    public Boolean getPartPlacentaComp() {
        return partPlacentaComp;
    }

    public void setPartPlacentaComp(Boolean partPlacentaComp) {
        this.partPlacentaComp = partPlacentaComp;
    }

    public String getPartMedicacionParto() {
        return partMedicacionParto;
    }

    public void setPartMedicacionParto(String partMedicacionParto) {
        this.partMedicacionParto = partMedicacionParto;
    }

    public String getPartNivelAtencion() {
        return partNivelAtencion;
    }

    public void setPartNivelAtencion(String partNivelAtencion) {
        this.partNivelAtencion = partNivelAtencion;
    }

    public String getPartAtendioPartoNombre() {
        return partAtendioPartoNombre;
    }

    public void setPartAtendioPartoNombre(String partAtendioPartoNombre) {
        this.partAtendioPartoNombre = partAtendioPartoNombre;
    }

    public String getPartAtendioPartoCargo() {
        return partAtendioPartoCargo;
    }

    public void setPartAtendioPartoCargo(String partAtendioPartoCargo) {
        this.partAtendioPartoCargo = partAtendioPartoCargo;
    }

    public String getPartAtendioNeonatoNombre() {
        return partAtendioNeonatoNombre;
    }

    public void setPartAtendioNeonatoNombre(String partAtendioNeonatoNombre) {
        this.partAtendioNeonatoNombre = partAtendioNeonatoNombre;
    }

    public String getPartAtendioNeonatoCargo() {
        return partAtendioNeonatoCargo;
    }

    public void setPartAtendioNeonatoCargo(String partAtendioNeonatoCargo) {
        this.partAtendioNeonatoCargo = partAtendioNeonatoCargo;
    }

    public String getPartNombreRn() {
        return partNombreRn;
    }

    public void setPartNombreRn(String partNombreRn) {
        this.partNombreRn = partNombreRn;
    }

    public String getPartHcRn() {
        return partHcRn;
    }

    public void setPartHcRn(String partHcRn) {
        this.partHcRn = partHcRn;
    }

    public Boolean getPartMultiple() {
        return partMultiple;
    }

    public void setPartMultiple(Boolean partMultiple) {
        this.partMultiple = partMultiple;
    }

    public Integer getPartOrden() {
        return partOrden;
    }

    public void setPartOrden(Integer partOrden) {
        this.partOrden = partOrden;
    }

    public String getPartPatologiaspp() {
        return partPatologiaspp;
    }

    public void setPartPatologiaspp(String partPatologiaspp) {
        this.partPatologiaspp = partPatologiaspp;
    }

    public Collection<TrabajoParto> getTrabajoPartoCollection() {
        return trabajoPartoCollection;
    }

    public void setTrabajoPartoCollection(Collection<TrabajoParto> trabajoPartoCollection) {
        this.trabajoPartoCollection = trabajoPartoCollection;
    }

    public String getRnSexo() {
        return rnSexo;
    }

    public void setRnSexo(String rnSexo) {
        this.rnSexo = rnSexo;
    }

    public Integer getRnPesoNacer() {
        return rnPesoNacer;
    }

    public void setRnPesoNacer(Integer rnPesoNacer) {
        this.rnPesoNacer = rnPesoNacer;
    }

    public Boolean getRnMenor2500g() {
        return rnMenor2500g;
    }

    public void setRnMenor2500g(Boolean rnMenor2500g) {
        this.rnMenor2500g = rnMenor2500g;
    }

    public Integer getRnTalla() {
        return rnTalla;
    }

    public void setRnTalla(Integer rnTalla) {
        this.rnTalla = rnTalla;
    }

    public Integer getRnPerCefalico() {
        return rnPerCefalico;
    }

    public void setRnPerCefalico(Integer rnPerCefalico) {
        this.rnPerCefalico = rnPerCefalico;
    }

    public Integer getRnEdadPorExFisico() {
        return rnEdadPorExFisico;
    }

    public void setRnEdadPorExFisico(Integer rnEdadPorExFisico) {
        this.rnEdadPorExFisico = rnEdadPorExFisico;
    }

    public Boolean getRnMenor37() {
        return rnMenor37;
    }

    public void setRnMenor37(Boolean rnMenor37) {
        this.rnMenor37 = rnMenor37;
    }

    public String getRnPesoEdadGestacional() {
        return rnPesoEdadGestacional;
    }

    public void setRnPesoEdadGestacional(String rnPesoEdadGestacional) {
        this.rnPesoEdadGestacional = rnPesoEdadGestacional;
    }

    public Integer getRnApgar1min() {
        return rnApgar1min;
    }

    public void setRnApgar1min(Integer rnApgar1min) {
        this.rnApgar1min = rnApgar1min;
    }

    public Integer getRnApgar5min() {
        return rnApgar5min;
    }

    public void setRnApgar5min(Integer rnApgar5min) {
        this.rnApgar5min = rnApgar5min;
    }

    public String getRnReaminRespir() {
        return rnReaminRespir;
    }

    public void setRnReaminRespir(String rnReaminRespir) {
        this.rnReaminRespir = rnReaminRespir;
    }

    public String getRnVdrl() {
        return rnVdrl;
    }

    public void setRnVdrl(String rnVdrl) {
        this.rnVdrl = rnVdrl;
    }

    public String getRnExamenFisico() {
        return rnExamenFisico;
    }

    public void setRnExamenFisico(String rnExamenFisico) {
        this.rnExamenFisico = rnExamenFisico;
    }

    public String getRnPatologias() {
        return rnPatologias;
    }

    public void setRnPatologias(String rnPatologias) {
        this.rnPatologias = rnPatologias;
    }

    public Boolean getRnAlojConjunto() {
        return rnAlojConjunto;
    }

    public void setRnAlojConjunto(Boolean rnAlojConjunto) {
        this.rnAlojConjunto = rnAlojConjunto;
    }

    public Boolean getRnHospitalizado() {
        return rnHospitalizado;
    }

    public void setRnHospitalizado(Boolean rnHospitalizado) {
        this.rnHospitalizado = rnHospitalizado;
    }

    public Boolean getRnBcg() {
        return rnBcg;
    }

    public void setRnBcg(Boolean rnBcg) {
        this.rnBcg = rnBcg;
    }

    public Boolean getRnPvo() {
        return rnPvo;
    }

    public void setRnPvo(Boolean rnPvo) {
        this.rnPvo = rnPvo;
    }

    public String getRnGrupoSanguineo() {
        return rnGrupoSanguineo;
    }

    public void setRnGrupoSanguineo(String rnGrupoSanguineo) {
        this.rnGrupoSanguineo = rnGrupoSanguineo;
    }

    public String getRnRhh() {
        return rnRhh;
    }

    public void setRnRhh(String rnRhh) {
        this.rnRhh = rnRhh;
    }

    public String getRnObservaciones() {
        return rnObservaciones;
    }

    public void setRnObservaciones(String rnObservaciones) {
        this.rnObservaciones = rnObservaciones;
    }

    public Collection<Puerperio> getPuerperioCollection() {
        return puerperioCollection;
    }

    public void setPuerperioCollection(Collection<Puerperio> puerperioCollection) {
        this.puerperioCollection = puerperioCollection;
    }

    public LocalDate getEgrFechaEgresoRn() {
        return egrFechaEgresoRn;
    }

    public void setEgrFechaEgresoRn(LocalDate egrFechaEgresoRn) {
        this.egrFechaEgresoRn = egrFechaEgresoRn;
    }

    public String getEgrEstadoRn() {
        return egrEstadoRn;
    }

    public void setEgrEstadoRn(String egrEstadoRn) {
        this.egrEstadoRn = egrEstadoRn;
    }

    public String getEgrAlimento() {
        return egrAlimento;
    }

    public void setEgrAlimento(String egrAlimento) {
        this.egrAlimento = egrAlimento;
    }

    public Integer getEgrPesoEgresoRn() {
        return egrPesoEgresoRn;
    }

    public void setEgrPesoEgresoRn(Integer egrPesoEgresoRn) {
        this.egrPesoEgresoRn = egrPesoEgresoRn;
    }

    public String getEgrResponsableEgresoRn() {
        return egrResponsableEgresoRn;
    }

    public void setEgrResponsableEgresoRn(String egrResponsableEgresoRn) {
        this.egrResponsableEgresoRn = egrResponsableEgresoRn;
    }

    public LocalDate getEgrFechaEgresoMaterno() {
        return egrFechaEgresoMaterno;
    }

    public void setEgrFechaEgresoMaterno(LocalDate egrFechaEgresoMaterno) {
        this.egrFechaEgresoMaterno = egrFechaEgresoMaterno;
    }

    public String getEgrResponsableEgresoMaterno() {
        return egrResponsableEgresoMaterno;
    }

    public void setEgrResponsableEgresoMaterno(String egrResponsableEgresoMaterno) {
        this.egrResponsableEgresoMaterno = egrResponsableEgresoMaterno;
    }

    public String getEgrAnticoncepcion() {
        return egrAnticoncepcion;
    }

    public void setEgrAnticoncepcion(String egrAnticoncepcion) {
        this.egrAnticoncepcion = egrAnticoncepcion;
    }

    public String getEgrEstadoMaterno() {
        return egrEstadoMaterno;
    }

    public void setEgrEstadoMaterno(String egrEstadoMaterno) {
        this.egrEstadoMaterno = egrEstadoMaterno;
    }


    public String getRecOpServicio() {
        return recOpServicio;
    }

    public void setRecOpServicio(String recOpServicio) {
        this.recOpServicio = recOpServicio;
    }

    public String getRecOpSala() {
        return recOpSala;
    }

    public void setRecOpSala(String recOpSala) {
        this.recOpSala = recOpSala;
    }

    public String getRecOpCama() {
        return recOpCama;
    }

    public void setRecOpCama(String recOpCama) {
        this.recOpCama = recOpCama;
    }

    public String getRecOpPreoperatorio() {
        return recOpPreoperatorio;
    }

    public void setRecOpPreoperatorio(String recOpPreoperatorio) {
        this.recOpPreoperatorio = recOpPreoperatorio;
    }

    public String getRecOpPostoperatorio() {
        return recOpPostoperatorio;
    }

    public void setRecOpPostoperatorio(String recOpPostoperatorio) {
        this.recOpPostoperatorio = recOpPostoperatorio;
    }

    public String getRecOpProyectada() {
        return recOpProyectada;
    }

    public void setRecOpProyectada(String recOpProyectada) {
        this.recOpProyectada = recOpProyectada;
    }

    public String getRecOpTipoOperacion() {
        return recOpTipoOperacion;
    }

    public void setRecOpTipoOperacion(String recOpTipoOperacion) {
        this.recOpTipoOperacion = recOpTipoOperacion;
    }

    public String getRecOpRealizada() {
        return recOpRealizada;
    }

    public void setRecOpRealizada(String recOpRealizada) {
        this.recOpRealizada = recOpRealizada;
    }

    public String getRecOpCirujano() {
        return recOpCirujano;
    }

    public void setRecOpCirujano(String recOpCirujano) {
        this.recOpCirujano = recOpCirujano;
    }

    public String getRecOpPrimerAyudante() {
        return recOpPrimerAyudante;
    }

    public void setRecOpPrimerAyudante(String recOpPrimerAyudante) {
        this.recOpPrimerAyudante = recOpPrimerAyudante;
    }

    public String getRecOpSegundoAyudante() {
        return recOpSegundoAyudante;
    }

    public void setRecOpSegundoAyudante(String recOpSegundoAyudante) {
        this.recOpSegundoAyudante = recOpSegundoAyudante;
    }

    public String getRecOpTercerAyudante() {
        return recOpTercerAyudante;
    }

    public void setRecOpTercerAyudante(String recOpTercerAyudante) {
        this.recOpTercerAyudante = recOpTercerAyudante;
    }

    public String getRecOpInstrumentista() {
        return recOpInstrumentista;
    }

    public void setRecOpInstrumentista(String recOpInstrumentista) {
        this.recOpInstrumentista = recOpInstrumentista;
    }

    public String getRecOpCirculante() {
        return recOpCirculante;
    }

    public void setRecOpCirculante(String recOpCirculante) {
        this.recOpCirculante = recOpCirculante;
    }

    public String getRecOpAnestesia() {
        return recOpAnestesia;
    }

    public void setRecOpAnestesia(String recOpAnestesia) {
        this.recOpAnestesia = recOpAnestesia;
    }

    public String getRecOpAyudanteAnestesia() {
        return recOpAyudanteAnestesia;
    }

    public void setRecOpAyudanteAnestesia(String recOpAyudanteAnestesia) {
        this.recOpAyudanteAnestesia = recOpAyudanteAnestesia;
    }

    public LocalDate getRecOpFechaOperacion() {
        return recOpFechaOperacion;
    }

    public void setRecOpFechaOperacion(LocalDate recOpFechaOperacion) {
        this.recOpFechaOperacion = recOpFechaOperacion;
    }

    public String getRecOpHoraInicio() {
        return recOpHoraInicio;
    }

    public void setRecOpHoraInicio(String recOpHoraInicio) {
        this.recOpHoraInicio = recOpHoraInicio;
    }

    public String getRecOpHoraTerminacion() {
        return recOpHoraTerminacion;
    }

    public void setRecOpHoraTerminacion(String recOpHoraTerminacion) {
        this.recOpHoraTerminacion = recOpHoraTerminacion;
    }

    public String getRecOpTipoAnestesia() {
        return recOpTipoAnestesia;
    }

    public void setRecOpTipoAnestesia(String recOpTipoAnestesia) {
        this.recOpTipoAnestesia = recOpTipoAnestesia;
    }

    public String getRecOpDieresis() {
        return recOpDieresis;
    }

    public void setRecOpDieresis(String recOpDieresis) {
        this.recOpDieresis = recOpDieresis;
    }

    public String getRecOpExposicion() {
        return recOpExposicion;
    }

    public void setRecOpExposicion(String recOpExposicion) {
        this.recOpExposicion = recOpExposicion;
    }

    public String getRecOpExploHallazgosQui() {
        return recOpExploHallazgosQui;
    }

    public void setRecOpExploHallazgosQui(String recOpExploHallazgosQui) {
        this.recOpExploHallazgosQui = recOpExploHallazgosQui;
    }

    public String getRecOpProcedimientoOperatorio() {
        return recOpProcedimientoOperatorio;
    }

    public void setRecOpProcedimientoOperatorio(String recOpProcedimientoOperatorio) {
        this.recOpProcedimientoOperatorio = recOpProcedimientoOperatorio;
    }

    public Collection<HojaEvolucionPrescripcion> getHojaEvolucionPrescripcionCollection() {
        return hojaEvolucionPrescripcionCollection;
    }

    public void setHojaEvolucionPrescripcionCollection(Collection<HojaEvolucionPrescripcion> hojaEvolucionPrescripcionCollection) {
        this.hojaEvolucionPrescripcionCollection = hojaEvolucionPrescripcionCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichaPrenatal that = (FichaPrenatal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fechaRegistro, that.fechaRegistro) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(anteFamiliares, that.anteFamiliares) &&
                Objects.equals(antePersonales, that.antePersonales) &&
                Objects.equals(anteOtros, that.anteOtros) &&
                Objects.equals(anteNingunoOmasEmbarazos, that.anteNingunoOmasEmbarazos) &&
                Objects.equals(anteRnMenor2500, that.anteRnMenor2500) &&
                Objects.equals(anteGemelares, that.anteGemelares) &&
                Objects.equals(anteGestas, that.anteGestas) &&
                Objects.equals(antePartos, that.antePartos) &&
                Objects.equals(anteAbortos, that.anteAbortos) &&
                Objects.equals(anteVaginales, that.anteVaginales) &&
                Objects.equals(anteCesareas, that.anteCesareas) &&
                Objects.equals(anteNacidosVivos, that.anteNacidosVivos) &&
                Objects.equals(anteNacidosMuertos, that.anteNacidosMuertos) &&
                Objects.equals(anteViven, that.anteViven) &&
                Objects.equals(anteMuertos1Semana, that.anteMuertos1Semana) &&
                Objects.equals(anteMuertosmas1Semana, that.anteMuertosmas1Semana) &&
                Objects.equals(anteFechaFinAnteriorEmbarazo, that.anteFechaFinAnteriorEmbarazo) &&
                Objects.equals(anteRnMayorPeso, that.anteRnMayorPeso) &&
                Objects.equals(embPesoAnterior, that.embPesoAnterior) &&
                Objects.equals(embTalla, that.embTalla) &&
                Objects.equals(embFum, that.embFum) &&
                Objects.equals(embFfp, that.embFfp) &&
                Objects.equals(embDudas, that.embDudas) &&
                Objects.equals(embAntitetanicaPrevia, that.embAntitetanicaPrevia) &&
                Objects.equals(embAntitetanicaMesGestaActual1, that.embAntitetanicaMesGestaActual1) &&
                Objects.equals(embAntitetanicaMesGestaActual2, that.embAntitetanicaMesGestaActual2) &&
                Objects.equals(embGrupoSanguineo, that.embGrupoSanguineo) &&
                Objects.equals(embRh, that.embRh) &&
                Objects.equals(embSensibilidad, that.embSensibilidad) &&
                Objects.equals(embGrupoSanguineoSensibilidad, that.embGrupoSanguineoSensibilidad) &&
                Objects.equals(embFuma, that.embFuma) &&
                Objects.equals(embCigarrilosPorDia, that.embCigarrilosPorDia) &&
                Objects.equals(embHospitalizacion, that.embHospitalizacion) &&
                Objects.equals(embTraslado, that.embTraslado) &&
                Objects.equals(embLugarTraslado, that.embLugarTraslado) &&
                Objects.equals(embExClinico, that.embExClinico) &&
                Objects.equals(embExMamas, that.embExMamas) &&
                Objects.equals(embExOdontologico, that.embExOdontologico) &&
                Objects.equals(embExPelvis, that.embExPelvis) &&
                Objects.equals(embExPapanicolao, that.embExPapanicolao) &&
                Objects.equals(embExColposcopia, that.embExColposcopia) &&
                Objects.equals(embExCervix, that.embExCervix) &&
                Objects.equals(embVdrl, that.embVdrl) &&
                Objects.equals(embFechaVdrl, that.embFechaVdrl) &&
                Objects.equals(embGlucosa, that.embGlucosa) &&
                Objects.equals(embFechaGlucosa, that.embFechaGlucosa) &&
                Objects.equals(embHb1, that.embHb1) &&
                Objects.equals(embFechaHb1, that.embFechaHb1) &&
                Objects.equals(embHb2, that.embHb2) &&
                Objects.equals(embFechaHb2, that.embFechaHb2) &&
                Objects.equals(consultaEmbarazoCollection, that.consultaEmbarazoCollection) &&
                Objects.equals(partEsAborto, that.partEsAborto) &&
                Objects.equals(partOrigenParto, that.partOrigenParto) &&
                Objects.equals(partConsultaPrenatal, that.partConsultaPrenatal) &&
                Objects.equals(partEnHospital, that.partEnHospital) &&
                Objects.equals(partCarnet, that.partCarnet) &&
                Objects.equals(partFechaIngresoParto, that.partFechaIngresoParto) &&
                Objects.equals(partTemperaturaParto, that.partTemperaturaParto) &&
                Objects.equals(partEdadGestacion, that.partEdadGestacion) &&
                Objects.equals(partMenor37, that.partMenor37) &&
                Objects.equals(partMayor41, that.partMayor41) &&
                Objects.equals(partPresentacion, that.partPresentacion) &&
                Objects.equals(partTamanoFetalAdecuado, that.partTamanoFetalAdecuado) &&
                Objects.equals(partFechaTerminacion, that.partFechaTerminacion) &&
                Objects.equals(partInicio, that.partInicio) &&
                Objects.equals(partMembranas, that.partMembranas) &&
                Objects.equals(partFechaRuptura, that.partFechaRuptura) &&
                Objects.equals(partPatologias, that.partPatologias) &&
                Objects.equals(partTerminacion, that.partTerminacion) &&
                Objects.equals(partFechaNacimiento, that.partFechaNacimiento) &&
                Objects.equals(partIndicacionPrincipal, that.partIndicacionPrincipal) &&
                Objects.equals(partMuerteIntegerraUt, that.partMuerteIntegerraUt) &&
                Objects.equals(partEpisiotomia, that.partEpisiotomia) &&
                Objects.equals(partDesgarro, that.partDesgarro) &&
                Objects.equals(partAlumbEspont, that.partAlumbEspont) &&
                Objects.equals(partPlacentaComp, that.partPlacentaComp) &&
                Objects.equals(partMedicacionParto, that.partMedicacionParto) &&
                Objects.equals(partNivelAtencion, that.partNivelAtencion) &&
                Objects.equals(partAtendioPartoNombre, that.partAtendioPartoNombre) &&
                Objects.equals(partAtendioPartoCargo, that.partAtendioPartoCargo) &&
                Objects.equals(partAtendioNeonatoNombre, that.partAtendioNeonatoNombre) &&
                Objects.equals(partAtendioNeonatoCargo, that.partAtendioNeonatoCargo) &&
                Objects.equals(partNombreRn, that.partNombreRn) &&
                Objects.equals(partHcRn, that.partHcRn) &&
                Objects.equals(partMultiple, that.partMultiple) &&
                Objects.equals(partOrden, that.partOrden) &&
                Objects.equals(partPatologiaspp, that.partPatologiaspp) &&
                Objects.equals(trabajoPartoCollection, that.trabajoPartoCollection) &&
                Objects.equals(rnSexo, that.rnSexo) &&
                Objects.equals(rnPesoNacer, that.rnPesoNacer) &&
                Objects.equals(rnMenor2500g, that.rnMenor2500g) &&
                Objects.equals(rnTalla, that.rnTalla) &&
                Objects.equals(rnPerCefalico, that.rnPerCefalico) &&
                Objects.equals(rnEdadPorExFisico, that.rnEdadPorExFisico) &&
                Objects.equals(rnMenor37, that.rnMenor37) &&
                Objects.equals(rnPesoEdadGestacional, that.rnPesoEdadGestacional) &&
                Objects.equals(rnApgar1min, that.rnApgar1min) &&
                Objects.equals(rnApgar5min, that.rnApgar5min) &&
                Objects.equals(rnReaminRespir, that.rnReaminRespir) &&
                Objects.equals(rnVdrl, that.rnVdrl) &&
                Objects.equals(rnExamenFisico, that.rnExamenFisico) &&
                Objects.equals(rnPatologias, that.rnPatologias) &&
                Objects.equals(rnAlojConjunto, that.rnAlojConjunto) &&
                Objects.equals(rnHospitalizado, that.rnHospitalizado) &&
                Objects.equals(rnBcg, that.rnBcg) &&
                Objects.equals(rnPvo, that.rnPvo) &&
                Objects.equals(rnGrupoSanguineo, that.rnGrupoSanguineo) &&
                Objects.equals(rnRhh, that.rnRhh) &&
                Objects.equals(rnObservaciones, that.rnObservaciones) &&
                Objects.equals(puerperioCollection, that.puerperioCollection) &&
                Objects.equals(egrFechaEgresoRn, that.egrFechaEgresoRn) &&
                Objects.equals(egrEstadoRn, that.egrEstadoRn) &&
                Objects.equals(egrAlimento, that.egrAlimento) &&
                Objects.equals(egrPesoEgresoRn, that.egrPesoEgresoRn) &&
                Objects.equals(egrResponsableEgresoRn, that.egrResponsableEgresoRn) &&
                Objects.equals(egrFechaEgresoMaterno, that.egrFechaEgresoMaterno) &&
                Objects.equals(egrResponsableEgresoMaterno, that.egrResponsableEgresoMaterno) &&
                Objects.equals(egrAnticoncepcion, that.egrAnticoncepcion) &&
                Objects.equals(egrEstadoMaterno, that.egrEstadoMaterno) &&
                Objects.equals(recOpServicio, that.recOpServicio) &&
                Objects.equals(recOpSala, that.recOpSala) &&
                Objects.equals(recOpCama, that.recOpCama) &&
                Objects.equals(recOpPreoperatorio, that.recOpPreoperatorio) &&
                Objects.equals(recOpPostoperatorio, that.recOpPostoperatorio) &&
                Objects.equals(recOpProyectada, that.recOpProyectada) &&
                Objects.equals(recOpTipoOperacion, that.recOpTipoOperacion) &&
                Objects.equals(recOpRealizada, that.recOpRealizada) &&
                Objects.equals(recOpCirujano, that.recOpCirujano) &&
                Objects.equals(recOpPrimerAyudante, that.recOpPrimerAyudante) &&
                Objects.equals(recOpSegundoAyudante, that.recOpSegundoAyudante) &&
                Objects.equals(recOpTercerAyudante, that.recOpTercerAyudante) &&
                Objects.equals(recOpInstrumentista, that.recOpInstrumentista) &&
                Objects.equals(recOpCirculante, that.recOpCirculante) &&
                Objects.equals(recOpAnestesia, that.recOpAnestesia) &&
                Objects.equals(recOpAyudanteAnestesia, that.recOpAyudanteAnestesia) &&
                Objects.equals(recOpFechaOperacion, that.recOpFechaOperacion) &&
                Objects.equals(recOpHoraInicio, that.recOpHoraInicio) &&
                Objects.equals(recOpHoraTerminacion, that.recOpHoraTerminacion) &&
                Objects.equals(recOpTipoAnestesia, that.recOpTipoAnestesia) &&
                Objects.equals(recOpDieresis, that.recOpDieresis) &&
                Objects.equals(recOpExposicion, that.recOpExposicion) &&
                Objects.equals(recOpExploHallazgosQui, that.recOpExploHallazgosQui) &&
                Objects.equals(recOpProcedimientoOperatorio, that.recOpProcedimientoOperatorio) &&
                Objects.equals(hojaEvolucionPrescripcionCollection, that.hojaEvolucionPrescripcionCollection) &&
                Objects.equals(paciente, that.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaRegistro, estado, anteFamiliares, antePersonales, anteOtros, anteNingunoOmasEmbarazos, anteRnMenor2500, anteGemelares, anteGestas, antePartos, anteAbortos, anteVaginales, anteCesareas, anteNacidosVivos, anteNacidosMuertos, anteViven, anteMuertos1Semana, anteMuertosmas1Semana, anteFechaFinAnteriorEmbarazo, anteRnMayorPeso, embPesoAnterior, embTalla, embFum, embFfp, embDudas, embAntitetanicaPrevia, embAntitetanicaMesGestaActual1, embAntitetanicaMesGestaActual2, embGrupoSanguineo, embRh, embSensibilidad, embGrupoSanguineoSensibilidad, embFuma, embCigarrilosPorDia, embHospitalizacion, embTraslado, embLugarTraslado, embExClinico, embExMamas, embExOdontologico, embExPelvis, embExPapanicolao, embExColposcopia, embExCervix, embVdrl, embFechaVdrl, embGlucosa, embFechaGlucosa, embHb1, embFechaHb1, embHb2, embFechaHb2, consultaEmbarazoCollection, partEsAborto, partOrigenParto, partConsultaPrenatal, partEnHospital, partCarnet, partFechaIngresoParto, partTemperaturaParto, partEdadGestacion, partMenor37, partMayor41, partPresentacion, partTamanoFetalAdecuado, partFechaTerminacion, partInicio, partMembranas, partFechaRuptura, partPatologias, partTerminacion, partFechaNacimiento, partIndicacionPrincipal, partMuerteIntegerraUt, partEpisiotomia, partDesgarro, partAlumbEspont, partPlacentaComp, partMedicacionParto, partNivelAtencion, partAtendioPartoNombre, partAtendioPartoCargo, partAtendioNeonatoNombre, partAtendioNeonatoCargo, partNombreRn, partHcRn, partMultiple, partOrden, partPatologiaspp, trabajoPartoCollection, rnSexo, rnPesoNacer, rnMenor2500g, rnTalla, rnPerCefalico, rnEdadPorExFisico, rnMenor37, rnPesoEdadGestacional, rnApgar1min, rnApgar5min, rnReaminRespir, rnVdrl, rnExamenFisico, rnPatologias, rnAlojConjunto, rnHospitalizado, rnBcg, rnPvo, rnGrupoSanguineo, rnRhh, rnObservaciones, puerperioCollection, egrFechaEgresoRn, egrEstadoRn, egrAlimento, egrPesoEgresoRn, egrResponsableEgresoRn, egrFechaEgresoMaterno, egrResponsableEgresoMaterno, egrAnticoncepcion, egrEstadoMaterno, recOpServicio, recOpSala, recOpCama, recOpPreoperatorio, recOpPostoperatorio, recOpProyectada, recOpTipoOperacion, recOpRealizada, recOpCirujano, recOpPrimerAyudante, recOpSegundoAyudante, recOpTercerAyudante, recOpInstrumentista, recOpCirculante, recOpAnestesia, recOpAyudanteAnestesia, recOpFechaOperacion, recOpHoraInicio, recOpHoraTerminacion, recOpTipoAnestesia, recOpDieresis, recOpExposicion, recOpExploHallazgosQui, recOpProcedimientoOperatorio, hojaEvolucionPrescripcionCollection, paciente);
    }
}
