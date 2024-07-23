public class Tabuleiro {
    private String[][] plano;
    private final int TAMANHO = 4;

    public Tabuleiro() {
        this.plano = new String[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                plano[i][j] = ".";
            }
        }
    }

    public void atualizarTabuleiro(Robo robo, String icone, Alimento alimento){
        inicializarTabuleiro();
        plano[robo.getX()][robo.getY()] = icone; 
        adicionarAlimento(alimento);
    }

    public void adicionarAlimento(Alimento alimento) {
        plano[alimento.getX()][alimento.getY()] = "A";
    }

    public void adicionarRobo(Robo robo) {
        plano[robo.getX()][robo.getY()] = "R";
    }

    public void adicionarObstaculo(Obstaculo obstaculo){
        int[] coordenadas = obstaculo.getCoordenadas();
        int x = coordenadas[0];
        int y = coordenadas[1];

        if(obstaculo instanceof Bomba){
            plano[x][y] = "B";
        } else if (obstaculo instanceof Rocha){
            plano[x][y] = "R";
        }
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(plano[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void retirarRobo(Robo robo) {

    }
}
