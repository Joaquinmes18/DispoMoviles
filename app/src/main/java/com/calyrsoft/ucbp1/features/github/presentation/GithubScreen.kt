package com.calyrsoft.ucbp1.features.github.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import org.koin.androidx.compose.koinViewModel

@Composable
fun GithubScreen( modifier: Modifier,
                  vm : GithubViewModel = koinViewModel()
                  ) {

    var nickname by remember { mutableStateOf("") }

    val state by vm.state.collectAsState()

    Column {
        Text("")
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                    it -> nickname = it
            }
        )
        OutlinedButton( onClick = {
            vm.fetchAlias(Nickname(nickname.trim()))
        }) {
            Text("")
        }
        when( val st = state) {
            is GithubViewModel.GithubStateUI.Error -> {
                Text(st.message )
            }
            GithubViewModel.GithubStateUI.Init -> {
                Text("Init" )
            }
            GithubViewModel.GithubStateUI.Loading -> {
                Text("Loading" )
            }
            is GithubViewModel.GithubStateUI.Success -> {
                val nickname = st.github.nickname.value
                Text(nickname)
                AsyncImage(
                    model = st.github.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(st.github.pathUrl.value)
            }
        }
    }
}