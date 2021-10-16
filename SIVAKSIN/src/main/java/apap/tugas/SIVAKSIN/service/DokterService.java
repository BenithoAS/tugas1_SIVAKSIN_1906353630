package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.DokterModel;
import java.util.List;

public interface DokterService {
    DokterModel getDokterByIdDokter(Long id_dokter);
    List<DokterModel> getDokterList();
}
