package com.entertainment.showflix.feature_album.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.entertainment.showflix.R
import com.entertainment.showflix.base.BaseActivity
import com.entertainment.showflix.base.Status
import com.entertainment.showflix.databinding.ActivityAlbumsBinding
import com.entertainment.showflix.feature_album.adapter.AlbumAdapter
import com.entertainment.showflix.feature_album.model.Album
import com.entertainment.showflix.feature_album.viewmodel.AlbumsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_albums.*
import javax.inject.Inject

class AlbumsActivity : BaseActivity<ActivityAlbumsBinding, AlbumsViewModel>(AlbumsViewModel::class.java) {

    private val columnWidth = R.dimen.dp_180

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutRes() = R.layout.activity_albums

    override fun getVMFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }

    override fun onInject() {
        AndroidInjection.inject(this)
    }

    override fun initViewModel(viewModel: AlbumsViewModel) {
        viewModel.getAlbums().observe(this, Observer { albums ->
            albums?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        hideProgressBar()
                        inflateRecyclerView(it.data)
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        showError(it.message)
                    }

                    else -> {
                        showProgressBar()
                    }
                }
            } ?: kotlin.run {
                hideProgressBar()
            }


        })
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun inflateRecyclerView(albums: List<Album>?) {
        albums?.let {
            setLayoutManager()
            recyclerView.adapter = AlbumAdapter(this, it)
        }

    }

    private fun setLayoutManager() {
        val columnWidthInPixels = resources.getDimension(columnWidth)
        val spanCount =
            Math.floor((recyclerView.width / columnWidthInPixels).toDouble()).toInt()
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)

    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}
