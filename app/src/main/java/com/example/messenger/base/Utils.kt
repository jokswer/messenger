package com.example.messenger.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

object Utils {

    private val MAX_IMAGE_FILE_SIZE = 1024 * 256
    private val MAX_SAVE_IMAGE_SIDE = 1000 // px
    private val SAVE_IMAGE_QUALITY = 50 // (0, 100]

    fun getDraftExternalDir(context: Context): File {
        return File(context.getExternalFilesDir(null), "draft").apply { mkdirs() }
    }

    fun tryCatch(call: () -> Unit) = try { call(); null } catch (e: Throwable) { e }
    fun close(target: Closeable?) { tryCatch { target?.close() }?.printStackTrace() }

    fun copy(input: InputStream?, output: OutputStream?): Boolean {
        input ?: return false
        output ?: return false

        return (tryCatch {

            input.copyTo(output)

        }?.let { it.printStackTrace(); false } ?: true).apply {
            close(input)
            close(output)
        }
    }

    fun copy(input: File?, output: File?): Boolean {
        input ?: return false
        output ?: return false

        return tryCatch {

            input.copyTo(output, true)

        }?.let { it.printStackTrace(); false } ?: true
    }

    // Сжатие изображения
    fun compressImage(path: String) {

        if (File(path).length() > MAX_IMAGE_FILE_SIZE) {

            val options: BitmapFactory.Options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, options)

            options.inSampleSize = calculateInSampleSize(
                options, MAX_SAVE_IMAGE_SIDE, MAX_SAVE_IMAGE_SIDE
            )
            options.inJustDecodeBounds = false
            options.inPreferredConfig = Bitmap.Config.RGB_565
            val bitmap: Bitmap = BitmapFactory.decodeFile(path, options)
            try {
                FileOutputStream(File(path)).use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, SAVE_IMAGE_QUALITY, it)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
    ): Int {
        var result = 1
        var height = options.outHeight.toDouble()
        var width = options.outWidth.toDouble()
        while (height > reqHeight || width > reqWidth) {
            result *= 2
            height /= 2.0
            width /= 2.0
        }
        return result
    }
}