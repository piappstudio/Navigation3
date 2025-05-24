package com.piappstudio.pinavigation.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import com.piappstudio.pinavigation.model.Product
import com.piappstudio.pinavigation.ui.ProductDetail

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductListScreen(
    arrOfList: Array<Product>,
    backStack: NavBackStack
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Product List")
        })
    }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(arrOfList) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            backStack.add(ProductDetail(item.id ?: "Default"))
                        }) {
                    Text(item.name ?: "Default")
                    IconButton(onClick = {
                        backStack.add(ProductDetail(item.id ?: "Default"))
                    }) {
                        Icon(Icons.Default.ChevronRight, "Detail")
                    }

                }
                HorizontalDivider()
            }
        }
    }
}