package id.rafidewi.mutabaahyaumiyah.views.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import id.rafidewi.mutabaahyaumiyah.R
import android.content.Intent
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.views.login.LoginActivity
import id.rafidewi.mutabaahyaumiyah.views.addkegiatan.AddKegiatanActivity
import id.rafidewi.mutabaahyaumiyah.views.kegiatanlalu.KegiatanLaluActivity
import id.rafidewi.mutabaahyaumiyah.views.kegiatantoday.KegiatanTodayTodayActivity
import id.rafidewi.mutabaahyaumiyah.views.userlain.UserLainActivity

class MainActivity : AppCompatActivity(), MainView {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        onAttachView()

        presenter!!.cekUser()

        buttons()
    }

    private fun buttons() {
        buttonKegiatanHariIni.setOnClickListener {
            startActivity(Intent(this, KegiatanTodayTodayActivity::class.java))
        }

        buttonAddKegiatan.setOnClickListener {
            startActivity(Intent(this, AddKegiatanActivity::class.java))
        }

        buttonKegiatanLalu.setOnClickListener {
            startActivity(Intent(this, KegiatanLaluActivity::class.java))
        }

        buttonUserLain.setOnClickListener {
            startActivity(Intent(this, UserLainActivity::class.java))
        }

        buttonLogout.setOnClickListener {
            presenter!!.signOut()
        }
    }


    override fun onResponse(response: String) {
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
    }

    override fun signOut() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
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
