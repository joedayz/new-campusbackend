package pe.joedayz.admin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Hangout.
 */
@Entity
@Table(name = "hangout")
public class Hangout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 250)
    @Column(name = "titulo", length = 250, nullable = false)
    private String titulo;

    @NotNull
    @Size(min = 3, max = 250)
    @Column(name = "ponente", length = 250, nullable = false)
    private String ponente;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Size(max = 250)
    @Column(name = "url", length = 250, nullable = false)
    private String url;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private ZonedDateTime fecha;

    @Column(name = "status")
    private Boolean status;

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

    public Hangout titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPonente() {
        return ponente;
    }

    public Hangout ponente(String ponente) {
        this.ponente = ponente;
        return this;
    }

    public void setPonente(String ponente) {
        this.ponente = ponente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Hangout descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public Hangout url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public Hangout fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public Boolean isStatus() {
        return status;
    }

    public Hangout status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        Hangout hangout = (Hangout) o;
        if (hangout.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hangout.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hangout{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", ponente='" + getPonente() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", url='" + getUrl() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
