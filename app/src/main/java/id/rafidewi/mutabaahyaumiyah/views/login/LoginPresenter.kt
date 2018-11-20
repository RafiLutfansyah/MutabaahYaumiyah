package id.rafidewi.mutabaahyaumiyah.views.login

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.User
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rafi Lutfansyah on 16/02/2018.
 */

class LoginPresenter(val context: Context) : Presenter<LoginView> {

    private var mView: LoginView? = null

    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001
    private val mAuth = FirebaseAuth.getInstance()
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail().build()
    private val mGoogleSignInClient: GoogleSignInClient? = GoogleSignIn.getClient(context, gso)

    private var mApiService = RetrofitClient.getClient()

    override fun onAttach(view: LoginView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun checkUser() {
        val user = mAuth.currentUser
        if(user != null) {
            mView!!.onResponse()
        }
    }

    fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        mView!!.signIn(signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
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
                            getUser(user)
                        }
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        mView!!.signInWithCredentialError()
                    }
                }
    }

    private fun getUser(user: FirebaseUser) {
        mApiService.getUser(user.email!!).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                val response = response!!.body()
                Log.d("onResponse", response.toString())
                mView!!.onResponse()
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
                setUser(user)
            }
        })
    }

    private fun setUser(user: FirebaseUser) {
        mApiService.setUser(user.email!!, user.displayName!!, user.photoUrl!!.toString()).enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                mView!!.onResponse()
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("onFailure", t.toString())
                signOut()
            }
        })
    }

    fun signOut() {
        mAuth!!.signOut()
        mGoogleSignInClient!!.signOut()
    }
}