public class Rocha extends Obstaculo{
    public Rocha(int x, int y){
        super(x, y);
    }

    // Não prometo que a lógica esteja correta
    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        if (robo.getX() == this.getCoordenadas()[0] && robo.getY() == this.getCoordenadas()[1]) {
            robo.voltarPosicaoAnterior(); 
            System.out.println("O robô " + robo.getCor() + " bateu em uma rocha e voltou para a posição anterior!");
        }
    }
}
