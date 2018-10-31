package id.rafidewi.mutabaahyaumiyah.views.kegiatanlalu

import android.content.Context
import id.rafidewi.mutabaahyaumiyah.base.Presenter

class KegiatanLaluPresenter(private val context: Context) : Presenter<KegiatanLaluView> {

    private var mView: KegiatanLaluView? = null

    override fun onAttach(view: KegiatanLaluView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }
}

