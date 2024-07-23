public class Robo {
    // Atributos
    protected int x;
    protected int y;
    protected String cor;
    protected int movimentoValido;
    protected int movimentoInvalido;

    protected String sentido;

    // Construtor
    public Robo(String cor) {
        this.cor = cor;
        this.x = 0;
        this.y = 0;
        this.movimentoValido = 0;
        this.movimentoInvalido = 0;

        this.sentido = "direita";

    }

    // Getters e Setters
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return this.cor;
    }

    public int getMovimentoValido() {
        return this.movimentoValido;
    }

    public int getMovimentoInvalido() {
        return this.movimentoInvalido;
    }

    // Metodos de incremento em atributos
    public void incrementaMovimentoValido() {
        this.movimentoValido++;
    }

    public void incrementaMovimentoInvalido() {
        this.movimentoInvalido++;
    }

    // moverPadrao() -> move peca
    /*
     * public void mover(String direcao) throws MovimentoInvalidoException {
     * int novoX = this.getX();
     * int novoY = this.getY();
     * switch (direcao) {
     * case "right":
     * novoY++;
     * break;
     * case "left":
     * novoY--;
     * break;
     * case "down":
     * novoX--;
     * break;
     * case "up":
     * novoX++;
     * break;
     * default:
     * // Verifica se opção recebe um dos cases acima.
     * throw new IllegalArgumentException("Direção aplicada inválida! " + direcao);
     * }
     * try {
     * validarMovimento(novoX, novoY);
     * this.setX(novoX);
     * this.setY(novoY);
     * incrementaMovimentoValido();
     * System.out.println(
     * "Nova coordenada do robô " + this.getCor() + ": (" + this.getY() + "," +
     * this.getX() + ").");
     * } catch (MovimentoInvalidoException e) {
     * incrementaMovimentoInvalido();
     * throw e; // Relanca a mesma excecao lancada
     * }
     * }
     */

    private int TAMANHO = 4;

    public void mover(String direcao) throws MovimentoInvalidoException {
        int novoX = this.x;
        int novoY = this.y;

        switch (direcao.toLowerCase()) {
            case "down":
                novoY--;
                break;
            case "up":
                novoY++;
                break;
            case "left":
                novoX--;
                break;
            case "right":
                novoX++;
                break;
            default:
                // Verifica se opção recebe um dos cases acima.
                throw new IllegalArgumentException("Direção aplicada inválida! " + direcao);
        }

        if (novoX < 0 || novoY < 0) {
            incrementaMovimentoInvalido();
            throw new MovimentoInvalidoException(
                    "Movimento inválido: posição negativa" + "(" + novoX + ", " + novoY + ")");
        }

        if (novoX >= TAMANHO || novoY >= TAMANHO) {
            incrementaMovimentoInvalido();
            throw new MovimentoInvalidoException(
                    "Movimento inválido: fora do limite" + "(" + novoX + ", " + novoY + ")");
        }

        this.x = novoX;
        this.y = novoY;
        incrementaMovimentoValido();
        System.out.println("Nova coordenada do robô " + this.getCor() + ": (" + this.getX() + "," + this.getY() + ").");
    }

    public void mover(int direcao) throws MovimentoInvalidoException {
        String direcaoStr;
        switch (direcao) {
            case 1:
                direcaoStr = "up";
                break;
            case 2:
                direcaoStr = "down";
                break;
            case 3:
                direcaoStr = "right";
                break;
            case 4:
                direcaoStr = "left";
                break;
            default:
                throw new IllegalArgumentException("Opção inválida: " + direcao);
        }
        mover(direcaoStr); // Chama o método mover que recebe uma String
    }
    /*
     * public void incrementarX(int x) {
     * this.x = (this.x + x + TAMANHO) % TAMANHO; // Adiciona TAMANHO para garantir
     * que não seja negativo
     * 
     * }
     * 
     * public void incrementarY(int y) {
     * this.y = (this.y + y + TAMANHO) % TAMANHO; // Adiciona TAMANHO para garantir
     * que não seja negativo
     * }
     * 
     * public void movendoRobo(String direcao) throws MovimentoInvalidoException {
     * switch (direcao) {
     * case "down":
     * incrementarY(-1);
     * break;
     * case "up":
     * incrementarY(1);
     * break;
     * case "left":
     * incrementarX(-1);
     * break;
     * case "right":
     * incrementarX(1);
     * break;
     * default:
     * // Verifica se opção recebe um dos cases acima.
     * throw new IllegalArgumentException("Direção aplicada inválida! " + direcao);
     * }
     * 
     * 
     * 
     * System.out.println("Nova coordenada do robô " + this.getCor() + ": (" +
     * this.getX() + "," + this.getY() + ").");
     * }
     */

    // moverString() -> string
    /*
     * public String mover(int opcao) {
     * String direcaoStr;
     * switch (opcao) {
     * case 1:
     * direcaoStr = "up";
     * break;
     * case 2:
     * direcaoStr = "down";
     * break;
     * case 3:
     * direcaoStr = "right";
     * break;
     * case 4:
     * direcaoStr = "left";
     * break;
     * default:
     * // Verifica se opção recebe um dos cases acima.
     * throw new IllegalArgumentException("Opção aplicada inválida! " + opcao);
     * }
     * return direcaoStr;
     * }
     */

    // encontrouAlimento() -> boolean
    public boolean encontrouAlimento(Alimento alimento) {
        return this.getX() == alimento.getX() && this.getY() == alimento.getY();
    }

    // validarMovimento -> Exception
    public void validarMovimento(int x, int y) throws MovimentoInvalidoException {
        if (x < 0 || y < 0 || x > 3 || y > 3) {
            throw new MovimentoInvalidoException("Movimento inválido para a posição: (" + x + "," + y + ")!");
        }

    }
}
