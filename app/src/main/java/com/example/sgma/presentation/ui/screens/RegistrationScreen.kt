package com.example.sgma.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R

@Composable
fun RegistrationScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Назад",
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = "Регистрация",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        OutlinedTextField(
            value = nickname,
            onValueChange = { if (it.length <= 16) nickname = it },
            label = { Text(text = "Никнейм", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            supportingText = { Text(text = "${nickname.length} / 16") }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { if (it.length <= 50) email = it },
            label = { Text(text = "Email", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            supportingText = { Text(text = "${email.length} / 50") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { if (it.length <= 32) password = it },
            label = { Text(text = "Пароль", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisibility) R.drawable.icon_visibilityoff else R.drawable.icon_visibility),
                        contentDescription = if (passwordVisibility) "Скрыть пароль" else "Показать пароль"
                    )
                }
            },
            supportingText = { Text(text = "${password.length} / 32") }
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { if (it.length <= 32) confirmPassword = it },
            label = { Text(text = "Повторить пароль", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }) {
                    Icon(
                        painter = painterResource(id = if (confirmPasswordVisibility) R.drawable.icon_visibilityoff else R.drawable.icon_visibility),
                        contentDescription = if (confirmPasswordVisibility) "Скрыть пароль" else "Показать пароль"
                    )
                }
            },
            supportingText = { Text(text = "${confirmPassword.length} / 32") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* Обработка нажатия на "зарегистрироваться" */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
        ) {
            Text(
                text = "Зарегистрироваться",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

