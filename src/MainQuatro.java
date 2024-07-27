import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
public class MainQuatro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Inicialização de variáveis do jogo
        int alimentoX = 0;
        int alimentoY = 0;

        List<Obstaculo> obstaculos = new ArrayList<>();

        int qntdBombas = 1;
        int bombaX = 0;
        int bombaY = 0;
        
        
        int qntdRochas = 1;
        int rochaX = 0;
        int rochaY = 0;

        boolean inputValidoAlimento = false;
        boolean inputValidoBomba = false;
        boolean inputValidoRocha = false;

        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiro2 = new Tabuleiro();

        Robo roboNormal = new Robo("Branco");
        Robo roboInteligente = new RoboInteligente("Verde");

        // Inserção de alimento no plano xy
        while(!inputValidoAlimento){
            try{
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                alimentoX = scanner.nextInt();
                alimentoY = scanner.nextInt();
                if(alimentoX < 0 || alimentoX >= 4 || alimentoY < 0 || alimentoY >= 4){
                    System.out.println("As coordenadas do alimento devem ser entre 0 e 3");
                    scanner.nextLine();
                } else {
                    inputValidoAlimento = true;
                }
            } catch(InputMismatchException e){
                System.out.println("Valor inserido incorreto, insira valores inteiros!");
                scanner.next();
                scanner.next();
            }
        }
        
        // Loop para inserção de bombas
        if(qntdBombas <= 0 || qntdBombas > 2){
            System.out.println("Não será possível inseir bombas no jogo.");
        } else {
            for (int i = 0; i < qntdBombas; i++) {
                while (!inputValidoBomba) {
                    try {
                        System.out.println("Insira as coordenadas da bomba " + (i + 1));
                        bombaX = scanner.nextInt();
                        bombaY = scanner.nextInt();
                        if (bombaX < 0 || bombaX >= 4 || bombaY < 0 || bombaY >= 4) {
                            System.out.println("As coordenadas da bomba devem ser entre 0 e 3");
                            scanner.nextLine();
                        } else {
                            obstaculos.add(new Bomba(bombaX, bombaY));
                            inputValidoBomba = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inserido incorreto, insira valores inteiros!");
                        scanner.next();
                    }
                }
            }
        }

        // Loop para inserção de rochas
        if(qntdRochas <= 0 || qntdRochas > 2){
            System.out.println("Não será possível inseir rochas no jogo.");
        } else {
            for (int i = 0; i < qntdRochas; i++) {
                while (!inputValidoRocha) {
                    try {
                        System.out.println("Insira as coordenadas da rocha " + (i + 1));
                        rochaX = scanner.nextInt();
                        rochaY = scanner.nextInt();
                        if (rochaX < 0 || rochaX >= 4 || rochaY < 0 || rochaY >= 4) {
                            System.out.println("As coordenadas da rocha devem ser entre 0 e 3");
                            scanner.nextLine();
                        } else {
                            obstaculos.add(new Rocha(rochaX, rochaY));
                            inputValidoRocha = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inserido incorreto, insira valores inteiros!");
                        scanner.next();
                    }
                }
            }
        }

        // TODO: Implementar verificacao de roboPosicao == obstaculoPosicao, aplicando lógica de bomba e rocha quando o robô os atingir
        if (inputValidoAlimento && inputValidoBomba && inputValidoRocha) {
            Alimento alimento = new Alimento(alimentoX, alimentoY);
            tabuleiro1.adicionarAlimento(alimento);
            tabuleiro2.adicionarAlimento(alimento);
            System.out.println("Quantidade de Obstaculos criados: " + obstaculos.size());

            for (Obstaculo o : obstaculos) {
                tabuleiro1.adicionarObstaculo(o);
                tabuleiro2.adicionarObstaculo(o);
            }

            // Execução
            while (true) {
                if (!roboNormal.encontrouAlimento(alimento) && !roboNormal.isExplodiu()) {
                    int valorRandom1 = random.nextInt(4) + 1;
                    String direcao1 = roboNormal.mover(valorRandom1);

                    try {
                        roboNormal.mover(direcao1);
                        tabuleiro1.atualizarTabuleiro(roboNormal, "RN", alimento, obstaculos);
                        tabuleiro1.mostrarTabuleiroRotacionado();

                        for (Obstaculo o : obstaculos) {
                            o.bater(roboNormal, tabuleiro1, tabuleiro2, obstaculos);
                        }
                    } catch (MovimentoInvalidoException e) {
                        System.out.println(e.getMessage());
                        tabuleiro1.mostrarTabuleiroRotacionado();
                    }

                    if (roboNormal.encontrouAlimento(alimento)) {
                        System.out.println("O robô " + roboNormal.getCor() + " encontrou o alimento!");
                        tabuleiro1.adicionarAlimentoEncontrado(alimento);
                        tabuleiro1.mostrarTabuleiroRotacionado();
                        System.out.println("Pressione ENTER para continuar");
                        scanner.nextLine();
                        break;
                    }
                }

                if (!roboInteligente.encontrouAlimento(alimento) && !roboInteligente.isExplodiu()) {
                    int valorRandom2 = random.nextInt(4) + 1;
                    String direcao2 = roboInteligente.mover(valorRandom2);

                    try {
                        roboInteligente.mover(direcao2);
                        tabuleiro2.atualizarTabuleiro(roboInteligente, "RI", alimento, obstaculos);
                        tabuleiro2.mostrarTabuleiroRotacionado();
                        
                        for (Obstaculo o : obstaculos) {
                            o.bater(roboInteligente, tabuleiro2, tabuleiro1, obstaculos);
                        }
                    } catch (MovimentoInvalidoException e) {
                        System.out.println(e.getMessage());
                        tabuleiro2.mostrarTabuleiroRotacionado();
                    }

                    if (roboInteligente.encontrouAlimento(alimento)) {
                        System.out.println("O robô " + roboInteligente.getCor() + " encontrou o alimento!");
                        tabuleiro2.adicionarAlimentoEncontrado(alimento);
                        tabuleiro2.mostrarTabuleiroRotacionado();
                        System.out.println("Pressione ENTER para continuar");
                        scanner.nextLine();
                        break;
                    }
                }

                if (roboNormal.isExplodiu() && roboInteligente.isExplodiu()) {
                    System.out.println("Ambos os robôs explodiram! Fim de jogo!");
                    break;
                }

            }

            // Resultado de execução
            int totalMovimentosJogo = (roboInteligente.getMovimentoValido() + roboInteligente.getMovimentoInvalido())
                    + (roboNormal.getMovimentoValido() + roboNormal.getMovimentoInvalido());
            System.out.println("Movimentos Válidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboNormal.getCor() + ": " + roboNormal.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboNormal.getCor() + ": "
                    + (roboNormal.getMovimentoValido() + roboNormal.getMovimentoInvalido()));
            System.out.println();
            System.out.println("Movimentos Válidos de " + roboInteligente.getCor() + ": "
                    + roboInteligente.getMovimentoValido());
            System.out.println("Movimentos Inválidos de " + roboInteligente.getCor() + ": "
                    + roboInteligente.getMovimentoInvalido());
            System.out.println("Total de movimentos de " + roboInteligente.getCor() + ": "
                    + (roboInteligente.getMovimentoValido() + roboInteligente.getMovimentoInvalido()));
            System.out.println();
            System.out.println("Total de movimentos executados: " + totalMovimentosJogo);
        }  
        scanner.close();
    }
}
