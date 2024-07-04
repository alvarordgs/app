package com.example.trabalho_final_pdm.screen.pedido

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.trabalho_final_pdm.data_classes.Cliente
import com.example.trabalho_final_pdm.data_classes.Pedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido

@Composable
fun GetPedidoScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelPedido
) {
    var pedidoID: String by remember { mutableStateOf("") }
    var clienteCPF: String by remember { mutableStateOf("") }
    var produtoID: String by remember { mutableStateOf("") }
    var quantidade: String by remember { mutableStateOf("") }
    var quantidadeInt: Int by remember { mutableIntStateOf(0) }
    var data: String by remember { mutableStateOf("") }

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
                value = pedidoID,
                onValueChange = {
                    pedidoID = it
                },
                label = {
                    Text(text = "ID do pedido")
                }
            )

            Button(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(120.dp),
                onClick = {
                    sharedViewModel.retrieveData(
                        pedidoID = pedidoID,
                        context = context
                    ) { item ->
                        clienteCPF = item.clienteCPF
                        produtoID = item.produtoID
                        quantidade = item.quantidade.toString()
                        data = item.data
                    }
                }
            ) {
                Text(
                    text = "Buscar",
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = clienteCPF,
            onValueChange = {
                clienteCPF = it
            },
            label = {
                Text(text = "CPF do cliente")
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
                if(quantidade.isNotEmpty()) {
                    quantidadeInt = quantidade.toInt()
                }
            },
            label = {
                Text(text = "Quantidade")
            },
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