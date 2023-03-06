package com.kriaactividade.xitiqueapp.entity

data class UserEntity (
    val fullname:String,
    val birthday: Long,
    val balance:Int
        )

sealed class UserForAdapter {
    data class Header(val description: String) : UserForAdapter()
    data class Item(val id: Int, val fullname: String, val birthday: Long, val balance: Int) :
        UserForAdapter()
}

data class BalanceTotal(
    val currentBalance: String = "",
    val nextBalance: String = "",
    val balanceForYear: String = "",
    val percentForTotal:Int = 0,
    val percentCurrent:Int = 0,
    val percent:Int = 0
)