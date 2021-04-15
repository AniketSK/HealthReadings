package com.aniketkadam.healthreadings.readinglist

open class BaseDisplayClass {
    init {
        check(Thread.currentThread().name != "main") { "Display classes shouldn't do their conversion on the main thread. Create them off the main thread." }
    }
}