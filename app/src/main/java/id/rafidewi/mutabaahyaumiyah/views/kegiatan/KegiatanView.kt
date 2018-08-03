package id.rafidewi.mutabaahyaumiyah.views.kegiatan

import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.base.View

interface KegiatanView : View {
    fun onShowListKegiatan(adapter: AdapterKegiatan)
}