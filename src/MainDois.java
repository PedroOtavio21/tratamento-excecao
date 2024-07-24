import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainDois {
    public static void main(String[] args) {
        // Atributos
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Robo roboUm = new Robo("Azul");
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
                if (alimentoX < 0 || alimentoY < 0) {
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

        // Procura de alimento no plano xy
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
            int maximoMovimentos = 250;
            int qntdMovimentos = 0;

            while ((!roboUm.encontrouAlimento(alimento) || !roboDois.encontrouAlimento(alimento))
                    && qntdMovimentos < maximoMovimentos) {
                int valorRandom1 = random.nextInt(4) + 1;
                int valorRandom2 = random.nextInt(4) + 1;
                
                String direcao1 = roboUm.mover(valorRandom1);
                String direcao2 = roboDois.mover(valorRandom2);
                
                // try {
                //     roboUm.mover(valorRandom1);
                // } catch (MovimentoInvalidoException e) {
                //     System.out.println(e.getMessage());
                // }

                // try {
                //     roboDois.mover(valorRandom2);
                // } catch (MovimentoInvalidoException e) {
                //     System.out.println(e.getMessage());
                // }

                
                try {
                    roboUm.mover(direcao1);
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
                 
                try {
                    roboDois.mover(direcao2);
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }

                tabuleiro1.atualizarTabuleiro(roboUm, "R1", alimento);
                tabuleiro2.atualizarTabuleiro(roboDois, "R2", alimento);

                tabuleiro1.mostrarTabuleiroRotacionado();
                tabuleiro2.mostrarTabuleiroRotacionado();

                qntdMovimentos++;

            }

            if (roboUm.encontrouAlimento(alimento)) {
                System.out.println("O robô " + roboUm.getCor() + " encontrou o alimento!");
            }
            if (roboDois.encontrouAlimento(alimento)) {
                System.out.println("O robô " + roboDois.getCor() + " encontrou o alimento!");
            }

            System.out.println("Movimentos Válidos de " + roboUm.getCor() + ": " + roboUm.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboUm.getCor() + ": " + roboUm.getMovimentoInvalido());
            System.out.println("Movimentos Válidos de " + roboDois.getCor() + ": " + roboDois.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboDois.getCor() + ": " + roboDois.getMovimentoInvalido());

            System.out.println("Total de movimentos executados: " + qntdMovimentos);
        }

        scanner.close();
    }
}
