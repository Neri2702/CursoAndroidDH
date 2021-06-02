fun main() {
    val cesar = Cliente(1234, "Rodrigues", "23123", 2343242)
    val contaPoupancaCaixa = ContaPoupanca1(cliente = cesar)
    contaPoupancaCaixa.deposito(200.0)
    contaPoupancaCaixa.recolherJuros()

    val contaCorrentItau = ContaCorrente1(cliente = cesar)
    contaCorrentItau.sacar()
}

class Cliente(
    numeroCliente: Int,
    sobrenome: String,
    rg: String,
    cpf: Int
)

open class Conta(cliente: Cliente) {

    var saldo: Double = 0.0

    fun deposito(valor: Double) {
        saldo += valor
        println("O novo saldo é $saldo")
    }

    open fun sacar() {
        println("fazer saque")
    }

    fun consultarSaldo() {
       println("consultar saldo")
    }
}

class ContaPoupanca1(
    cliente: Cliente,
    var taxaJuros: Double = 3.5
) : Conta(cliente = cliente) {

    fun recolherJuros() {
        val juros = saldo * (taxaJuros / 100)
        saldo += juros
        println("Meu novo saldo é $saldo")
    }
}

class ContaCorrente1(
    cliente: Cliente
) : Conta(cliente = cliente) {

    fun depositarCheque() {
       println("depositar um novo cheque")
    }

    override fun sacar() {
       println("sacar conta corrente")
    }

}
