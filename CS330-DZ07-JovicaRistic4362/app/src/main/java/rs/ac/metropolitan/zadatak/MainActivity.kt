package rs.ac.metropolitan.zadatak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = fragmentManager
        val fragmentTransaction =fragmentManager.beginTransaction()

        val fragment1 = Fragment1()

        fragmentTransaction.replace(android.R.id.content, fragment1)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}