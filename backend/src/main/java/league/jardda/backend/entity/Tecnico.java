package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tecnico", schema = "jardda_league")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tecnicoId;

    private String nomeTecnico;
    private LocalDate aniversarioTecnico;

    @ManyToOne
    @JoinColumn(name = "pais_tecnico")
    private Pais paisTecnico;
}
