package com.example.trabalho_final_pdm.screen.pedido

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
import com.example.trab_final_pdm.data_classes.Produto
import com.example.trabalho_final_pdm.view_models.SharedViewModelPedido
import com.example.trabalho_final_pdm.view_models.SharedViewModelProduto
import kotlinx.coroutines.launch

@Composable
fun MainScreenPedido(
    navController: NavController,
    sharedViewModel: SharedViewModelPedido
) {
//    val context = LocalContext.current
//    val produtos by sharedViewModel.produtos.observeAsState(initial = emptyList())
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(key1 = Unit) {
//        scope.launch {
//            sharedViewModel.fetchProdutos(context)
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        TopBarProduto(navController = navController)
//        ActionButtonsProduto(navController = navController, modifier = Modifier.weight(1f))
//        ProdutoList(
//            produtos = produtos,
//            navController = navController,
//            sharedViewModel = sharedViewModel,
//            modifier = Modifier.weight(9f)
//        )
//    }
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
                navController.navigate(Screens.AddProdutoScreen.route)
            }
        ) {
            Text(text = "Adicionar")
        }

        Button(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.GetProdutoScreen.route)
            }
        ) {
            Text(text = "Buscar")
        }
    }
}

@Composable
fun ProdutoList(
    produtos: List<Produto>,
    navController: NavController,
    sharedViewModel: SharedViewModelProduto,
    modifier: Modifier
) {

    LazyColumn(
        modifier = modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize()
    ) {
        items(produtos) { produto ->
            Text(
                text = produto.tipoGrao,
                modifier = Modifier.clickable {
                    sharedViewModel.selectProduto(produto)
                    navController.navigate(Screens.EditProdutoScreen.route)
                },
                color = Color.Black,
            )
        }
    }
}