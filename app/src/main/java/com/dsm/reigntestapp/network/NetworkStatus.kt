package com.dsm.reigntestapp.network

sealed class NetworkStatus {
    object Success : NetworkStatus()
    object Error : NetworkStatus()
    object Loading : NetworkStatus()
}