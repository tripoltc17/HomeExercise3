package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class User(@ColumnInfo(name="user_name") var name: String, @ColumnInfo(name = "user_age") var age: Int) {
    @ColumnInfo(name="user_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
