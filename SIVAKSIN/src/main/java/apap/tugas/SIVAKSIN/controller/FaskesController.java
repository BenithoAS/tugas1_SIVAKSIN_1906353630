package apap.tugas.SIVAKSIN.controller;

import apap.tugas.SIVAKSIN.model.*;
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
import java.nio.charset.*;
import java.util.Random;

@Controller
public class FaskesController {

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("dokterPasienServiceImpl")
    @Autowired
    private DokterPasienService dokterPasienService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @GetMapping("/faskes")
    public String faskesViewAll(Model model){
        List<FaskesModel> listFaskes = faskesService.getFaskesList();

        model.addAttribute("listFaskes", listFaskes);
        return "viewall-faskes";
    }

    @GetMapping("/faskes/tambah")
    public String addFaskesForm(Model model){
        FaskesModel faskes = new FaskesModel();
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        model.addAttribute("faskes", faskes);
        model.addAttribute("listVaksin", listVaksin);
        return "form-add-faskes";
    }

    @PostMapping("/faskes/tambah")
    public String addFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ){
        faskesService.addFaskes(faskes);
        model.addAttribute("nama_faskes", faskes.getNama_faskes());
        return "add-faskes";
    }

    @GetMapping("/faskes/{idFaskes}")
    public String faskesViewDetail(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByNoIdFaskes(idFaskes);
        List<PasienModel> listPasien = faskes.getListPasien();

        model.addAttribute("faskes", faskes);
        model.addAttribute("listPasien", listPasien);
        return "view-faskes";
    }

    @DateTimeFormat(pattern = "HH:mm")
    @GetMapping("/faskes/ubah/{idFaskes}")
    public String faskesUpdateForm(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByNoIdFaskes(idFaskes);
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        LocalTime now = LocalTime.now();

        if(now.isAfter(faskes.getTutup()) || now.isBefore(faskes.getMulai())){
            int lsp;
            if(faskes.getListPasien().size() == 0){
                lsp = 0;
            }
            else {
                lsp = 1;
            }
            model.addAttribute("faskes", faskes);
            model.addAttribute("listVaksin", listVaksin);
            model.addAttribute("lsp", lsp);
            return "form-update-faskes";
        }



        model.addAttribute("nama_faskes", faskes.getNama_faskes());
        model.addAttribute("ms", "gagal");
        return "update-faskes";
    }

    @PostMapping("/faskes/ubah")
    public String faskesUpdateSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ){
        faskesService.updateFaskes(faskes);
        model.addAttribute("nama_faskes", faskes.getNama_faskes());
        model.addAttribute("ms", "berhasil");
        return "update-faskes";
    }

    @DateTimeFormat(pattern = "HH:mm")
    @GetMapping("/faskes/hapus/{idFaskes}")
    public String faksesDelete(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByNoIdFaskes(idFaskes);
        LocalTime now = LocalTime.now();

        if(faskes.getListPasien().size() == 0 && (now.isAfter(faskes.getTutup()) || now.isBefore(faskes.getMulai()))){
            faskesService.deleteFaskes(faskes);
            model.addAttribute("nama_faskes", faskes.getNama_faskes());
            model.addAttribute("ms", "berhasil");
            return "delete-faskes";
        }
        model.addAttribute("nama_faskes", faskes.getNama_faskes());
        model.addAttribute("ms", "gagal");
        return "delete-faskes";
    }

    @DateTimeFormat(pattern = "HH:mm")
    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addFaskesPasienForm(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByNoIdFaskes(idFaskes);
        List<DokterModel> dokterList = dokterService.getDokterList();
        List<PasienModel> pasienList = pasienService.getPasienList();
        LocalTime now = LocalTime.now();

        if(now.isBefore(faskes.getTutup()) && now.isAfter(faskes.getMulai())){
            DokterPasienModel dokterPasien = new DokterPasienModel();
            model.addAttribute("id_faskes", idFaskes);
            model.addAttribute("dokterPasien", dokterPasien);
            model.addAttribute("dokterList", dokterList);
            model.addAttribute("pasienList", pasienList);
            return "form-add-dokterPasien";
        }

        model.addAttribute("ms", "gagal");
        return "add-dokterPasien";
    }

    @PostMapping("/faskes/tambah/pasien")
    public String addFaskesPasienSubmit(
            @ModelAttribute DokterPasienModel dokterPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByNoIdPasien(dokterPasien.getPasien().getId_pasien());

        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        int n = 2;

        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if ((ch >= 'A' && ch <= 'Z') && (n > 0)) {
                r.append(ch);
                n--;
            }
        }

        String char1 = String.valueOf(pasien.getJenis_kelamin());
        String char2 = pasien.getNama_pasien().substring(pasien.getNama_pasien().length()-1);
        String char34 = pasien.getTempat_lahir().substring(0,2);
        String char56;
        String char78;
        if(pasien.getTanggalLahir().getMonthValue() < 10){
            char56 = "0" + String.valueOf(pasien.getTanggalLahir().getDayOfMonth());
        }
        else {
            char56 = String.valueOf(pasien.getTanggalLahir().getDayOfMonth());
        }
        if(pasien.getTanggalLahir().getMonthValue() < 10){
            char78 = "0" + String.valueOf(pasien.getTanggalLahir().getMonthValue());
        }
        else {
            char78 = String.valueOf(pasien.getTanggalLahir().getMonthValue());
        }
        String char91011 = String.valueOf(pasien.getTanggalLahir().getYear() / 10);
        String char1213 = r.toString();

        dokterPasien.setBatch_id((char1+char2+char34+char56+char78+char91011+char1213).toUpperCase());
        dokterPasienService.addDokterPasien(dokterPasien);
        faskesService.addPasien(pasien, dokterPasien.getId_faskes());
        model.addAttribute("ms", "berhasil");
        return "add-dokterPasien";
    }

    @GetMapping("/cari/faskesvaksin")
    public String searchFaskesByVaksin(Model model){
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        model.addAttribute("listVaksin", listVaksin);
        return "search-fakses-vaksin";
    }

    @GetMapping("/cari/faskes")
    public String searchFaskesByVaksin(
            @RequestParam(value = "jenisVaksin") String jenisVaksin,
            Model model
    ){
        List<FaskesModel> listFaskes = vaksinService.getFaskesListByVaksin(jenisVaksin);
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);
        return "search-fakses-vaksin";
    }
}
