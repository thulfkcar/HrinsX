package com.hrins.hrinsx.network.api

@FunctionalInterface
interface OnCompleteListener<T> {
    fun onComplete(t: T)
    fun onError(error: String?)

}

