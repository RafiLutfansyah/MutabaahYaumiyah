package id.rafidewi.mutabaahyaumiyah.network

import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.model.User
import id.rafidewi.mutabaahyaumiyah.model.jadwalsholat.Api
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Rafi Lutfansyah on 12/02/2018.
 */

interface BaseApiService {
    @GET("User")
    fun getUser(@Query("email") email: String): Call<User>

    @POST("User")
    @FormUrlEncoded
    fun setUser(@Field("email") email: String,
                @Field("name") nama: String,
                @Field("photo_url") url_foto: String): Call<User>

    @GET("User")
    fun getUserLain() : Call<List<User>>

    @GET("Kegiatan")
    fun getKegiatan(@Query("query") query: String,
                    @Query("email") email: String): Call<List<Kegiatan>>

    @POST("Kegiatan")
    @FormUrlEncoded
    fun setKegiatan(@Field("email") email: String,
                    @Field("nama") nama: String,
                    @Field("jam") jam: String): Call<List<Kegiatan>>

    @GET("Kegiatan")
    fun removeKegiatan(@Query("query") query: String,
                       @Query("email") email: String,
                       @Query("id") id: Int): Call<List<Kegiatan>>

    @GET("KegiatanToday")
    fun checkKegiatanToday(@Query("case") case: String,
                           @Query("email") email: String,
                           @Query("tanggal") tanggal: String,
                           @Query("fajr") fajr: String,
                           @Query("dhuhr") dhuhr: String,
                           @Query("asr") asr: String,
                           @Query("maghrib") maghrib: String,
                           @Query("isha") isha: String): Call<String>

    @GET("KegiatanToday")
    fun getKegiatanToday(@Query("case") case: String,
                         @Query("email") email: String,
                         @Query("tanggal") tanggal: String): Call<List<KegiatanToday>>

    @GET("KegiatanToday")
    fun updateKegiatanToday(@Query("case") case: String,
                            @Query("id") id: Int,
                            @Query("email") email: String,
                            @Query("tanggal") tanggal: String,
                            @Query("jam_kerjakan") jamKerjakan: String): Call<List<KegiatanToday>>

    @GET("KegiatanToday")
    fun removeKegiatanToday(@Query("case") case: String,
                            @Query("id") id: Int,
                            @Query("email") email: String,
                            @Query("tanggal") tanggal: String): Call<List<KegiatanToday>>

    @GET("KegiatanToday")
    fun cancelKegiatanToday(@Query("query") query: String,
                            @Query("id") id: Int,
                            @Query("email") email: String,
                            @Query("tanggal") tanggal: String): Call<List<KegiatanToday>>

    @GET("KegiatanToday")
    fun getKegiatanUserLain(@Query("case") case: String,
                            @Query("email") email: String) : Call<List<KegiatanToday>>

    @GET("KegiatanToday")
    fun getDetailKegiatanUserLain(@Query("case") case: String,
                                  @Query("email") email: String,
                                  @Query("tanggal") tanggal: String) : Call<List<KegiatanToday>>

    @GET()
    fun getWaktuSholat(@Url url: String): Call<Api>
}