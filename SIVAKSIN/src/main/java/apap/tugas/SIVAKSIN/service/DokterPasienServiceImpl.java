package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.DokterPasienModel;
import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.repository.DokterPasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterPasienServiceImpl implements DokterPasienService{

    @Autowired
    DokterPasienDb dokterPasienDb;

    @Override
    public void addDokterPasien(DokterPasienModel dokterPasien){
        dokterPasienDb.save(dokterPasien);
    }

    @Override
    public List<DokterPasienModel> getDokterPasienByFaskesList(Long id_faskes){
        List<DokterPasienModel> listDokterPasien = dokterPasienDb.findAll();
        List<DokterPasienModel> listHasil = new ArrayList<>();
        for(int i=0; i<listDokterPasien.size(); i++){
            if(listDokterPasien.get(i).getId_faskes().equals(id_faskes)){
                listHasil.add(listDokterPasien.get(i));
            }
        }
        return listHasil;
    }

}
