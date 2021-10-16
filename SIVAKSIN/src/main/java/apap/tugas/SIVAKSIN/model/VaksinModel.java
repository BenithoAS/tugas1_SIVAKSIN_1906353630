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
@Table(name = "vaksin")
public class VaksinModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vaksin;

    @NotNull
    @Size(max = 255)
    @Column(name ="asal_negara", nullable = false)
    private String asal_negara;

    @NotNull
    @Column(name ="efikasi", nullable = false)
    private double efikasi;

    @NotNull
    @Size(max = 255)
    @Column(name ="jenis_vaksin", nullable = false)
    private String jenis_vaksin;

    // Relasi dengan FaskesModel
    @OneToMany(mappedBy = "vaksin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FaskesModel> listFaskes;
}
