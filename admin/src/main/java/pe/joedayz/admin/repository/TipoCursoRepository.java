package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.TipoCurso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoCursoRepository extends JpaRepository<TipoCurso, Long> {

}
