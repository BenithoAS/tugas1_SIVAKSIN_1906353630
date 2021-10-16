package apap.tugas.SIVAKSIN.repository;

import apap.tugas.SIVAKSIN.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaksinDb extends JpaRepository<VaksinModel, Long>{
}
