package com.cheng.trademedemo.service

import android.support.test.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test

class TradeMeApiServiceTest {

    private val syncObject = Object()
    private val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun getCategoriesTest() {
        var result = "No result"
        TradeMeApiService.requestCategories(
                appContext,
                {
                    result = "Success"
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {result = "Fail"})

        synchronized (syncObject){
            syncObject.wait()
        }
        assertEquals("Success", result)
    }

    @Test
    fun searchCategoryTest() {
        var result = "No result"
        TradeMeApiService.searchCategory(
                appContext,
                "3720",
                {
                    result = "Success"
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {result = "Fail"})

        synchronized (syncObject){
            syncObject.wait()
        }
        assertEquals("Success", result)
    }

    @Test
    fun searchWithKeywordTest() {
        var result = "No result"
        TradeMeApiService.searchKeyword(
                appContext,
                "iPhone",
                {
                    result = "Success"
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {result = "Fail"})

        synchronized (syncObject){
            syncObject.wait()
        }
        assertEquals("Success", result)
    }

}