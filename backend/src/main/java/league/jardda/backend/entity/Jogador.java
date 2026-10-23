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
@Table(name = "jogador", schema = "jardda_league")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jogadorId;

    private String nomeJogador;

    @ManyToOne
    @JoinColumn(name = "pais_jogador")
    private Pais paisJogador;

    private LocalDate aniversarioJogador;

    private String posicao;

    private Boolean lesionado;
}
