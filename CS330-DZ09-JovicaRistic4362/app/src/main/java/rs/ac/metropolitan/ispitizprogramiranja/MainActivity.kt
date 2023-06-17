package rs.ac.metropolitan.ispitizprogramiranja

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    var brojIndeksaTxt: EditText? = null
    var imeTxt: EditText? = null
    var brojBodovaTxt: EditText? = null
    var saveBtn: Button? = null
    var listBtn: Button? = null
    var deleteBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = DBAdapter(this)
        try {
            val destPath = "/data/data/" + packageName +  "/databases"
            val f = File(destPath)
            if (!f.exists()) {
                f.mkdirs()
                f.createNewFile()
                //---kopira db iz assets foldera u databases folder---
                CopyDB(
                    baseContext.assets.open("ispit"),
                    FileOutputStream("$destPath/ispit")
                )
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        brojIndeksaTxt = findViewById<View>(R.id.txtBrojIndeksa) as EditText
        imeTxt = findViewById<View>(R.id.txtIme) as EditText
        brojBodovaTxt = findViewById<View>(R.id.txtBrojBodova) as EditText
        saveBtn = findViewById<View>(R.id.btnSave) as Button

        saveBtn!!.setOnClickListener {
            val brojindeksa = brojIndeksaTxt!!.text.toString().toInt()
            val ime = imeTxt!!.text.toString().trimIndent()
            val brojBodova = brojBodovaTxt!!.text.toString().toDouble()

            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            startActivity(intent)

            db.open()
            val c = db.insertStudent(brojindeksa, ime, brojBodova)
            db.close()

            Toast.makeText(applicationContext, "Details Inserted Successfully", Toast.LENGTH_SHORT)
                .show()
        }

        listBtn = findViewById<View>(R.id.btnList) as Button
        listBtn!!.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        deleteBtn = findViewById<View>(R.id.btnDelete) as Button
        deleteBtn!!.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }

    }

    @Throws(IOException::class)
    fun CopyDB(
        inputStream: InputStream,
        outputStream: OutputStream
    ) {

        //---kopira 1KB u datom trenutku---
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        inputStream.close()
        outputStream.close()
    }

}