public class Bomba extends Obstaculo{
    public Bomba(int x, int y){
        super(x, y);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        if (robo.getX() == this.getCoordenadas()[0] && robo.getY() == this.getCoordenadas()[1]) {
            robo.setExplodiu(true); 
            tabuleiro.retirarRobo(robo); 
            tabuleiro.adicionarObstaculo(this); 
            System.out.println("O robô " + robo.getCor() + " explodiu ao atingir a bomba!");
        }
    }

}
