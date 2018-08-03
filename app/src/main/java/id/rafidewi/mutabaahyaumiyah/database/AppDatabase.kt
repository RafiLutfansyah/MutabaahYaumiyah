package id.rafidewi.mutabaahyaumiyah.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan

@Database(entities = [(Kegiatan::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kegiatanDao(): KegiatanDao
}