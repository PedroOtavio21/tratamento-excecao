public class Robo {
    // Atributos
    private int coordenadaX;
    private int coordenadaY;
    private String cor;

    // Construtor
    public Robo(String cor){
        this.cor = cor;
        this.coordenadaX = 0;
        this.coordenadaY = 0;
    }

    // Getters e Setters
    public int getCoordenadaX(){
        return this.coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX){
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY(){
        return this.coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY){
        this.coordenadaY = coordenadaY;
    }

    public String getCor(){
        return this.cor;
    }

    // mover() padrão
    public void mover(String direcao) throws MovimentoInvalidoException{
        int novoX = this.getCoordenadaX();
        int novoY = this.getCoordenadaX();
        switch (direcao) {
            case "up":
                novoY++;
                break;
            case "down":
                novoY--;
                break;
            case "right":
                novoX++;
                break;
            case "left":
                novoX--;
                break;
            default:
                // Verifica se opção recebe um dos cases acima.
                throw new IllegalArgumentException("Direção inserida inválida! " + direcao);
        }
        validarMovimento(novoX, novoY);
        this.setCoordenadaX(novoX);
        this.setCoordenadaY(novoY);
        System.out.println("Nova coordenada do robô: (" + this.getCoordenadaX() + "," + this.getCoordenadaY() + ").");
    }

    // mover() string
    public String mover(int opcao){
        String direcaoStr;
        switch (opcao) {
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
                // Verifica se opção recebe um dos cases acima.
                throw new IllegalArgumentException("Opção inserida inválida! " + opcao);
        }
        return direcaoStr;
    }

    // encontrouAlimento()
    public boolean encontrouAlimento(Alimento alimento){
        return this.getCoordenadaX() == alimento.getX() || this.getCoordenadaY() == alimento.getY();
    }

    // validarMovimento -> Exception
    public void validarMovimento(int x, int y) throws MovimentoInvalidoException{
        if(x < 0 || y < 0){
            throw new MovimentoInvalidoException("Movimento inválido para a posição: (" + x + "," + y + ")!");
        }
    }
}
