import java.util.InputMismatchException;
import java.util.Scanner;
public class MainUm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Azul");

        int posicaoX = 0;
        int posicaoY = 0;
        boolean inputValido = false;

        while(!inputValido){
            try{
                System.out.println("Insira a posição do alimento, de acordo com a posição no eixo cartesiano (x,y)");
                posicaoX = scanner.nextInt();
                posicaoY = scanner.nextInt();
                if(posicaoX < 0 || posicaoY < 0){
                    System.out.println("As coordenadas do alimento não podem ser menores que 0!");
                    scanner.nextLine();
                } else {
                    inputValido = true;
                }
            } catch (InputMismatchException e){
                System.out.println("Valor inserido incorreto, insira valores inteiro!");
                scanner.next();
                scanner.next();
            }
        }
        // TODO: fazer a lógica setValor() funcionar como esperado.
        if(inputValido){
            Alimento teste = new Alimento(posicaoX, posicaoY, "teste");
            while (!robo.encontrouAlimento(teste)){
                System.out.println("Digite o movimento desejado do robô (up, down, right, left)");
                String movimentoEscolhido = scanner.next();
                try{
                    robo.mover(movimentoEscolhido);
                } catch (MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }
            }
    
            System.out.println("O alimento foi encontrado com sucesso!");
            scanner.close();
        }
    }
}