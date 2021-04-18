package com.aniketkadam.healthreadings

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AssistedComposerVmFactory {
    fun create(@Assisted("existingReading") existingReadingId: String?): ComposerVmFactory
}