package apap.tugas.SIVAKSIN.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pasien;

    @NotNull
    @Size(max = 255)
    @Column(name ="nama_pasien", nullable = false)
    private String nama_pasien;

    @NotNull
    @Size(max = 255)
    @Column(name ="tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Size(max = 13)
    @Column(name ="nomor_telepon", nullable = false)
    private String nomor_telepon;

    @NotNull
    @Column(name ="jenis_kelamin", nullable = false)
    private int jenis_kelamin;

    @NotNull
    @Size(max = 255)
    @Column(name ="pekerjaan", nullable = false)
    private String pekerjaan;

    @NotNull
    @Size(max = 16)
    @Column(name ="nik", nullable = false)
    private String nik;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime tanggalLahir;

    // Relasi dengan FaskesModel
    @ManyToMany(mappedBy = "listPasien")
    List<FaskesModel> listFaskes;

    // Relasi dengan DisuntikModel
    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterPasienModel> listDokterPasien;
}
