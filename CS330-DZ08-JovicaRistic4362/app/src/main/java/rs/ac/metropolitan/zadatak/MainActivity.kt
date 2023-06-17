package rs.ac.metropolitan.zadatak

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.zadatak.ui.theme.ZadatakTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZadatakTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()

                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "Food") })
                }) { innerPadding ->
                    NavSettup(navController)
                }
            }
        }
    }
}
