package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.model.PasienModel;

import java.util.List;

public interface FaskesService {
    List<FaskesModel> getFaskesList();
    void addFaskes(FaskesModel faskes);
    FaskesModel getFaskesByNoIdFaskes(Long id_faskes);
    void updateFaskes(FaskesModel faskes);
    void deleteFaskes(FaskesModel faskes);
    void addPasien(PasienModel pasien, Long id_faskes);
}
