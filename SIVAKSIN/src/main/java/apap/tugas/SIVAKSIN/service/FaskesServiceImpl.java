package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.model.PasienModel;
import apap.tugas.SIVAKSIN.repository.FaskesDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService{

    @Autowired
    FaskesDb faskesDb;

    @Override
    public List<FaskesModel> getFaskesList(){
        List<FaskesModel> listFaskes = faskesDb.findAll();
        return listFaskes;
    }

    @Override
    public void addFaskes(FaskesModel faskes){
        faskesDb.save(faskes);
    }

    @Override
    public FaskesModel getFaskesByNoIdFaskes(Long id_faskes){
        Optional<FaskesModel> faskes = faskesDb.findById(id_faskes);
        if (faskes.isPresent()) {
            return faskes.get();
        }
        return null;
    }

    @Override
    public void updateFaskes(FaskesModel faskes){
        faskesDb.save(faskes);
    }

    @Override
    public void deleteFaskes(FaskesModel faskes){
        faskesDb.delete(faskes);
    }

    @Override
    public void addPasien(PasienModel pasien, Long id_faskes){
        FaskesModel faskes = faskesDb.getById(id_faskes);
        if(!faskes.getListPasien().contains(pasien)){
            faskes.getListPasien().add(pasien);
            pasien.getListFaskes().add(faskes);
        }
    }

}
