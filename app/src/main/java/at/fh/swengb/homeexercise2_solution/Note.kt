package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes",
        foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Note(@ColumnInfo(name="note_title")var title: String, @ColumnInfo(name="note_content") var content: String, @ColumnInfo(name="user_id")var userId: Int) {
    @ColumnInfo(name="note_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

