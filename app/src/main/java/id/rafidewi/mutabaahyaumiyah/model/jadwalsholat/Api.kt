package id.rafidewi.mutabaahyaumiyah.model.jadwalsholat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Api(
        @SerializedName("status") @Expose var status: String,
        @SerializedName("query") @Expose var query: Query,
        @SerializedName("jadwal") @Expose var jadwal: Jadwal
)