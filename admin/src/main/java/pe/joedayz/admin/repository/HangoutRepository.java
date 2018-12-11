package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.Hangout;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Hangout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HangoutRepository extends JpaRepository<Hangout, Long> {

}
