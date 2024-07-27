import java.util.List;
public class Tabuleiro {
    private String[][] plano;
    private final int TAMANHO = 4;

    public Tabuleiro() {
        this.plano = new String[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    // Metodo Getter
    public int getTAMANHO(){
        return this.TAMANHO;
    }

    // Inicializar visualização de tabuleiro
    public void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                plano[i][j] = ".";
            }
        }
    }

    // Metodo utilizado para atualizar a matriz plano[][]
    public void atualizarTabuleiro(Robo robo, String icone, Alimento alimento){
        inicializarTabuleiro();
        plano[robo.getX()][robo.getY()] = icone; 
        adicionarAlimento(alimento);
    }

    // Sobrecarga de metodo atualizarTabuleiro(), implementando visualizacao de obstaculos
    public void atualizarTabuleiro(Robo robo, String icone, Alimento alimento, List<Obstaculo> obstaculos){
        inicializarTabuleiro();
        plano[robo.getX()][robo.getY()] = icone;
        adicionarAlimento(alimento);
        for(Obstaculo obstaculo : obstaculos){
            adicionarObstaculo(obstaculo);
        }
    }

    // Adicao de elementos no tabuleiro
    public void adicionarAlimento(Alimento alimento) {
        plano[alimento.getX()][alimento.getY()] = "A";
    }

    public void adicionarAlimentoEncontrado(Alimento alimento){
        plano[alimento.getX()][alimento.getY()] = "X";
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

    public void retirarRobo(Robo robo){
        plano[robo.getX()][robo.getY()] = ".";
    }

    public void retirarObstaculo(Obstaculo obstaculo){
        int []coordenadas = obstaculo.getCoordenadas();
        plano[coordenadas[0]][coordenadas[1]] = ".";
    }

    // Visualização padrao de Tabuleiro -> Matriz
    public void mostrarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(plano[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Visualizacao de Tabuleiro -> Plano xy
    public void mostrarTabuleiroRotacionado() {
        String[][] rotacionado = rotacionarTabuleiro(plano);

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(rotacionado[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private String[][] rotacionarTabuleiro(String[][] matriz) {
        String[][] rotacionado = new String[TAMANHO][TAMANHO];

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                rotacionado[i][j] = matriz[j][TAMANHO - 1 - i];
            }
        }
        return rotacionado;
    }

}
