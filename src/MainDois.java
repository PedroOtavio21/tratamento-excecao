import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainDois {
    public static void main(String[] args) {
        // Atributos
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Robo roboUm = new Robo("Branco");
        Robo roboDois = new Robo("Verde");

        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiro2 = new Tabuleiro();

        int alimentoX = 0;
        int alimentoY = 0;
        boolean inputValido = false;

        // Verificacao de alimento no plano(xy)
        while (!inputValido) {
            try {
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                alimentoX = scanner.nextInt();
                alimentoY = scanner.nextInt();
                if (alimentoX < 0 || alimentoY < 0 || alimentoX >= 4 || alimentoY >= 4) {
                    System.out.println("As coordenadas do alimento não podem ser menores que 0!");
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

        // Execuçãa de lógica de negócio da Task2
        if (inputValido) {
            Alimento alimento = new Alimento(alimentoX, alimentoY);
            tabuleiro1.adicionarAlimento(alimento);
            tabuleiro2.adicionarAlimento(alimento);

            tabuleiro1.adicionarRobo(roboUm);
            tabuleiro2.adicionarRobo(roboDois);

            tabuleiro1.atualizarTabuleiro(roboUm, "R1", alimento);
            tabuleiro2.atualizarTabuleiro(roboDois, "R2", alimento);

            System.out.println("Tabuleiro inicializado");
            tabuleiro1.mostrarTabuleiroRotacionado();
            tabuleiro2.mostrarTabuleiroRotacionado();

            // variaveis de controle para evitar fluxo infinito
            // int maximoMovimentos = 250;

            while (!roboUm.encontrouAlimento(alimento) && !roboDois.encontrouAlimento(alimento)) {
                int valorRandom1 = random.nextInt(4) + 1;
                int valorRandom2 = random.nextInt(4) + 1;

                String direcao1 = roboUm.mover(valorRandom1);
                String direcao2 = roboDois.mover(valorRandom2);

                try {
                    roboUm.mover(direcao1);
                    tabuleiro1.atualizarTabuleiro(roboUm, "R1", alimento);
                    tabuleiro1.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    tabuleiro1.mostrarTabuleiroRotacionado();
                }

                try {
                    roboDois.mover(direcao2);
                    tabuleiro2.atualizarTabuleiro(roboDois, "R2", alimento);
                    tabuleiro2.mostrarTabuleiroRotacionado();
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    tabuleiro2.mostrarTabuleiroRotacionado();
                }


                // Robo 1 ganhou
                if (roboUm.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboUm.getCor() + " encontrou o alimento!");
                    tabuleiro1.adicionarAlimentoEncontrado(alimento);
                    tabuleiro1.mostrarTabuleiroRotacionado();
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }

                // Robo 2 ganhou
                if (roboDois.encontrouAlimento(alimento)) {
                    System.out.println("O robô " + roboDois.getCor() + " encontrou o alimento!");
                    tabuleiro2.adicionarAlimentoEncontrado(alimento);
                    tabuleiro2.mostrarTabuleiroRotacionado();
                    System.out.println("Pressione ENTER para continuar");
                    scanner.nextLine();
                }

            }

            // Resultado final da Task2
            System.out.println("Movimentos Válidos de " + roboUm.getCor() + ": " + roboUm.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboUm.getCor() + ": " + roboUm.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboUm.getCor() + ": " + (roboUm.getMovimentoValido() + roboUm.getMovimentoInvalido()));
            System.out.println();
            System.out.println("Movimentos Válidos de " + roboDois.getCor() + ": " + roboDois.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboDois.getCor() + ": " + roboDois.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboDois.getCor() + ": " + (roboDois.getMovimentoValido() + roboDois.getMovimentoInvalido()));
            System.out.println();
        }

        scanner.close();
    }
}
