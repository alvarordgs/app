package com.example.trabalho_final_pdm.data_classes

data class Pedido(
    var pedidoID: String = "",
    var clienteCPF: String = "",
    var produtoID: String = "",
    var quantidade: Int = 0,
    var valorTotal: Double = 0.0,
    var data: String = "",
)
