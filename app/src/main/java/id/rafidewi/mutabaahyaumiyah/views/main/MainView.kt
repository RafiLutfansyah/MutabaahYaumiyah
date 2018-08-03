package id.rafidewi.mutabaahyaumiyah.views.main

import id.rafidewi.mutabaahyaumiyah.base.View
import id.rafidewi.mutabaahyaumiyah.model.Kegiatan
import id.rafidewi.mutabaahyaumiyah.model.muslimsalat.Items

interface MainView : View {
    fun onShowWaktuSholat(waktuSholat: Items)
    fun signOut()
}