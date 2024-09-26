package data.db.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReturnId(entity: T): Long

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(vararg entity: T)

    @Update
    suspend fun update(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun delete(vararg entity: T)

    @Delete
    suspend fun delete(entities: List<T>)
}