package br.com.furb.web2.Exceptions;

public class EquipamentoNaoEncontradoException extends RuntimeException {
    public EquipamentoNaoEncontradoException(String message) {
        super(message);
    }
}
