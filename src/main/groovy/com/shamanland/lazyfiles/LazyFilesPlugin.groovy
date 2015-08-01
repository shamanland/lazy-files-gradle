package com.shamanland.lazyfiles

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class LazyFilesPlugin implements Plugin<Project> {
    static final Logger logger = Logging.getLogger LazyFilesPlugin

    @Override
    void apply(Project project) {
        project.extensions.create "lazyFiles", LazyFilesExtension
        project.task "fetchLazyFiles", type: LazyFilesFetchTask
        project.task "uploadLazyFiles", type: LazyFilesUploadTask
        project.task "loginLazyFiles", type: LazyFilesLoginTask
    }
}
