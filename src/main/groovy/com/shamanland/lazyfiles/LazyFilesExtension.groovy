package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxFactory
import com.shamanland.lazyfiles.internal.DropBoxFactoryImpl

import static java.util.Collections.unmodifiableCollection

class LazyFilesExtension {
    String dropboxAccessToken

    List<LazyFilesItem> fetchItems = new ArrayList<>()

    List<LazyFilesItem> uploadItems = new ArrayList<>()

    DropBoxFactory dropboxFactory = new DropBoxFactoryImpl()

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
