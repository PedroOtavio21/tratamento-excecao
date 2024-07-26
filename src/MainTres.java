import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainTres {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        Robo roboNormal = new Robo("Azul");
        Robo roboInteligente = new RoboInteligente("Verde");

        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiro2 = new Tabuleiro();

        int alimentoX = 0;
        int alimentoY = 0;

        // Inserção de alimento no plano xy
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


        // Loop principal da Task3
        if (inputValido) {
            Alimento alimento = new Alimento(alimentoX, alimentoY);
            tabuleiro1.adicionarAlimento(alimento);
            tabuleiro2.adicionarAlimento(alimento);

            tabuleiro1.adicionarRobo(roboNormal);
            tabuleiro2.adicionarRobo(roboInteligente);

            tabuleiro1.atualizarTabuleiro(roboNormal, "RN", alimento);
            tabuleiro2.atualizarTabuleiro(roboInteligente, "RI", alimento);

            System.out.println("Tabuleiro inicializado!");
            tabuleiro1.mostrarTabuleiroRotacionado();
            tabuleiro2.mostrarTabuleiroRotacionado();

            // variaveis de controle para evitar fluxo infinito
            // int maximoMovimentos = 250;


            while (!roboNormal.encontrouAlimento(alimento) && !roboInteligente.encontrouAlimento(alimento)) {
                int valorRandom1 = random.nextInt(4) + 1;
                int valorRandom2 = random.nextInt(4) + 1;

                String direcao1 = roboNormal.mover(valorRandom1);
                String direcao2 = roboInteligente.mover(valorRandom2);

                // Movimento RoboNormal
                try {
                    roboNormal.mover(direcao1);
                    tabuleiro1.atualizarTabuleiro(roboNormal, "RN", alimento);
                    tabuleiro1.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    tabuleiro1.mostrarTabuleiroRotacionado();
                }

                // Movimento RoboInteligente
                try {
                    roboInteligente.mover(direcao2);
                    tabuleiro2.atualizarTabuleiro(roboInteligente, "RI", alimento);
                    tabuleiro2.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    tabuleiro2.mostrarTabuleiroRotacionado();
                }

                // Robo normal Ganhou
                if (roboNormal.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboNormal.getCor() + " encontrou o alimento!");
                    tabuleiro1.adicionarAlimentoEncontrado(alimento);
                    tabuleiro1.mostrarTabuleiroRotacionado();
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }

                // Robo Inteligente Ganhou
                if (roboInteligente.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboInteligente.getCor() + " encontrou o alimento!");
                    tabuleiro2.adicionarAlimentoEncontrado(alimento);
                    tabuleiro2.mostrarTabuleiroRotacionado();
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }
            }

            // Resultado final da Task3
            System.out.println("Movimentos Válidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboNormal.getCor() + ": " + (roboNormal.getMovimentoValido() + roboNormal.getMovimentoInvalido()));
            System.out.println();
            System.out.println("Movimentos Válidos de " + roboInteligente.getCor() + ": " + roboInteligente.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboInteligente.getCor() + ": " + roboInteligente.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboInteligente.getCor() + ": " + (roboInteligente.getMovimentoValido() + roboInteligente.getMovimentoInvalido()));
            System.out.println();
        }

        scanner.close();
    }
}