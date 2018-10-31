package id.rafidewi.mutabaahyaumiyah.views.addkegiatan

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.adapter.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_add_kegiatan.*

class AddKegiatanActivity : AppCompatActivity(), AddKegiatanView {
    private var presenter: AddKegiatanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kegiatan)

        initPresenter()
        onAttachView()

        buttons()

        rvKegiatan.setHasFixedSize(true)
        rvKegiatan.layoutManager = LinearLayoutManager(this)

        presenter!!.getKegiatan()
    }

    private fun buttons() {
        buttonInsert.setOnClickListener {
            presenter!!.setKegiatan(etNamaKegiatan.text.toString(), etJamKegiatan.text.toString())
            etNamaKegiatan.setText("")
            etJamKegiatan.setText("")
        }
    }

    override fun onResponse(adapterKegiatan: AdapterKegiatan) {
        rvKegiatan.adapter = adapterKegiatan

        rvKegiatan.addOnItemTouchListener(
                RecyclerItemClickListener(this, rvKegiatan, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        AlertDialog.Builder(this@AddKegiatanActivity)
                                .setTitle("Hapus?")
                                .setPositiveButton("Ya") { dialog, id ->
                                    presenter!!.removeKegiatan(position)
                                }
                                .setNegativeButton("Tidak") { dialog, id ->

                                }
                                .create()
                                .show()
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
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
