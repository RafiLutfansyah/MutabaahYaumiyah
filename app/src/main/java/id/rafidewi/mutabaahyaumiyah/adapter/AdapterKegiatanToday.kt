package id.rafidewi.mutabaahyaumiyah.adapter

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.R
import kotlinx.android.synthetic.main.adapter_kegiatan_today.view.*
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AdapterKegiatanToday(private val kegiatanTodayTodays: List<KegiatanToday>) : RecyclerView.Adapter<AdapterKegiatanToday.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(kegiatanToday: KegiatanToday) = with(itemView) {

            if (kegiatanToday.jamKerjakan != null) {
                itemView.cbKegiatan.text = """
                    ${kegiatanToday.jam} - ${kegiatanToday.nama}
                    Jam dikerjakan: ${kegiatanToday.jamKerjakan}
                    """.trimIndent()
                itemView.cbKegiatan.isChecked = true
                itemView.cbKegiatan.isEnabled = false
            } else {
                itemView.cbKegiatan.text = """
                    ${kegiatanToday.jam} - ${kegiatanToday.nama}
                    Belum dikerjakan!
                    """.trimIndent()
            }

            if (kegiatanToday.jamKerjakan == null) {
                itemView.cbKegiatan.setOnClickListener {
                    AlertDialog.Builder(context)
                            .setTitle("Yakin sudah mengerjakan?")
                            .setCancelable(false)
                            .setPositiveButton("Ya") { dialog, id ->
                                itemView.cbKegiatan.isChecked = true
                                itemView.cbKegiatan.isEnabled = false

                                val mApiService = RetrofitClient.getClient()
                                val email = FirebaseAuth.getInstance().currentUser!!.email!!

                                    mApiService.updateKegiatanToday("update", kegiatanToday.id, kegiatanToday.email, getCurrentDate(), getCurrentTime()).enqueue(object : Callback<List<KegiatanToday>> {
                                        override fun onResponse(call: Call<List<KegiatanToday>>, response: Response<List<KegiatanToday>>) {
                                            val response = response.body()
                                            itemView.cbKegiatan.isChecked = true
                                            itemView.cbKegiatan.isEnabled = false
                                        }

                                        override fun onFailure(call: Call<List<KegiatanToday>>, t: Throwable) {
                                            Log.d("onFailure", t.toString())
                                            itemView.cbKegiatan.isChecked = false
                                            itemView.cbKegiatan.isEnabled = true
                                        }
                                    })
                            }
                            .setNegativeButton("Tidak") { dialog, id ->
                                itemView.cbKegiatan.isChecked = false
                                itemView.cbKegiatan.isEnabled = true
                            }
                            .create().show()
                }
            }
        }

        private fun getCurrentDate(): String {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("yyyy-MM-dd")

            return df.format(c)
        }

        private fun getCurrentTime(): String {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("HH:mm:ss")

            return df.format(c)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kegiatan_today, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(kegiatanTodayTodays[position])
    }

    override fun getItemCount(): Int {
        return kegiatanTodayTodays.size
    }
}