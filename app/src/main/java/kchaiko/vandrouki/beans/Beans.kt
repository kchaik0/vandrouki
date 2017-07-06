package kchaiko.vandrouki.beans

import java.util.*

/**
 * File for data class
 *
 * Created by kchaiko on 05.07.2017.
 */

data class DiscountBean(var title: String?, var date: Date?) {
    constructor() : this(null, null)
}

data class DiscountBeanList(var discountBeanList: MutableList<DiscountBean>)
