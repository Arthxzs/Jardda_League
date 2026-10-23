package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "liga", schema = "jardda_league")
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ligaId;

    private String nomeLiga;

    @ManyToOne
    @JoinColumn(name = "pais_liga")
    private Pais paisLiga;

    private String categoriaLiga;
}
