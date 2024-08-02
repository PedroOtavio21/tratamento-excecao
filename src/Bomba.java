import java.util.List;

public class Bomba extends Obstaculo{
    private boolean explodiu;
    
    // Construtor
    public Bomba(int x, int y){
        super(x, y);
        this.explodiu = false;
    }

    public boolean isExplodiu(){
        return this.explodiu;
    }

    public void explodir(){
        this.explodiu = true;
    }

    // TODO: Necessário mudar lógica de negócio a partir de explosão de bomba
    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro1, Tabuleiro tabuleiro2, List<Obstaculo> obstaculos) {
        if (robo.getX() == this.getCoordenadas()[0] && robo.getY() == this.getCoordenadas()[1]) {
            robo.setExplodiu(true);
            this.explodir();
            obstaculos.remove(this);
            tabuleiro1.retirarObstaculo(this);
            tabuleiro2.retirarObstaculo(this);
            tabuleiro1.retirarRobo(robo);
            System.out.println("O robô " + robo.getCor() + " explodiu ao atingir a bomba!");
            tabuleiro1.mostrarTabuleiroRotacionado();
        }
    }

}
