package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.presentation.screens.BookmarksScreen
import com.example.booksapp.presentation.screens.SearchScreen
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentMedium
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary
import com.ricramiel.mobilebooks.BookmarksDestination
import com.ricramiel.mobilebooks.LibraryDestination
import com.ricramiel.mobilebooks.LoginDestination
import com.ricramiel.mobilebooks.SearchDestination


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val selectedItem = remember { mutableIntStateOf(0) }
            val fabActive = remember { mutableStateOf(false) }
            BooksTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), floatingActionButton = {
                        if (fabActive.value) {
                            CustomFloatingBar(selectedItem,
                                goToLibrary = { navController.navigate(LibraryDestination) },
                                goToStart = { navController.navigate(LoginDestination) },
                                goToBookmarks = { navController.navigate(BookmarksDestination) },
                                goToSearch = { navController.navigate(SearchDestination) },
                                goToChapter = { navController.navigate(ChapterScreenDestination) })
                        } else {
                            null
                        }
                    }, floatingActionButtonPosition = FabPosition.Center
                ) {
                    it

                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination::class,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable<ChapterScreenDestination>{
                            fabActive.value = false
                            selectedItem.intValue = 0
                            ChapterScreen(openChapterScreen = {navController.navigateUp()})
                        }
                        composable<LoginDestination> {
                            fabActive.value = false
                            selectedItem.intValue = 0
                            LoginScreen(openLibraryScreen = {
                                navController.navigate(LibraryDestination);
                            })
                        }
                        composable<LibraryDestination> {
                            fabActive.value = true
                            selectedItem.intValue = 0
                            LibraryScreen(openBookDetailsScreen = {
                                navController.navigate(BookDetailsDestination)
                            })
                        }
                        composable<BookmarksDestination> {
                            selectedItem.intValue = 2
                            fabActive.value = true
                            BookmarksScreen()
                        }
                        composable<SearchDestination> {
                            selectedItem.intValue = 1
                            fabActive.value = true
                            SearchScreen(fabActive = fabActive, openDetailsScreen = {navController.navigate(BookDetailsDestination)})
                        }
                        composable<BookDetailsDestination> {
                            selectedItem.intValue = 0
                            fabActive.value = false
                            BookDetails(openBookDetailsScreen = {
                                navController.navigateUp()
                            }, openChapterScreen = {navController.navigate(ChapterScreenDestination)})
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomFloatingBar(
    selectedItem: MutableIntState,
    goToLibrary: () -> Unit,
    goToBookmarks: () -> Unit,
    goToSearch: () -> Unit,
    goToStart: () -> Unit,
    goToChapter: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(color = AccentDark, shape = RoundedCornerShape(100.dp))
                .align(Alignment.Center)
                .padding(horizontal = 8.dp, vertical = 7.dp)
                .fillMaxWidth(0.9f), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {goToLibrary.invoke() }) {

                Icon(
                    painterResource(R.drawable.library),
                    contentDescription = null,
                    tint = if (selectedItem.intValue == 0) {
                        White
                    } else {
                        AccentMedium
                    }
                )
            }
            IconButton(onClick = {goToSearch.invoke() }) {
                Icon(
                    painterResource(R.drawable.search),
                    contentDescription = null,
                    tint = if (selectedItem.intValue == 1) {
                        White
                    } else {
                        AccentMedium
                    }
                )
            }
            Spacer(modifier = Modifier.width(80.dp))
            IconButton(onClick = {goToBookmarks.invoke() }) {
                Icon(
                    painterResource(R.drawable.bookmarks),
                    contentDescription = null,
                    tint = if (selectedItem.intValue == 2) {
                        White
                    } else {
                        AccentMedium
                    }
                )
            }
            IconButton(onClick = {goToStart.invoke() }) {
                Icon(
                    painterResource(R.drawable.log_out),
                    contentDescription = null,
                    tint = if (selectedItem.intValue == 3) {
                        White
                    } else {
                        AccentMedium
                    }

                )
            }
        }
        FloatingActionButton(
            onClick = { goToChapter.invoke() },
            containerColor = Secondary,
            contentColor = White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
        ) {
            Icon(painterResource(R.drawable.play), contentDescription = null)
        }
    }
}


