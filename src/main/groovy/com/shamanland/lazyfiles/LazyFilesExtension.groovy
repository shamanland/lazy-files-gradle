package com.shamanland.lazyfiles

import groovy.transform.PackageScope

import static java.util.Collections.unmodifiableCollection

class LazyFilesExtension {
    String dropboxAccessToken

    @PackageScope
    final List<LazyFilesItem> fetchItems = new ArrayList<>()

    @PackageScope
    final List<LazyFilesItem> uploadItems = new ArrayList<>()

    Iterable<LazyFilesItem> fetch() {
        unmodifiableCollection fetchItems
    }

    Iterable<LazyFilesItem> upload() {
        unmodifiableCollection uploadItems
    }

    def fetch(File dropboxFile, File localFile) {
        fetchItems.add new LazyFilesItem(localFile, dropboxFile)
    }

    def upload(File localFile, File dropboxFile) {
        uploadItems.add new LazyFilesItem(localFile, dropboxFile)
    }
}
