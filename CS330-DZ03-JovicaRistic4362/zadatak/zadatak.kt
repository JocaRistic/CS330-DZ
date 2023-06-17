import kotlin.random.Random

fun main() {
    var prisustvo = Prisustvo()
    for (i in 1..15) {
        println("Odsutni u nastavnoj nedelji $i:")
        for(student in prisustvo.getStudenti()){
            val prisutan = Random.nextBoolean()
            if(prisutan == true){
                prisustvo.studentPrisutan(student)
            } else {
                student.stampajStudenta()
            }
        }
    }
}

class Student(val indeks: Int, val ime: String, val prezime: String) {
    fun stampajStudenta() {
        println("$indeks $ime $prezime")
    }
}

class Prisustvo {
    val student1: Student = Student(4362, "Jovica", "Ristic")
    val student2: Student = Student(1111, "Nikola", "Nikolic")
    val student3: Student = Student(2222, "Jovana", "Jovanovic")
    val student4: Student = Student(3333, "Ana", "Jovic")

    private var prisustvoMapa: MutableMap<Student, Int?> = mutableMapOf(
        student1 to null,
        student2 to null,
        student3 to null,
        student4 to null
    )

    fun studentPrisutan(student: Student) {
        val trenutnoPrisustvo = prisustvoMapa[student]
        trenutnoPrisustvo?.let {
            prisustvoMapa[student] = trenutnoPrisustvo + 1
        } ?: run {
            prisustvoMapa[student] = 1
        }
    }

    fun getStudenti(): List<Student> {
        return prisustvoMapa.keys.toList()
    }

    fun getPrisustvoStudenta(student: Student): Int? {
        return prisustvoMapa[student]
    }

}

