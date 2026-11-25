package br.com.fatec;
import lombok.Getter;
import java.util.Random;
import java.util.Objects;

@Getter
public class Piloto {
    private String nome;
    private Carro carro;
    private int pontos;
    private int vitorias;
    private int tempo;

    public Piloto(String nome, Carro carro) {
        this.nome = nome;
        this.carro = carro;
        this.pontos = 0;
        this.vitorias = 0;
        this.tempo = 0;
    }

    public void correr(Random gerador, boolean nitro) {
        tempo = 0;

        for (int i = 0; i < 3; i++) {
            tempo += carro.getTempoDeVolta() + gerador.nextInt(5);
        }

        if (nitro) {
            tempo -= 10;
        }
    }

    public void somarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void registrarVitoria() {
        vitorias++;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piloto)) {
            return false;
        }

        Piloto outro = (Piloto) obj;

        return nome.equals(outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return nome + " - " + pontos + " pontos - "
            + vitorias + " vitoria(s)";
    }
}