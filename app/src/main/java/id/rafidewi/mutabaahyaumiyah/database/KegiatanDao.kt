package id.rafidewi.mutabaahyaumiyah.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import io.reactivex.Flowable

@Dao
interface KegiatanDao {
    @Query("SELECT * FROM kegiatan")
    fun getAll(): Flowable<List<Kegiatan>>

    @Insert(onConflict = REPLACE)
    fun insert(kegiatan: Kegiatan)

}