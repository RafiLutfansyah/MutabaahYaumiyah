package id.rafidewi.mutabaahyaumiyah.views.addkegiatan

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddKegiatanPresenter(val context: Context) : Presenter<AddKegiatanView> {
    private var mView: AddKegiatanView? = null
    private var mApiService = RetrofitClient.getClient()
    private val email = FirebaseAuth.getInstance().currentUser!!.email!!
    private var kegiatans: List<Kegiatan> = emptyList()

    override fun onAttach(view: AddKegiatanView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getKegiatan() {
        mApiService.getKegiatan("get", email).enqueue(object : Callback<List<Kegiatan>> {
            override fun onResponse(call: Call<List<Kegiatan>>, response: Response<List<Kegiatan>>) {
                kegiatans.isEmpty()
                kegiatans = response.body()!!
                mView!!.onResponse(AdapterKegiatan(kegiatans))
            }

            override fun onFailure(call: Call<List<Kegiatan>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mView!!.onFailure(t.toString())
            }
        })
    }

    fun setKegiatan(nama: String, jam: String) {
        mApiService.setKegiatan(email, nama, jam).enqueue(object : Callback<List<Kegiatan>> {
            override fun onResponse(call: Call<List<Kegiatan>>, response: Response<List<Kegiatan>>) {
                kegiatans.isEmpty()
                kegiatans = response.body()!!
                mView!!.onResponse(AdapterKegiatan(kegiatans!!))
            }

            override fun onFailure(call: Call<List<Kegiatan>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mView!!.onFailure(t.toString())
            }
        })
    }

    fun removeKegiatan(position: Int) {
        val id = kegiatans!![position].id
        mApiService.removeKegiatan("remove", email, id!!).enqueue(object : Callback<List<Kegiatan>> {
            override fun onResponse(call: Call<List<Kegiatan>>, response: Response<List<Kegiatan>>) {
                kegiatans.isEmpty()
                kegiatans = response.body()!!
                mView!!.onResponse(AdapterKegiatan(kegiatans!!))
            }

            override fun onFailure(call: Call<List<Kegiatan>>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mView!!.onFailure(t.toString())
            }
        })
    }

//    fun insertKegiatan(nama: String, jam: String) {
//        Single.fromCallable {
//            App.database?.kegiatanDao()?.insert(Kegiatan(null, nama, jam))
//        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
//    }
//
//    fun getListKegiatan() {
//        App.database?.kegiatanDao()?.getAll()
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe { t: List<Kegiatan> ->
//                    Toast.makeText(context, t[0].nama, Toast.LENGTH_SHORT).show()
//
//                }
//    }
}

