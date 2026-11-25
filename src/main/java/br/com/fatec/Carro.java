package br.com.fatec;
import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Carro implements Comparable<Carro> {
    private String modelo;
    private int potencia;

    public int getTempoDeVolta() {
        return 30 + 6000 / potencia;
    }

    @Override
    public String toString() {
        return modelo + " (" + potencia + " cv)";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Carro)) {
            return false;
        }

        Carro outro = (Carro) obj;

        return modelo.equals(outro.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(modelo);
    }

    @Override
    public int compareTo(Carro outro) {
        if (potencia != outro.potencia) {
            return Integer.compare(outro.potencia, potencia);
        }

        return modelo.compareTo(outro.modelo);
    }
}