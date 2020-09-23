package cl.desafiolatam.tddproyectofinal.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val birthday: String,
    @Embedded val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    @Embedded val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    @Embedded val betterCallSaulAppearance: List<Int?>
)