package pe.joedayz.admin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Portada.
 */
@Entity
@Table(name = "portada")
public class Portada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "titulo_foto", length = 50)
    private String tituloFoto;

    @NotNull
    @Size(max = 50)
    @Column(name = "url_foto", length = 50, nullable = false)
    private String urlFoto;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloFoto() {
        return tituloFoto;
    }

    public Portada tituloFoto(String tituloFoto) {
        this.tituloFoto = tituloFoto;
        return this;
    }

    public void setTituloFoto(String tituloFoto) {
        this.tituloFoto = tituloFoto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public Portada urlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
        return this;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Boolean isStatus() {
        return status;
    }

    public Portada status(Boolean status) {
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
        Portada portada = (Portada) o;
        if (portada.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), portada.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Portada{" +
            "id=" + getId() +
            ", tituloFoto='" + getTituloFoto() + "'" +
            ", urlFoto='" + getUrlFoto() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
