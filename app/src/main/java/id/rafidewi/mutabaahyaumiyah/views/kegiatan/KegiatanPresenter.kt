package id.rafidewi.mutabaahyaumiyah.views.kegiatan

import android.content.Context
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.base.App
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KegiatanPresenter(private val context: Context) : Presenter<KegiatanView> {

    private var mView: KegiatanView? = null

    override fun onAttach(view: KegiatanView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getListKegiatan() {
        App.database?.kegiatanDao()?.getAll()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: List<Kegiatan> ->
                    mView!!.onShowListKegiatan(AdapterKegiatan(t))
                }
    }
}

