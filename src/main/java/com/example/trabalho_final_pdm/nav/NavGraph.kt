package com.example.trabalho_final_pdm.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trabalho_final_pdm.screen.MainScreen
import com.example.trabalho_final_pdm.screen.cliente.AddClienteScreen
import com.example.trabalho_final_pdm.screen.produto.AddProdutoScreen
import com.example.trabalho_final_pdm.screen.cliente.EditClienteScreen
import com.example.trab_final_pdm.screen.cliente.EditProdutoScreen
import com.example.trab_final_pdm.screen.cliente.GetProdutoScreen
import com.example.trabalho_final_pdm.screen.cliente.GetClienteScreen
import com.example.trabalho_final_pdm.screen.cliente.MainScreenCliente
import com.example.trabalho_final_pdm.screen.produto.MainScreenProduto
import com.example.trab_final_pdm.view_models.SharedViewModelCliente
import com.example.trabalho_final_pdm.screen.pedido.MainScreenPedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelProduto

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModelCliente: SharedViewModelCliente,
    sharedViewModelProduto: SharedViewModelProduto,
    sharedViewModelPedido: SharedViewModelPedido
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.MainScreenCliente.route) {
            MainScreenCliente(
                navController = navController,
                sharedViewModel = sharedViewModelCliente
            )
        }

        composable(route = Screens.AddClienteScreen.route) {
            AddClienteScreen(
                navController = navController,
                sharedViewModel = sharedViewModelCliente
            )
        }

        composable(route = Screens.GetClienteScreen.route) {
            GetClienteScreen(
                navController = navController,
                sharedViewModel = sharedViewModelCliente
            )
        }

        composable(route = Screens.EditClienteScreen.route) {
            EditClienteScreen(
                navController = navController,
                sharedViewModel = sharedViewModelCliente
            )
        }

        composable(route = Screens.MainScreenProduto.route) {
            MainScreenProduto(
                navController = navController,
                sharedViewModel = sharedViewModelProduto
            )
        }

        composable(route = Screens.AddProdutoScreen.route) {
            AddProdutoScreen(
                navController = navController,
                sharedViewModel = sharedViewModelProduto
            )
        }

        composable(route = Screens.EditProdutoScreen.route) {
            EditProdutoScreen(
                navController = navController,
                sharedViewModel = sharedViewModelProduto
            )
        }

        composable(route = Screens.GetProdutoScreen.route) {
            GetProdutoScreen(
                navController = navController,
                sharedViewModel = sharedViewModelProduto
            )
        }

        composable(route = Screens.MainScreenPedido.route) {
            MainScreenPedido(
                navController = navController,
                sharedViewModel = sharedViewModelPedido
            )
        }
    }
}