package com.juvcarl.batch.mcs.characterlist.repository

class Resource<T> private constructor(val status: Resource.Status, val data: T?, val exception: Exception?){
    enum class Status{
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?) : Resource<T>{
            return Resource(Status.SUCCESS,data,null)
        }
        fun <T> error(exception: Exception?) : Resource<T>{
            return Resource(Status.ERROR,null,exception)
        }
        fun <T> loading(data: T?) : Resource<T>{
            return Resource(Status.LOADING,data,null)
        }
    }
}
