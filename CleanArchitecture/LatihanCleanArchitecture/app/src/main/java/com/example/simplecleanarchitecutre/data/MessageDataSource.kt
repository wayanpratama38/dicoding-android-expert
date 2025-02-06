package com.example.simplecleanarchitecutre.data

import com.example.simplecleanarchitecutre.domain.MessageEntity

// 6. Mengoverride fungsi dari interface
class MessageDataSource : IMessageDataSource {
    override fun getMessageFromSource(name: String) = MessageEntity("Hello $name! Selamat datang di Clean Architecture")
}