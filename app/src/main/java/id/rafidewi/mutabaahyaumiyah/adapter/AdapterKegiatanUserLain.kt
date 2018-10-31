package id.rafidewi.mutabaahyaumiyah.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.views.detailkegiatanuserlain.DetailKegiatanUserLainActivity
import kotlinx.android.synthetic.main.adapter_kegiatan_user_lain.view.*
import org.jetbrains.anko.startActivity

class AdapterKegiatanUserLain(private val kegiatans: List<KegiatanToday>) : RecyclerView.Adapter<AdapterKegiatanUserLain.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatan: KegiatanToday) = with(itemView) {
            itemView.textTanggal.text = kegiatan.tanggal

            itemView.setOnClickListener {
                context.startActivity<DetailKegiatanUserLainActivity>("email" to kegiatan.email, "tanggal" to kegiatan.tanggal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kegiatan_user_lain, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatans[position])
    }

    override fun getItemCount(): Int {
        return kegiatans.size
    }
}