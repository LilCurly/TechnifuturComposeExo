package com.example.technifutur_and17_exo01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.technifutur_and17_exo01.ui.theme.TECHNIFUTURAND17EXO01Theme

class MainActivity : ComponentActivity() {
    private val mViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TECHNIFUTURAND17EXO01Theme {
                ComposeMainView()
            }
        }
    }
}

@Composable
fun ComposeMainView() {
    Scaffold(
        topBar = { AddTopBar() },
        floatingActionButton = { AddFloatingActionButton() },
        bottomBar = { AddBottomNavigation() }
    ) {
        ComposeListView()
    }
}

@Composable
fun AddTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), color = Color.White) },
        backgroundColor = Color.Black
    )
}

@Composable
fun AddFloatingActionButton(viewModel: MyViewModel = viewModel()) {
    FloatingActionButton(
        content = {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        },
        onClick = {
            viewModel.foodList.add(
                FoodModel(
                    "Test",
                    "Test",
                    "Test"
                )
            )
        }
    )
}

@Composable
fun AddBottomNavigation() {
    BottomNavigation {
        var selectedItem by remember { mutableStateOf(0) }
        val navController = rememberNavController()

        BottomNavigationItem(
            selected = selectedItem == 0,
            onClick = { navController.navigate("") },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = "Home") }
        )

        BottomNavigationItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Fun") },
            label = { Text(text = "Fun") }
        )

        BottomNavigationItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Food") },
            label = { Text(text = "Food") }
        )

        BottomNavigationItem(
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Move") },
            label = { Text(text = "Move") }
        )
    }
}

@Composable
fun ComposeListView(mViewModel: MyViewModel = viewModel()) {
    mViewModel.loadFakeData()

    AddList(foodList = mViewModel.foodList)
}

@Composable
fun AddList(foodList: SnapshotStateList<FoodModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(foodList) {
            AddItem(model = it)
        }
    }
}

@Composable
fun AddItem(model: FoodModel) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp, 4.dp)) {
        Column {
            Text(
                text = model.country,
                color = Color.DarkGray,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            Text(
                text = model.name,
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            Text(
                text = model.city,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun AddContent(mViewModel: MyViewModel = viewModel()) {

    val login by mViewModel.login.observeAsState("")
    val password by mViewModel.password.observeAsState("")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = login,
            onValueChange = { mViewModel.updateLogin(it) },
            label = { Text(text = "Login") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp, 4.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { mViewModel.updatePassword(it) },
            label = { Text(text = "Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp, 4.dp)
        )
        Row {
            TextButton(
                onClick = { },
                content = {
                    Text(text = "Connect", color = Color.Black)
                }
            )
            TextButton(
                onClick = { },
                content = {
                    Text(
                        text = "Create Account",
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TECHNIFUTURAND17EXO01Theme {
        ComposeMainView()
    }
}