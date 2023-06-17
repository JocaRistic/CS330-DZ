data class Predmet(
    val sifraPredmeta: String,
    val nazivPredmeta: String,
    val espbPoeni: Int,
    val semestar: Int,
    val obavezan: Boolean
) {
    override fun toString(): String {
        return "$sifraPredmeta-$nazivPredmeta ESP:$espbPoeni semestar:$semestar (${if (obavezan) return "obavezan" else "izborni"})"
    }
}