package com.example.trabalho_final_pdm.data_classes

data class Produto(
    var produtoID: String = "",
    var tipoGrao: String = "",
    var pontoTorra: String = "",
    var valor: Double = 0.0,
    var blend: Boolean = false,
)
