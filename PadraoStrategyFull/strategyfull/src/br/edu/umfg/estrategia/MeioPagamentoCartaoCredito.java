package br.edu.umfg.estrategia;

public class MeioPagamentoCartaoCredito extends MeioPagamentoCieloEstrategia{
    public MeioPagamentoCartaoCredito(String numeroCartao, String cpf, String cvv, String dataValidade) {
        super(numeroCartao, cpf, cvv, dataValidade, "C");
    }
    @Override
    public void validarDadosCredito(){
        super.validarDadosCredito();
    }
}
