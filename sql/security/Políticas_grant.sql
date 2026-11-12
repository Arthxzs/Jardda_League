-- Políticas de acesso do jardda_league via GRANT (senhas e criação de roles em um arquivo separado)
--   neondb_owner: dono das tabelas
--   normal_user: somente leitura em todas as tabelas
--   admin: todos os privilégios nas tabelas (e nas sequences)

-- 1 - Acesso ao schema
GRANT USAGE ON SCHEMA jardda_league TO normal_user, admin;

-- 2 - normal_user - só leitura
GRANT SELECT ON ALL TABLES IN SCHEMA jardda_league TO normal_user;

-- 3 - admin - tudo nas tabelas
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA jardda_league TO admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA jardda_league TO admin;

-- 4 - Privilégios padrão para objetos FUTUROS (teoricamente isso faz as permissões funcionarem pra tabelas futuras)
ALTER DEFAULT PRIVILEGES FOR ROLE neondb_owner IN SCHEMA jardda_league
    GRANT SELECT ON TABLES TO normal_user;

ALTER DEFAULT PRIVILEGES FOR ROLE neondb_owner IN SCHEMA jardda_league
    GRANT ALL PRIVILEGES ON TABLES TO admin;

ALTER DEFAULT PRIVILEGES FOR ROLE neondb_owner IN SCHEMA jardda_league
    GRANT ALL PRIVILEGES ON SEQUENCES TO admin;
