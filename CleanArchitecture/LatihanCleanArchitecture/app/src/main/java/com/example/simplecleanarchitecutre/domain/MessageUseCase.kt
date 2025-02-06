package com.example.simplecleanarchitecutre.domain


// 3. Buat UseCase sebagai abstract, disini diisi dengan semua fungsi/usecase/bisnis model nantinya
interface MessageUseCase {
    fun getMessage(name : String):MessageEntity
}