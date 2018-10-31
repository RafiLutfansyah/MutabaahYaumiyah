package id.rafidewi.mutabaahyaumiyah.views.kegiatanuserlain

import id.rafidewi.mutabaahyaumiyah.base.View
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.model.User

interface KegiatanUserLainView : View {
    fun onResponse(users: List<KegiatanToday>)
    fun onFailure(t: String)
}