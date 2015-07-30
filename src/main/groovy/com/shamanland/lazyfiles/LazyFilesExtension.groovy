package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxFactory
import com.shamanland.lazyfiles.internal.DropBoxFactoryImpl

import static java.util.Collections.unmodifiableCollection

class LazyFilesExtension {
    String dropboxAccessToken

    List<LazyFilesItem> _fetchItems = new ArrayList<>()

    List<LazyFilesItem> _uploadItems = new ArrayList<>()

    DropBoxFactory _dropboxFactory = new DropBoxFactoryImpl()

    Iterable<LazyFilesItem> fetch() {
        unmodifiableCollection _fetchItems
    }

    Iterable<LazyFilesItem> upload() {
        unmodifiableCollection _uploadItems
    }

    def fetch(File dropboxFile, File localFile) {
        _fetchItems.add new LazyFilesItem(localFile, dropboxFile)
    }

    def upload(File localFile, File dropboxFile) {
        _uploadItems.add new LazyFilesItem(localFile, dropboxFile)
    }
}
