package cl.desafiolatam.tddproyectofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cl.desafiolatam.tddproyectofinal.model.BBRepository

class BBViewModel(application: Application) : AndroidViewModel(application) {

    val tag = "ViewModel"

    private var bbrepository: BBRepository = BBRepository(application)

    var characterList = bbrepository.characterList

    init {
        bbrepository = BBRepository(application)
        bbrepository.loadApidata()
    }
}