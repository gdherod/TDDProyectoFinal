package cl.desafiolatam.tddproyectofinal.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BBDao {
    @Query("SELECT * FROM character_table")
    fun getAllCharacter(): LiveData<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacter(characterList: List<Character>)

    @Delete
    fun deleteCharacter(character: Character)
}