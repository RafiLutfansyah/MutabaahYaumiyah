package id.rafidewi.mutabaahyaumiyah.views.kegiatanuserlain

import android.content.Context
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KegiatanUserLainPresenter(private val context: Context) : Presenter<KegiatanUserLainView> {
    private var mView: KegiatanUserLainView? = null
    private val mApiService = RetrofitClient.getClient()

    override fun onAttach(view: KegiatanUserLainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getKegiatanUserLain(email: String) {
        mApiService.getKegiatanUserLain("user_lain", email).enqueue(object : Callback<List<KegiatanToday>>{
            override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                mView!!.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                mView!!.onFailure(t.toString())
            }
        })
    }
}

