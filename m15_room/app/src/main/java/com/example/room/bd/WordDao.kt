package com.example.room.bd

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

@Query("SELECT * FROM word ORDER BY repeat DESC Limit 5 ")
    fun getAll(): Flow<List<Word>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)
    @Query("UPDATE word Set repeat=repeat+1 Where word_id=:word_id")
    suspend fun update(word_id: String)
    @Query("DELETE FROM word ")
    suspend fun delete()
}