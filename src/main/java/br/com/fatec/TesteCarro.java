package br.com.fatec;
import java.util.ArrayList;
import java.util.Collections;

public class TesteCarro {
    public static void main(String[] args) {

        Carro c1 = new Carro("Grotti Cheetah", 500);
        Carro c2 = new Carro("Bravado Banshee", 400);
        Carro c3 = new Carro("Dinka Blista", 200);

        System.out.println(c1 + " faz a volta em " + c1.getTempoDeVolta() + "s");
        System.out.println(c2 + " faz a volta em " + c2.getTempoDeVolta() + "s");
        System.out.println(c3 + " faz a volta em " + c3.getTempoDeVolta() + "s");

        Carro c4 = new Carro("Grotti Cheetah", 500);
        Carro c5 = new Carro("Pegassi Infernus", 500);

        System.out.println("Iguais? " + c1.equals(c4));
        System.out.println("Iguais? " + c1.equals(c5));

        ArrayList<Carro> lista = new ArrayList<>();

        lista.add(c3);
        lista.add(new Carro("Ocelot Pariah", 500));
        lista.add(c2);
        lista.add(c1);

        Collections.sort(lista);

        System.out.println(lista);
    }
}