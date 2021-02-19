package com.budi.myapplication

import androidx.room.*

@Dao
interface SoundDao {

    @Update
    fun update(vararg sounds: Sound)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sound : Sound)

    @Query("DELETE FROM sound")
    suspend fun deleteAll()

    @Query("SELECT * FROM sound")
    fun getAll(): List<Sound>

    @Query("select * from sound where uid = :soundId")
    suspend fun loadTool(soundId: Int): Sound

    @Query("SELECT * FROM sound")
    fun loadAllUsers(): Array<Sound>
}
