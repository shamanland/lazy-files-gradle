package com.shamanland.lazyfiles.test

import com.shamanland.lazyfiles.LazyFilesExtension
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
        LazyFilesExtension lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        assertFalse(lazyFiles.fetch().iterator().hasNext())
        assertFalse(lazyFiles.upload().iterator().hasNext())
    }

    @Test
    void singleFetchItems() {
        LazyFilesExtension lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        File local = mock(File)
        File dropbox = mock(File)

        lazyFiles.fetch(dropbox, local)

        int count = 0
        lazyFiles.fetch().each {
            assertSame(local, it.local)
            assertSame(dropbox, it.dropbox)
            count += 1
        }

        assertEquals(1, count)
    }

    @Test
    void singleUploadItems() {
        LazyFilesExtension lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        File local = mock(File)
        File dropbox = mock(File)

        lazyFiles.upload(local, dropbox)

        int count = 0
        lazyFiles.upload().each {
            assertSame(local, it.local)
            assertSame(dropbox, it.dropbox)
            count += 1
        }

        assertEquals(1, count)
    }

    @Test
    void multipleFetchItems() {
        LazyFilesExtension lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension

        int count = 10

        File[] local = new File[10]
        File[] dropbox = new File[10]

        int i0 = 0
        int i1 = 1
        while (i1 < count) {
            local[i0] = local[i1] = mock(File)
            dropbox[i0] = dropbox[i1] = mock(File)

            lazyFiles.fetch(dropbox[i0], local[i0])
            lazyFiles.fetch(dropbox[i1], local[i1])

            i0 += 2
            i1 += 2
        }

        int i = 0
        lazyFiles.fetch().each {
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

            lazyFiles.upload(local[i0], dropbox[i0])
            lazyFiles.upload(local[i1], dropbox[i1])

            i0 += 2
            i1 += 2
        }

        int i = 0
        lazyFiles.upload().each {
            assertSame(local[i], it.local)
            assertSame(dropbox[i], it.dropbox)

            i += 1
        }

        assertEquals(count, i)
    }
}
