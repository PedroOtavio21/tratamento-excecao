import java.util.InputMismatchException;
import java.util.Scanner;

public class MainUm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Branco");

        int posicaoX = 0;
        int posicaoY = 0;
        boolean inputValido = false;
        int qntdMovimentos = 0;

        // Inserção de alimento no plano xy
        while (!inputValido) {
            try {
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                posicaoX = scanner.nextInt();
                posicaoY = scanner.nextInt();
                if (posicaoX < 0 || posicaoY < 0 || posicaoX >= 4 || posicaoY >= 4) {
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

        Alimento teste = new Alimento(posicaoX, posicaoY);
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionarAlimento(teste);
        tabuleiro.adicionarRobo(robo);

        tabuleiro.mostrarTabuleiroRotacionado();

        // Loop principal da task1
        if (inputValido) {
            while (!robo.encontrouAlimento(teste)) {
                try {
                    System.out.println("Digite o movimento desejado do robô (up, down, right, left)");
                    String movimentoEscolhido = scanner.next();
                    // robo.mover(movimentoEscolhido);
                    robo.mover(movimentoEscolhido);
                    tabuleiro.atualizarTabuleiro(robo, "R", teste);
                    tabuleiro.mostrarTabuleiroRotacionado();
                    qntdMovimentos++;
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    tabuleiro.mostrarTabuleiroRotacionado();
                } catch (IllegalArgumentException e){
                    System.out.println("Valor inserido incorreto! escolha uma das opções: (up, down, right, left)");
                }

                if(robo.encontrouAlimento(teste)){
                    System.out.println("O robô " + robo.getCor() + " encontrou o alimento com sucesso!");
                    tabuleiro.adicionarAlimentoEncontrado(teste);
                    tabuleiro.mostrarTabuleiroRotacionado();
                }
            }

            // Resultado final da task1
            System.out.println("Quantidade de movimentos realizados: " + qntdMovimentos);
            scanner.close();
        }
    }
}