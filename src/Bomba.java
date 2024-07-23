public class Bomba extends Obstaculo{
    public Bomba(int x, int y){
        super(x, y);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        String roboString = robo.getX() + "," + robo.getY();
        if(roboString.equals(this.getId())){
            System.out.println("O robô " + robo.getCor() + " encostou na bomba!");
            robo.setExplodiu(true);
        }
    }

}
