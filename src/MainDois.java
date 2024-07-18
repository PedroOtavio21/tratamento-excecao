import java.util.InputMismatchException;
import java.util.Scanner;
public class MainDois {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo roboUm = new Robo("Azul");
        Robo roboDois = new Robo("Verde");

        int alimentoX = 0;
        int alimentoY = 0; 
        boolean inputValido = false;

        while(!inputValido){
            try{
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                alimentoX = scanner.nextInt();
                alimentoY = scanner.nextInt();
                if(alimentoX < 0 || alimentoY < 0){
                    System.out.println("As coordenadas do alimento não podem ser menores que 0!");
                    scanner.nextLine();
                } else {
                    inputValido = true;
                }
            } catch (InputMismatchException e){
                System.out.println("Valor inserido incorreto, insira valores inteiros!");
                scanner.next();
                scanner.next();
            }
        }

        // if(inputValido){
        //     Alimento alimento = new Alimento(alimentoX, alimentoY);
        //     while (!roboUm.encontrouAlimento(alimento) || !roboDois.encontrouAlimento(alimento)) {
                
        //     }
        // }
    }
}
