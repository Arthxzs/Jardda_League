package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "estadio", schema = "jardda_league")
public class Estadio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadioId;

    private String nomeEstadio;

    @ManyToOne
    @JoinColumn(name = "cidade_estadio")
    private Cidade cidadeEstadio;

    @ManyToOne
    @JoinColumn(name = "clube_id")
    private Clube clubeId;
}
