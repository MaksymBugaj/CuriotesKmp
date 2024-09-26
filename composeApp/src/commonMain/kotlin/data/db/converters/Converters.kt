package data.db.converters

import androidx.room.TypeConverter
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds()
    }

    @TypeConverter
    fun fromUUID(uuid: Uuid?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUuid(uuidString: String?): Uuid? {
        return if (uuidString != null) uuidFrom(uuidString) else null
    }
}