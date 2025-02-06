package com.example.simplecleanarchitecutre.di

import com.example.simplecleanarchitecutre.data.IMessageDataSource
import com.example.simplecleanarchitecutre.data.MessageDataSource
import com.example.simplecleanarchitecutre.data.MessageRepository
import com.example.simplecleanarchitecutre.domain.IMessageRepository
import com.example.simplecleanarchitecutre.domain.MessageInteractor
import com.example.simplecleanarchitecutre.domain.MessageUseCase

object Injection {
    fun provideUseCase() :MessageUseCase{
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository() : IMessageRepository{
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource() : IMessageDataSource{
        return MessageDataSource()
    }
}