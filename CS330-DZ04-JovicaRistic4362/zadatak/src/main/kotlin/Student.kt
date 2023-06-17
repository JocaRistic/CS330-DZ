data class Student(
    val index: Int,
    val ime: String,
    val prezime: String,
    var predmeti: List<Predmet>
) {
    override fun toString(): String {
        return "$index $ime $prezime"
    }
}