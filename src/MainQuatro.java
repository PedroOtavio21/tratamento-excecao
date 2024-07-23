import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
public class MainQuatro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int alimentoX = 0;
        int alimentoY = 0;

        int qntdBombas = 0;
        int qntdRochas = 0;


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
            // TODO: Implementar a leitura e posicionamento de Bombas e Obstaculos
        }

        if (inputValidoAlimento) {
           Alimento alimento = new Alimento(alimentoX, alimentoY);
           tabuleiro.adicionarAlimento(alimento); 
        }
    }
}
