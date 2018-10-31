package id.rafidewi.mutabaahyaumiyah.views.detailkegiatanuserlain

import id.rafidewi.mutabaahyaumiyah.base.View
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday
import id.rafidewi.mutabaahyaumiyah.model.User

interface DetailKegiatanUserLainView : View {
    fun onResponse(users: List<KegiatanToday>)
    fun onFailure(t: String)
}