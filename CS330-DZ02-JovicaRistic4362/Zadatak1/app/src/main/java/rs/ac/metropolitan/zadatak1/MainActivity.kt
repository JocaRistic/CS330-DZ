package rs.ac.metropolitan.zadatak1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import rs.ac.metropolitan.zadatak1.ui.theme.Zadatak1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zadatak1Theme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = {
        Text(text = "BMI kalkulator", modifier = Modifier.padding(16.dp))
    }) {paddingValues ->
        Bmi(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bmi(paddingValues: PaddingValues) {

    var visina by remember {
        mutableStateOf("")
    }
    var tezina by remember {
        mutableStateOf("")
    }
    var rezultat by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = visina,
            onValueChange = {visina = it},
            label = {
                Text(text = "Visina (M)")
            }
        )

        TextField(
            value = tezina,
            onValueChange = {tezina = it},
            label = {
                Text(text = "Tezina (KG)")
            },
            modifier = Modifier.padding(top = 10.dp)
        )

        Button(
            onClick = {
                val bmi = tezina.toDouble() / (visina.toDouble() * visina.toDouble())
                rezultat = "Vas BMI je $bmi"
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "Izracunaj")
        }

        if (rezultat.isNotBlank()){
            Text(
                text = rezultat,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Zadatak1Theme {
        MainScreen()
    }
}