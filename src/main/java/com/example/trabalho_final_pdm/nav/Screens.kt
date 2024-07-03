package com.example.trabalho_final_pdm.nav

sealed class Screens(val route: String) {
    object MainScreen : Screens(route = "main_screen")
    object MainScreenCliente : Screens(route = "main_screen_cliente")
    object AddClienteScreen : Screens(route = "add_cliente_screen")
    object GetClienteScreen : Screens(route = "get_cliente_screen")
    object EditClienteScreen : Screens(route = "edit_cliente_screen")
    object MainScreenProduto : Screens(route = "main_screen_produto")
    object AddProdutoScreen : Screens(route = "add_produto_screen")
    object EditProdutoScreen : Screens(route = "edit_produto_screen")
    object GetProdutoScreen : Screens(route = "get_produto_screen")
    object MainScreenPedido : Screens(route = "main_screen_pedido")
}