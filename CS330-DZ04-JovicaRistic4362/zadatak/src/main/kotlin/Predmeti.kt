
class Predmeti : RegistracijaOdjava {
    private val listaPredmeta: MutableList<Predmet> = mutableListOf()

    fun dodajPredmet(predmet: Predmet){
        listaPredmeta.add(predmet)
    }

    fun vratiPredmete(): List<Predmet> {
        return listaPredmeta
    }

    override fun registrujStudenta(student: Student, predmet: Predmet) {
        var novaLista = student.predmeti.toMutableList()
        if (novaLista.contains(predmet)){
            println("Student vec slusa predmet ${predmet.nazivPredmeta}")
        } else {
            novaLista.add(predmet)
            student.predmeti = novaLista.toList()
            println("Student ${student.ime} ${student.prezime} je uspesno prijavljen na predmet ${predmet.nazivPredmeta}")
        }

    }

    override fun odjaviStudenta(student: Student, predmet: Predmet) {
        var novaLista = student.predmeti.toMutableList()
        if (novaLista.contains(predmet)){
            novaLista.remove(predmet)
            student.predmeti = novaLista.toMutableList()
            println("Student ${student.ime} ${student.prezime} je uspesno odjavljen sa predmeta ${predmet.nazivPredmeta}")
        } else {
            println("Student ne slusa predmet ${predmet.nazivPredmeta}")
        }

    }


}