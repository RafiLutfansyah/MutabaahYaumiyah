package id.rafidewi.mutabaahyaumiyah.views.kegiatantoday

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class KegiatanTodayPresenter(private val context: Context) : Presenter<KegiatanTodayView> {
    private var mTodayView: KegiatanTodayView? = null
    private var mApiService = RetrofitClient.getClient()
    private val email = FirebaseAuth.getInstance().currentUser!!.email!!

    override fun onAttach(todayView: KegiatanTodayView) {
        mTodayView = todayView
    }

    override fun onDetach() {
        mTodayView = null
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd")

        return df.format(c)
    }

    fun getKegiatan() {
        mApiService.getKegiatanToday("get", email, getCurrentDate()).enqueue(object : Callback<List<KegiatanToday>> {
            override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                val response = response.body()
                mTodayView!!.onResponse(response!!)
            }

            override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mTodayView!!.onFailure(t.toString())
            }
        })
    }

    fun removekegiatans(id: Int) {
        mApiService.removeKegiatanToday("remove", id, email, getCurrentDate()).enqueue(object : Callback<List<KegiatanToday>>{
            override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                val response = response.body()
                mTodayView!!.onResponse(response!!)
            }

            override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mTodayView!!.onFailure(t.toString())
            }
        })
    }

    fun cancelKegiatan(kegiatans: List<KegiatanToday>, position: Int) {
        val id = kegiatans[position].id
        mApiService.cancelKegiatanToday("update", id, email, getCurrentDate()).enqueue(object : Callback<List<KegiatanToday>> {
            override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                val response = response.body()
                mTodayView!!.onResponse(response!!)
            }

            override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mTodayView!!.onFailure(t.toString())
            }
        })
    }
}