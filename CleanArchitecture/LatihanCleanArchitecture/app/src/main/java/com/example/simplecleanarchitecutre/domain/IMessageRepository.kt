package com.example.simplecleanarchitecutre.domain


// 2. Buat Interface Repository
interface IMessageRepository {
    fun getWelcomeMessage(name : String) : MessageEntity
}