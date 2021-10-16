package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.PasienModel;
import apap.tugas.SIVAKSIN.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{

    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> getPasienList(){
        List<PasienModel> listPasien = pasienDb.findAll();
        return listPasien;
    }

    @Override
    public void addPasien(PasienModel pasien){
        pasienDb.save(pasien);
    }

    @Override
    public PasienModel getPasienByNoIdPasien(Long id_pasien){
        Optional<PasienModel> pasien = pasienDb.findById(id_pasien);
        if (pasien.isPresent()) {
            return pasien.get();
        }
        return null;
    }

    @Override
    public void updatePasien(PasienModel pasien){
        pasienDb.save(pasien);
    }

}
