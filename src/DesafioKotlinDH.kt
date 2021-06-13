import java.time.LocalDate

fun main(){
    val digital = DigitalHouseManager()

    var aluno = Aluno("Arnaldo", "Neri", 22)
    var alunoA = Aluno("Pamela", "Texeira", 23)
    var alunoB = Aluno("Camila", "Novaes", 24)
    var alunoC = Aluno("Esther", "Souza", 25)
    var alunoD = Aluno("Madel", "Cecin", 26)

    digital.registrarProfTitular(
        "César",
        "Rodrigues",
        11,
        "Android")

    digital.registrarProfAdjunto(
        "Edu",
        "Misina",
        10,
        44)

    digital.registrarProfTitular(
        "Antonio",
        "Paes",
        13,
        "IOS")

    digital.registrarProfAdjunto(
        "Carlos",
        "Souza",
        12,
        40)


    digital.registrarCurso(
        "IOS",
        1234,
        3)
    digital.registrarCurso("Android",
        4321,
        2)


    digital.alocarProf(
        1234,
        10,
        11)
    digital.alocarProf(
        4321,
        12,
        13)


    digital.matricularAluno(22, 1234)

    digital.matricularAluno(23, 4321)

    digital.matricularAluno(24, 1234)

    digital.matricularAluno(25, 4321)

    digital.matricularAluno(26, 1234)

}


class Aluno (var nome:String,
             var sobrenome:String,
             var codAluno:Int){
}

class Curso (var nome:String,
             var codCurso:Int,
             var quantidadeAluno:Int,
             var profTitular: Professor? = null,
             var profAdjunto: Professor? = null) {

    var qtdAlunos:Int = 0
    var listaAlunos = mutableListOf<Aluno>()

    fun turmaLotada():Boolean?{
        if (qtdAlunos == quantidadeAluno){
            return true
        }
        return false
    }

    fun adicionarUmAluno(umAluno:Aluno):Boolean{
        if(this.qtdAlunos <= this.quantidadeAluno){
            qtdAlunos++
            listaAlunos.add(umAluno)
            return true
        }else return false
    }

    fun excluirAluno(umAluno: Aluno){
        listaAlunos.forEach{
            if(umAluno.codAluno == it.codAluno){
                listaAlunos.remove(it)
            }
        }
    }
}

 open class Professor (
     open var nomeProf:String,
     open var sobrenomeProf:String,
     open var codProf:Int) {

    constructor (nomeProf:String,
                 sobrenomeProf:String,
                 codProf:Int,
                 especialidadeProf:String) : this(nomeProf, sobrenomeProf, codProf) {}

    constructor (nomeProf:String,
                 sobrenomeProf:String,
                 codProf:Int,
                 horasMonitoria:Int) : this(nomeProf, sobrenomeProf, codProf) {}
}

class ProfessorTitular (override var nomeProf:String,
                        override var sobrenomeProf:String,
                        override var codProf:Int,
                        private var especialidadeProf:String)
    :Professor(
    nomeProf,
    sobrenomeProf,
    codProf){
}

class professorAdjunto (override var nomeProf:String,
                        override var sobrenomeProf:String,
                        override var codProf:Int,
                        var hrsMonitoria:Int)
    :Professor(
    nomeProf,
    sobrenomeProf,
    codProf){
}

class Matricula(
    var aluno: Aluno?,
    var curso: Curso?,
    var dataMatricula: LocalDate = LocalDate.now()
) {
}

class DigitalHouseManager{

    var alunos = mutableListOf<Aluno>()
    var professores = mutableListOf<Professor>()
    var cursos = mutableListOf<Curso>()
    var matriculas = mutableListOf<Matricula>()

    fun registrarCurso(nome:String, codigo:Int, quantidadeMaxAluno:Int){
        cursos.add(Curso(nome, codigo, quantidadeMaxAluno))
    }

    fun excluirCurso(codigoCurso:Int){
        cursos.forEach{
            if(it.codCurso == codigoCurso){
                cursos.remove(it)
            }
        }
    }


    fun registrarProfAdjunto(
        nomeProf:String,
        sobrenomeProf:String,
        codProf:Int,
        hrsMonitoria:Int){
        professores.add(Professor(
            nomeProf,
            sobrenomeProf,
            codProf,
            hrsMonitoria))
    }

    fun registrarProfTitular(
        nomeProf:String,
        sobrenomeProf:String,
        codProf:Int,
        especialidade:String){
        professores.add(Professor(
            nomeProf,
            sobrenomeProf,
            codProf,
            especialidade))
    }

    fun excluirProf(codigoProfessor: Int){
        professores.forEach{
            if(it.codProf == codigoProfessor){
                professores.remove(it)
            }
        }
    }

    fun matricularAluno(codigoAluno: Int, codigoCurso:Int){
        var aluno1:Aluno? = null
        var curso1:Curso? = null
        alunos.forEach{ if(codigoAluno == it.codAluno){ aluno1 = it } }
        cursos.forEach{ if(codigoCurso == it.codCurso){ curso1 = it } }
        if(curso1?.turmaLotada() == false){
            matriculas.add(Matricula(aluno1, curso1, LocalDate.now()))
            println(matriculas.last().dataMatricula)
        }else{
            println("Infelizmente não temos mais vagas.")
        }
    }

    fun alocarProf(codCurso:Int, codProfAdjunto: Int, codProfTitular: Int){
        var profadj:Professor? = null
        var proftitu:Professor? = null
        professores.forEach{ if(it.codProf == codProfAdjunto){ profadj = it } }
        professores.forEach{ if(it.codProf == codProfTitular){ proftitu = it } }
        cursos.forEach{
            if(it.codCurso == codCurso){
                it.profTitular = proftitu
                it.profAdjunto = profadj
            }
        }
    }
}