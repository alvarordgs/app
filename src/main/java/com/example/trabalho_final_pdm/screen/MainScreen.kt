package com.example.trabalho_final_pdm.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.nav.Screens

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.MainScreenCliente.route)
            }
        ) {
            Text(text = "Cliente")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.MainScreenProduto.route)
            }
        ) {
            Text(text = "Produto")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.MainScreenPedido.route)
            }
        ) {
            Text(text = "Pedido")
        }
    }
}