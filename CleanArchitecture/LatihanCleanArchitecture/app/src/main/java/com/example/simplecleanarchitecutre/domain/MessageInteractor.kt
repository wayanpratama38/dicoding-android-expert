package com.example.simplecleanarchitecutre.domain


// 4. Message Interactor sebagai tempat yang menggabungkan Use Case dan Entity(dari Interface)
class MessageInteractor(private val messageRepository: IMessageRepository):MessageUseCase {
    override fun getMessage(name: String): MessageEntity {
        return messageRepository.getWelcomeMessage(name)
    }
}