package com.example.trabalho_final_pdm.screen.pedido

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.data_classes.Pedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido

@Composable
fun AddPedidoScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelPedido
) {

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = data,
            onValueChange = {
                data = it
            },
            label = {
                Text(text = "Data")
            },
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            onClick = {
                val pedido = Pedido(
                    clienteCPF = clienteCPF,
                    produtoID = produtoID,
                    quantidade = quantidadeInt,
                    data = data
                )

                sharedViewModel.saveData(pedido = pedido, context = context)
            }) {
            Text(text = "Save")
        }
    }
}