package com.example.thaidai.backend.domain.util

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(entity: T): DomainModel
    fun mapFromDomainModel(appModel: DomainModel): T
}