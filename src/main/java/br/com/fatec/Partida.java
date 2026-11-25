package br.com.fatec;
import lombok.Getter;
import lombok.Builder;

@Getter
public class Partida {
    private int codigo;
    private String jogador;
    private int pontuacao;

    @Builder
    private Partida(int codigo, String jogador, int pontuacao) {
        this.codigo = codigo;
        this.jogador = jogador;
        this.pontuacao = pontuacao;
    }
}