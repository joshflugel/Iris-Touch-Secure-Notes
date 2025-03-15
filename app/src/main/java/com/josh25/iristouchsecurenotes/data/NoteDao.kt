package com.josh25.iristouchsecurenotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * from NoteEntity")
    fun getTasks(): Flow<List<NoteEntity>>

    @Insert
    suspend fun addNote(item: NoteEntity)

    @Update
    suspend fun updateNote(taskEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(taskEntity: NoteEntity)
}