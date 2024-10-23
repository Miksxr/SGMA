package com.example.sgma.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.MediaCard
import com.example.sgma.presentation.ui.SGMAAppBar
import com.example.sgma.presentation.ui.getFakeMediaList

@Composable
fun MainScreen(navController: NavController) {
    val mediaList = getFakeMediaList()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Все") }
    var selectedSortOption by remember { mutableStateOf("Самые популярные") }
    var showFilterDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        SGMAAppBar(
            onSearchQueryChange = { newQuery -> searchQuery = newQuery },
            searchQuery = searchQuery,
            searchPlaceholder = "Поиск медиа...",
            navController = navController
        )
    }, bottomBar = {
        Navigation(navController = navController)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val headerText = "${if (selectedCategory == "Все") "Все" else selectedCategory} - $selectedSortOption"

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = headerText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = { showFilterDialog = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_filter),
                        contentDescription = "Фильтр",
                        Modifier.size(30.dp)
                    )
                }
            }

            if (showFilterDialog) {
                FilterDialog(selectedCategory = selectedCategory,
                    selectedSortOption = selectedSortOption,
                    onDismissRequest = { showFilterDialog = false },
                    onApplyFilter = { category, sortOption ->
                        selectedCategory = category
                        selectedSortOption = sortOption
                        showFilterDialog = false
                    })
            }

            val filteredList = mediaList.filter {
                (selectedCategory == "Все" || it.type.name == selectedCategory) && it.name.contains(
                    searchQuery, ignoreCase = true
                )
            }.sortedWith(when (selectedSortOption) {
                "Самые популярные" -> compareByDescending { it.year }
                "Самые высокооценённые" -> compareByDescending { it.sgmaRating }
                "Самые низкооценённые" -> compareBy { it.sgmaRating }
                "По типу" -> compareBy { it.type }
                else -> compareBy { it.name }
            })

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredList) { media ->
                    MediaCard(mediaDBModel = media, onClick = {
                        when (media.type) {
                            ContentTypes.Game -> navController.navigate("game_detail/${media.id}")
                            else -> navController.navigate("multimedia_detail/${media.id}")
                        }
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    selectedCategory: String,
    selectedSortOption: String,
    onDismissRequest: () -> Unit,
    onApplyFilter: (String, String) -> Unit
) {
    val categories = listOf("Все", "Game", "Film", "Anime", "Serial")
    val sortOptions = listOf("Самые популярные", "Самые высокооценённые", "Самые низкооценённые")

    var category by remember { mutableStateOf(selectedCategory) }
    var sortOption by remember { mutableStateOf(selectedSortOption) }

    var categoryExpanded by remember { mutableStateOf(false) }
    var sortOptionExpanded by remember { mutableStateOf(false) }

    AlertDialog(onDismissRequest = onDismissRequest, title = { Text(text = "Фильтры") }, text = {
        Column {
            Text(text = "Медиа:")
            ExposedDropdownMenuBox(expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = !categoryExpanded }) {
                TextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Выберите категорию") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = categoryExpanded
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
                )
                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }) {
                    categories.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = {
                            category = option
                            categoryExpanded = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Сортировка:")

            ExposedDropdownMenuBox(expanded = sortOptionExpanded,
                onExpandedChange = { sortOptionExpanded = !sortOptionExpanded }) {
                TextField(
                    value = sortOption,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Выберите сортировку") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = sortOptionExpanded
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
                )
                ExposedDropdownMenu(
                    expanded = sortOptionExpanded,
                    onDismissRequest = { sortOptionExpanded = false }) {
                    sortOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = {
                            sortOption = option
                            sortOptionExpanded = false
                        })
                    }
                }
            }
        }
    }, confirmButton = {
        TextButton(onClick = { onApplyFilter(category, sortOption) }) {
            Text(text = "Применить")
        }
    }, dismissButton = {
        TextButton(onClick = onDismissRequest) {
            Text(text = "Отмена")
        }
    })
}


