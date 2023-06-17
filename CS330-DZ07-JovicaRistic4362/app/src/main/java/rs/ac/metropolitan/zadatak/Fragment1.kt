package rs.ac.metropolitan.zadatak

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.widget.Toast

class Fragment1 : PreferenceFragment() {

    val btn1: CheckBoxPreference
        get() { return findPreference("btn1") as CheckBoxPreference }
    val btn2: CheckBoxPreference
        get() { return findPreference("btn2") as CheckBoxPreference }
    val btn3: CheckBoxPreference
        get() { return findPreference("btn3") as CheckBoxPreference }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.preferences)

        btn1.setOnPreferenceChangeListener { preference, any -> processCbClick() }
        btn2.setOnPreferenceChangeListener { preference, any -> processCbClick() }
        btn3.setOnPreferenceChangeListener { preference, any -> processCbClick() }
    }



    fun processCbClick(): Boolean{


        if (btn1.isChecked && !btn2.isChecked && !btn3.isChecked){
            Toast.makeText(
                context,
                "Odgovor je tacan",
                Toast.LENGTH_LONG
            ).show()
        } else{
            Toast.makeText(
                context,
                "Odgovor nije tacan",
                Toast.LENGTH_LONG
            ).show()
        }


        return true
    }
}