package com.example.simplecleanarchitecutre.data

import com.example.simplecleanarchitecutre.domain.IMessageRepository

class MessageRepository(private val messageDataSource: IMessageDataSource) : IMessageRepository{
    override fun getWelcomeMessage(name: String) = messageDataSource.getMessageFromSource(name)

}