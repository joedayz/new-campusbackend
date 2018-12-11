package pe.joedayz.admin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GeneralTable.
 */
@Entity
@Table(name = "general_table")
public class GeneralTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @NotNull
    @Size(max = 250)
    @Column(name = "tabla", length = 250, nullable = false)
    private String tabla;

    @NotNull
    @Size(max = 50)
    @Column(name = "valor", length = 50, nullable = false)
    private String valor;

    @Column(name = "orden")
    private Double orden;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public GeneralTable code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTabla() {
        return tabla;
    }

    public GeneralTable tabla(String tabla) {
        this.tabla = tabla;
        return this;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getValor() {
        return valor;
    }

    public GeneralTable valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Double getOrden() {
        return orden;
    }

    public GeneralTable orden(Double orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Double orden) {
        this.orden = orden;
    }

    public Boolean isStatus() {
        return status;
    }

    public GeneralTable status(Boolean status) {
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
        GeneralTable generalTable = (GeneralTable) o;
        if (generalTable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), generalTable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeneralTable{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", tabla='" + getTabla() + "'" +
            ", valor='" + getValor() + "'" +
            ", orden=" + getOrden() +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
