package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [User::class, Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
    abstract val userDao: UserDao
    abstract val NotesAndUserDao: NotesAndUserDao
    companion object {
        fun getDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(context, NotesDatabase::class.java, "db_notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}