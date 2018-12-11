package pe.joedayz.admin.repository;

import pe.joedayz.admin.domain.ResetRegistry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResetRegistry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResetRegistryRepository extends JpaRepository<ResetRegistry, Long> {

}
