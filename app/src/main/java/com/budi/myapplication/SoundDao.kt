package com.budi.myapplication

import androidx.room.*

@Dao
interface SoundDao {

    @Update
    fun update(vararg sounds: Sound)

    @Insert
    fun insert(sound: Sound)

    @Query("DELETE FROM sound")
    fun deleteAll()

    @Query("SELECT * FROM sound")
    fun getAll(): List<Sound>

    @Query("SELECT * FROM sound")
    fun loadAllUsers(): Array<Sound>
}
