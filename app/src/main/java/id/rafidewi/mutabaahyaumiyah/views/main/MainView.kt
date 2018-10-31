package id.rafidewi.mutabaahyaumiyah.views.main

import id.rafidewi.mutabaahyaumiyah.base.View

interface MainView : View {
    fun onResponse(response: String)
    fun onFailure(t: String)
    fun signOut()
}