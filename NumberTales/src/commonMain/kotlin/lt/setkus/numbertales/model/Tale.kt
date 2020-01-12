package lt.setkus.numbertales.model

import kotlinx.serialization.Serializable

@Serializable
data class Tale(val text: String, val number: Int)