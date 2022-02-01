/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.sistema_estagios.model;

/**
 *
 * @author henri
 */
public class AvaliacaoRelatorio {
    private String dataApresentacaoRelatorio;
    private String assunto;
    private String avaliação;
    private String dataAvaliacao;

    public String getDataApresentacaoRelatorio() {
        return dataApresentacaoRelatorio;
    }

    public void setDataApresentacaoRelatorio(String dataApresentacaoRelatorio) {
        this.dataApresentacaoRelatorio = dataApresentacaoRelatorio;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAvaliação() {
        return avaliação;
    }

    public void setAvaliação(String avaliação) {
        this.avaliação = avaliação;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
    
    
}
