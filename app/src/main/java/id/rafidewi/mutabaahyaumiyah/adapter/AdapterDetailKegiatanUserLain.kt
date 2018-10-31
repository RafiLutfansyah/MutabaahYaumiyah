package id.rafidewi.mutabaahyaumiyah.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import kotlinx.android.synthetic.main.adapter_detail_kegiatan_user_lain.view.*

class AdapterDetailKegiatanUserLain(private val kegiatans: List<KegiatanToday>) : RecyclerView.Adapter<AdapterDetailKegiatanUserLain.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatan: KegiatanToday) = with(itemView) {
            itemView.cbKegiatan.text = "${kegiatan.jam} - ${kegiatan.nama}"
            if (kegiatan.jamKerjakan != null) {
                itemView.cbKegiatan.isChecked = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_detail_kegiatan_user_lain, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatans[position])
    }

    override fun getItemCount(): Int {
        return kegiatans.size
    }
}