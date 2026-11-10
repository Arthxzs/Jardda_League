package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "estatistica_partida", schema = "jardda_league")
public class EstatisticaPartida {
    @Id
    private Long partidaId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "partida_id")
    private Partida partida;

    @Column(name = "time_1_gols")
    private Integer time1Gols;

    @Column(name = "time_2_gols")
    private Integer time2Gols;

    @Column(name = "time_1_cartoes")
    private Integer time1Cartoes;

    @Column(name = "time_2_cartoes")
    private Integer time2Cartoes;

    @Column(name = "time_1_chutes")
    private Integer time1Chutes;

    @Column(name = "time_2_chutes")
    private Integer time2Chutes;
}
