fun main(args: Array<String>) {

    val cs330: Predmet = Predmet("CS330", "Razvoj mobilnih aplikacija", 8,6, false)
    val it255: Predmet = Predmet("IT255", "Veb sistemi 1", 8,5, true)
    val it355: Predmet = Predmet("IT355", "Veb sistemi 2", 8,6, true)

    val predmeti: Predmeti = Predmeti()
    predmeti.dodajPredmet(cs330)
    predmeti.dodajPredmet(it255)
    predmeti.dodajPredmet(it355)

    val student1: Student = Student(4362, "Jovica", "Ristic", listOf(cs330, it355))
    val student2: Student = Student(1111, "Jovana", "Jovic", listOf(it355))
    val student3: Student = Student(2222, "Nikola", "Nikolic", listOf(it255))

    //registrovanje i odjavljivanje studenata sa random predmeta
    predmeti.registrujStudenta(student1, predmeti.vratiPredmete().random())
    predmeti.registrujStudenta(student2, predmeti.vratiPredmete().random())
    predmeti.registrujStudenta(student3, predmeti.vratiPredmete().random())
    predmeti.odjaviStudenta(student1, predmeti.vratiPredmete().random())
    predmeti.odjaviStudenta(student2, predmeti.vratiPredmete().random())
    predmeti.odjaviStudenta(student3, predmeti.vratiPredmete().random())



}