-- Dados de exemplo para o schema jardda_league criados através do ChatGPT

BEGIN;

INSERT INTO jardda_league.pais (nome_pais, sigla) VALUES
('Brasil', 'BRA'),
('Inglaterra', 'ENG'),
('Espanha', 'ESP'),
('Itália', 'ITA');

INSERT INTO jardda_league.cidade (pais_cidade, nome_cidade) VALUES
(1, 'Rio de Janeiro'),
(1, 'São Paulo'),
(2, 'Londres'),
(2, 'Liverpool'),
(2, 'Manchester'),
(3, 'Madri'),
(3, 'Barcelona'),
(4, 'Turim'),
(4, 'Milão');

INSERT INTO jardda_league.clube (nome_clube, cidade_clube) VALUES
('Flamengo', 1),
('Palmeiras', 2),
('Corinthians', 2),
('Arsenal', 3),
('Liverpool', 4),
('Manchester City', 5),
('Real Madrid', 6),
('FC Barcelona', 7),
('Atlético de Madrid', 6),
('Juventus', 8),
('Inter de Milão', 9),
('AC Milan', 9);

INSERT INTO jardda_league.liga (nome_liga, pais_liga, categoria_liga, estilo_liga) VALUES
('Campeonato Brasileiro Série A', 1, 'nacional', 'pontos_corridos'),
('Premier League', 2, 'nacional', 'pontos_corridos'),
('LaLiga', 3, 'nacional', 'pontos_corridos'),
('Serie A', 4, 'nacional', 'pontos_corridos');

INSERT INTO jardda_league.estadio (nome_estadio, cidade_estadio, clube_id) VALUES
('Maracanã', 1, 1),
('Allianz Parque', 2, 2),
('Neo Química Arena', 2, 3),
('Emirates Stadium', 3, 4),
('Anfield', 4, 5),
('Etihad Stadium', 5, 6),
('Santiago Bernabéu', 6, 7),
('Estadi Olímpic Lluís Companys', 7, 8),
('Cívitas Metropolitano', 6, 9),
('Allianz Stadium', 8, 10),
('San Siro', 9, 11);

-- Treinadores fictícios; os países de nacionalidade são reais.
INSERT INTO jardda_league.tecnico (nome_tecnico, aniversario_tecnico, pais_tecnico) VALUES
('Rafael Monteiro', '1972-03-14', 1),
('Eduardo Vasconcelos', '1975-08-22', 1),
('Bruno Siqueira', '1978-01-09', 1),
('Thomas Whitaker', '1970-06-18', 2),
('Oliver Bennett', '1974-11-03', 2),
('Daniel Foster', '1977-04-27', 2),
('Álvaro Serrano', '1971-02-11', 3),
('Sergio Valdés', '1976-09-16', 3),
('Marcos Ibáñez', '1979-12-05', 3),
('Lorenzo Moretti', '1969-05-20', 4),
('Matteo Ricci', '1973-07-13', 4),
('Paolo Conti', '1978-10-29', 4);

