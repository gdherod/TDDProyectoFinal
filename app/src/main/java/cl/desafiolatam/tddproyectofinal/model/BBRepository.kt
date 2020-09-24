package cl.desafiolatam.tddproyectofinal.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.desafiolatam.tddproyectofinal.model.db.BBDataBase
import cl.desafiolatam.tddproyectofinal.model.db.Character
import cl.desafiolatam.tddproyectofinal.model.remote.RetrofitClient
import cl.desafiolatam.tddproyectofinal.model.remote.pojo.BBWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BBRepository(context: Context) {

    val tag = "Repository"

    var bbDataBase = BBDataBase.getDatabase(context)
    var allCharacters: LiveData<List<Character>> = bbDataBase.bbDao().getAllCharacter()

    fun loadApidata() = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().charactersList()

        if (response.isSuccessful) {
            insertCharacterDB(characterApitoEntity(response.body()!!))
            /*response.body()?.map {
                Log.d(tag, "${it.char_id} - ${it.name} - ${it.img}")
            }*/
        } else {
            Log.d(tag, response.errorBody().toString())
        }
    }

    private fun characterApitoEntity(charactersList: List<BBWrapper.Character>): List<Character> {
        return charactersList.map { character ->
            Character(
                character.char_id,
                character.name,
                character.birthday,
                character.occupation,
                character.img,
                character.status,
                character.nickname,
                character.appearance,
                character.portrayed,
                character.category,
                character.betterCallSaulAppearance
            )
        }
    }

    private fun insertCharacterDB(charactersList: List<Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            bbDataBase.bbDao().insertAllCharacter(charactersList)
        }
    }
}