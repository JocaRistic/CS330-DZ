package rs.ac.metropolitan.zadatak

import android.content.Context
import com.beust.klaxon.Klaxon
import java.io.BufferedReader

private val klaxon = Klaxon()

data class FoodModel(val foods: List<Food>) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<FoodModel>(json)

        fun readAsset(context: Context, fileName: String): String =
            context
                .assets
                .open(fileName)
                .bufferedReader()
                .use(BufferedReader::readText)
    }
}

data class Food(
    val id: String,
    val title: String,
    val difficulty: String,
    val portion: String,
    val time: String,
    val description: String,
    val ingredients: ArrayList<String>,
    val method: ArrayList<String>,
    val image: String
) {
}