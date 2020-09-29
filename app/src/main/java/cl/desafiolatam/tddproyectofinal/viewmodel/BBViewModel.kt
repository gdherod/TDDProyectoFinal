package cl.desafiolatam.tddproyectofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.desafiolatam.tddproyectofinal.model.BBRepository
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity

class BBViewModel(application: Application) : AndroidViewModel(application) {

    private var bbrepository: BBRepository = BBRepository(application)

    var characterList = bbrepository.characterList

    init {
        bbrepository = BBRepository(application)
        bbrepository.loadApidata()
    }

    fun getCharacterDetail(param1: String): LiveData<CharacterEntity> {
        return bbrepository.getDetail(param1)
    }
}