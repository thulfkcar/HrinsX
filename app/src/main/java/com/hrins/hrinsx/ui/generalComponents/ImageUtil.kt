package com.hrins.hrinsx.ui.generalComponents

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hrins.hrinsx.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<ImageBitmap> {

    val bitmapState: MutableState<ImageBitmap> = mutableStateOf(
        ImageBitmap(200, 200)
    )


//    GlideApp.with(imageView.getContext())
//        .load(url)
//        .placeholder(R.drawable.boy_32)
//        .into(imageView)
    Glide.with(LocalContext.current)
        .asBitmap()
        .placeholder(R.drawable.ic_baseline_airplanemode_active_24)
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource.asImageBitmap()
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .placeholder(R.drawable.ic_baseline_airplanemode_active_24)
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource.asImageBitmap()
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })
    return bitmapState

}