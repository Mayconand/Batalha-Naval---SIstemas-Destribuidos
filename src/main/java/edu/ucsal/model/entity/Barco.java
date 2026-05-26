package edu.ucsal.model.entity;
import edu.ucsal.enums.Direcao;
import edu.ucsal.enums.TipoBarco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barco implements Serializable{

    private Coordenada coordenadaInicial;
    private TipoBarco tipoBarco;
    private Direcao direcao;
    private List<Coordenada> lista_coordenadas = new ArrayList<>();
    private List<Coordenada> coordenadasAtingidas = new ArrayList<>();



    public Barco(Coordenada coordenada, TipoBarco tipoBarco, Direcao direcao) {
        this.coordenadaInicial = coordenada;
        this.tipoBarco = tipoBarco;
        this.direcao = direcao;


    }

    public Coordenada getCoordenada() {
        return coordenadaInicial;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenadaInicial = coordenada;
    }

    public TipoBarco getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(TipoBarco tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public List<Coordenada> getLista_coordenadas() {
        return lista_coordenadas;
    }

    public List<Coordenada> getCoordenadasAtingidas() {
        return coordenadasAtingidas;
    }


    public boolean barcoAfundou(){

        return coordenadasAtingidas.size() == lista_coordenadas.size();

    }
    public List<Coordenada> gerarCoordenadas() {

        lista_coordenadas.clear();

        for(int i = 0; i < tipoBarco.getTamanho(); i++){

            if(direcao == Direcao.VERTICAL){
                this.lista_coordenadas.add(new Coordenada(coordenadaInicial.getX(), coordenadaInicial.getY()+i));
            }

            if(direcao == Direcao.HORIZONTAL){
                this.lista_coordenadas.add(new Coordenada(coordenadaInicial.getX()+i, coordenadaInicial.getY()));
            }
        }

        return this.lista_coordenadas;

    }

}
