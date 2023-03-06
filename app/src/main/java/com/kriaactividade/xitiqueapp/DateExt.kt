package com.kriaactividade.xitiqueapp

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongDateForString():String{
    val data = Date(this)
    val simpleDateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
    return simpleDateFormat.format(data)
}

fun Long.groupMonths():String{
    val data = Date(this)
    val simpleData = SimpleDateFormat("MMMM", Locale.getDefault())
    return simpleData.format(data)
}

fun Long.isSameMonth(valueDate: Long): Boolean {
    val dateInserted = Date(this)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted == monthCurrent
}

fun Long.isNextMonth(valueDate: Long): Boolean {
    val dateInserted = Date(this)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted > monthCurrent
}

fun Long.isBalanceInMonth(valueDate: Long): Boolean {
    val dateInserted = Date(this)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted == monthCurrent || monthCurrent > monthInserted
}