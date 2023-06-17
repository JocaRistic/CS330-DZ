package rs.ac.metropolitan.zadatak.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.common.Common
import rs.ac.metropolitan.common.UserItem
import rs.ac.metropolitan.zadatak.AppViewModel
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date
import java.util.UUID
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUserScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var grad by remember { mutableStateOf(TextFieldValue("")) }
    var drzava by remember { mutableStateOf(TextFieldValue("")) }
    var opis by remember { mutableStateOf(TextFieldValue("")) }
    var pol by rememberSaveable { mutableStateOf("") }
    var zainteresovanPol by rememberSaveable { mutableStateOf("") }
    val genders = listOf("Male", "Female")

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { vm.goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "New User", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                TextField(
                    value = username,
                    onValueChange = { newText ->
                        username = newText
                    },
                    label = { Text("Username") },
                    placeholder = { Text("Enter your username") },
                )
            }
            item {
                TextField(
                    value = grad,
                    onValueChange = { newText ->
                        grad = newText
                    },
                    label = { Text("City") },
                    placeholder = { Text("Enter your city") },
                )
            }
            item {
                TextField(
                    value = drzava,
                    onValueChange = {
                        drzava = it
                    },
                    label = { Text(text = "Country") },
                    placeholder = { Text(text = "Enter your country") },
                )
            }
            item {
                Text(text = "Gender:")
                SegmentedControl(
                    items = genders,
                    defaultSelectedItemIndex = 0,
                ) { index ->
                    pol = if(index==0) "Male" else "Female"
                } }
            item {
                Text(text = "Interested in:")
                SegmentedControl(
                    items = genders,
                    defaultSelectedItemIndex = 0,
                ) { index ->
                    zainteresovanPol = if(index==0) "Male" else "Female"
                } }
            item {
                TextField(
                    value = opis,
                    onValueChange = {
                        opis = it
                    },
                    label = { Text(text = "Description") },
                    placeholder = { Text(text = "Enter your description") },
                )
            }

            item {
                Button(onClick = { vm.submitUser(
                    UserItem(
                        id =  UUID.randomUUID().toString(),
                        username = username.text,
                        avatar = Common.generateAvatarImage("${username.text}").toString(),
                        grad = grad.text,
                        drzava = drzava.text,
                        pol = pol,
                        zainteresovanPol = zainteresovanPol,
                        opis = opis.text
                    )
                )
                    vm.goBack()
                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}