package rs.ac.metropolitan.zadatak

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var selectedItemIndex = 0;

    fun showDialog(view: View){
        val opcije = arrayOf("FIT","FAM","FDU");
        val linkovi = arrayOf("https://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/",
            "https://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/",
            "https://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/")

        MaterialAlertDialogBuilder(this)
            .setTitle("Izaberite fakultet")
            .setSingleChoiceItems(opcije, selectedItemIndex){
                    dialog, which ->
                selectedItemIndex = which
            }
            .setPositiveButton("OK"){
                    dialog, which ->
                openLink(linkovi[selectedItemIndex])
            }
            .setNeutralButton("Cancel"){
                    dialog, which->
                dialog.cancel()
            }
            .show()
    }

    fun openLink(link: String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data= Uri.parse(link)
        startActivity(openURL);
    }
}