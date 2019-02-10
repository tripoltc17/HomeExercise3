package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Query("SELECT * FROM notes")
    fun selectAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE user_id = :id")
    fun findAllNotesFromUser(id: Long): List<Note>

    @Query("SELECT * FROM notes WHERE  note_id=:note_id")
    fun getNote(note_id:Long): Note

    @Query("DELETE FROM notes WHERE note_id=:note_id")
    fun deleteNote(note_id:Long)
}
