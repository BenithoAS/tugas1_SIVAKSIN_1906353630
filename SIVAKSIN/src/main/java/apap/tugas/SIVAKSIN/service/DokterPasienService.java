package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.DokterPasienModel;
import apap.tugas.SIVAKSIN.model.FaskesModel;

import java.util.List;

public interface DokterPasienService {
    void addDokterPasien(DokterPasienModel dokterPasien);
    List<DokterPasienModel> getDokterPasienByFaskesList(Long id_faskes);
}
