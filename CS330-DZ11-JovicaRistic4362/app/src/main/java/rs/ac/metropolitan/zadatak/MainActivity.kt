package rs.ac.metropolitan.zadatak

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import rs.ac.metropolitan.zadatak.ui.theme.ZadatakTheme

class MainActivity : ComponentActivity() {
    val textValue = mutableStateOf("")

    var SENT = "SMS_SENT"
    var DELIVERED = "SMS_DELIVERED"
    var sentPI: PendingIntent? = null
    var deliveredPI: PendingIntent? = null
    var smsSentReceiver: BroadcastReceiver? = null
    var smsDeliveredReceiver: BroadcastReceiver? = null
    var intentFilter: IntentFilter? = null


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sentPI = PendingIntent.getBroadcast(
            this, 0,
            Intent(SENT), 0
        )

        deliveredPI = PendingIntent.getBroadcast(
            this, 0,
            Intent(DELIVERED), 0
        )
        //-filter prijema SMS poruka-
        intentFilter = IntentFilter()
        intentFilter!!.addAction("SMS_RECEIVED_ACTION")

        //---registrovanje primaoca---
        registerReceiver(intentReceiver, intentFilter)

        setContent {
            ZadatakTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(50.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            TextField(
                                value = textValue.value,
                                onValueChange = {
                                    textValue.value = it
                                },
                                placeholder = {
                                    Text(text = "Tekst poruke")
                                }
                            )
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        ){
                            Button(onClick = {
                                sendSMS("5556", textValue.value)
                            }) {
                                Icon(painter = painterResource(id = R.drawable.baseline_sms_24), contentDescription = "sms")
                                Text(text = "Send SMS", modifier = Modifier.padding(start = 5.dp))
                            }
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Button(onClick = {
                                val to = arrayOf(
                                    "ime.prezime@metropolitan.ac.rs",
                                    "student1@metropolitan.ac.rs"
                                )
                                val cc = arrayOf("student2@metropolitan.ac.rs")
                                sendEmail(to, cc, "Pozdrav", textValue.value)
                            }) {
                                Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "email")
                                Text(text = "Send email", modifier = Modifier.padding(start = 5.dp))
                            }
                        }
                    }
                }
            }
        }
    }


    private val intentReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //-prikazuje primljeni SMS u TextView pogledu -
            val sms = intent.extras!!.getString("sms")
            textValue.value = sms?: ""
        }
    }


    override fun onResume() {
        super.onResume()

        //---registrovanje primaoca---
        registerReceiver(intentReceiver, intentFilter)

        //---kreira BroadcastReceiver kada je SMS poslat---
        smsSentReceiver = object: BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                when (getResultCode()) {
                    RESULT_OK -> Toast.makeText(
                        baseContext, "SMS prosleđen",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(
                        baseContext, "Generička greška",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(
                        baseContext, "Nema usluge",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(
                        baseContext, "Null PDU",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(
                        baseContext, "Radio isključen",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        //---kreira BroadcastReceiver kada SMS dostavljen---
        smsDeliveredReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (resultCode) {
                    RESULT_OK -> Toast.makeText(
                        baseContext, "SMS dostavljen",
                        Toast.LENGTH_SHORT
                    ).show()
                    RESULT_CANCELED -> Toast.makeText(
                        baseContext, "SMS nije dostavljen",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        //---registruje dva BroadcastReceiver - a---
        registerReceiver(smsDeliveredReceiver, IntentFilter(DELIVERED))
        registerReceiver(smsSentReceiver, IntentFilter(SENT))
    }


    override fun onPause() {
        super.onPause()
        //---odjavljuje primaoca---
        unregisterReceiver(intentReceiver);

        //---odjavljuje dva BroadcastReceiver-a---
        unregisterReceiver(smsSentReceiver)
        unregisterReceiver(smsDeliveredReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()

        //---odjavljivanje primaoca---
        unregisterReceiver(intentReceiver)
    }


    //Šalje poruku drugom uređaju”-
    private fun sendSMS(phoneNumber: String, message: String) {
        val smsManager = this.getSystemService(SmsManager::class.java)
        smsManager.sendTextMessage(
            phoneNumber, null, message,
            sentPI, deliveredPI
        )
    }

    //Slanje maila
    private fun sendEmail(
        emailAddresses: Array<String>,
        carbonCopies: Array<String>,
        subject: String,
        message: String
    ) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_CC, carbonCopies)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)
        emailIntent.type = "message/rfc822"
        startActivity(Intent.createChooser(emailIntent, "Email"))
    }

    fun onSMSIntentClick(v: View?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.putExtra("address", "5556; 5558; 5560")
        i.putExtra("sms_body", "Pozdravni SMS - primer!")
        i.type = "vnd.android-dir/mms-sms"
        startActivity(i)
    }


}


