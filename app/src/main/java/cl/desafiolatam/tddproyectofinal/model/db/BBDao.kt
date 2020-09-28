package cl.desafiolatam.tddproyectofinal.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BBDao {
    @Query("SELECT * FROM character_table")
    fun getAllCharacter(): LiveData<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characterList: List<CharacterEntity>)

    @Query("SELECT * FROM character_table WHERE char_id = :id")
    fun getDetailCharacter(id: String): LiveData<CharacterEntity>
}