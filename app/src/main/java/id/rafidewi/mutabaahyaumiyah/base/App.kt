package id.rafidewi.mutabaahyaumiyah.base

import id.rafidewi.mutabaahyaumiyah.database.AppDatabase
import android.arch.persistence.room.Room
import android.app.Application


class App : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mutabaahyaumiyah").build()
    }
}