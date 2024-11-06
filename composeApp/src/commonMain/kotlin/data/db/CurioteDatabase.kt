package data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.db.converters.Converters
import data.db.dao.category.CategoryDao
import data.db.dao.category.CurioteCategoryCombinedDao
import data.db.dao.curiote.CurioteDao
import data.db.dao.curiote.CurioteLinkDao
import data.db.entity.category.CurioteCategoryEntity
import data.db.entity.curiote.CurioteEntity
import data.db.entity.curiote.CurioteLinkEntity
import data.db.entity.full.CurioteCategoryCombinedEntity

@Database(
    entities = [
        CurioteEntity::class,
        CurioteLinkEntity::class,
        CurioteCategoryEntity::class,
        CurioteCategoryCombinedEntity::class
               ],
    version = DatabaseHelper.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class CurioteDatabase : RoomDatabase(){

    abstract fun curioteDao(): CurioteDao
    abstract fun curioteLinkDao(): CurioteLinkDao
    abstract fun categoryDao(): CategoryDao

    abstract fun curioteCategoryCombinedDao(): CurioteCategoryCombinedDao
}