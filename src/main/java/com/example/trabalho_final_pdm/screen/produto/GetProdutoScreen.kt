package com.example.trab_final_pdm.screen.cliente

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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trab_final_pdm.data_classes.Produto
import com.example.trab_final_pdm.view_models.SharedViewModelCliente
import com.example.trabalho_final_pdm.view_models.SharedViewModelProduto

@Composable
fun GetProdutoScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelProduto
) {

    var produtoID: String by remember { mutableStateOf("") }
    var tipoGrao: String by remember { mutableStateOf("") }
    var pontoTorra: String by remember { mutableStateOf("") }
    var valor: String by remember { mutableStateOf("") }
    var valorDouble: Double by remember { mutableDoubleStateOf(0.0) }
    var blend: Boolean by remember { mutableStateOf(false) }

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
                value = produtoID,
                onValueChange = {
                    produtoID = it
                },
                label = {
                    Text(text = "ID do Produto")
                }
            )

            Button(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(120.dp),
                onClick = {
                    sharedViewModel.retrieveData(
                        produtoID = produtoID,
                        context = context
                    ) { data ->
                        tipoGrao = data.tipoGrao
                        pontoTorra = data.pontoTorra
                        valor = data.valor.toString()
                        blend = data.blend
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
            value = tipoGrao,
            onValueChange = {
                tipoGrao = it
            },
            label = {
                Text(text = "Tipo do grao")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pontoTorra,
            onValueChange = {
                pontoTorra = it
            },
            label = {
                Text(text = "Ponto da torra")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = valor,
            onValueChange = {
                valor = it
                if(valor.isNotEmpty()) {
                    valorDouble = valor.toDouble()
                }
            },
            label = {
                Text(text = "Telefone")
            }
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Blend")
            Switch(
                checked = blend,
                onCheckedChange = { blend = it }
            )
        }

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
                    val produto = Produto(
                        tipoGrao = tipoGrao,
                        pontoTorra = pontoTorra,
                        valor = valorDouble,
                        blend = blend
                    )

                    sharedViewModel.saveData(produto = produto, context = context)
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
                        produtoID = produtoID,
                        context = context,
                        navController = navController
                    )
                }) {
                Text(text = "Delete")
            }
        }
    }
}