package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface NotesAndUserDao {

    @Query("SELECT * from user")
    fun findAllUserAndNotes(): List<NotesAndUser>
}