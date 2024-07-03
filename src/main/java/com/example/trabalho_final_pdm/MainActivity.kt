package com.example.trabalho_final_pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trabalho_final_pdm.nav.NavGraph
import com.example.trab_final_pdm.view_models.SharedViewModelCliente
import com.example.trabalho_final_pdm.view_models.SharedViewModelProduto
import com.example.trabalho_final_pdm.ui.theme.TrabalhofinalpdmTheme
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModelCliente: SharedViewModelCliente by viewModels()
    private val sharedViewModelProduto: SharedViewModelProduto by viewModels()
    private val sharedViewModelPedido: SharedViewModelPedido by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhofinalpdmTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        sharedViewModelCliente = sharedViewModelCliente,
                        sharedViewModelProduto = sharedViewModelProduto,
                        sharedViewModelPedido = sharedViewModelPedido
                    )
                }
            }
        }
    }
}
