package id.rafidewi.mutabaahyaumiyah.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday

class AdapterKegiatanLalu(private val kegiatans: List<KegiatanToday>) : RecyclerView.Adapter<AdapterKegiatanLalu.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatan: KegiatanToday) = with(itemView) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kegiatan_today, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatans[position])
    }

    override fun getItemCount(): Int {
        return kegiatans.size
    }
}