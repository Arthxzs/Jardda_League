CREATE SCHEMA jardda_league;
 
CREATE TABLE jardda_league.pais(
	pais_id SERIAL PRIMARY KEY,
	nome_pais varchar(50) NOT NULL UNIQUE,
	sigla CHAR(3) NOT NULL UNIQUE  
);
 
CREATE TABLE jardda_league.cidade(
	cidade_id SERIAL PRIMARY KEY,
	pais_cidade INTEGER NOT NULL,
	nome_cidade varchar(50) NOT NULL,
	UNIQUE(nome_cidade, pais_cidade),
	CONSTRAINT fk_pais_cidade FOREIGN KEY (pais_cidade) REFERENCES jardda_league.pais(pais_id)
);
 
CREATE TABLE jardda_league.clube(
	clube_id SERIAL PRIMARY KEY,
	nome_clube TEXT NOT NULL UNIQUE,
	cidade_clube INTEGER NOT NULL,
	CONSTRAINT fk_cidade_clube FOREIGN KEY (cidade_clube) REFERENCES jardda_league.cidade(cidade_id)
);
 
CREATE TABLE jardda_league.liga(
	liga_id SERIAL PRIMARY KEY,
	nome_liga TEXT NOT NULL UNIQUE,
	pais_liga INTEGER,
	categoria_liga VARCHAR(20) NOT NULL CHECK(categoria_liga IN ('mata_mata', 'pontos_corridos', 'amistoso', 'tira_teima', 'misto', 'grupos')),
	CONSTRAINT fk_pais_liga FOREIGN KEY (pais_liga) REFERENCES jardda_league.pais(pais_id)
);
 
CREATE TABLE jardda_league.estadio(
	estadio_id SERIAL PRIMARY KEY,
	nome_estadio TEXT NOT NULL UNIQUE,
	cidade_estadio INTEGER NOT NULL,
	clube_id INTEGER,
	CONSTRAINT fk_clube_id FOREIGN KEY (clube_id) REFERENCES jardda_league.clube(clube_id),
	CONSTRAINT fk_cidade_estadio FOREIGN KEY (cidade_estadio) REFERENCES jardda_league.cidade(cidade_id)
);
 
CREATE TABLE jardda_league.tecnico(
  	tecnico_id SERIAL PRIMARY KEY,
  	nome_tecnico TEXT NOT NULL,
  	aniversario_tecnico DATE NOT NULL CHECK(aniversario_tecnico >= '1900-01-01' AND aniversario_tecnico <= CURRENT_DATE),
  	pais_tecnico INTEGER NOT NULL,
  	CONSTRAINT fk_pais_tecnico FOREIGN KEY (pais_tecnico) REFERENCES jardda_league.pais(pais_id)
);
 
CREATE TABLE jardda_league.jogador(
	jogador_id SERIAL PRIMARY KEY,
	nome_jogador TEXT NOT NULL,
	pais_jogador INTEGER NOT NULL,
	aniversario_jogador DATE NOT NULL CHECK(aniversario_jogador >= '1900-01-01' AND aniversario_jogador <= CURRENT_DATE),
	posicao CHAR(3) NOT NULL CHECK(posicao IN ('ZAG', 'GOL', 'ATC', 'MEI', 'LAT', 'VOL')),
	lesionado BOOLEAN DEFAULT FALSE,
	CONSTRAINT fk_pais_jogador FOREIGN KEY (pais_jogador) REFERENCES jardda_league.pais(pais_id)
);
 
CREATE TABLE jardda_league.clube_liga(
	clube_liga_id SERIAL PRIMARY KEY,
	clube_id INTEGER NOT NULL,
	liga_id INTEGER NOT NULL,
	periodo_entrada DATE NOT NULL CHECK(periodo_entrada >= '1900-01-01' AND periodo_entrada <= CURRENT_DATE), -- pode mudar
	periodo_saida DATE CHECK(periodo_saida > periodo_entrada AND periodo_saida <= CURRENT_DATE), -- pode mudar
	CONSTRAINT fk_liga_id FOREIGN KEY (liga_id) REFERENCES jardda_league.liga(liga_id),
	CONSTRAINT fk_clube_id FOREIGN KEY (clube_id) REFERENCES jardda_league.clube(clube_id)
);

