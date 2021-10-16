package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.DokterModel;
import apap.tugas.SIVAKSIN.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{

    @Autowired
    DokterDb dokterDb;

    @Override
    public DokterModel getDokterByIdDokter(Long id_dokter){
        Optional<DokterModel> dokter = dokterDb.findById(id_dokter);
        if (dokter.isPresent()) {
            return dokter.get();
        }
        return null;
    }

    @Override
    public List<DokterModel> getDokterList(){
        List<DokterModel> listDokter = dokterDb.findAll();
        return listDokter;
    }

}
