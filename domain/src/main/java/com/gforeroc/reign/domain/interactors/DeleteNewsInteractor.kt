package com.gforeroc.reign.domain.interactors

import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.preferences.IDeletedNewsRepository

class DeleteNewsInteractor(private val newsRepository: IDeletedNewsRepository) :
    BaseInteractor<Unit, Long> {
    override suspend fun invoke(params: Long) {
        newsRepository.deleteItem(params)
    }
}