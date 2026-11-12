# Minimundo — Jardda League

O **Jardda League** é um sistema destinado ao gerenciamento e à consulta de informações relacionadas ao futebol, abrangendo clubes, jogadores, treinadores, ligas, estádios e partidas.

O sistema permite cadastrar países e suas respectivas cidades, nas quais estão localizados os clubes e estádios. Cada clube pode participar de diferentes ligas ao longo do tempo, possuir jogadores em seu elenco e manter contratos com treinadores. Essas relações possuem informações sobre períodos de vínculo, previsão de encerramento e valores salariais, permitindo acompanhar o histórico profissional dos envolvidos.

As partidas são registradas com informações sobre os clubes mandante e visitante, a liga, o estádio, a data, a rodada e o público presente. Cada partida também possui um conjunto de estatísticas, incluindo gols, cartões e finalizações de ambas as equipes.

O projeto utiliza o **PostgreSQL** como banco de dados principal, com uma estrutura relacional voltada à integridade e à consistência das informações. Também apresenta uma modelagem alternativa utilizando **MongoDB**, explorando as possibilidades de armazenamento documental.

Além disso, o sistema conta com uma API desenvolvida em Java e Spring Boot, responsável por disponibilizar consultas sobre os dados armazenados.

O objetivo do Jardda League é centralizar informações do universo futebolístico, permitindo consultas organizadas e relacionamentos entre diferentes entidades do domínio.
