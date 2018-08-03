package id.rafidewi.mutabaahyaumiyah.views.login

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.R

class LoginPresenter(context: Context) : Presenter<LoginView> {
    private var mView: LoginView? = null

    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001
    // [START declare_auth]
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    // [END declare_auth]
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail().build()
    private val mGoogleSignInClient: GoogleSignInClient? = GoogleSignIn.getClient(context, gso)

    fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        mView!!.signIn(signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
                mView!!.onActivityResultSuccess()
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                mView!!.onActivityResultError()
            }
        }
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth.currentUser
                        if (user != null) {
                            val name = user.displayName
                            val email = user.email
                            val photoUrl = user.photoUrl
                            mView!!.onSuccessLogin(name!!, email!!, photoUrl!!)
                        }
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        mView!!.signInWithCredentialError()
                    }
                }
    }

    override fun onAttach(view: LoginView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }
}