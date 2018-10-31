package id.rafidewi.mutabaahyaumiyah.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("email") @Expose val email: String,
        @SerializedName("name") @Expose val name: String,
        @SerializedName("photo_url") @Expose val photoUrl: String)