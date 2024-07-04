package com.example.trabalho_final_pdm.screen.pedido

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.data_classes.Pedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido

@Composable
fun EditPedidoScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelPedido
) {
    val pedido by sharedViewModel.selectedPedido.observeAsState()

    var pedidoID by remember { mutableStateOf(pedido?.pedidoID ?: "") }
    var clienteCPF by remember { mutableStateOf(pedido?.clienteCPF ?: "") }
    var produtoID by remember { mutableStateOf(pedido?.produtoID ?: "") }
    var quantidade by remember { mutableStateOf(pedido?.quantidade.toString() ?: "") }
    var quantidadeInt by remember { mutableIntStateOf(0) }
    var data by remember { mutableStateOf(pedido?.data ?: "") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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

    Column(
        modifier = Modifier
            .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = pedidoID,
            onValueChange = {
                pedidoID = it
            },
            label = {
                Text(text = "ID do pedido")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = produtoID,
            onValueChange = {
                produtoID = it
            },
            label = {
                Text(text = "ID do produto")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = quantidade,
            onValueChange = {
                quantidade = it
                if (it.isNotEmpty()) {
                    quantidadeInt = it.toInt()
                }
            },
            label = {
                Text(text = "Quantidade")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = data,
            onValueChange = {
                data = it
            },
            label = {
                Text(text = "Data do pedido")
            },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .weight(0.5f),
                onClick = {
                    val pedido = Pedido(
                        pedidoID = pedidoID,
                        clienteCPF = clienteCPF,
                        produtoID = produtoID,
                        quantidade = quantidadeInt,
                        data = data
                    )

                    sharedViewModel.saveData(pedido = pedido, context = context)
                }) {
                Text(text = "Salvar")
            }

            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .weight(0.5f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                ),
                onClick = {
                    sharedViewModel.deleteData(
                        pedidoID = pedidoID,
                        context = context,
                        navController = navController
                    )
                }) {
                Text(text = "Delete")
            }
        }
    }
}