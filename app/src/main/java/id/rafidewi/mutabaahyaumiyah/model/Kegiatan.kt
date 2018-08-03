package id.rafidewi.mutabaahyaumiyah.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "kegiatan")
data class Kegiatan(@PrimaryKey(autoGenerate = true) val id: Int?,
                    @ColumnInfo(name = "nama") val nama: String,
                    @ColumnInfo(name = "jam") var jam: String
)