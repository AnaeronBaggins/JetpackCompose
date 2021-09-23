package com.varun.jetpackcompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val items = listOf(
    Screen.Home,
    Screen.Dashboard)

@Composable
fun BottomBar(navController : NavController){
    BottomNavigation(
        elevation = 5.dp,
        backgroundColor =  MaterialTheme.colors.primarySurface,
    contentColor =  contentColorFor(backgroundColor)){

        items.map {
            BottomNavigationItem(
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label= {
                    Text(
                        text = it.title
                    )
                },
                selected = false,
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.route)
                }
            )
        }

    }
}

@Composable
fun BottomBarMain(navController : NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            ConversationHomeScreen(messages = SampleData.conversationSample)
        }
        composable(Screen.Dashboard.route){
            DashboardScreen()
        }
    }
}

@Composable
fun MainView(){
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController) }) {
        BottomBarMain(navController = navController)
    }
}

@Composable
fun ConversationHomeScreen(messages: List<Message>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)){
        LazyColumn {
            items(messages) { message ->
                MessageCard(msg = message)
            }
        }
    }

}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "This is the profile photo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)

        Column(Modifier.clickable { isExpanded = !isExpanded }) {
            Row(Modifier.fillMaxWidth()){
                Text(
                    text = msg.author,
                    Modifier.weight(1f),
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2)
                CompositionLocalProvider( LocalContentAlpha provides ContentAlpha.medium) {

                    Text(text = "3 minutes ago",
                        style = MaterialTheme.typography.subtitle2)
                }
            }


            Spacer(modifier = Modifier.height(4.dp))



            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    Modifier.padding(4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }

}


@Composable
fun DashboardScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)){
        Text(text = Screen.Dashboard.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp)
    }
}
