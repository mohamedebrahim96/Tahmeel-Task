package com.tahmeel.task.ui.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.tahmeel.task.model.Filter
import com.tahmeel.task.model.Order
import com.tahmeel.task.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pendingOrdersFetchingIndex: MutableStateFlow<Filter> =
        MutableStateFlow(Filter(0, null))
    private val pendingOrdersListFlow = pendingOrdersFetchingIndex.flatMapLatest { page ->
        mainRepository.fetchOrderList(
            filter = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = {
                Timber.e(it)
                toastMessage = it
            }
        )
    }

    @get:Bindable
    val pendingOrdersList: List<Order> by pendingOrdersListFlow.asBindingProperty(
        viewModelScope,
        emptyList()
    )

    init {
        Timber.d("init MainViewModel")
    }


    fun getSearchedProducts(cents: Int?) {
        pendingOrdersFetchingIndex.value = Filter(0, cents)
    }


    @MainThread
    fun fetchNextPendingOrderList() {
        if (!isLoading && pendingOrdersFetchingIndex.value.filterByCent == null) {
            val pageNumber = pendingOrdersFetchingIndex.value.page + 1
            pendingOrdersFetchingIndex.value = Filter(pageNumber, null)
        }
    }
}