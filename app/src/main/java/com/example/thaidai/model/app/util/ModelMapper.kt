package com.example.thaidai.model.app.util

interface ModelMapper<NetworkModel, AppModel> {
    fun mapFromNetworkModel(entity: NetworkModel): AppModel
    fun mapToNetworkModel(appModel: AppModel): NetworkModel
}