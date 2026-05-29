package Carlos_rm558073.urban_farm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_satelite")
public class Satelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_satelite", nullable = false, length = 100)
    private String nomeSatelite;

    @Column(name = "data_captura", nullable = false)
    private LocalDate dataCaptura;

    @Column(name = "temperatura_detectada", nullable = false)
    private Double temperaturaDetectada;

    @Column(name = "nivel_poluicao", nullable = false)
    private Double nivelPoluicao;

    @OneToMany(mappedBy = "satelite")
    private List<Fazenda> fazendas = new ArrayList<>();
}
