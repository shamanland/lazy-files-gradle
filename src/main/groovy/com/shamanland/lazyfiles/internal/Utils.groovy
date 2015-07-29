package com.shamanland.lazyfiles.internal

import com.shamanland.lazyfiles.LazyFilesPlugin

import java.util.concurrent.TimeUnit

class Utils {
    static def time(String name, Closure task) {
        LazyFilesPlugin.logger.lifecycle name
        long before = System.nanoTime()
        task.run()
        long after = System.nanoTime()
        long took = TimeUnit.NANOSECONDS.toMillis(after - before)
        LazyFilesPlugin.logger.info "$name took $took ms."
    }

    static <T> T notNull(T obj) {
        if (obj == null) {
            throw new NullPointerException()
        }

        return obj
    }
}
