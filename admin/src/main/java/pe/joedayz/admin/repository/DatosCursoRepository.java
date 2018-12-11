package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.DatosCurso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DatosCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatosCursoRepository extends JpaRepository<DatosCurso, Long> {

}
