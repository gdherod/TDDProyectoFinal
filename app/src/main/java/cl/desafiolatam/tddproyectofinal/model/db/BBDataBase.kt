package cl.desafiolatam.tddproyectofinal.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class BBDataBase : RoomDatabase() {

    abstract fun bbDao(): BBDao

    companion object {
        @Volatile
        private var INSTANCE: BBDataBase? = null

        fun getDatabase(context: Context): BBDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BBDataBase::class.java,
                    "character_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}