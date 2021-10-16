package apap.tugas.SIVAKSIN.repository;

import apap.tugas.SIVAKSIN.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaskesDb extends JpaRepository<FaskesModel, Long>{
}
