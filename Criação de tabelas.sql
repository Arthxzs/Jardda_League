CREATE SCHEMA jardda_league;

CREATE TABLE jardda_league.pais (
  id_pais INTEGER PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  sigla CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE jardda_league.tecnico (
  id_tecnico INTEGER PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  nascimento DATE NOT NULL,
  id_pais INTEGER NOT NULL,
  CONSTRAINT fk_id_pais FOREIGN KEY (id_pais) REFERENCES jardda_league.pais(id_pais)
);

CREATE TABLE jardda_league.estadio (
  id_estadio INTEGER PRIMARY KEY,
  nome VARCHAR(80) NOT NULL UNIQUE,
  id_pais INTEGER NOT NULL,
  CONSTRAINT fk_id_pais FOREIGN KEY (id_pais) REFERENCES jardda_league.pais(id_pais)
);

CREATE TABLE jardda_league.jogador (
  id_jogador INTEGER PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  apelido VARCHAR(80),
  nascimento DATE NOT NULL,
  id_pais INTEGER NOT NULL,
  CONSTRAINT fk_id_pais FOREIGN KEY (id_pais) REFERENCES jardda_league.pais(id_pais)
);

CREATE TABLE jardda_league.liga (
  id_liga INTEGER PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  id_pais INTEGER,
  CONSTRAINT fk_id_pais FOREIGN KEY (id_pais) REFERENCES jardda_league.pais(id_pais)
);

CREATE TABLE jardda_league.clube (
  id_clube INTEGER PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  sigla CHAR(4) NOT NULL UNIQUE,
  id_liga INTEGER NOT NULL,
  id_tecnico INTEGER NOT NULL,
  id_pais INTEGER NOT NULL,
  id_estadio INTEGER,
  CONSTRAINT fk_id_pais FOREIGN KEY (id_pais) REFERENCES jardda_league.pais(id_pais),
  CONSTRAINT fk_id_liga FOREIGN KEY (id_liga) REFERENCES jardda_league.liga(id_liga),
  CONSTRAINT fk_id_tecnico FOREIGN KEY (id_tecnico) REFERENCES jardda_league.tecnico(id_tecnico),
  CONSTRAINT fk_id_estadio FOREIGN KEY (id_estadio) REFERENCES jardda_league.estadio(id_estadio)
);

CREATE TABLE jardda_league.clube_liga(
  id_clube_liga SERIAL PRIMARY KEY,
  id_liga INTEGER NOT NULL,
  id_clube INTEGER NOT NULL,
  id_pais_clube INTEGER NOT NULL,
  CONSTRAINT fk_id_liga FOREIGN KEY (id_liga) REFERENCES jardda_league.liga(id_liga),
  CONSTRAINT fk_id_clube FOREIGN KEY (id_clube) REFERENCES jardda_league.clube(id_clube),
  CONSTRAINT fk_pais_clube FOREIGN KEY (id_pais_clube) REFERENCES jardda_league.pais(id_pais)
);

CREATE TABLE jardda_league.clube_tecnico (
  id_transferencia SERIAL PRIMARY KEY,
  id_tecnico INTEGER NOT NULL,
  id_clube INTEGER NOT NULL,
  chegada DATE NOT NULL,
  saida DATE,
  CONSTRAINT fk_id_tecnico FOREIGN KEY (id_tecnico) REFERENCES jardda_league.tecnico(id_tecnico),
  CONSTRAINT fk_id_clube FOREIGN KEY (id_clube) REFERENCES jardda_league.clube(id_clube)
);

CREATE TABLE jardda_league.depto_medico (
  id_dm SERIAL PRIMARY KEY,
  grau_lesao INTEGER NOT NULL,
  entrada DATE NOT NULL,
  saida DATE,
  id_clube INTEGER NOT NULL,
  id_jogador INTEGER NOT NULL,
  CONSTRAINT fk_id_clube FOREIGN KEY (id_clube) REFERENCES jardda_league.clube(id_clube),
  CONSTRAINT fk_id_jogador FOREIGN KEY (id_jogador) REFERENCES jardda_league.jogador(id_jogador)
);

CREATE TABLE jardda_league.jogador_clube (
  id_transferencia SERIAL PRIMARY KEY,
  id_jogador INTEGER NOT NULL,
  id_clube INTEGER NOT NULL,
  posicao CHAR(4) NOT NULL,
  chegada DATE NOT NULL,
  saida DATE,
  CONSTRAINT fk_id_clube FOREIGN KEY (id_clube) REFERENCES jardda_league.clube(id_clube),
  CONSTRAINT fk_id_jogador FOREIGN KEY (id_jogador) REFERENCES jardda_league.jogador(id_jogador)
);

CREATE TABLE jardda_league.partida (
  id_partida SERIAL PRIMARY KEY,
  rodada INTEGER,
  id_liga INTEGER,
  time_casa INTEGER NOT NULL,
  time_fora INTEGER NOT NULL,
  id_estadio INTEGER NOT NULL,
  CONSTRAINT fk_id_liga FOREIGN KEY (id_liga) REFERENCES jardda_league.liga(id_liga),
  CONSTRAINT fk_time_casa FOREIGN KEY (time_casa) REFERENCES jardda_league.clube(id_clube),
  CONSTRAINT fk_time_fora FOREIGN KEY (time_fora) REFERENCES jardda_league.clube(id_clube),
  CONSTRAINT fk_id_estadio FOREIGN KEY (id_estadio) REFERENCES jardda_league.estadio(id_estadio)
);

CREATE TABLE jardda_league.estatistica_partida (
  id_partida INTEGER PRIMARY KEY, 
  gols_casa INTEGER,
  gols_fora INTEGER,
  cartoes_casa INTEGER,
  cartoes_fora INTEGER,
  CONSTRAINT fk_id_partida FOREIGN KEY (id_partida) REFERENCES jardda_league.partida(id_partida)
); 