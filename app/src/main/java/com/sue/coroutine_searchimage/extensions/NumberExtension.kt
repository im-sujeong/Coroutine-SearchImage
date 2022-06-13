package com.sue.coroutine_searchimage.extensions

import android.content.res.Resources

internal fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}