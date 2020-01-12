package lt.setkus.numbertales

import lt.setkus.numbertales.model.Tale

interface NumberTalesRepository {
    suspend fun fetchNumberTale(): Tale
}