package id.rafidewi.mutabaahyaumiyah.views.userlain

import id.rafidewi.mutabaahyaumiyah.base.View
import id.rafidewi.mutabaahyaumiyah.model.User

interface UserLainView : View {
    fun onResponse(users: List<User>)
    fun onFailure(t: String)
}