package pe.joedayz.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DatosCurso.
 */
@Entity
@Table(name = "datos_curso")
public class DatosCurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 250)
    @Column(name = "instructor", length = 250)
    private String instructor;

    @Size(max = 250)
    @Column(name = "metodologia", length = 250)
    private String metodologia;

    @Size(max = 250)
    @Column(name = "participantes", length = 250)
    private String participantes;

    @Size(max = 500)
    @Column(name = "requisitos", length = 500)
    private String requisitos;

    @Size(max = 250)
    @Column(name = "syllabus", length = 250)
    private String syllabus;

    @Size(max = 250)
    @Column(name = "costo", length = 250)
    private String costo;

    @Size(max = 250)
    @Column(name = "duracion", length = 250)
    private String duracion;

    @Size(max = 250)
    @Column(name = "fecha_inscripcion", length = 250)
    private String fechaInscripcion;

    @Size(max = 250)
    @Column(name = "horarios", length = 250)
    private String horarios;

    @Size(max = 250)
    @Column(name = "numero_participantes", length = 250)
    private String numeroParticipantes;

    @Size(max = 250)
    @Column(name = "membresia_anual", length = 250)
    private String membresiaAnual;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Curso curso;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public DatosCurso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstructor() {
        return instructor;
    }

    public DatosCurso instructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public DatosCurso metodologia(String metodologia) {
        this.metodologia = metodologia;
        return this;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getParticipantes() {
        return participantes;
    }

    public DatosCurso participantes(String participantes) {
        this.participantes = participantes;
        return this;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public DatosCurso requisitos(String requisitos) {
        this.requisitos = requisitos;
        return this;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public DatosCurso syllabus(String syllabus) {
        this.syllabus = syllabus;
        return this;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getCosto() {
        return costo;
    }

    public DatosCurso costo(String costo) {
        this.costo = costo;
        return this;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getDuracion() {
        return duracion;
    }

    public DatosCurso duracion(String duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public DatosCurso fechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
        return this;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getHorarios() {
        return horarios;
    }

    public DatosCurso horarios(String horarios) {
        this.horarios = horarios;
        return this;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public DatosCurso numeroParticipantes(String numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
        return this;
    }

    public void setNumeroParticipantes(String numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public String getMembresiaAnual() {
        return membresiaAnual;
    }

    public DatosCurso membresiaAnual(String membresiaAnual) {
        this.membresiaAnual = membresiaAnual;
        return this;
    }

    public void setMembresiaAnual(String membresiaAnual) {
        this.membresiaAnual = membresiaAnual;
    }

    public Curso getCurso() {
        return curso;
    }

    public DatosCurso curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatosCurso datosCurso = (DatosCurso) o;
        if (datosCurso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), datosCurso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DatosCurso{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", instructor='" + getInstructor() + "'" +
            ", metodologia='" + getMetodologia() + "'" +
            ", participantes='" + getParticipantes() + "'" +
            ", requisitos='" + getRequisitos() + "'" +
            ", syllabus='" + getSyllabus() + "'" +
            ", costo='" + getCosto() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", fechaInscripcion='" + getFechaInscripcion() + "'" +
            ", horarios='" + getHorarios() + "'" +
            ", numeroParticipantes='" + getNumeroParticipantes() + "'" +
            ", membresiaAnual='" + getMembresiaAnual() + "'" +
            "}";
    }
}
