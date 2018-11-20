package id.rafidewi.mutabaahyaumiyah.views.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.rafidewi.mutabaahyaumiyah.R
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import id.rafidewi.mutabaahyaumiyah.views.main.MainActivity
import id.rafidewi.mutabaahyaumiyah.views.main.MainPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

class LoginActivity : AppCompatActivity(), LoginView {
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)

        signInButton.setOnClickListener {
            signInButton.isEnabled = false
            loading.visibility = View.VISIBLE
            presenter!!.signIn()
        }
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        presenter!!.checkUser()
    }

    override fun signIn(signInIntent: Intent, RC_SIGN_IN: Int) {
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter!!.onActivityResult(requestCode, data!!)
    }

    override fun onActivityResultSuccess() {
        Toast.makeText(this, "Google Sign In was successful, authenticate with Firebase", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResultError() {
        Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
        signInButton.isEnabled = true
        loading.visibility = View.INVISIBLE
    }

    override fun signInWithCredentialSuccess() {
        Toast.makeText(this, "signInWithCredential:success", Toast.LENGTH_SHORT).show()
    }

    override fun signInWithCredentialError() {
        Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
        signInButton.isEnabled = true
        loading.visibility = View.INVISIBLE
    }

    override fun onResponse() {
        startActivity(intentFor<MainActivity>())
        finish()
    }
}
