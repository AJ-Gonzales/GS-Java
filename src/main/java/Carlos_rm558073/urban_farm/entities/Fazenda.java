package Carlos_rm558073.urban_farm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_fazenda")
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fazenda", nullable = false, length = 100)
    private String nomeFazenda;

    @Column(name = "regiao", nullable = false, length = 100)
    private String regiao;

    @Column(name = "area_cultivada", nullable = false)
    private Double areaCultivada;

    @Column(name = "ultima_irrigacao", nullable = false)
    private LocalDate ultimaIrrigacao;

    @ManyToOne
    @JoinColumn(name = "satelite_id")
    private Satelite satelite;

}
