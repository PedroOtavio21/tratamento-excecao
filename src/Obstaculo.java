public abstract class Obstaculo {
    // Atributos
    protected String id;

    // Metodos
    public Obstaculo(int x, int y){
        // Transforma a posicao do Obstaculo em uma String!
        this.id = x + "," + y; 
    }

    // Metodo Getter
    public String getId() {
        return id;
    }

    // Transformar id em => [x,y]
    public int[] getCoordenadas(){
        String[] partes = id.split(",");
        return new int[]{Integer.parseInt(partes[0]), Integer.parseInt(partes[1])};
    }

    // Metodo bater()
    public abstract void bater(Robo robo, Tabuleiro tabuleiro);
}