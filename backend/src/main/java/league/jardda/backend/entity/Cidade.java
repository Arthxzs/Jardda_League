package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cidade", schema = "jardda_league")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidadeId;

    @ManyToOne
    @JoinColumn(name = "pais_cidade")
    private Pais pais;

    private String nomeCidade;
}
