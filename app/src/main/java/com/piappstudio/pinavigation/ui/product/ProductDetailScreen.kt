package com.piappstudio.pinavigation.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piappstudio.pinavigation.model.Product
import com.piappstudio.pinavigation.ui.ProductDetail

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun ProductDetailScreen(
    arrOfList: Array<Product>,
    key: ProductDetail
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Product Detail")
            }/*, navigationIcon = {
                        IconButton(onClick = {
                            backStack.removeLastOrNull()
                        }) {
                            Icon(Icons.Default.ChevronLeft, "Detail")
                        }
                    }*/
        )
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()) {
            val product = arrOfList.find { it.id == key.id }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Id: ${product?.id}", style = MaterialTheme.typography.bodyLarge)
            Text("Name: ${product?.name}", style = MaterialTheme.typography.bodyLargeEmphasized)
        }
    }
}
