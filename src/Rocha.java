public class Rocha extends Obstaculo{
    public Rocha(int x, int y){
        super(x, y);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro1, Tabuleiro tabuleiro2) {
        if (robo.getX() == this.getCoordenadas()[0] && robo.getY() == this.getCoordenadas()[1]) {
            robo.voltarPosicaoAnterior(); 
            System.out.println("O robô " + robo.getCor() + " bateu em uma rocha e voltou para a posição anterior!");
            tabuleiro1.mostrarTabuleiroRotacionado();
        }
    }
}
