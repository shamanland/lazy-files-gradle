package com.shamanland.lazyfiles.internal

import com.shamanland.lazyfiles.LazyFilesPlugin

import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class Utils {
    static final File propsDir = new File(new File(System.getProperty("user.home")), ".lazy-files")
    static final File propsFile = new File(propsDir, "app.properties")

    static {
        propsDir.mkdirs()
        propsFile.createNewFile()
    }

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

    static String hash(String s) {
        MessageDigest.getInstance("SHA1").digest(s.bytes).encodeHex().toString()
    }

    static void saveProperty(String key, String value) {
        def props = new Properties()
        def is = new FileInputStream(propsFile)

        try {
            props.load(is)
            props.put(key, value)
        } finally {
            is.close()
        }

        def os = new FileOutputStream(propsFile)

        try {
            props.store(os, null)
        } finally {
            os.close()
        }
    }

    static String readProperty(String key) {
        def props = new Properties()

        propsFile.withInputStream { is ->
            props.load(is)
        }

        return props[key]
    }
}
