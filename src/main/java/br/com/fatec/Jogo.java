package br.com.fatec;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

public class Jogo {
    private static final int CORRIDAS = 3;
    private static final int NITROS = 2;

    public static void main(String[] args) {
        String opcao;

        do {
            opcao = JOptionPane.showInputDialog(
                "GTA VI - Vice City\n\n"
                + "1-Jogar\n"
                + "2-Ranking\n"
                + "0-Sair"
            );

            if (opcao == null) {
                opcao = "0";
            }

            switch (opcao) {
                case "1":
                    jogar();
                    break;

                case "2":
                    ranking();
                    break;

                case "0":
                    JOptionPane.showMessageDialog(null, "Ate a proxima!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }

        } while (!opcao.equals("0"));
    }

    public static void jogar() {

        String nome = JOptionPane.showInputDialog("Nome do jogador:");

        if (nome == null) {
            return;
        }

        String escolha = JOptionPane.showInputDialog(
            "Escolha seu piloto:\n1-Jason\n2-Lucia"
        );

        if (escolha == null) {
            return;
        }

        String nomePiloto;

        switch (escolha) {
            case "1":
                nomePiloto = "Jason";
                break;

            case "2":
                nomePiloto = "Lucia";
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
                return;
        }

        Random gerador = new Random();
        Concessionaria concessionaria = new Concessionaria();
        Campeonato campeonato = new Campeonato();

        Piloto jogador = new Piloto(nomePiloto, concessionaria.sortear());

        campeonato.inscrever(jogador);
        campeonato.inscrever(new Piloto("Raul", concessionaria.sortear()));
        campeonato.inscrever(new Piloto("Boobie", concessionaria.sortear()));
        campeonato.inscrever(new Piloto("Cal", concessionaria.sortear()));

        JOptionPane.showMessageDialog(null,
            "Seu carro: " + jogador.getCarro());

        int cargas = NITROS;

        for (int i = 1; i <= CORRIDAS; i++) {

            String resposta = JOptionPane.showInputDialog(
                "Corrida " + i + "/" + CORRIDAS + "\n\n"
                + formatarClassificacao(campeonato.getClassificacao())
                + "\nUsar nitro? 1-Sim 2-Nao (restam " + cargas + ")"
            );

            boolean nitro = false;

            if ("1".equals(resposta)) {

                if (cargas > 0) {
                    nitro = true;
                    cargas--;
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Sem nitro restante");
                }
            }

            List<Piloto> chegada =
                campeonato.disputarCorrida(gerador, jogador, nitro);

            StringBuilder texto = new StringBuilder("Chegada\n\n");

            for (int j = 0; j < chegada.size(); j++) {

                Piloto piloto = chegada.get(j);

                texto.append(j + 1)
                    .append(". ")
                    .append(piloto.getNome())
                    .append(" - ")
                    .append(piloto.getTempo())
                    .append("s\n");
            }

            JOptionPane.showMessageDialog(null, texto.toString());
        }

        List<Piloto> classificacao = campeonato.getClassificacao();

        int posicao = classificacao.indexOf(jogador) + 1;

        String resultado;

        if (posicao == 1) {
            resultado = "Campeao de Vice City!";
        } else {
            resultado = "Posicao final: " + posicao;
        }

        JOptionPane.showMessageDialog(null,
            "Classificacao final\n\n"
            + formatarClassificacao(classificacao)
            + "\n" + resultado);

        Partida partida = Partida.builder()
            .jogador(nome)
            .pontuacao(jogador.getPontos())
            .build();

        PartidaDAO dao = new PartidaDAO();

        if (dao.cadastrar(partida)) {
            JOptionPane.showMessageDialog(null, "Partida salva");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a partida");
        }
    }

    private static String formatarClassificacao(List<Piloto> pilotos) {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < pilotos.size(); i++) {

            texto.append(i + 1)
                .append(". ")
                .append(pilotos.get(i))
                .append("\n");
        }

        return texto.toString();
    }
    private static void ranking() {

    PartidaDAO dao = new PartidaDAO();

    List<Partida> partidas = dao.listar();

    partidas.sort(
        Comparator.comparingInt(Partida::getPontuacao).reversed()
    );

    StringBuilder texto = new StringBuilder("RANKING\n\n");

    for (int i = 0; i < partidas.size() && i < 5; i++) {

        Partida p = partidas.get(i);

        texto.append(i + 1)
            .append(". ")
            .append(p.getJogador())
            .append(" - ")
            .append(p.getPontuacao())
            .append(" pontos\n");
    }

    JOptionPane.showMessageDialog(null, texto.toString());
    }
}