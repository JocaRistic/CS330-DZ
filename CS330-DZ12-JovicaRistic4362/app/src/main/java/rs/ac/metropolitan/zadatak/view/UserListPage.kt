package rs.ac.metropolitan.zadatak.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.ac.metropolitan.common.UserItem
import rs.ac.metropolitan.zadatak.AppViewModel

@Composable
fun UserListPage(vm: AppViewModel, paddingValues: PaddingValues) {
    val users = vm.users.observeAsState()
    LaunchedEffect(vm.loadUsers()) {
    }

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        users.value?.let {
            items(it) { student ->
                UserCardView(student) {
                    vm.navigateToUserDetail(it)
                }
            }
        }
    }
}

@Composable
fun UserCardView(user: UserItem, onSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(user.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${user.username}",
                    fontSize = 18.sp
                )
                Text(
                    text = "Gender: ${user.pol}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "${user.drzava}, ${user.grad}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}