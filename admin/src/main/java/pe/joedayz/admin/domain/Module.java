package pe.joedayz.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Module.
 */
@Entity
@Table(name = "module")
public class Module implements Serializable {

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
    @Column(name = "nombre", length = 250, nullable = false)
    private String nombre;

    @NotNull
    @Size(max = 50)
    @Column(name = "url", length = 50, nullable = false)
    private String url;

    @Size(max = 150)
    @Column(name = "menu_label", length = 150)
    private String menuLabel;

    @Column(name = "menu_orden")
    private Double menuOrden;

    @Column(name = "orden")
    private Double orden;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "padre")
    private Set<Module> modules = new HashSet<>();
    @OneToMany(mappedBy = "moduleId")
    private Set<Permission> permissions = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("modules")
    private Module padre;

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

    public Module code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public Module nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public Module url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public Module menuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
        return this;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public Double getMenuOrden() {
        return menuOrden;
    }

    public Module menuOrden(Double menuOrden) {
        this.menuOrden = menuOrden;
        return this;
    }

    public void setMenuOrden(Double menuOrden) {
        this.menuOrden = menuOrden;
    }

    public Double getOrden() {
        return orden;
    }

    public Module orden(Double orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Double orden) {
        this.orden = orden;
    }

    public Boolean isVisible() {
        return visible;
    }

    public Module visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean isStatus() {
        return status;
    }

    public Module status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Module modules(Set<Module> modules) {
        this.modules = modules;
        return this;
    }

    public Module addModule(Module module) {
        this.modules.add(module);
        module.setPadre(this);
        return this;
    }

    public Module removeModule(Module module) {
        this.modules.remove(module);
        module.setPadre(null);
        return this;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Module permissions(Set<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Module addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.setModuleId(this);
        return this;
    }

    public Module removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.setModuleId(null);
        return this;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Module getPadre() {
        return padre;
    }

    public Module padre(Module module) {
        this.padre = module;
        return this;
    }

    public void setPadre(Module module) {
        this.padre = module;
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
        Module module = (Module) o;
        if (module.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), module.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Module{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", url='" + getUrl() + "'" +
            ", menuLabel='" + getMenuLabel() + "'" +
            ", menuOrden=" + getMenuOrden() +
            ", orden=" + getOrden() +
            ", visible='" + isVisible() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
