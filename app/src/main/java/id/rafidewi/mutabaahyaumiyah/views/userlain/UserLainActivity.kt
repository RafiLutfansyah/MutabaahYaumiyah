package id.rafidewi.mutabaahyaumiyah.views.userlain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.rafidewi.mutabaahyaumiyah.R
import id.rafidewi.mutabaahyaumiyah.adapter.AdapterUserLain
import id.rafidewi.mutabaahyaumiyah.model.User
import kotlinx.android.synthetic.main.activity_user_lain.*
import org.jetbrains.anko.toast

class UserLainActivity : AppCompatActivity(), UserLainView {

    private var presenter: UserLainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_lain)

        initPresenter()
        onAttachView()

    }

    private fun initPresenter() {
        presenter = UserLainPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)

        listUserLain.setHasFixedSize(true)
        listUserLain.layoutManager = LinearLayoutManager(this)

        presenter!!.getUserLain()
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onResponse(users: List<User>) {
        listUserLain.adapter = AdapterUserLain(users)
    }

    override fun onFailure(t: String) {
        toast(t)
    }
}
