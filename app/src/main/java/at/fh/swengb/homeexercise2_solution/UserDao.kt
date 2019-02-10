package at.fh.swengb.homeexercise2_solution

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Query("SELECT * from user")
    fun selectAllUser(): List<User>

    @Query("SELECT user_name FROM user WHERE user_id = :id")
    fun findUserName(id: Long): String

    @Query("SELECT user_age FROM user WHERE user_id = :id")
    fun findUserAge(id: Long): Int

    @Query("SELECT * FROM user WHERE user_id=:id")
    fun getUser(id:Long): User

    @Query("SELECT COUNT(*) from user WHERE user_id=:id")
    fun countUserByID(id:Long) : Int

    @Query("SELECT COUNT(*) FROM user WHERE user_name=:name")
    fun countUserByName(name:String): Int

    @Query("SELECT * FROM user WHERE user_name=:name")
    fun getUserID(name:String): Long
}
