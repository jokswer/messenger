package com.example.messenger.presentation.main.messages

import android.app.Activity
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.messenger.App
import com.example.messenger.R
import com.example.messenger.base.ABaseAdapter
import com.example.messenger.base.ABaseListFragment
import com.example.messenger.base.Utils
import com.example.messenger.domain.di.modules.NetModule
import com.example.messenger.domain.repositories.models.rest.Message
import com.example.messenger.domain.repositories.models.rest.User
import com.example.messenger.presentation.main.IMainActivity
import kotlinx.android.synthetic.main.drawer.*
import kotlinx.android.synthetic.main.fragment_messages.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.*
import javax.inject.Inject


class MessagesFragment : ABaseListFragment<Message, RecyclerView.ViewHolder>(), IMessagesView, OnContactsListener {

    companion object {
        private const val REQUEST_CODE_GALLERY = 100
        private const val REQUEST_PERMISSION = 101
    }

    private var cacheFile: File? = null
    private var imagePath: String? = null
    private var imageStream: InputStream? = null

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

        presenter.getNewMessages()

        createContactsList(view)

        presenter.getUsers()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) return
        val provider = this.activity

        when(requestCode){
            REQUEST_CODE_GALLERY -> {
                if (resultCode == Activity.RESULT_OK){

                    val selectedImage = data.data
                    imagePath = getRealPathFromURI(selectedImage, provider)
                    if (imagePath != null || selectedImage != null) {
                        cacheFile = prepareCache()
                        var success = imagePath != null
                        if (!success && selectedImage != null) {
                            try {
                                imageStream = provider?.contentResolver?.openInputStream(selectedImage)
                                success = imageStream != null
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }

                        if (success) {
                            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
                        }
                    }

                    cacheFile = prepareCache()
                    val file = cacheFile

                    if (file != null) {

                        val sourcePath = file.absolutePath
                        Utils.compressImage(sourcePath)

                        onSuccess(file)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            REQUEST_PERMISSION -> {
                val cacheFile = cacheFile
                if (cacheFile != null && (imagePath != null || imageStream != null)) {
                    onPrepareImage(cacheFile, imagePath, imageStream)
                }
            }
        }
    }

    private val adapter = MessagesAdapter()
    override fun provideAdapter(): ABaseAdapter<Message, RecyclerView.ViewHolder> = adapter

    private val contactsAdapter = ContactsAdapter(this)

    override fun bindUserInfo(user: User?) {
        tvName.text = user?.login
    }

    private fun createContactsList(view: View){
        val list = view.findViewById<RecyclerView>(R.id.rvContactsList)
        list.layoutManager  = LinearLayoutManager(context)
        list.adapter = contactsAdapter
    }

    override fun onError(message: String?) {
        if (message != null) toast(message)
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/" }
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    private fun prepareCache(): File {
        return File(Utils.getDraftExternalDir(App.appContext), UUID.randomUUID().toString())
    }

    private val projection = arrayOf(MediaStore.Images.Media.DATA)
    private fun getRealPathFromURI(
        contentURI: Uri?,
        activity: FragmentActivity?
    ): String? {

        if (contentURI == null)
            return null

        activity?.contentResolver?.query(contentURI, projection, null, null, null)?.use {
            it.moveToFirst()
            val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return if (idx == -1) null else it.getString(idx)
        }

        return null
    }

    private fun onPrepareImage(cacheFile: File, imagePath: String?, imageStream: InputStream?) {

        if (imagePath == null && imageStream == null)
            return

        Thread {

            val copyResult = when {
                imagePath != null -> Utils.copy(File(imagePath), cacheFile)
                imageStream != null -> Utils.copy(imageStream, FileOutputStream(cacheFile))
                else -> return@Thread
            }

            val sourcePath = cacheFile.absolutePath

            if (copyResult && !sourcePath.isNullOrEmpty()) {
                Utils.compressImage(sourcePath)
                this.activity?.runOnUiThread { onSuccess(cacheFile) }
            }
        }.start()
    }

    private fun onSuccess(file: File){
        presenter.uploadAvatar(file)
    }

    override fun onSuccess(url: String?) {
        val fullPath = "${NetModule.DOMAIN_MAIN_API}$url"

        Glide
            .with(this)
            .load(fullPath)
            .apply(RequestOptions.circleCropTransform())
            .into(ivAvatar)
    }

    override fun bindContacts(users: List<User>) {
        contactsAdapter.data = users.toMutableList()
    }

    override fun bindMessages(messages: List<Message>) {
        adapter.data = messages.toMutableList()
    }

    override fun onContactClick(data: User) {
        activity.let {
            if (it is IMainActivity) it.showDialogue(data)
        }
    }
}
