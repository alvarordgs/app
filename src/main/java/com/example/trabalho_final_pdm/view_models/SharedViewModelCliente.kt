package com.example.trab_final_pdm.view_models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.trabalho_final_pdm.data_classes.Cliente
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModelCliente(): ViewModel() {

    val selectedCliente = MutableLiveData<Cliente>()
    val clientes = MutableLiveData<List<Cliente>>()

    fun saveData(
        cliente: Cliente,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("cliente")
            .document(cliente.cpf)

        try {
            fireStoreRef.set(cliente)
                .addOnSuccessListener {
                    Toast.makeText(context, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        cpf: String,
        context: Context,
        data: (Cliente) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("cliente")
            .document(cpf)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()) {
                        val cliente = it.toObject<Cliente>()!!
                        data(cliente)
                    } else {
                        Toast.makeText(context, "Nenhum cliente encontrado!", Toast.LENGTH_SHORT).show()
                    }

                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun fetchClientes(
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore.collection("cliente")

        try {
            fireStoreRef.get()
                .addOnSuccessListener { result ->
                    val clientes = result.documents.mapNotNull { it.toObject<Cliente>() }
                    Log.i("clientes fetch", "$clientes")
                    this@SharedViewModelCliente.clientes.postValue(clientes)
                }
        } catch (e: Exception) {
            Log.e("fetchClientes", "Erro ao buscar clientes", e)
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun selectCliente(cliente: Cliente) {
        selectedCliente.value = cliente
    }

    fun deleteData(
        cpf: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("cliente")
            .document(cpf)

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