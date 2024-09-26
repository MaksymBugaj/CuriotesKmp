package data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.db.converters.Converters
import data.db.dao.CurioteDao
import data.db.dao.CurioteLinkDao
import data.db.entity.CurioteEntity
import data.db.entity.CurioteLinkEntity

@Database(
    entities = [
        CurioteEntity::class,
        CurioteLinkEntity::class
               ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CurioteDatabase : RoomDatabase(){

    abstract fun curioteDao(): CurioteDao
    abstract fun curioteLinkDao(): CurioteLinkDao
}