-- Jogadores fictícios. As posições respeitam o domínio definido no CHECK.
INSERT INTO jardda_league.jogador
(nome_jogador, pais_jogador, aniversario_jogador, posicao, lesionado) VALUES
('Lucas Andrade', 1, '1998-02-10', 'GOL', FALSE),
('Pedro Nascimento', 1, '1997-06-21', 'ZAG', FALSE),
('Caio Menezes', 1, '2000-09-14', 'MEI', FALSE),
('Gabriel Farias', 1, '1999-12-02', 'ATC', FALSE),
('Matheus Ribeiro', 1, '1996-04-18', 'LAT', FALSE),
('André Carvalho', 1, '2001-01-30', 'VOL', FALSE),
('Rafael Costa', 1, '1997-03-11', 'GOL', FALSE),
('Henrique Barros', 1, '1998-07-19', 'ZAG', FALSE),
('João Vitor Lima', 1, '2000-05-23', 'MEI', FALSE),
('Bruno Tavares', 1, '1999-10-08', 'ATC', FALSE),
('Felipe Duarte', 1, '1996-12-17', 'LAT', FALSE),
('Thiago Moreira', 1, '2001-08-25', 'VOL', FALSE),
('Thomas Clarke', 2, '1997-01-16', 'GOL', FALSE),
('Jack Harrison', 2, '1998-04-09', 'ZAG', FALSE),
('Oliver James', 2, '2000-11-28', 'MEI', FALSE),
('Harry Collins', 2, '1999-02-13', 'ATC', FALSE),
('George Wilson', 2, '1996-09-07', 'LAT', FALSE),
('William Turner', 2, '2001-06-04', 'VOL', FALSE),
('James Walker', 2, '1997-08-15', 'GOL', FALSE),
('Charlie Evans', 2, '1998-12-22', 'ZAG', FALSE),
('Henry Scott', 2, '2000-03-30', 'MEI', FALSE),
('Oscar Green', 2, '1999-07-12', 'ATC', FALSE),
('Arthur Hill', 2, '1996-10-26', 'LAT', FALSE),
('Freddie Adams', 2, '2001-02-05', 'VOL', FALSE),
('Diego Navarro', 3, '1997-05-17', 'GOL', FALSE),
('Pablo Torres', 3, '1998-09-03', 'ZAG', FALSE),
('Sergio Molina', 3, '2000-01-24', 'MEI', FALSE),
('Javier Ruiz', 3, '1999-06-16', 'ATC', FALSE),
('Adrián Castro', 3, '1996-11-09', 'LAT', FALSE),
('Marcos León', 3, '2001-04-01', 'VOL', FALSE),
('Alessandro Greco', 4, '1997-02-19', 'GOL', FALSE),
('Luca Romano', 4, '1998-05-06', 'ZAG', FALSE),
('Marco Bianchi', 4, '2000-08-12', 'MEI', FALSE),
('Federico Gallo', 4, '1999-11-21', 'ATC', FALSE),
('Davide Costa', 4, '1996-03-08', 'LAT', TRUE),
('Stefano Rinaldi', 4, '2001-07-27', 'VOL', FALSE);

-- Cada clube participa de sua liga nacional.
INSERT INTO jardda_league.clube_liga (clube_id, liga_id, periodo_entrada) VALUES
(1, 1, '2020-01-01'), (2, 1, '2020-01-01'), (3, 1, '2020-01-01'),
(4, 2, '2020-01-01'), (5, 2, '2020-01-01'), (6, 2, '2020-01-01'),
(7, 3, '2020-01-01'), (8, 3, '2020-01-01'), (9, 3, '2020-01-01'),
(10, 4, '2020-01-01'), (11, 4, '2020-01-01'), (12, 4, '2020-01-01');

-- Três jogadores fictícios por clube, todos com contrato ativo.
INSERT INTO jardda_league.clube_jogador
(clube_id, jogador_id, inicio_contrato_jogador, previsao_contrato_jogador,
 termino_contrato_jogador, valor_salario_jogador) VALUES
(1, 1, '2024-01-01', '2027-12-31', NULL, 1800000.00),
(1, 2, '2023-07-01', '2027-06-30', NULL, 2200000.00),
(1, 3, '2025-01-01', '2028-12-31', NULL, 1500000.00),
(2, 4, '2024-01-01', '2027-12-31', NULL, 2000000.00),
(2, 5, '2023-07-01', '2027-06-30', NULL, 1700000.00),
(2, 6, '2025-01-01', '2028-12-31', NULL, 1300000.00),
(3, 7, '2024-01-01', '2027-12-31', NULL, 900000.00),
(3, 8, '2023-07-01', '2027-06-30', NULL, 1100000.00),
(3, 9, '2025-01-01', '2028-12-31', NULL, 850000.00),
(4, 10, '2024-07-01', '2028-06-30', NULL, 3500000.00),
(4, 11, '2023-07-01', '2027-06-30', NULL, 2800000.00),
(4, 12, '2025-01-01', '2028-12-31', NULL, 2400000.00),
(5, 13, '2024-07-01', '2028-06-30', NULL, 4000000.00),
(5, 14, '2023-07-01', '2027-06-30', NULL, 3200000.00),
(5, 15, '2025-01-01', '2028-12-31', NULL, 2600000.00),
(6, 16, '2024-07-01', '2028-06-30', NULL, 4500000.00),
(6, 17, '2023-07-01', '2027-06-30', NULL, 3300000.00),
(6, 18, '2025-01-01', '2028-12-31', NULL, 2900000.00),
(7, 19, '2024-07-01', '2028-06-30', NULL, 4200000.00),
(7, 20, '2023-07-01', '2027-06-30', NULL, 3700000.00),
(7, 21, '2025-01-01', '2028-12-31', NULL, 3000000.00),
(8, 22, '2024-07-01', '2028-06-30', NULL, 4100000.00),
(8, 23, '2023-07-01', '2027-06-30', NULL, 3600000.00),
(8, 24, '2025-01-01', '2028-12-31', NULL, 2800000.00),
(9, 25, '2024-07-01', '2028-06-30', NULL, 2700000.00),
(9, 26, '2023-07-01', '2027-06-30', NULL, 2300000.00),
(9, 27, '2025-01-01', '2028-12-31', NULL, 1900000.00),
(10, 28, '2024-07-01', '2028-06-30', NULL, 3900000.00),
(10, 29, '2023-07-01', '2027-06-30', NULL, 3100000.00),
(10, 30, '2025-01-01', '2028-12-31', NULL, 2500000.00),
(11, 31, '2024-07-01', '2028-06-30', NULL, 3800000.00),
(11, 32, '2023-07-01', '2027-06-30', NULL, 3000000.00),
(11, 33, '2025-01-01', '2028-12-31', NULL, 2400000.00),
(12, 34, '2024-07-01', '2028-06-30', NULL, 3600000.00),
(12, 35, '2023-07-01', '2027-06-30', NULL, 2900000.00),
(12, 36, '2025-01-01', '2028-12-31', NULL, 2200000.00);

