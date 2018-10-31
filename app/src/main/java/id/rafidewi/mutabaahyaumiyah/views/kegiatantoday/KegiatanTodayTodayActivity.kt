package id.rafidewi.mutabaahyaumiyah.views.kegiatantoday

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatanToday
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import kotlinx.android.synthetic.main.activity_kegiatan.*
import id.rafidewi.mutabaahyaumiyah.adapter.RecyclerItemClickListener


class KegiatanTodayTodayActivity : AppCompatActivity(), KegiatanTodayView {
    private var todayPresenter: KegiatanTodayPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        initPresenter()
        onAttachView()

        rvKegiatanNow.setHasFixedSize(true)
        rvKegiatanNow.layoutManager = LinearLayoutManager(this)

        todayPresenter!!.getKegiatan()
    }

    override fun onResponse(kegiatans: List<KegiatanToday>) {
        rvKegiatanNow.adapter = AdapterKegiatanToday(kegiatans)
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        todayPresenter = KegiatanTodayPresenter(this)
    }

    override fun onAttachView() {
        todayPresenter!!.onAttach(this)
    }

    override fun onDetachView() {
        todayPresenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }
}
