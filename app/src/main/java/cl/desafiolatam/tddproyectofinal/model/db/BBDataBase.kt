package cl.desafiolatam.tddproyectofinal.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Character::class], version = 1)
abstract class BBDataBase : RoomDatabase() {

    abstract fun bbDao(): BBDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
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