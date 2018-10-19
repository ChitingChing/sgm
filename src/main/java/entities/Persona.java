package entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class Persona {
    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Basic
    @Column(name = "cedula")
    private String cedula;
    @Basic
    @Column(name = "primernombre")
    private String primernombre;
    @Basic
    @Column(name = "segundonombre")
    private String segundonombre;
    @Basic
    @Column(name = "primerapellido")
    private String primerapellido;
    @Basic
    @Column(name = "segundoapellido")
    private String segundoapellido;
    @Basic
    @Column(name = "localidad")
    private String localidad;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "provincia")
    private String provincia;
    @Basic
    @Column(name = "canton")
    private String canton;
    @Basic
    @Column(name = "fechanacimiento")
    private LocalDate fechanacimiento;
    @Basic
    @Column(name = "estadocivil")
    private String estadocivil;
    @Basic
    @Column(name = "estudios")
    private String estudios;
    @Basic
    @Column(name = "alfabeta")
    private Boolean alfabeta;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "celular")
    private String celular;
    @Basic
    @Column(name = "sexo")
    private String sexo;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }


    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }


    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }


    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }


    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }


    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }


    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }


    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }


    public Boolean getAlfabeta() {
        return alfabeta;
    }

    public void setAlfabeta(Boolean alfabeta) {
        this.alfabeta = alfabeta;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



}
