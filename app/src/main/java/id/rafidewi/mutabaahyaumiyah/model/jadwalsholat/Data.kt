package id.rafidewi.mutabaahyaumiyah.model.jadwalsholat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("ashar") @Expose var ashar: String,
        @SerializedName("dhuha") @Expose var dhuha: String,
        @SerializedName("dzuhur") @Expose var dzuhur: String,
        @SerializedName("imsak") @Expose var imsak: String,
        @SerializedName("isya") @Expose var isya: String,
        @SerializedName("maghrib") @Expose var maghrib: String,
        @SerializedName("subuh") @Expose var subuh: String,
        @SerializedName("tanggal") @Expose var tanggal: String,
        @SerializedName("terbit") @Expose var terbit: String
)