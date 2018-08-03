package id.rafidewi.mutabaahyaumiyah.views.addkegiatan

import android.content.Context
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.base.App
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.database.AppDatabase
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddKegiatanPresenter(val context: Context) : Presenter<AddKegiatanView> {

    private var mView: AddKegiatanView? = null

    override fun onAttach(view: AddKegiatanView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun insertKegiatan(nama: String, jam: String) {

        Single.fromCallable {
            App.database?.kegiatanDao()?.insert(Kegiatan(null, nama, jam))
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()

        mView!!.onSuccessInsert("""
            Nama: $nama
            Jam: $jam
        """.trimIndent())
    }
}

