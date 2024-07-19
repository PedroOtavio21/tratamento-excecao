import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class MainDois {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
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

        if(inputValido){
            Alimento alimento = new Alimento(alimentoX, alimentoY);
            int valorRandom1 = 0;
            int valorRandom2 = 0;
            String direcao1 = null;
            String direcao2 = null;
            int maximoMovimentos = 100;
            int qntdMovimentos = 0;

            while ((!roboUm.encontrouAlimento(alimento) || !roboDois.encontrouAlimento(alimento)) && qntdMovimentos < maximoMovimentos) {
                valorRandom1 = random.nextInt(4) + 1;
                valorRandom2 = random.nextInt(4) + 1;

                direcao1 = roboUm.mover(valorRandom1);
                direcao2 = roboDois.mover(valorRandom2);

                try {
                    roboUm.mover(direcao1);
                } catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }

                try {
                    roboDois.mover(direcao2);
                } catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }

                qntdMovimentos++;   
            }
               
            if(roboUm.encontrouAlimento(alimento)){
                System.out.println("O robô " + roboUm.getCor() + " encontrou o alimento!");
            }
            if(roboDois.encontrouAlimento(alimento)){
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
