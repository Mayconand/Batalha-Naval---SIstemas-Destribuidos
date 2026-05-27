package edu.ucsal.model.entity;

import java.io.Serializable;

import edu.ucsal.enums.ResultadoAtaque;
import edu.ucsal.enums.TipoBarco;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro implements Serializable{

    private final int TAMANHO = 10;

    private List<Barco> barcos = new ArrayList<>();

    private List<Coordenada> ataquesRecebidos = new ArrayList<>();

    public int getTAMANHO() {
        return TAMANHO;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public boolean adicionarBarco(Barco barco) {

        List<Coordenada> coordenadas = barco.gerarCoordenadas();

        for (Coordenada coordenada : coordenadas) {

            if (coordenada.getX() < 0 || coordenada.getX() >= TAMANHO) {
                return false;
            }

            if (coordenada.getY() < 0 || coordenada.getY() >= TAMANHO) {
                return false;
            }

            for (Barco barcoExistente : barcos) {

                if (barcoExistente.getLista_coordenadas().contains(coordenada)) {
                    return false;
                }
            }
        }

        if(!quantidadePermitida(barco.getTipoBarco())){
            return false;
        }

        barcos.add(barco);

        return true;
    }

    private boolean quantidadePermitida(TipoBarco tipoBarco){

        int quantidade = 0;

        for(Barco barco : barcos){

            if(barco.getTipoBarco() == tipoBarco){
                quantidade++;
            }
        }

        return quantidade < tipoBarco.getQuantidadeMaxima();
    }

    public ResultadoAtaque receberAtaque(Coordenada coordenada){

        if(ataquesRecebidos.contains(coordenada)){
            return ResultadoAtaque.REPETIDO;
        }

        ataquesRecebidos.add(coordenada);

        for(Barco barco : barcos){

            if(barco.getLista_coordenadas().contains(coordenada)){

                barco.getCoordenadasAtingidas().add(coordenada);

                if(barco.barcoAfundou()){
                    return ResultadoAtaque.AFUNDOU;
                }

                return ResultadoAtaque.ACERTOU;
            }
        }

        return ResultadoAtaque.ERROU;
    }
    public String visualizarTabuleiro(){

        char[][] matriz = new char[10][10];

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                matriz[i][j] = '~';
            }
        }

        for(Barco barco : barcos){

            for(Coordenada coordenada : barco.getLista_coordenadas()){

                matriz[coordenada.getY()][coordenada.getX()] = 'B';
            }
        }

        for(Coordenada coordenada : ataquesRecebidos){

            if(matriz[coordenada.getY()][coordenada.getX()] == 'B'){

                matriz[coordenada.getY()][coordenada.getX()] = 'X';

            }else{

                matriz[coordenada.getY()][coordenada.getX()] = 'O';
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("  0 1 2 3 4 5 6 7 8 9\n");

        for(int i = 0; i < 10; i++){

            sb.append(i).append(" ");

            for(int j = 0; j < 10; j++){

                sb.append(matriz[i][j]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}