package id.rafidewi.mutabaahyaumiyah.views.kegiatanlalu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rafidewi.mutabaahyaumiyah.R

class KegiatanLaluActivity : AppCompatActivity(), KegiatanLaluView {
    private var presenter: KegiatanLaluPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = KegiatanLaluPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }
}
