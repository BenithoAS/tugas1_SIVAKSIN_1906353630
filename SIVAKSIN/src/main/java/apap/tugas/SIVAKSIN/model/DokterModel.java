package apap.tugas.SIVAKSIN.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "dokter")
public class DokterModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dokter;

    @NotNull
    @Size(max = 255)
    @Column(name ="nama_dokter", nullable = false)
    private String nama_dokter;

    @NotNull
    @Size(max = 18)
    @Column(name ="nip", nullable = false)
    private String nip;

    @NotNull
    @Size(max = 13)
    @Column(name ="nomor_telepon", nullable = false)
    private String nomor_telepon;

    // Relasi dengan DokterPasienModel
    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterPasienModel> listDokterPasien;
}
