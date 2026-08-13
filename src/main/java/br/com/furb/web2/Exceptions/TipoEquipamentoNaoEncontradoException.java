package br.com.furb.web2.Exceptions;

public class TipoEquipamentoNaoEncontradoException extends RuntimeException {
    public TipoEquipamentoNaoEncontradoException(String message) {
        super(message);
    }
}
