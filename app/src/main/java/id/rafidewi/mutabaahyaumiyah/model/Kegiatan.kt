package id.rafidewi.mutabaahyaumiyah.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Kegiatan(@SerializedName("id") @Expose val id: Int?,
                    @SerializedName("nama") @Expose val nama: String,
                    @SerializedName("jam") @Expose var jam: String
)