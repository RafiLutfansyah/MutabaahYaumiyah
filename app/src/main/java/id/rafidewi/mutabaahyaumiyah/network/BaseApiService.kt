package id.rafidewi.mutabaahyaumiyah.network

import id.rafidewi.mutabaahyaumiyah.model.muslimsalat.Main
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Rafi Lutfansyah on 12/02/2018.
 */

interface BaseApiService {
    @GET("jakarta.json")
    fun getWaktuSholat(@Query("key") key: String): Call<Main>
}