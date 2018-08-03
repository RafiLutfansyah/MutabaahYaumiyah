package id.rafidewi.mutabaahyaumiyah.views.kegiatan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.views.kegiatan.KegiatanPresenter
import id.rafidewi.mutabaahyaumiyah.views.kegiatan.KegiatanView
import kotlinx.android.synthetic.main.activity_kegiatan.*


class KegiatanActivity : AppCompatActivity(), KegiatanView {
    private var presenter: KegiatanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        initPresenter()
        onAttachView()

        listKegiatan.setHasFixedSize(true)
        listKegiatan.layoutManager = LinearLayoutManager(this)

        presenter!!.getListKegiatan()
    }

    override fun onShowListKegiatan(adapter: AdapterKegiatan) {
        listKegiatan.adapter = adapter
    }

    private fun initPresenter() {
        presenter = KegiatanPresenter(this)
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
