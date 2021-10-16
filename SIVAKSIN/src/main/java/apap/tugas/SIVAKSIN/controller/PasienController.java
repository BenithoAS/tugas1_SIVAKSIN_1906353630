package apap.tugas.SIVAKSIN.controller;

import apap.tugas.SIVAKSIN.model.DokterPasienModel;
import apap.tugas.SIVAKSIN.model.FaskesModel;
import apap.tugas.SIVAKSIN.model.PasienModel;
import apap.tugas.SIVAKSIN.model.VaksinModel;
import apap.tugas.SIVAKSIN.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @Qualifier("dokterPasienServiceImpl")
    @Autowired
    private DokterPasienService dokterPasienService;

    @GetMapping("/pasien")
    public String pasienViewAll(Model model){
        List<PasienModel> listPasien = pasienService.getPasienList();

        model.addAttribute("listPasien", listPasien);
        return "viewall-pasien";
    }

    @GetMapping("/pasien/tambah")
    public String addPasienForm(Model model){
        PasienModel pasien = new PasienModel();

        model.addAttribute("pasien", pasien);
        return "form-add-pasien";
    }

    @PostMapping("/pasien/tambah")
    public String addPasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ){
        pasienService.addPasien(pasien);
        model.addAttribute("nama_pasien", pasien.getNama_pasien());
        return "add-pasien";
    }

    @GetMapping("/pasien/{idPasien}")
    public String pasienViewDetail(
            @PathVariable Long idPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByNoIdPasien(idPasien);
        List<DokterPasienModel> listDokterPasien = pasien.getListDokterPasien();

        model.addAttribute("pasien", pasien);
        model.addAttribute("listDokterPasien", listDokterPasien);
        return "view-pasien";
    }

    @GetMapping("/pasien/ubah/{id_Pasien}")
    public String pasienUpdateForm(
            @PathVariable Long id_Pasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByNoIdPasien(id_Pasien);

        model.addAttribute("pasien", pasien);
        return "form-update-pasien";
    }

    @PostMapping("/pasien/ubah")
    public String pasienUpdateSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ){
        pasienService.updatePasien(pasien);
        model.addAttribute("nama_pasien", pasien.getNama_pasien());
        return "update-pasien";
    }

    @GetMapping("/cari/pasienvaksin")
    public String searchPasienByVaskinFaskesShow(Model model){
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        List<FaskesModel> listFaskes = faskesService.getFaskesList();

        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);
        return "search-pasien-vaskinfaskes";
    }

    @GetMapping("/cari/pasien")
    public String searchPasienByVaskinFaskesShow(
            @RequestParam(value = "jenisVaksin") String jenisVaksin,
            @RequestParam(value = "namaFaskes") String namaFaskes,
            Model model
    ){
        List<FaskesModel> listFaskesVakin = vaksinService.getFaskesListByVaksin(jenisVaksin);
        FaskesModel faskes = vaksinService.getPasienListByVaksinFaskes(namaFaskes, listFaskesVakin);
        List<DokterPasienModel> listDokterPasien = dokterPasienService.getDokterPasienByFaskesList(faskes.getId_faskes());
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        List<FaskesModel> listFaskes = faskesService.getFaskesList();

        model.addAttribute("listDokterPasien",listDokterPasien);
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);
        return "search-pasien-vaskinfaskes";
    }
}
