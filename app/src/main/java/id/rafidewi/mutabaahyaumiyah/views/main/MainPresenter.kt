package id.rafidewi.mutabaahyaumiyah.views.main

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.muslimsalat.Main
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.R


class MainPresenter(private val context: Context) : Presenter<MainView> {

    private var mView: MainView? = null
    private var mApiService = RetrofitClient.getClient()
    private val mAuth = FirebaseAuth.getInstance()

    override fun onAttach(view: MainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun cekUser() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {

        }
        else {
            signOut()
        }
    }

    fun signOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val mGoogleSignInClient: GoogleSignInClient? = GoogleSignIn.getClient(context, gso)

        mAuth!!.signOut()
        mGoogleSignInClient!!.signOut()
                .addOnCompleteListener {
                    mView!!.signOut()
                }
    }

    fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("EEEE, dd-MM-yyyy")

        return df.format(c)
    }

    fun getWaktuSholat() {
        mApiService.getWaktuSholat("bd099c5825cbedb9aa934e255a81a5fc").enqueue(object : Callback<Main> {
            override fun onResponse(call: retrofit2.Call<Main>, response: Response<Main>) {
                val response = response.body()!!.items[0]

                // mView!!.onShowWaktuSholat(response)
            }

            override fun onFailure(call: retrofit2.Call<Main>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }

        })
    }
}

