package com.cheng.trademedemo.service

import android.support.test.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test

class TradeMeApiServiceTest {

    private val syncObject = Object()
    private val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun getCategoriesTest() {
        val result = "No result"
        TradeMeApiService.requestCategories(
                appContext,
                {
                    response -> assertEquals("Root", response.Name)
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {
                    assertEquals("Success", result)
                })

        synchronized (syncObject){
            syncObject.wait()
        }
    }

    @Test
    fun searchCategoryTest() {
        val result = "No result"
        TradeMeApiService.searchCategory(
                appContext,
                "3720",
                {
                    response -> assertNotNull(response.List)
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {
                    assertEquals("Success", result)
                })

        synchronized (syncObject){
            syncObject.wait()
        }
    }

    @Test
    fun searchWithKeywordTest() {
        val result = "No result"
        TradeMeApiService.searchKeyword(
                appContext,
                "iPhone",
                {
                    response -> assertNotNull(response.List)
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                },
                {
                    assertEquals("Success", result)
                })

        synchronized (syncObject){
            syncObject.wait()
        }
    }

}