package id.rafidewi.mutabaahyaumiyah.views.kegiatanuserlain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatanUserLain
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import kotlinx.android.synthetic.main.activity_kegiatan_user_lain.*
import org.jetbrains.anko.toast

class KegiatanUserLainActivity : AppCompatActivity(), KegiatanUserLainView {

    private var presenter: KegiatanUserLainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan_user_lain)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = KegiatanUserLainPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)

        listKegiatanUserLain.setHasFixedSize(true)
        listKegiatanUserLain.layoutManager = LinearLayoutManager(this)

        presenter!!.getKegiatanUserLain(intent.getStringExtra("email"))
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onResponse(users: List<KegiatanToday>) {
        listKegiatanUserLain.adapter = AdapterKegiatanUserLain(users)
    }

    override fun onFailure(t: String) {
        toast(t)
    }
}
