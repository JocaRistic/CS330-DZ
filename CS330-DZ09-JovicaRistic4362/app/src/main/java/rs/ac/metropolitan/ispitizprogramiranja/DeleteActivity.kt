package rs.ac.metropolitan.ispitizprogramiranja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val db = DBAdapter(this)

        val brojIndeksaTxt = findViewById<View>(R.id.txtBrojIndeksa) as EditText
        val deleteBtn = findViewById<View>(R.id.btnDelete) as Button

        deleteBtn.setOnClickListener {
            val brojIndeksa = brojIndeksaTxt.text.toString().toInt()

            db.open();
            if (db.deleteStudent(brojIndeksa))
                Toast.makeText(this, "Brisanje je bilo uspešno.",
                    Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Brisanje je bilo neuspešno.",
                    Toast.LENGTH_LONG).show();
            db.close();

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}