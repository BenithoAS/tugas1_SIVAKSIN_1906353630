package apap.tugas.SIVAKSIN.repository;

import apap.tugas.SIVAKSIN.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long>{

}
