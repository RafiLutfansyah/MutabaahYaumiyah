package id.rafidewi.mutabaahyaumiyah.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import kotlinx.android.synthetic.main.adapter_kegiatan.view.*

class AdapterKegiatan(private val kegiatans: List<Kegiatan>) : RecyclerView.Adapter<AdapterKegiatan.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatan: Kegiatan) = with(itemView) {
            itemView.textKegiatan.text = "${kegiatan.jam} - ${kegiatan.nama}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kegiatan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatans[position])
    }

    override fun getItemCount(): Int {
        return kegiatans.size
    }
}