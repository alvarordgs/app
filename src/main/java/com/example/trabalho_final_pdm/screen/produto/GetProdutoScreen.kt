package com.example.trab_final_pdm.screen.cliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.trab_final_pdm.view_models.SharedViewModelCliente

@Composable
fun GetProdutoScreen(
    navController: NavController,
    sharedViewModel: SharedViewModelCliente
) {

    var tipoGrao: String by remember { mutableStateOf("") }
    var pontoTorra: String by remember { mutableStateOf("") }
    var valor: String by remember { mutableStateOf("") }
    var valorDouble: Double by remember { mutableDoubleStateOf(0.0) }
    var blend: Boolean by remember { mutableStateOf(false) }

    val context = LocalContext.current

    /*

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
                value = tipoGrao,
                onValueChange = {
                    tipoGrao = it
                },
                label = {
                    Text(text = "Tipo Grão")
                }
            )

            Button(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(120.dp),
                onClick = {
                    sharedViewModel.retrieveData(
                        cpf = cpf,
                        context = context
                    ) { data ->
                        nome = data.nome
                        telefone = data.telefone
                        endereco = data.endereco
                        instagram = data.instagram
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
    }*/
}