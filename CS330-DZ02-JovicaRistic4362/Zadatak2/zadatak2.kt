fun main() {
    println("Unesite visinu u cm: ")
    val visina = procitajSaKonzole()
    println("Unesite tezinu u kg: ")
    val tezina = procitajSaKonzole()

    val visinaMetri = visina / 100
    val bmi = tezina / (visinaMetri * visinaMetri)

    println("Vas BMI je: $bmi")
}

fun procitajSaKonzole(): Double {
    val unos = readLine()
    return unos?.toDouble() ?: 0.0
}

