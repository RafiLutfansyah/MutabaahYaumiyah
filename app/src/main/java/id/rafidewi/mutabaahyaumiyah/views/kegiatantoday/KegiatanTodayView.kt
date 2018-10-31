package id.rafidewi.mutabaahyaumiyah.views.kegiatantoday

import id.rafidewi.mutabaahyaumiyah.base.View
import id.rafidewi.mutabaahyaumiyah.model.KegiatanToday

interface KegiatanTodayView : View {
    fun onResponse(kegiatans: List<KegiatanToday>)
    fun onFailure(t: String)
}