package com.tahmeel.task.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.Transformations.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

class MainRepository @Inject constructor(
    private val tahmeelClient: TahmeelClient,
    private val tahmeelDao: TahmeelDao
) : Repository {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var orders = tahmeelDao.getPokemonList(page)
        if (orders.isEmpty()) {

            val response = tahmeelClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    orders = response.results
                    orders.forEach { order -> order.page = page }
                    tahmeelDao.insertPokemonList(orders)
                    emit(tahmeelDao.getAllPokemonList(page))
                }
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        } else {
            emit(tahmeelDao.getAllPokemonList(page))
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}