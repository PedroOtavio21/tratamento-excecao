public abstract class Obstaculo {
    // Atributos
    protected String id;

    // Metodos
    public Obstaculo(int x, int y){
        // Transforma a posicao do Obstaculo em uma String!
        this.id = x + "," + y; 
    }

    public String getId() {
        return id;
    }

    // Metodo bater()
    public abstract void bater(Robo robo, Tabuleiro tabuleiro);
}