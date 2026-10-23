package league.jardda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clube", schema = "jardda_league")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubeId;

    private String nomeClube;

    @ManyToOne
    @JoinColumn(name = "cidade_clube")
    private Cidade cidade;
}
