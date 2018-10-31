package id.rafidewi.mutabaahyaumiyah.views.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.model.jadwalsholat.Api
import retrofit2.Call

class MainPresenter(private val context: Context) : Presenter<MainView> {

    private var mView: MainView? = null
    private var mApiService = RetrofitClient.getClient()
    private val mAuth = FirebaseAuth.getInstance()
    private var email = ""

    override fun onAttach(view: MainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun cekUser() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            signOut()
        } else {
            email = mAuth.currentUser!!.email!!
            getWaktuSholat()
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

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(c)
    }

    private fun getWaktuSholat() {
        mApiService.getWaktuSholat("https://api.banghasan.com/sholat/format/json/jadwal/kota/703/tanggal/${getCurrentDate()}").enqueue(object : Callback<Api> {
            override fun onResponse(call: retrofit2.Call<Api>, response: Response<Api>) {
                val r = response.body()!!.jadwal.data
                val fajr = r.subuh
                val dhuhr = r.dzuhur
                val asr = r.ashar
                val maghrib = r.maghrib
                val isha = r.isya

                checkKegiatans(fajr, dhuhr, asr, maghrib, isha)
            }

            override fun onFailure(call: retrofit2.Call<Api>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }
        })
    }

    private fun checkKegiatans(fajr: String, dhuhr: String, asr: String, maghrib: String, isha: String) {
        mApiService.checkKegiatanToday("check", email, getCurrentDate(), fajr, dhuhr, asr, maghrib, isha).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val response = response.body()
                mView!!.onResponse(response!!)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("onFailure", t.toString())
                mView!!.onFailure(t.toString())
            }
        })
    }
}

