package br.com.fatec;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class Campeonato {
    private List<Piloto> inscritos = new ArrayList<>();

    private static final int[] PONTOS = {10, 6, 3, 1};

    public void inscrever(Piloto piloto) {
        if (inscritos.size() >= 4) {
            System.out.println("Grid cheio");
        } else if (inscritos.contains(piloto)) {
            System.out.println("Piloto repetido");
        } else {
            inscritos.add(piloto);
        }
    }

    public void registrarCorrida(List<Piloto> chegada) {
        for (int i = 0; i < chegada.size(); i++) {
            chegada.get(i).somarPontos(PONTOS[i]);

            if (i == 0) {
                chegada.get(i).registrarVitoria();
            }
        }
    }

    public List<Piloto> disputarCorrida(Random gerador,
            Piloto jogador, boolean nitro) {

        for (Piloto piloto : inscritos) {
            piloto.correr(gerador, piloto.equals(jogador) && nitro);
        }

        List<Piloto> chegada = new ArrayList<>(inscritos);

        chegada.sort(Comparator.comparingInt(Piloto::getTempo));

        registrarCorrida(chegada);

        return chegada;
    }

    public List<Piloto> getClassificacao() {

        List<Piloto> classificacao = new ArrayList<>(inscritos);

        classificacao.sort(
            Comparator.comparingInt(Piloto::getPontos).reversed()
                .thenComparing(
                    Comparator.comparingInt(Piloto::getVitorias).reversed()
                )
                .thenComparing(Piloto::getNome)
        );

        return classificacao;
    }
}