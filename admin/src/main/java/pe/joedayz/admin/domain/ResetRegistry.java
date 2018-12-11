package pe.joedayz.admin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResetRegistry.
 */
@Entity
@Table(name = "reset_registry")
public class ResetRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @Column(name = "token", length = 250)
    private String token;

    @Size(max = 50)
    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public ResetRegistry token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public ResetRegistry userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean isStatus() {
        return status;
    }

    public ResetRegistry status(Boolean status) {
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
        ResetRegistry resetRegistry = (ResetRegistry) o;
        if (resetRegistry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resetRegistry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResetRegistry{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", userName='" + getUserName() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
