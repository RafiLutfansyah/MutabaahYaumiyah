package id.rafidewi.mutabaahyaumiyah.model.jadwalsholat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Jadwal(
        @SerializedName("status") @Expose var status: String,
        @SerializedName("data") @Expose var data: Data
)