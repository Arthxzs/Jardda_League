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
@Table(name = "partida", schema = "jardda_league")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partidaId;

    @ManyToOne
    @JoinColumn(name = "time_1_id")
    private Clube clube1;

    @ManyToOne
    @JoinColumn(name = "time_2_id")
    private Clube clube2;

    @ManyToOne
    @JoinColumn(name = "liga_partida")
    private Liga ligaPartida;

    @ManyToOne
    @JoinColumn(name = "estadio_id")
    private Estadio estadioPartida;

    private LocalDate dataPartida;
    private Integer publicoPartida;
    private Integer rodadaPartida;
}
