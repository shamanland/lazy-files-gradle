package com.shamanland.lazyfiles.test

import com.shamanland.lazyfiles.LazyFilesExtension
import com.shamanland.lazyfiles.LazyFilesFetchTask
import com.shamanland.lazyfiles.LazyFilesUploadTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertTrue

class LazyFilesPluginTest {
    Project project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: "lazy-files"
    }

    @Test
    void pluginAddsExtension() {
        assertTrue(project.extensions.getByName("lazyFiles") instanceof LazyFilesExtension)
    }

    @Test
    void pluginAddsFetchTask() {
        assertTrue(project.tasks.getByName("fetchLazyFiles") instanceof LazyFilesFetchTask)
    }

    @Test
    void pluginAddsUploadTask() {
        assertTrue(project.tasks.getByName("uploadLazyFiles") instanceof LazyFilesUploadTask)
    }
}
