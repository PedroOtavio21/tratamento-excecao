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

        Tabuleiro tabuleiro = new Tabuleiro();

        Robo roboNormal = new Robo("Azul");
        Robo roboInteligente = new RoboInteligente("Verde");

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
        
        // TODO: Implementar a leitura e posicionamento de Bombas e Obstaculos

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

        if (inputValidoAlimento && inputValidoBomba && inputValidoRocha) {
           Alimento alimento = new Alimento(alimentoX, alimentoY);
           tabuleiro.adicionarAlimento(alimento); 
           System.out.println("Quantidade de Obstaculos criados: " + obstaculos.size());
        }
    }
}
