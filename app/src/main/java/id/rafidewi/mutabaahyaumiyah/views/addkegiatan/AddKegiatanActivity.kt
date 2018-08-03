package id.rafidewi.mutabaahyaumiyah.views.addkegiatan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.R
import kotlinx.android.synthetic.main.activity_add_kegiatan.*


class AddKegiatanActivity : AppCompatActivity(), AddKegiatanView {
    private var presenter: AddKegiatanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kegiatan)

        initPresenter()
        onAttachView()

        buttons()
    }

    private fun buttons() {
        buttonInsert.setOnClickListener {
            presenter!!.insertKegiatan(etNamaKegiatan.text.toString(), etJamKegiatan.text.toString())
        }
    }

    override fun onSuccessInsert(onSuccess: String) {
        Toast.makeText(this, onSuccess, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        presenter = AddKegiatanPresenter(this)
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
