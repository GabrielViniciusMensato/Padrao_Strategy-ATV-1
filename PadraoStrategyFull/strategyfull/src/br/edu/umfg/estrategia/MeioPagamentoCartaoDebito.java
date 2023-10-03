package br.edu.umfg.estrategia;

public class MeioPagamentoCartaoDebito extends MeioPagamentoCieloEstrategia {
    public MeioPagamentoCartaoDebito(String numeroCartao, String cpf, String cvv, String dataValidade, String c) {
        super(numeroCartao, cpf, cvv, dataValidade, "C");
    }
    @Override
    public boolean validarDadosDebito() {
        super.validarDadosDebito();
        return false;
    }
}


