package id.rafidewi.mutabaahyaumiyah.views.userlain

import android.content.Context
import id.rafidewi.mutabaahyaumiyah.base.Presenter
import id.rafidewi.mutabaahyaumiyah.model.User
import id.rafidewi.mutabaahyaumiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UserLainPresenter(private val context: Context) : Presenter<UserLainView> {
    private var mView: UserLainView? = null
    private val mApiService = RetrofitClient.getClient()

    override fun onAttach(view: UserLainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getUserLain() {
        mApiService.getUserLain().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                mView!!.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                mView!!.onFailure(t.toString())
            }
        })
    }
}

