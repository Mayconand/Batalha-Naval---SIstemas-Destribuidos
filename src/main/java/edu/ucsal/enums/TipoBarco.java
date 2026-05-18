package edu.ucsal.enums;

public enum TipoBarco {

    PORTA_AVIOES(5,1),
    ENCORACADO(4,1),
    CRUZADOR(3,2),
    SUBMARINO(2,2);

    private int tamanho;
    private int quantidadeMaxima;

    TipoBarco(int tamanho, int quantidadeMaxima) {
        this.tamanho = tamanho;
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }
}