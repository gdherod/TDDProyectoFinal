package cl.desafiolatam.tddproyectofinal.model

import android.util.Log
import cl.desafiolatam.tddproyectofinal.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {

    val tag = "Repository"

    fun loadApidata() = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().charactersList()

        if (response.isSuccessful) {
            response.body()?.map {
                Log.d(tag, "${it.char_id} - ${it.name} - ${it.img}")
            }
        } else {
            Log.d(tag, response.errorBody().toString())
        }
    }
}