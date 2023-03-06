package com.kriaactividade.xitiqueapp.ui.home

import java.util.*

fun userTimeStamp(month: Int, date: Int): Long {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(2023, month, date)

    return calendar.time.time
}

data class User(
    val fullname: String, val birthday: Long, val balance: Int
)

val users = listOf(
    User(fullname = "Mutaquilha", balance = 2200, birthday = userTimeStamp(1, 12)),
    User(fullname = "Rogerio", balance = 300, birthday = userTimeStamp(1, 20)),
    User(fullname = "Edmilson", balance = 1400, birthday = userTimeStamp(2, 1)),
    User(fullname = "Alirio", balance = 300, birthday = userTimeStamp(2, 19)),
    User(fullname = "Soares", balance = 300, birthday = userTimeStamp(3, 2)),
    User(fullname = "Valter", balance = 300, birthday = userTimeStamp(3, 13)),
    User(fullname = "Cassamo", balance = 300, birthday = userTimeStamp(3, 22)),
    User(fullname = "Gilberto", balance = 300, birthday = userTimeStamp(4, 21)),
    User(fullname = "Edson", balance = 300, birthday = userTimeStamp(4, 30)),
    User(fullname = "Olisio", balance = 300, birthday = userTimeStamp(5, 7)),
    User(fullname = "Gildo", balance = 400, birthday = userTimeStamp(6, 14)),
    User(fullname = "Tony", balance = 700, birthday = userTimeStamp(6, 16)),
    User(fullname = "Andrew", balance = 0, birthday = userTimeStamp(6, 18)),
    User(fullname = "Silva Condoeira", balance = 0, birthday = userTimeStamp(7, 20)),
    User(fullname = "Anaiva", balance = 0, birthday = userTimeStamp(8, 14)),
    User(fullname = "Vene", balance = 300, birthday = userTimeStamp(8, 14)),
    User(fullname = "Gomes", balance = 400, birthday = userTimeStamp(9, 11)),
    User(fullname = "Isidro Duarte", balance = 300, birthday = userTimeStamp(9, 20)),
    User(fullname = "Augusto", balance = 500, birthday = userTimeStamp(9, 29)),
    User(fullname = "Kamaik", balance = 300, birthday = userTimeStamp(10, 4)),
    User(fullname = "Alexandre Dunduro", balance = 7600, birthday = userTimeStamp(10, 14)),
    User(fullname = "Ernesto", balance = 300, birthday = userTimeStamp(10, 20)),
    User(fullname = "Loisse", balance = 300, birthday = userTimeStamp(11, 6)),
    User(fullname = "Nelson", balance = 900, birthday = userTimeStamp(11, 10)),
    User(fullname = "Deofrank lanappe", balance = 300, birthday = userTimeStamp(11, 15)),
    User(fullname = "Arsénio Tibik", balance = 0, birthday = userTimeStamp(11, 16)),
    User(fullname = "Maravi", balance = 300, birthday = userTimeStamp(11, 31))
)