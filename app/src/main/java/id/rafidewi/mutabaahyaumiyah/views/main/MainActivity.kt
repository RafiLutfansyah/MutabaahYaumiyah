package id.rafidewi.mutabaahyaumiyah.views.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rafidewi.mutabaahyaumiyah.model.muslimsalat.Items
import kotlinx.android.synthetic.main.activity_main.*
import id.rafidewi.mutabaahyaumiyah.R
import android.content.Intent
import id.rafidewi.mutabaahyaumiyah.views.login.LoginActivity
import id.rafidewi.mutabaahyaumiyah.views.addkegiatan.AddKegiatanActivity

class MainActivity : AppCompatActivity(), MainView {

    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        onAttachView()

        presenter!!.cekUser()
        presenter!!.getCurrentDate()
        presenter!!.getWaktuSholat()

        buttons()
    }

    private fun buttons() {
        buttonKegiatanHariIni.setOnClickListener {
            startActivity(Intent(this, KegiatanActivity::class.java))
        }

        buttonAddKegiatan.setOnClickListener {
            startActivity(Intent(this, AddKegiatanActivity::class.java))
        }

        buttonLogout.setOnClickListener {
            presenter!!.signOut()
        }
    }

    override fun signOut() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onShowWaktuSholat(waktuSholat: Items) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initPresenter() {
        presenter = MainPresenter(this)
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
