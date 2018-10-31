package id.rafidewi.mutabaahyaumiyah.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KegiatanToday(@SerializedName("id") @Expose val id: Int,
                         @SerializedName("email") @Expose val email: String,
                         @SerializedName("nama") @Expose val nama: String,
                         @SerializedName("tanggal") @Expose val tanggal: String,
                         @SerializedName("jam") @Expose val jam: String,
                         @SerializedName("jam_kerjakan") @Expose val jamKerjakan: String)