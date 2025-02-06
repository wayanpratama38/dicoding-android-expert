package com.example.simplecleanarchitecutre.data

import com.example.simplecleanarchitecutre.domain.MessageEntity

// 5. Buat interface untuk sumber data yang isinya fungsi dari entitas
interface IMessageDataSource {
    fun getMessageFromSource(name : String) : MessageEntity
}