package id.rafidewi.mutabaahyaumiyah.views.addkegiatan

import id.rafidewi.mutabaahyaumiyah.adapter.AdapterKegiatan
import id.rafidewi.mutabaahyaumiyah.base.View

interface AddKegiatanView : View {
    fun onResponse(adapterKegiatan: AdapterKegiatan)
    fun onFailure(t: String)
}