package cl.desafiolatam.tddproyectofinal.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.desafiolatam.tddproyectofinal.model.db.BBDataBase
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity
import cl.desafiolatam.tddproyectofinal.model.remote.RetrofitClient
import cl.desafiolatam.tddproyectofinal.model.remote.pojo.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BBRepository(context: Context) {

    val tag = "Repository"

    var bbDataBase = BBDataBase.getDatabase(context)
    var characterList = bbDataBase.bbDao().getAllCharacter()

    fun loadApidata() = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().charactersList()

        if (response.isSuccessful) {
            insertCharacterDB(characterApitoEntity(response.body()!!))
        } else {
            Log.d(tag, response.errorBody().toString())
        }
    }

    fun characterApitoEntity(characterList: List<Character>): List<CharacterEntity> {
        return characterList.map { character ->
            CharacterEntity(
                character.char_id,
                character.name,
                character.birthday,
                character.occupation,
                character.img,
                character.status,
                character.nickname,
                //character.appearance,
                character.portrayed,
                character.category
                //character.betterCallSaulAppearance
            )
        }
    }

    fun insertCharacterDB(charactersListEntity: List<CharacterEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            bbDataBase.bbDao().insertAllCharacters(charactersListEntity)
        }
    }

    fun getDetail(param1: String): LiveData<CharacterEntity> {
        return bbDataBase.bbDao().getDetailCharacter(param1)

    }
}