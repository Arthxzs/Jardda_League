-- Grant

-- GRANT all privileges ON all tables in schema jardda_league TO authenticated

-- Políticas para admins

CREATE POLICY admin_clube ON jardda_league.clube AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_clube_liga ON jardda_league.clube_liga AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_clube_tecnico ON jardda_league.clube_tecnico AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_depto_medico ON jardda_league.depto_medico AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_estadio ON jardda_league.estadio AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_estatistica_partida ON jardda_league.estatistica_partida AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_jogador ON jardda_league.jogador AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_jogador_clube ON jardda_league.jogador_clube AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_liga ON jardda_league.liga AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_pais ON jardda_league.pais AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_partida ON jardda_league.partida AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');
CREATE POLICY admin_tecnico ON jardda_league.tecnico AS permissive FOR all TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'admin');

--  Políticas para employees

CREATE POLICY select_employee_clube ON jardda_league.clube AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_clube_liga ON jardda_league.clube_liga AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_clube_tecnico ON jardda_league.clube_tecnico AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_depto_medico ON jardda_league.depto_medico AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_estadio ON jardda_league.estadio AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_estatistica_partida ON jardda_league.estatistica_partida AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_jogador ON jardda_league.jogador AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_jogador_clube ON jardda_league.jogador_clube AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_liga ON jardda_league.liga AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_pais ON jardda_league.pais AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_partida ON jardda_league.partida AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY select_employee_tecnico ON jardda_league.tecnico AS permissive FOR select TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');

CREATE POLICY update_employee_clube ON jardda_league.clube AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_clube_liga ON jardda_league.clube_liga AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_clube_tecnico ON jardda_league.clube_tecnico AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_depto_medico ON jardda_league.depto_medico AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_estadio ON jardda_league.estadio AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_estatistica_partida ON jardda_league.estatistica_partida AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_jogador ON jardda_league.jogador AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_jogador_clube ON jardda_league.jogador_clube AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_liga ON jardda_league.liga AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_pais ON jardda_league.pais AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_partida ON jardda_league.partida AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');
CREATE POLICY update_employee_tecnico ON jardda_league.tecnico AS permissive FOR update TO authenticated USING(auth.jwt() -> 'user_metadata' ->> 'role' = 'employee');