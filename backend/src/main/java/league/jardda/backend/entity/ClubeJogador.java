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
@Table(name = "clube_jogador", schema = "jardda_league")
public class ClubeJogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubeJogadorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clube_id")
    private Clube clube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    private LocalDate inicioContratoJogador;
    private LocalDate previsaoContratoJogador;
    private LocalDate terminoContratoJogador;
    private BigDecimal valorSalarioJogador;
}
