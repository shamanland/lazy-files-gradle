package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxFactory
import com.shamanland.lazyfiles.internal.DropBoxUtils
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.mockito.Matchers.eq
import static org.mockito.Matchers.notNull
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner)
class LazyFilesUploadTaskTest {
    Project project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: "lazy-files"
    }

    @Test
    void taskPassAllItemsToUtils() {
        DropBoxUtils mockUtils = mock(DropBoxUtils)
        when(mockUtils.upload(notNull(String), notNull(LazyFilesItem))).thenReturn(true)

        DropBoxFactory mockFactory = mock(DropBoxFactory)
        when(mockFactory.createUtils()).thenReturn(mockUtils)

        int count = 10

        Iterator<LazyFilesItem> mockIterator = mock(Iterator)
        def whenHasNext = when(mockIterator.hasNext())
        for (int i = 0; i < count; ++i) {
            whenHasNext = whenHasNext.thenReturn(true)
        }
        whenHasNext.thenReturn(false)

        def whenNext = when(mockIterator.next())
        for (int i = 0; i < count; ++i) {
            whenNext = whenNext.thenReturn(mock(LazyFilesItem))
        }
        whenNext.thenThrow(NoSuchElementException)

        List<LazyFilesItem> mockItems = mock(List)
        when(mockItems.iterator()).thenReturn(mockIterator)

        def mockAccessToken = String.valueOf(Math.random());

        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension
        lazyFiles.dropboxAccessToken = mockAccessToken
        lazyFiles.dropboxFactory = mockFactory
        lazyFiles.uploadItems = mockItems

        def task = project.tasks.findByName("uploadLazyFiles") as LazyFilesUploadTask
        task.execute()

        verify(mockFactory, times(1)).createUtils()
        verify(mockUtils, times(count)).upload(eq(mockAccessToken), notNull(LazyFilesItem))
    }
}
