import br.edu.umfg.estrategia.*;


public class Main {
    public static void main(String[] args) {
        try {
            String formaDePagamento = "GG";

            Produto produto1 = new Produto("0001",
                    "Cola cola 350ml", 3.59);
            Produto produto2 = new Produto("0002",
                    "X-salada", 15.99);
            Carrinho carrinho = new Carrinho();

            carrinho.adicionarProduto(produto1);
            carrinho.adicionarProduto(produto2);


            if (formaDePagamento.equalsIgnoreCase("GG")) {
                MeioPagamentoCieloEstrategia pagamentoCredito = new MeioPagamentoCieloEstrategia("4478-4399-6886-4632", "98765432101", "777", "04/22", "V");
                pagamentoCredito.validarDadosCredito();
                carrinho.pagar(pagamentoCredito);

            } else if (formaDePagamento.equalsIgnoreCase("GV")) {
                MeioPagamentoCieloEstrategia pagamentoDebito = new MeioPagamentoCartaoDebito("8557-8956-6523-7451", "12345678901", "545", "04/27", "G");
                pagamentoDebito.validarDadosDebito();
                carrinho.pagar(pagamentoDebito);

            } else if (formaDePagamento.equalsIgnoreCase("G")) {
                carrinho.pagar(new MeioPagamentoDinheiroEstrategia());

            }
        } catch (Exception ex){
            System.out.println("Error  " + ex);
        }
    }
}


