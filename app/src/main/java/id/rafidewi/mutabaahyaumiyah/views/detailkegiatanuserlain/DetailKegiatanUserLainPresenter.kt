package id.rafidewi.mutabaahyaumiyah.views.detailkegiatanuserlain

import android.content.Context
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailKegiatanUserLainPresenter(private val context: Context) : Presenter<DetailKegiatanUserLainView> {
    private var mViewKegiatan: DetailKegiatanUserLainView? = null
    private val mApiService = RetrofitClient.getClient()

    override fun onAttach(viewKegiatan: DetailKegiatanUserLainView) {
        mViewKegiatan = viewKegiatan
    }

    override fun onDetach() {
        mViewKegiatan = null
    }

    fun getDetailKegiatanUserLain(email: String, tanggal: String) {
        mApiService.getDetailKegiatanUserLain("detail", email, tanggal).enqueue(object : Callback<List<KegiatanToday>>{
            override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                mViewKegiatan!!.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                mViewKegiatan!!.onFailure(t.toString())
            }
        })
    }
}

