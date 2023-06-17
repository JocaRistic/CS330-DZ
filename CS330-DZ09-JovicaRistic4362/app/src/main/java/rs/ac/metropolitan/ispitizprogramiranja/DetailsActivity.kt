package rs.ac.metropolitan.ispitizprogramiranja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //---preuzimanje svih studenta---
        val db = DBAdapter(this)
        db.open()
        val c = db.allStudents

        val userList = ArrayList<HashMap<String, String>>()

        if (c.moveToFirst()) {
            do {
                val student = HashMap<String, String>()

                student["brojIndeksa"] = "Broj indeksa: ${c.getString(0).toString()}"
                student["ime"] = "Ime: ${c.getString(1).toString()}"
                student["brojBodova"] = "Broj bodova: ${c.getString(2).toString()}"

                userList.add(student)
            } while (c.moveToNext())
        }
        db.close()

        val lv = findViewById<View>(R.id.user_list) as ListView
        val adapter: ListAdapter = SimpleAdapter(
            this@DetailsActivity,
            userList,
            R.layout.list_row,
            arrayOf("brojIndeksa", "ime", "brojBodova"),
            intArrayOf(R.id.brojIndeksa, R.id.ime, R.id.brojBodova)
        )
        lv.adapter = adapter
        val back = findViewById<View>(R.id.btnBack) as Button
        back.setOnClickListener {
            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }



    }
}