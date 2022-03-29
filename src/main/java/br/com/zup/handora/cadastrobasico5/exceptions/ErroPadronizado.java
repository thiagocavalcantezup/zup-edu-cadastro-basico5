package br.com.zup.handora.cadastrobasico5.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.FieldError;

public class ErroPadronizado {

    @JsonProperty("codigo_http")
    private Integer codigoHttp;

    @JsonProperty("mensagem_http")
    private String mensagemHttp;

    @JsonProperty("mensagem_geral")
    private String mensagemGeral;

    @JsonProperty("mensagens")
    private List<String> mensagens;

    public ErroPadronizado(Integer codigoHttp, String mensagemHttp, String mensagemGeral) {
        this.codigoHttp = codigoHttp;
        this.mensagemHttp = mensagemHttp;
        this.mensagemGeral = mensagemGeral;
        this.mensagens = new ArrayList<>();
    }

    public void adicionarErro(FieldError fieldError) {
        String field = fieldError.getField();

        if (field.equals("dataNascimento")) {
            field = "data_nascimento";
        }

        mensagens.add(field + ": " + fieldError.getDefaultMessage());
    }

    public void adicionarErro(String erro) {
        mensagens.add(erro);
    }

    public Integer getCodigoHttp() {
        return codigoHttp;
    }

    public String getMensagemHttp() {
        return mensagemHttp;
    }

    public String getMensagemGeral() {
        return mensagemGeral;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

}
