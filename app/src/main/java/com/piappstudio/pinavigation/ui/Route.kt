package com.piappstudio.pinavigation.ui

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object ProductList: NavKey
@kotlinx.serialization.Serializable
data class ProductDetail(val id: String): NavKey