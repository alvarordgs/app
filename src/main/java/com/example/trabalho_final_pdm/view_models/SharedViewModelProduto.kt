package com.example.trabalho_final_pdm.view_models

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.trab_final_pdm.data_classes.Produto
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class SharedViewModelProduto(): ViewModel() {

    val selectedProduto = MutableLiveData<Produto>()
    val produtos = MutableLiveData<List<Produto>>()

    fun saveData(
        produto: Produto,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        val produtoID = generateUniqueID()

        produto.produtoID = produtoID

        val fireStoreRef = Firebase.firestore
            .collection("produto")
            .document(produtoID)

        try {
            fireStoreRef.set(produto)
                .addOnSuccessListener {
                    Toast.makeText(context, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateUniqueID(): String {
        return UUID.randomUUID().toString()
    }

    fun retrieveData(
        produtoID: String,
        context: Context,
        data: (Produto) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("produto")
            .document(produtoID)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()) {
                        val produto = it.toObject<Produto>()!!
                        data(produto)
                    } else {
                        Toast.makeText(context, "Nenhum produto encontrado!", Toast.LENGTH_SHORT).show()
                    }

                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun fetchProdutos(
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore.collection("produto")

        try {
            fireStoreRef.get()
                .addOnSuccessListener { result ->
                    val produtos = result.documents.mapNotNull { it.toObject<Produto>() }
                    this@SharedViewModelProduto.produtos.postValue(produtos)
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun selectProduto(produto: Produto) {
        selectedProduto.value = produto
    }

    fun deleteData(
        produtoID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("produto")
            .document(produtoID)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Dados deletados com sucessoo!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}