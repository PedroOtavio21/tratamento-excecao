import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainTres {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        Robo roboNormal = new Robo("Azul");
        Robo roboInteligente = new RoboInteligente("Verde");

        Tabuleiro tabuleiro = new Tabuleiro();

        int alimentoX = 0;
        int alimentoY = 0;

        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                alimentoX = scanner.nextInt();
                alimentoY = scanner.nextInt();
                if (alimentoX < 0 || alimentoY < 0 || alimentoX >= 4 || alimentoY >= 4) {
                    System.out.println("As coordenadas do alimento devem ser entre 0 e 3");
                    scanner.nextLine();
                } else {
                    inputValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inserido incorreto, insira valores inteiros!");
                scanner.next();
                scanner.next();
            }
        }

        Alimento alimento = new Alimento(alimentoX, alimentoY);
        tabuleiro.adicionarAlimento(alimento);

        if (inputValido) {

            // variaveis de controle para evitar fluxo infinito
            // int maximoMovimentos = 250;
            int qntdMovimentos = 0;

            while (!roboNormal.encontrouAlimento(alimento) && !roboInteligente.encontrouAlimento(alimento)) {
                int valorRandom1 = random.nextInt(4) + 1;
                int valorRandom2 = random.nextInt(4) + 1;

                String direcao1 = roboNormal.mover(valorRandom1);
                String direcao2 = roboInteligente.mover(valorRandom2);

                // Movimento RoboNormal
                try {
                    roboNormal.mover(direcao1);
                    tabuleiro.atualizarTabuleiro(roboNormal, "RN", alimento);
                    tabuleiro.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }

                // Movimento RoboInteligente
                try {
                    roboInteligente.mover(direcao2);
                    tabuleiro.atualizarTabuleiro(roboInteligente, "RI", alimento);
                    tabuleiro.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }

                qntdMovimentos++;

                if (roboNormal.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboNormal.getCor() + " encontrou o alimento!");
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }
                if (roboInteligente.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboInteligente.getCor() + " encontrou o alimento!");
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }
            }

            System.out.println("Movimentos Válidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoValido());
            System.out.println(
                    "Movimentos Inválidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoInvalido());
            System.out.println(
                    "Movimentos Válidos de " + roboInteligente.getCor() + ": " + roboInteligente.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboInteligente.getCor() + ": "
                    + roboInteligente.getMovimentoInvalido());

            System.out.println("Total de movimentos executados: " + qntdMovimentos);
        }

        scanner.close();
    }
}