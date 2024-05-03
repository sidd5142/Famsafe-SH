package com.example.otp_number.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GuardianDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(guardian: Guardian)

    @Query("DELETE from guardian_table")
    fun clear()

    @Query("SELECT * from guardian_table ORDER BY id DESC")
    fun getAllGuardians(): LiveData<List<Guardian>>

    @Query("SELECT * from guardian_table")
    fun getEmail(): List<Guardian>
}