-- Um treinador por clube, com vínculo ativo.
INSERT INTO jardda_league.clube_tecnico
(clube_id, tecnico_id, inicio_contrato_tecnico, previsao_contrato_tecnico,
 termino_contrato_tecnico, valor_salario_tecnico) VALUES
(1, 1, '2024-01-01', '2027-12-31', NULL, 2400000.00),
(2, 2, '2024-01-01', '2027-12-31', NULL, 2200000.00),
(3, 3, '2025-01-01', '2028-12-31', NULL, 1500000.00),
(4, 4, '2024-07-01', '2028-06-30', NULL, 5000000.00),
(5, 5, '2024-07-01', '2028-06-30', NULL, 5200000.00),
(6, 6, '2025-01-01', '2028-12-31', NULL, 5500000.00),
(7, 7, '2024-07-01', '2028-06-30', NULL, 5300000.00),
(8, 8, '2024-07-01', '2028-06-30', NULL, 5100000.00),
(9, 9, '2025-01-01', '2028-12-31', NULL, 4000000.00),
(10, 10, '2024-07-01', '2028-06-30', NULL, 4300000.00),
(11, 11, '2024-07-01', '2028-06-30', NULL, 4200000.00),
(12, 12, '2025-01-01', '2028-12-31', NULL, 3900000.00);

-- Partidas fictícias, mas disputadas entre clubes reais e em estádios reais.
INSERT INTO jardda_league.partida
(time_1_id, time_2_id, liga_partida, estadio_id, data_partida, publico_partida, rodada_partida) VALUES
(1, 2, 1, 1, '2026-02-14', 62500, 1),
(2, 3, 1, 2, '2026-03-08', 41000, 2),
(3, 1, 1, 3, '2026-04-19', 44500, 3),
(4, 5, 2, 4, '2026-02-15', 60200, 1),
(5, 6, 2, 5, '2026-03-09', 61000, 2),
(6, 4, 2, 6, '2026-04-20', 53400, 3),
(7, 8, 3, 7, '2026-02-16', 72000, 1),
(8, 9, 3, 8, '2026-03-10', 48500, 2),
(9, 7, 3, 9, '2026-04-21', 65000, 3),
(10, 11, 4, 10, '2026-02-17', 40500, 1),
(11, 12, 4, 11, '2026-03-11', 71500, 2),
(12, 10, 4, 11, '2026-04-22', 68000, 3);

INSERT INTO jardda_league.estatistica_partida
(partida_id, time_1_gols, time_2_gols, time_1_cartoes, time_2_cartoes,
 time_1_chutes, time_2_chutes) VALUES
(1, 2, 1, 3, 4, 14, 10),
(2, 1, 1, 5, 2, 11, 13),
(3, 0, 2, 2, 3, 8, 16),
(4, 3, 1, 2, 3, 18, 9),
(5, 2, 2, 4, 5, 15, 14),
(6, 1, 0, 3, 2, 12, 7),
(7, 2, 2, 4, 3, 13, 12),
(8, 0, 1, 3, 4, 9, 11),
(9, 1, 3, 5, 2, 10, 17),
(10, 1, 0, 2, 4, 12, 8),
(11, 2, 1, 3, 3, 16, 10),
(12, 1, 1, 4, 5, 11, 13);

COMMIT;
