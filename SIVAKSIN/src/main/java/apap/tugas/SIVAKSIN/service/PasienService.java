package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.PasienModel;
import java.util.List;

public interface PasienService {
    List<PasienModel> getPasienList();
    void addPasien(PasienModel pasien);
    PasienModel getPasienByNoIdPasien(Long id_pasien);
    void updatePasien(PasienModel pasien);
}
