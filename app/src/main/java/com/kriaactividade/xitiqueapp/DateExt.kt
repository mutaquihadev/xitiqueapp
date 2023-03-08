package com.kriaactividade.xitiqueapp

import java.text.SimpleDateFormat
import java.util.*

fun Date.convertLongDateForString():String{
    val data = Date(this.time)
    val simpleDateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
    return simpleDateFormat.format(data)
}

fun Date.groupMonths():String{
    val data = Date(this.time)
    val simpleData = SimpleDateFormat("MMMM", Locale.getDefault())
    return simpleData.format(data)
}

fun Date.isSameMonth(valueDate: Date): Boolean {
    val dateInserted = Date(this.time)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate.time)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted == monthCurrent
}

fun Date.isNextMonth(valueDate: Date): Boolean {
    val dateInserted = Date(this.time)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate.time)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted == monthCurrent + 1
}

fun Date.isBalanceInMonth(valueDate: Date): Boolean {
    val dateInserted = Date(this.time)
    val calendar = Calendar.getInstance()
    calendar.time = dateInserted
    val monthInserted = calendar.get(Calendar.MONTH)

    val dateCurrent = Date(valueDate.time)
    calendar.time = dateCurrent
    val monthCurrent = calendar.get(Calendar.MONTH)

    return monthInserted == monthCurrent || monthCurrent > monthInserted
}