package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.model.PasienModel;
import apap.tugas.SIVAKSIN.model.VaksinModel;
import java.util.List;

public interface VaksinService {
    List<VaksinModel> getVaksinList();
    List<FaskesModel> getFaskesListByVaksin(String vaksin);
    FaskesModel getPasienListByVaksinFaskes(String fakses, List<FaskesModel> faskesList);
}
