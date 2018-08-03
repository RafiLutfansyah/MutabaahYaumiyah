package id.rafidewi.mutabaahyaumiyah.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import kotlinx.android.synthetic.main.list_kegiatan.view.*
import android.content.DialogInterface



class AdapterKegiatan(private val kegiatans: List<Kegiatan>) : RecyclerView.Adapter<AdapterKegiatan.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatan: Kegiatan) = with(itemView) {
            cbKegiatan.text = "${kegiatan.jam} - ${kegiatan.nama}"

            itemView.setOnClickListener {
                AlertDialog.Builder(context)
                        .setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, id ->
                            itemView.isActivated = false
                            cbKegiatan.isChecked = true
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, id ->
                            cbKegiatan.isChecked = false
                        })
                        .create()


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_kegiatan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatans.get(position))
    }

    override fun getItemCount(): Int {
        return kegiatans.size
    }
}