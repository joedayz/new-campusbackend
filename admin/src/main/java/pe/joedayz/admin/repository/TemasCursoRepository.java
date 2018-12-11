package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.TemasCurso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TemasCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemasCursoRepository extends JpaRepository<TemasCurso, Long> {

}
