public class Rocha extends Obstaculo{
    public Rocha(int x, int y){
        super(x, y);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        String roboString = robo.getX() + "," + robo.getY();
        if(roboString.equals(this.getId())){
            System.out.println("O robô " + robo.getCor() + " bateu na rocha!");
            robo.voltarPosicaoAnterior();
            System.out.println("Nova posicao do robo " + robo.getCor() + ": " + robo.getX() + "," + robo.getY());
        }
    }
}
