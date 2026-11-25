package br.com.fatec;
import java.util.Random;

public class TestePiloto {
    public static void main(String[] args) {

        Random gerador = new Random();

        Piloto p1 = new Piloto("Lucia",
            new Carro("Grotti Cheetah", 500));

        System.out.println(p1);

        p1.correr(gerador, false);
        System.out.println("Tempo sem nitro: " + p1.getTempo() + "s");

        p1.correr(gerador, true);
        System.out.println("Tempo com nitro: " + p1.getTempo() + "s");

        p1.somarPontos(10);
        p1.registrarVitoria();

        System.out.println(p1);

        Piloto p2 = new Piloto("Lucia",
            new Carro("Dinka Blista", 200));

        System.out.println("Iguais? " + p1.equals(p2));
    }
}