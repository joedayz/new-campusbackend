package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.GeneralTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GeneralTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralTableRepository extends JpaRepository<GeneralTable, Long> {

}
