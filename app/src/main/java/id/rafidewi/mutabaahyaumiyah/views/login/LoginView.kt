package id.rafidewi.mutabaahyaumiyah.views.login

import android.content.Intent
import android.net.Uri
import id.rafidewi.mutabaahyaumiyah.base.View

interface LoginView : View {
    fun signIn(signInIntent: Intent, RC_SIGN: Int)
    fun onActivityResultSuccess()
    fun onActivityResultError()
    fun signInWithCredentialSuccess()
    fun signInWithCredentialError()
    fun onSuccessLogin(name: String, email: String, photoUrl: Uri)
}