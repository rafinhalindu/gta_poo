package br.com.fatec;
import java.util.List;

public class TesteCampeonato {
    public static void main(String[] args) {
        Campeonato campeonato = new Campeonato();

        Piloto lucia = new Piloto("Lucia", new Carro("Grotti Cheetah", 500));
        Piloto jason = new Piloto("Jason", new Carro("Bravado Banshee", 400));
        Piloto raul = new Piloto("Raul", new Carro("Pegassi Infernus", 480));
        Piloto cal = new Piloto("Cal", new Carro("Dinka Blista", 200));

        campeonato.inscrever(lucia);
        campeonato.inscrever(jason);
        campeonato.inscrever(new Piloto("Lucia", new Carro("Karin Sultan", 300)));
        campeonato.inscrever(raul);
        campeonato.inscrever(cal);
        campeonato.inscrever(new Piloto("Boobie", new Carro("Truffade Adder", 520)));

        campeonato.registrarCorrida(List.of(lucia, jason, raul, cal));

        campeonato.registrarCorrida(List.of(raul, cal, jason, lucia));

        campeonato.registrarCorrida(List.of(cal, raul, jason, lucia));

        List<Piloto> classificacao = campeonato.getClassificacao();

        for (int i = 0; i < classificacao.size(); i++) {
            System.out.println((i + 1) + ". " + classificacao.get(i));
        }
    }
}