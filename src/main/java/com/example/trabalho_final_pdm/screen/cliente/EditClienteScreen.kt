package com.example.trabalho_final_pdm.screen.cliente

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
import com.example.trab_final_pdm.view_models.SharedViewModelCliente

@Composable
fun EditClienteScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelCliente
) {
    val cliente by sharedViewModel.selectedCliente.observeAsState()

    var cpf by remember { mutableStateOf(cliente?.cpf ?: "") }
    var nome by remember { mutableStateOf(cliente?.nome ?: "") }
    var telefone by remember { mutableStateOf(cliente?.telefone ?: "") }
    var endereco by remember { mutableStateOf(cliente?.endereco ?: "") }
    var instagram by remember { mutableStateOf(cliente?.instagram ?: "") }

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
            value = cpf,
            onValueChange = {
                cpf = it
            },
            label = {
                Text(text = "CPF")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nome,
            onValueChange = {
                nome = it
            },
            label = {
                Text(text = "Nome")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = telefone,
            onValueChange = {
                telefone = it
            },
            label = {
                Text(text = "Telefone")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = endereco,
            onValueChange = {
                endereco = it
            },
            label = {
                Text(text = "Endereço")
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = instagram,
            onValueChange = {
                instagram = it
            },
            label = {
                Text(text = "Instagram")
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
                    val cliente = Cliente(
                        cpf = cpf,
                        nome = nome,
                        telefone = telefone,
                        endereco = endereco,
                        instagram = instagram
                    )

                    sharedViewModel.saveData(cliente = cliente, context = context)
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
                        cpf = cpf,
                        context = context,
                        navController = navController
                    )
                }) {
                Text(text = "Delete")
            }
        }
    }
}