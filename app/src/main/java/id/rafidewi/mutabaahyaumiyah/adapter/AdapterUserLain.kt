package id.rafidewi.mutabaahyaumiyah.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.User
import id.rafidewi.mutabaahyaumiyah.views.kegiatanuserlain.KegiatanUserLainActivity
import kotlinx.android.synthetic.main.adapter_user_lain.view.*
import org.jetbrains.anko.startActivity

class AdapterUserLain(private val users: List<User>) : RecyclerView.Adapter<AdapterUserLain.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(user: User) = with(itemView) {
            Glide.with(this).load(user.photoUrl).into(itemView.imageView)
            itemView.textName.text = user.name
            itemView.textEmail.text = user.email

            itemView.setOnClickListener {
                context.startActivity<KegiatanUserLainActivity>("email" to user.email)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user_lain, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}