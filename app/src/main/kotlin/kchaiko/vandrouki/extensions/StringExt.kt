package kchaiko.vandrouki.extensions

import android.text.Html

/**
 * @author Kanstantsin Chaiko on 30 Jun 2021
 */

val String.htmlText: String
    get() = Html.fromHtml(this@htmlText, Html.FROM_HTML_MODE_LEGACY).toString()