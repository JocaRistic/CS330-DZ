package rs.ac.metropolitan.zadatak1

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(
                baseContext,
                "Portrait mode",
                Toast.LENGTH_LONG
            ).show()
        }
        else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(
                baseContext,
                "Landscape mode",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun prikaziNaziv(view: View){
        Toast.makeText(
            baseContext,
            "Univerzitet Metropolitan",
            Toast.LENGTH_LONG
        ).show()
    }

    fun prikaziLokaciju(view: View){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =Uri.parse("geo:44.83067811230858, 20.45513576775011")

        startActivity(intent)
    }


}