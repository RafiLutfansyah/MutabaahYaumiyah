package id.rafidewi.mutabaahyaumiyah.views.detailkegiatanuserlain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterDetailKegiatanUserLain
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import kotlinx.android.synthetic.main.activity_detail_kegiatan_user_lain.*
import org.jetbrains.anko.toast

class DetailKegiatanUserLainActivity : AppCompatActivity(), DetailKegiatanUserLainView {

    private var presenterKegiatan: DetailKegiatanUserLainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan_user_lain)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenterKegiatan = DetailKegiatanUserLainPresenter(this)
    }

    override fun onAttachView() {
        presenterKegiatan!!.onAttach(this)

        listKegiatan.setHasFixedSize(true)
        listKegiatan.layoutManager = LinearLayoutManager(this)

        presenterKegiatan!!.getDetailKegiatanUserLain(intent.getStringExtra("email"), intent.getStringExtra("tanggal"))
    }

    override fun onDetachView() {
        presenterKegiatan!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onResponse(users: List<KegiatanToday>) {
        listKegiatan.adapter = AdapterDetailKegiatanUserLain(users)
    }

    override fun onFailure(t: String) {
        toast(t)
    }
}