CREATE TABLE jardda_league.clube_jogador(
	clube_jogador_id SERIAL PRIMARY KEY,
	clube_id INTEGER NOT NULL,
	jogador_id INTEGER NOT NULL,
	inicio_contrato_jogador DATE NOT NULL CHECK(inicio_contrato_jogador >= '1900-01-01' AND inicio_contrato_jogador <= CURRENT_DATE),
	previsao_contrato_jogador DATE NOT NULL CHECK(previsao_contrato_jogador <= inicio_contrato_jogador + INTERVAL '10 YEARS'),
	termino_contrato_jogador DATE CHECK(termino_contrato_jogador > inicio_contrato_jogador AND termino_contrato_jogador <= CURRENT_DATE),
	valor_salario_jogador NUMERIC(8, 2) NOT NULL, -- valor anual em dólares $
	CONSTRAINT fk_clube_id FOREIGN KEY (clube_id) REFERENCES jardda_league.clube(clube_id),
	CONSTRAINT fk_jogador_id FOREIGN KEY (jogador_id) REFERENCES jardda_league.jogador(jogador_id)
);
 
CREATE TABLE jardda_league.clube_tecnico(
	clube_tecnico_id SERIAL PRIMARY KEY,
	clube_id INTEGER NOT NULL,
	tecnico_id INTEGER NOT NULL,
	inicio_contrato_tecnico DATE NOT NULL CHECK(inicio_contrato_tecnico >= '1900-01-01' AND inicio_contrato_tecnico <= CURRENT_DATE),
	previsao_contrato_tecnico DATE NOT NULL CHECK(previsao_contrato_tecnico <= inicio_contrato_tecnico + INTERVAL '10 YEARS'),
	termino_contrato_tecnico DATE CHECK(termino_contrato_tecnico > inicio_contrato_tecnico AND termino_contrato_tecnico <= CURRENT_DATE),
	valor_salario_tecnico NUMERIC(8, 2) NOT NULL, -- valor anual em dólares $
	CONSTRAINT fk_clube_id FOREIGN KEY (clube_id) REFERENCES jardda_league.clube(clube_id),
	CONSTRAINT fk_tecnico_id FOREIGN KEY (tecnico_id) REFERENCES jardda_league.tecnico(tecnico_id)
);
 
CREATE TABLE jardda_league.partida(
	partida_id SERIAL PRIMARY KEY,
	time_1_id INTEGER NOT NULL,
	time_2_id INTEGER NOT NULL CHECK(time_2_id <> time_1_id),
	liga_partida INTEGER NOT NULL,
	estadio_id INTEGER, -- pode ser null se o estádio não for cadastrado
	data_partida DATE NOT NULL CHECK(data_partida >= '1900-01-01' AND data_partida <= CURRENT_DATE),
	publico_partida INTEGER NOT NULL CHECK(publico_partida >= 0),
	rodada_partida INTEGER, -- pode ser null se for amistoso ou tira-teima
	CONSTRAINT fk_time_1_id FOREIGN KEY (time_1_id) REFERENCES jardda_league.clube(clube_id),
	CONSTRAINT fk_time_2_id FOREIGN KEY (time_2_id) REFERENCES jardda_league.clube(clube_id),
	CONSTRAINT fk_liga_partida FOREIGN KEY (liga_partida) REFERENCES jardda_league.liga(liga_id),
	CONSTRAINT fk_estadio_id FOREIGN KEY (estadio_id) REFERENCES jardda_league.estadio(estadio_id)
);
 
CREATE TABLE jardda_league.estatistica_partida(
	partida_id INTEGER PRIMARY KEY,
	time_1_gols INTEGER NOT NULL CHECK(time_1_gols >= 0),
	time_2_gols INTEGER NOT NULL CHECK(time_2_gols >= 0),
	time_1_cartoes INTEGER NOT NULL CHECK(time_1_cartoes >= 0),
	time_2_cartoes INTEGER NOT NULL CHECK(time_2_cartoes >= 0),
	time_1_chutes INTEGER NOT NULL CHECK(time_1_chutes >= 0),
	time_2_chutes INTEGER NOT NULL CHECK(time_2_chutes >= 0),
	CONSTRAINT fk_partida_id FOREIGN KEY (partida_id) REFERENCES jardda_league.partida(partida_id)	
);
 
CREATE UNIQUE INDEX jogador_contrato_ativo ON jardda_league.clube_jogador(jogador_id)
WHERE termino_contrato_jogador is null;
 
CREATE UNIQUE INDEX tecnico_contrato_ativo ON jardda_league.clube_tecnico(tecnico_id)
WHERE termino_contrato_tecnico is null;
 