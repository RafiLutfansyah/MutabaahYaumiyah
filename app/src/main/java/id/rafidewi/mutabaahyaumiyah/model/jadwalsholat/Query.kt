package id.rafidewi.mutabaahyaumiyah.model.jadwalsholat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Query (
    @SerializedName("format") @Expose var format: String,
    @SerializedName("kota") @Expose var kota: String,
    @SerializedName("tanggal") @Expose var tanggal: String
)