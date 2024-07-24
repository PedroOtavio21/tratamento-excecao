public class Bomba extends Obstaculo{
    // Construtor
    public Bomba(int x, int y){
        super(x, y);
    }

    // Não prometo que a lógica esteja correta
    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        if (robo.getX() == this.getCoordenadas()[0] && robo.getY() == this.getCoordenadas()[1]) {
            robo.setExplodiu(true); 
            robo.setX(-1);
            robo.setY(-1);
            tabuleiro.adicionarObstaculo(this); 
            System.out.println("O robô " + robo.getCor() + " explodiu ao atingir a bomba!");
        }
    }

}
