package pe.joedayz.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Curso.
 */
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @Size(max = 200)
    @Column(name = "titulo_footer", length = 200)
    private String tituloFooter;

    @NotNull
    @Size(max = 100)
    @Column(name = "key_curso", length = 100, nullable = false)
    private String keyCurso;

    @Column(name = "destacado")
    private Boolean destacado;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TipoCurso tipoCurso;

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

    public Curso titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloFooter() {
        return tituloFooter;
    }

    public Curso tituloFooter(String tituloFooter) {
        this.tituloFooter = tituloFooter;
        return this;
    }

    public void setTituloFooter(String tituloFooter) {
        this.tituloFooter = tituloFooter;
    }

    public String getKeyCurso() {
        return keyCurso;
    }

    public Curso keyCurso(String keyCurso) {
        this.keyCurso = keyCurso;
        return this;
    }

    public void setKeyCurso(String keyCurso) {
        this.keyCurso = keyCurso;
    }

    public Boolean isDestacado() {
        return destacado;
    }

    public Curso destacado(Boolean destacado) {
        this.destacado = destacado;
        return this;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public Boolean isStatus() {
        return status;
    }

    public Curso status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public Curso tipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
        return this;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
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
        Curso curso = (Curso) o;
        if (curso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), curso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Curso{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", tituloFooter='" + getTituloFooter() + "'" +
            ", keyCurso='" + getKeyCurso() + "'" +
            ", destacado='" + isDestacado() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
