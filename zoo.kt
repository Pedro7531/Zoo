enum class Genero {
    MACHO, FEMEA
}

enum class Familia {
    FELIDAE, CANIDAE, HOMINIDAE, PSITTACIDAE, RANIDAE
}

enum class Ordem {
    CARNIVORA, PRIMATES, PASSERIFORMES, SQUAMATA, ANURA
}

enum class Classe {
    MAMMALIA, AVES, REPTILIA, AMPHIBIA, ACTINOPTERYGII
}

enum class Filo {
    CHORDATA, ARTHROPODA, MOLLUSCA, NEMATODA
}

enum class Reino {
    ANIMALIA, PLANTAE, FUNGI, PROTISTA
}

enum class Dieta {
    HERBIVORO, CARNIVORO, ONIVORO
}

enum class TipoGaiola {
    SAVANA, AQUARIO, DESERTO, FLORESTA, POLO
}

data class Localizacao(val setor: String, val gaiolaId: String)

data class Animal(
    val apelido: String,
    val reino: Reino,
    val filo: Filo,
    val classe: Classe,
    val ordem: Ordem,
    val familia: Familia,
    val genero: Genero,
    val especie: String,
    val origem: String,
    val dieta: Dieta,
    val tipoGaiola: TipoGaiola,
    val localizacao: Localizacao
) {
    override fun toString(): String {
        return """
            Apelido: $apelido
            Taxonomia(ReFiCOFaGE): $reino > $filo > $classe > $ordem > $familia > $genero > $especie
            Origem: $origem
            Dieta: $dieta
            Tipo da Gaiola: $tipoGaiola
            Localização no Zoo: Setor ${localizacao.setor}, Gaiola ${localizacao.gaiolaId}
        """.trimIndent()
    }
}

class Zoo {
    private val animais = mutableListOf<Animal>()

    fun cadastrarAnimal(animal: Animal) {
        animais.add(animal)
        println("Animal '${animal.apelido}' cadastrado com sucesso!")
    }

    fun listarAnimais() {
        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado no zoológico.")
        } else {
            println("Animais no Zoológico:")
            animais.forEach { println(it) }
        }
    }
}

fun mostrarMenu(): Int {
    println("--------------------------")
    println("|    Menu do Zoológico   |")
    println("--------------------------")
    println("1. Cadastrar Animal")
    println("2. Listar Animais")
    println("0. Sair")
    print("Escolha uma opção: ")
    return readLine()?.toIntOrNull() ?: 0
}

fun cadastrarAnimal(zoo: Zoo) {
    print("Apelido do Animal: ")
    val apelido = readLine() ?: ""

    print("Reino (Animalia, Plantae, Fungi, Protista): ")
    val reino = try {
        Reino.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Reino inválida! Definindo como ANIMALIA.")
        Reino.ANIMALIA
    }

    print("Filo (Chordata, Arthropoda, Mollusca, Nematoda): ")
    val filo = try {
        Filo.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Filo inválida! Definindo como CHORDATA.")
        Filo.CHORDATA
    }

    print("Classe (Mammalia, Aves, Reptilia, Amphibia, Actinopterygii): ")
    val classe = try {
        Classe.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Classee inválida! Definindo como MAMMALIA.")
        Classe.MAMMALIA
    }

    print("Ordem (Carnivora, Primates, Passeriformes, Squamata, Anura): ")
    val ordem = try {
        Ordem.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Ordem inválida! Definindo como CARNIVORA.")
        Ordem.CARNIVORA
    }

    print("Família (Felidae, Canidae, Hominidae, Psittacidae, Ranidae): ")
    val familia = try {
        Familia.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Familia inválida! Definindo como FELIDAE.")
        Familia.FELIDAE
    }

    print("Gênero (Macho ou fêmea): ")
    val genero = try {
        Genero.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Genero inválida! Definindo como MACHO.")
        Genero.MACHO
    }

    print("Espécie: ")
    val especie = readLine() ?: ""

    print("Origem: ")
    val origem = readLine() ?: ""

    print("Dieta (HERBIVORO, CARNIVORO, ONIVORO): ")
    val dieta = try {
        Dieta.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Dieta inválida! Definindo como HERBIVORO.")
        Dieta.HERBIVORO
    }

    print("Tipo de Gaiola (SAVANA, AQUARIO, DESERTO, FLORESTA, POLO): ")
    val tipoGaiola = try {
        TipoGaiola.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Tipo de gaiola inválido! Definindo como SAVANA.")
        TipoGaiola.SAVANA
    }

    print("Setor da Sela: ")
    val setor = readLine() ?: ""

    print("ID da Gaiola: ")
    val gaiolaId = readLine() ?: ""

    val localizacao = Localizacao(setor, gaiolaId)
    val animal = Animal(apelido, reino, filo, classe, ordem, familia, genero, especie, origem, dieta, tipoGaiola, localizacao)

    zoo.cadastrarAnimal(animal)
}

fun main() {
    val zoo = Zoo()
    while (true) {
        when (mostrarMenu()) {
            1 -> cadastrarAnimal(zoo)
            2 -> zoo.listarAnimais()
            0 -> {
                println("Fechando o programa!")
                return
            }
            else -> println("Opção inválida! Tente novamente.")
        }
    }
}