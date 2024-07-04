package com.example.trabalho_final_pdm.screen.pedido

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.trabalho_final_pdm.data_classes.Pedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelProduto
import kotlinx.coroutines.launch

@Composable
fun MainScreenPedido(
    navController: NavController,
    sharedViewModel: SharedViewModelPedido,
    sharedViewModelProduto: SharedViewModelProduto
) {
    val context = LocalContext.current
    val pedidos by sharedViewModel.pedidos.observeAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            sharedViewModel.fetchPedidos(context)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarProduto(navController = navController)
        ActionButtonsProduto(navController = navController, modifier = Modifier.weight(1f))
        PedidoList(
            pedidos = pedidos,
            navController = navController,
            sharedViewModel = sharedViewModel,
            sharedViewModelProduto = sharedViewModelProduto,
            context = context,
            modifier = Modifier.weight(9f)
        )
    }
}

@Composable
fun TopBarProduto(navController: NavController) {
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
fun ActionButtonsProduto(navController: NavController, modifier: Modifier) {
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
                navController.navigate(Screens.AddPedidoScreen.route)
            }
        ) {
            Text(text = "Adicionar")
        }

        Button(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.GetPedidoScreen.route)
            }
        ) {
            Text(text = "Buscar")
        }
    }
}

@Composable
fun PedidoList(
    pedidos: List<Pedido>,
    navController: NavController,
    sharedViewModel: SharedViewModelPedido,
    sharedViewModelProduto: SharedViewModelProduto,
    context: Context,
    modifier: Modifier
) {

    LazyColumn(
        modifier = modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize()
    ) {
        items(pedidos) { pedido ->

            Text(
                text = "ID do pedido: " + pedido.pedidoID + " - Data: " + pedido.data,
                modifier = Modifier.clickable {
                    sharedViewModel.selectPedido(pedido)
                    navController.navigate(Screens.EditPedidoScreen.route)
                },
                color = Color.Black,
            )
        }
    }
}

