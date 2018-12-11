package pe.joedayz.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TemasCurso.
 */
@Entity
@Table(name = "temas_curso")
public class TemasCurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @Column(name = "titulo", length = 250)
    private String titulo;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 250)
    @Column(name = "url", length = 250)
    private String url;

    @Column(name = "orden")
    private Double orden;

    @Column(name = "status")
    private Boolean status;

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

    public String getTitulo() {
        return titulo;
    }

    public TemasCurso titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TemasCurso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public TemasCurso url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getOrden() {
        return orden;
    }

    public TemasCurso orden(Double orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Double orden) {
        this.orden = orden;
    }

    public Boolean isStatus() {
        return status;
    }

    public TemasCurso status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Curso getCurso() {
        return curso;
    }

    public TemasCurso curso(Curso curso) {
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
        TemasCurso temasCurso = (TemasCurso) o;
        if (temasCurso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), temasCurso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TemasCurso{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", url='" + getUrl() + "'" +
            ", orden=" + getOrden() +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
