package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class NotesAndUser() {
    @Embedded
    lateinit var user: User
    @Relation(entity = Note::class, entityColumn = "user_id", parentColumn = "user_id")
    lateinit var notes: List<Note>
}