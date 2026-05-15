package edu.ucsal.enums;

public enum TipoBarco {
    PORTA_AVIOES(5),
    ENCORACADO(4),
    CRUZADOR(3),
    SUBMARINO(2);

    private int tamanho;

    TipoBarco(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }
}
