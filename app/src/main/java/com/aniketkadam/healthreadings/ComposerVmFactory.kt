package com.aniketkadam.healthreadings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.healthreadings.composer.ComposerVm
import com.aniketkadam.healthreadings.dao.ReadingsDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ComposerVmFactory @AssistedInject constructor(
    @Assisted("existingReading") private val existingReading: String?,
    private val readingsDao: ReadingsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComposerVm::class.java)) {
            return ComposerVm(readingsDao, existingReading) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.canonicalName}")
    }
}