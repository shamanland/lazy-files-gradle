package com.shamanland.lazyfiles

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*
import static org.mockito.Mockito.mock

class LazyFilesExtensionTest {
    Project project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: "lazy-files"
    }

    @Test
    void emptyItems() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        assertFalse(lazyFiles.fetchItems().iterator().hasNext())
        assertFalse(lazyFiles.uploadItems().iterator().hasNext())
    }

    @Test
    void singleFetchItems() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        File local = mock(File)
        File dropbox = mock(File)

        lazyFiles.fetchFile(dropbox, local)

        int count = 0
        lazyFiles.fetchItems().each {
            assertSame(local, it.local)
            assertSame(dropbox, it.dropbox)
            count += 1
        }

        assertEquals(1, count)
    }

    @Test
    void singleUploadItems() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        File local = mock(File)
        File dropbox = mock(File)

        lazyFiles.uploadFile(local, dropbox)

        int count = 0
        lazyFiles.uploadItems().each {
            assertSame(local, it.local)
            assertSame(dropbox, it.dropbox)
            count += 1
        }

        assertEquals(1, count)
    }

    @Test
    void multipleFetchItems() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        int count = 10

        File[] local = new File[10]
        File[] dropbox = new File[10]

        int i0 = 0
        int i1 = 1
        while (i1 < count) {
            local[i0] = local[i1] = mock(File)
            dropbox[i0] = dropbox[i1] = mock(File)

            lazyFiles.fetchFile(dropbox[i0], local[i0])
            lazyFiles.fetchFile(dropbox[i1], local[i1])

            i0 += 2
            i1 += 2
        }

        int i = 0
        lazyFiles.fetchItems().each {
            assertSame(local[i], it.local)
            assertSame(dropbox[i], it.dropbox)

            i += 1
        }

        assertEquals(count, i)
    }

    @Test
    void multipleUploadItems() {
        LazyFilesExtension lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        int count = 10

        File[] local = new File[10]
        File[] dropbox = new File[10]

        int i0 = 0
        int i1 = 1
        while (i1 < count) {
            local[i0] = local[i1] = mock(File)
            dropbox[i0] = dropbox[i1] = mock(File)

            lazyFiles.uploadFile(local[i0], dropbox[i0])
            lazyFiles.uploadFile(local[i1], dropbox[i1])

            i0 += 2
            i1 += 2
        }

        int i = 0
        lazyFiles.uploadItems().each {
            assertSame(local[i], it.local)
            assertSame(dropbox[i], it.dropbox)

            i += 1
        }

        assertEquals(count, i)
    }
}
