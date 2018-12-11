package pe.joedayz.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Permission.
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @Size(max = 1)
    @Column(name = "permission_type", length = 1)
    private String permissionType;

    @ManyToOne
    @JsonIgnoreProperties("permissions")
    private Module moduleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Permission roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public Permission permissionType(String permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public Permission moduleId(Module module) {
        this.moduleId = module;
        return this;
    }

    public void setModuleId(Module module) {
        this.moduleId = module;
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
        Permission permission = (Permission) o;
        if (permission.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), permission.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Permission{" +
            "id=" + getId() +
            ", roleName='" + getRoleName() + "'" +
            ", permissionType='" + getPermissionType() + "'" +
            "}";
    }
}
