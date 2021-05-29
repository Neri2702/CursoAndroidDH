fun main() {
    val FerrariF50 = Ferrari()
    FerrariF50.acelerar()
    FerrariF50.frear()
}
    abstract class Automovel {

        abstract val nome: String


        abstract fun acelerar()


        abstract fun frear()

    }
    class Ferrari: Automovel(){

        var motor = "530cv"
        val cor = "Vermelha"

        override val nome: String = "Ferrari F50"



        override fun acelerar() {
            println("Uma ferrari acelerando")
        }

        override fun frear() {
          println("Uma ferrari freando")
        }

        override fun equals(other: Any?): Boolean {
            val outraFerrari = other as? Ferrari
            return when(other) {
                is Ferrari ->{
                    this.nome == outraFerrari?.nome
                }
                else -> {
                    super.equals(other)
                }
            }

        }

        override fun toString(): String {
            return "O meu nome é $nome, tenho $motor de cavalos de potência e " +
                    "minha cor é $cor"
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }

    }
