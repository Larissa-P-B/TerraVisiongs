package br.com.api.terravision.exception;

public class CidadeNaoEncontradaException
        extends RuntimeException {

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}