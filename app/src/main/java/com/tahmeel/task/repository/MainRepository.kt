package com.tahmeel.task.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.tahmeel.task.model.Filter
import com.tahmeel.task.network.TahmeelClient
import com.tahmeel.task.persistence.TahmeelDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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
    fun fetchOrderList(
        filter: Filter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        if (filter.filterByCent != null) {
            emit(tahmeelDao.getFilteredByCents(filter.filterByCent!!))
        }else{
            var orders = tahmeelDao.getOrdersList(filter.page)
            if (orders.isEmpty()) {
                val response = tahmeelClient.fetchPendingOrdersList(page = filter.page)
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        orders = response.pendingOrders
                        orders.forEach { order -> order.page = filter.page }
                        tahmeelDao.insertOrdersList(orders)
                        emit(tahmeelDao.getAllOrdersList(filter.page))
                    }
                }
                    .onError {
                        onError("error")
                    }
                    .onException {
                        onError(message)
                    }
            } else {
                emit(tahmeelDao.getAllOrdersList(filter.page))
            }
        }

    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
