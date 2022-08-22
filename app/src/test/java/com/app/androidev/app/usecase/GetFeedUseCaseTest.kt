package com.app.androidev.app.usecase

import com.app.androidev.ui.home.mvvm.FeedRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFeedUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: FeedRepository

    lateinit var getFeedUseCase: GetFeedUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFeedUseCase = GetFeedUseCase(repository)
    }

    @Test
    fun whenListIsEmpty() = runBlocking {
    }
}