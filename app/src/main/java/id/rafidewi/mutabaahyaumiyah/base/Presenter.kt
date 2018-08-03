package id.rafidewi.mutabaahyaumiyah.base

interface Presenter<T : View> {
    fun onAttach(view: T)
    fun onDetach()
}