package com.example.trabalho_final_pdm.view_models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.data_classes.Pedido
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class SharedViewModelPedido(): ViewModel() {

    val selectedPedido = MutableLiveData<Pedido>()
    val pedidos = MutableLiveData<List<Pedido>>()

    fun saveData(
        pedido: Pedido,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        if(pedido.pedidoID.isEmpty()) {
            pedido.pedidoID = generateUniqueID()
        }

        val fireStoreRef = Firebase.firestore
            .collection("pedido")
            .document(pedido.pedidoID)

        try {
            fireStoreRef.set(pedido)
                .addOnSuccessListener {
                    Toast.makeText(context, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        pedidoID: String,
        context: Context,
        data: (Pedido) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("pedido")
            .document(pedidoID)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()) {
                        val pedido = it.toObject<Pedido>()!!
                        data(pedido)
                    } else {
                        Toast.makeText(context, "Nenhum pedido encontrado!", Toast.LENGTH_SHORT).show()
                    }

                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    private fun generateUniqueID(): String {
        return UUID.randomUUID().toString()
    }


    fun fetchPedidos(
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore.collection("pedido")

        try {
            fireStoreRef.get()
                .addOnSuccessListener { result ->
                    val pedido = result.documents.mapNotNull { it.toObject<Pedido>() }
                    this@SharedViewModelPedido.pedidos.postValue(pedido)
                }
        } catch (e: Exception) {
            Log.e("fetchClientes", "Erro ao buscar clientes", e)
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun selectPedido(pedido: Pedido) {
        selectedPedido.value = pedido
    }

    fun deleteData(
        pedidoID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("pedido")
            .document(pedidoID)

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