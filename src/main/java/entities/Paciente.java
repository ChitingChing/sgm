package entities;

import org.omg.CORBA.PERSIST_STORE;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Paciente extends Persona {
    @Basic
    @Column(name = "nhistoriaclinica")
    private String nhistoriaclinica;

    @OneToMany(mappedBy = "paciente")
    private Collection<FichaPrenatal> fichaPrenatalCollection;

    public Paciente() {
    }

    public Collection<FichaPrenatal> getFichaPrenatalCollection() {
        return fichaPrenatalCollection;
    }

    public void setFichaPrenatalCollection(Collection<FichaPrenatal> fichaPrenatalCollection) {
        this.fichaPrenatalCollection = fichaPrenatalCollection;
    }


    public String getNhistoriaclinica() {
        return nhistoriaclinica;
    }

    public void setNhistoriaclinica(String nhistoriaclinica) {
        this.nhistoriaclinica = nhistoriaclinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(nhistoriaclinica, paciente.nhistoriaclinica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nhistoriaclinica);
    }
}
