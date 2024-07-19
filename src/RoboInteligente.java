import java.util.Random;

public class RoboInteligente extends Robo{
    // Metodos
    public RoboInteligente(String cor){
        super(cor);
    }

    // Sobrescrita de Metodo mover para realizar novos movimentos apos excecao
    @Override
    public void mover(String direcao) throws MovimentoInvalidoException{
        int novoX = this.getX();
        int novoY = this.getY();
        switch(direcao){
            case "up":
                novoY++;
                break;
            case "down":
                novoY--;
                break;
            case "right":
                novoX++;
                break;
            case "left":
                novoX--;
                break;
            default:
                throw new IllegalArgumentException("Direção aplicada invállida!");    
        }
        try{
            validarMovimento(novoX, novoY);
            this.setX(novoX);
            this.setY(novoY);
            incrementaMovimentoValido();
            System.out.println("Nova coordenada do robô " + this.getCor() + ": (" + this.getX() + "," + this.getY() + ").");
        } catch (MovimentoInvalidoException e){
            incrementaMovimentoInvalido();
            // Início de um novo movimento a partir do disparo da excecao
            novoX = this.getX();
            novoY = this.getY();
            direcao = novoValorAleatorio();
            mover(direcao);
        }
    }

    public String novoValorAleatorio(){
        Random random = new Random();
        int opcao = random.nextInt(4) + 1;
        return mover(opcao);
    }
}