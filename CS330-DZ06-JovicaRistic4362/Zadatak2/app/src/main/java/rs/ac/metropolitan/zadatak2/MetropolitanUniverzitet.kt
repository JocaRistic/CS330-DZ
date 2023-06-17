package rs.ac.metropolitan.zadatak2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MetropolitanUniverzitet : AppCompatActivity() {

    var izabranFaks: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.meni, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fit -> izabranFaks = 0
            R.id.fdu -> izabranFaks = 1
            R.id.fam -> izabranFaks = 2
        }

        Toast.makeText(
            baseContext,
            "Fakultet izabran!",
            Toast.LENGTH_SHORT
        ).show()

        return true
    }

    fun klikDa(view: View){
        val tekst =findViewById<TextView>(R.id.tekst)

        when(izabranFaks) {
            0 -> tekst.text = "Student ste fakulteta informacionih tehnologija"
            1 -> tekst.text = "Student ste fakulteta digitalnih umetnosti"
            2 -> tekst.text = "Student ste fakulteta za menadzment"
        }
    }

    fun klikNe(view: View){
        val tekst =findViewById<TextView>(R.id.tekst)

        when(izabranFaks) {
            0 -> tekst.text = "Niste student fakulteta informacionih tehnologija"
            1 -> tekst.text = "Niste student fakulteta digitalnih umetnosti"
            2 -> tekst.text = "Niste student fakulteta za menadzment"
        }
    }
}