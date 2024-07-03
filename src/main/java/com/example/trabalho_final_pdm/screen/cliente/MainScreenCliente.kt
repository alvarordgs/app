package com.example.trabalho_final_pdm.screen.cliente

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.nav.Screens
import com.example.trab_final_pdm.view_models.SharedViewModelCliente
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.trabalho_final_pdm.data_classes.Cliente
import kotlinx.coroutines.launch

@Composable
fun MainScreenCliente(
    navController: NavController,
    sharedViewModel: SharedViewModelCliente
) {
    val context = LocalContext.current
    val clientes by sharedViewModel.clientes.observeAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            sharedViewModel.fetchClientes(context)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navController = navController)
        ActionButtons(navController = navController, modifier = Modifier.weight(1f))
        ClientList(
            clientes = clientes,
            navController = navController,
            sharedViewModel = sharedViewModel,
            modifier = Modifier.weight(9f)
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
        }
    }
}

@Composable
fun ActionButtons(navController: NavController, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.AddClienteScreen.route)
            }
        ) {
            Text(text = "Adicionar")
        }

        Button(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.GetClienteScreen.route)
            }
        ) {
            Text(text = "Buscar")
        }
    }
}

@Composable
fun ClientList(
    clientes: List<Cliente>,
    navController: NavController,
    sharedViewModel: SharedViewModelCliente,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize()
    ) {
        items(clientes) { cliente ->
            Text(
                text = cliente.nome,
                modifier = Modifier.clickable {
                    sharedViewModel.selectCliente(cliente)
                    navController.navigate(Screens.EditClienteScreen.route)
                },
                color = Color.Black,
            )
        }
    }
}