package ing.beribtur.storejpa.aggregate.beribturing.repository;

import ing.beribtur.storejpa.aggregate.beribturing.jpo.AccountJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpo, String> {
    //
}
