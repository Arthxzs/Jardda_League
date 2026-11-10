package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clube_tecnico", schema = "jardda_league")
public class ClubeTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubeTecnicoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clube_id")
    private Clube clube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    private LocalDate inicioContratoTecnico;
    private LocalDate previsaoContratoTecnico;
    private LocalDate terminoContratoTecnico;
    private BigDecimal valorSalarioTecnico;
}
