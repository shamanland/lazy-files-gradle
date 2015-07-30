package com.shamanland.lazyfiles.internal

import com.shamanland.lazyfiles.LazyFilesItem

interface DropBoxUtils {
    boolean fetch(String accessToken, LazyFilesItem item)

    boolean upload(String accessToken, LazyFilesItem item)
}
