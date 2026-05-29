-- =========================
-- SATELITES
-- =========================

INSERT INTO tb_satelite(nome_satelite, data_captura, temperatura_detectada, nivel_poluicao) VALUES ('UrbanClimate One', '2026-05-29', 31.5, 55.2);

INSERT INTO tb_satelite(nome_satelite, data_captura, temperatura_detectada, nivel_poluicao) VALUES ('EcoVision Sat', '2026-05-28', 28.7, 40.8);

INSERT INTO tb_satelite(nome_satelite, data_captura, temperatura_detectada, nivel_poluicao) VALUES ('GreenOrbit', '2026-05-27', 33.1, 61.9);


-- =========================
-- FAZENDAS
-- =========================

INSERT INTO tb_fazenda(nome_fazenda, regiao, area_cultivada, ultima_irrigacao, satelite_id) VALUES ('Sky Garden', 'Centro', 120.5, '2026-05-28', 1);

INSERT INTO tb_fazenda(nome_fazenda, regiao, area_cultivada, ultima_irrigacao, satelite_id) VALUES ('Eco Roof Farm', 'Zona Norte', 95.0, '2026-05-27', 1);

INSERT INTO tb_fazenda(nome_fazenda, regiao, area_cultivada, ultima_irrigacao, satelite_id) VALUES ('Solar Green Hub', 'Zona Sul', 140.2, '2026-05-26', 2);

INSERT INTO tb_fazenda(nome_fazenda, regiao, area_cultivada, ultima_irrigacao, satelite_id) VALUES ('Urban Leaf', 'Zona Leste', 80.7, '2026-05-25', 3);