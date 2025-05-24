package com.piappstudio.pinavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Dimension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProvider
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.piappstudio.pinavigation.model.Product
import com.piappstudio.pinavigation.ui.ProductDetail
import com.piappstudio.pinavigation.ui.ProductList
import com.piappstudio.pinavigation.ui.product.ProductDetailScreen
import com.piappstudio.pinavigation.ui.product.ProductListScreen
import com.piappstudio.pinavigation.ui.theme.PiNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiNavigationTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        LoadMainScreen()
                    }
                }
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun LoadMainScreen() {
    // Navigation 3
    // NaviDisplay = backk
    // Create a back stack that persists across configuration changes and process death.
    val backStack = rememberNavBackStack(ProductList)

    //val backStack = remember { mutableStateListOf<Any>(ProductList) }

    // 2. Create a list detail scene strategy.
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()

    val arrOfList = remember { arrayOf(Product("1", name = "Orange"),Product("2", name = "Apple"), Product("3", name = "Banana"), Product("4", name = "Pear") ) }

    NavDisplay(backStack = backStack,
        entryProvider = entryProvider {
            entry<ProductList> (
                metadata = ListDetailSceneStrategy.listPane()) {
                ProductListScreen(arrOfList, backStack)

            }
            entry<ProductDetail>  (metadata = ListDetailSceneStrategy.detailPane()){ key->
                ProductDetailScreen(arrOfList, key)
            }
        },
        entryDecorators = listOf(
            // Add the default decorators for managing scenes and saving state
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            // Then add the view model store decorator
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = listDetailStrategy)
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PiNavigationTheme {
        Greeting("Android")
    }
}