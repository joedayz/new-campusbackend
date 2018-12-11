package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.Portada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Portada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PortadaRepository extends JpaRepository<Portada, Long> {

}
