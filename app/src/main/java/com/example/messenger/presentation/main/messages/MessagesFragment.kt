package com.example.messenger.presentation.main.messages

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.base.ABaseListFragment
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.drawer.*
import kotlinx.android.synthetic.main.fragment_messages.*
import java.io.File
import java.io.InputStream
import javax.inject.Inject


class MessagesFragment : ABaseListFragment<Message, RecyclerView.ViewHolder>(), IMessagesView {

    private val REQUEST_ACCESS_READ_EXTERNAL_STORAGE = 111
    private val REQUEST_CODE_GALLERY = 100

    private var cacheFile: File? = null
    private var imagePath: String? = null
    private var imageStream: InputStream? = null

    private val messages = listOf(
        Message("12.12.12", true, 1, 22, "Hello", 2),
        Message("12.12.12", true, 1, 23, "Hello", 2),
        Message("12.12.12", true, 1, 24, "Hello", 2),
        Message("12.12.12", true, 1, 25, "Hello", 2),
        Message("12.12.12", true, 1, 26, "Hello", 2)
    )

    @Inject
    @InjectPresenter
    lateinit var presenter: MessagesPresenter

    @ProvidePresenter
    fun providerPresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_messages
    override fun getListId(): Int = R.id.rvMessagesList

    private val adapter = MessagesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topAppBar.setNavigationOnClickListener {
            dlMessages.openDrawer(Gravity.LEFT)
        }

        btnLogout.setOnClickListener{
            presenter.logout()
        }

        ivAvatar.setOnClickListener {
            openGallery()
        }

        bindUserInfo(presenter.getUserInfo())

        adapter.data = this.messages.toMutableList()
    }

    override fun provideAdapter(): ABaseAdapter<Message, RecyclerView.ViewHolder> = adapter

    override fun bindMessages(messages: List<Message>) {
        adapter.data = messages.toMutableList()
    }

    override fun bindUserInfo(user: User?) {
        tvName.text = user?.login
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/" }
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) return

        when(requestCode){
            REQUEST_CODE_GALLERY -> {

                val selectedImage = data.data?.path
                val bitmap = BitmapFactory.decodeFile(selectedImage)
                Log.i("tag", bitmap.toString())
                ivAvatar.setImageBitmap(bitmap)
            }
        }
    }
}
