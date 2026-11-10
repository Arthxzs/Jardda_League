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
@Table(name = "clube_liga", schema = "jardda_league")
public class ClubeLiga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubeLigaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clube_id")
    private Clube clube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liga_id")
    private Liga liga;

    private LocalDate periodoEntrada;
    private LocalDate periodoSaida;
}
