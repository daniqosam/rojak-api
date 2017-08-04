package id.rojak.auth.domain.model.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by inagi on 8/1/17.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);
}