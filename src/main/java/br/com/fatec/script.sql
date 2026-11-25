CREATE DATABASE db_gta;
USE db_gta;

CREATE TABLE IF NOT EXISTS tb_partida (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    jogador VARCHAR(200),
    pontuacao INT
);