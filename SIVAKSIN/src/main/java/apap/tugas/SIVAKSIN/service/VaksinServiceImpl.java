package apap.tugas.SIVAKSIN.service;

import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.model.PasienModel;
import apap.tugas.SIVAKSIN.model.VaksinModel;
import apap.tugas.SIVAKSIN.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VaksinServiceImpl implements VaksinService{
    @Autowired
    VaksinDb vaksinDb;

    @Override
    public List<VaksinModel> getVaksinList(){
        List<VaksinModel> listVaksin = vaksinDb.findAll();
        return listVaksin;
    }

    @Override
    public List<FaskesModel> getFaskesListByVaksin(String vaksin){
        List<VaksinModel> vaksinList = vaksinDb.findAll();

        for(int i=0; i<vaksinList.size(); i++){
            if(vaksinList.get(i).getJenis_vaksin().equals(vaksin)){
                List<FaskesModel> faskesList = vaksinList.get(i).getListFaskes();
                return faskesList;
            }
        }
        return null;
    }

    @Override
    public FaskesModel getPasienListByVaksinFaskes(String fakses, List<FaskesModel> faskesList){
        for(int j=0; j<faskesList.size(); j++){
            if(faskesList.get(j).getNama_faskes().equals(fakses)){
                FaskesModel faskes = faskesList.get(j);
                return faskes;
            }
        }
        return null;
    }
}
