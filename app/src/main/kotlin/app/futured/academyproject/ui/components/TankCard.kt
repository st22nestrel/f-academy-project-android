package app.futured.academyproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.academyproject.ui.theme.Grid
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TankCard(tank: Tank, onClick: (Int) -> Unit, modifier: Modifier = Modifier,
             onSelected: (tankId: Int) -> Unit = ::onSelectedPreview ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick(tank.id) }
            .padding(vertical = Grid.d2, horizontal = Grid.d4)
            .fillMaxWidth(),
    ) {
        var selected by remember { mutableStateOf(tank.isSelected)}
        Card(
            colors = CardDefaults.cardColors(),
            modifier = Modifier
                .size(Grid.d15),
        ) {
            /*AsyncImage(
                model = tank.bigIcon,
                contentDescription = "tank image",
            )*/
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(tank.bigIcon.replace("http", "https"))
                        .build(),
                ),
                contentDescription = tank.bigIcon,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f),
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = Grid.d2, horizontal = Grid.d4),
        ) {
            Text(
                text = tank.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(Grid.d1))
            Text(
                text = tank.tankType,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = Grid.d2, horizontal = Grid.d4),
        ) {
            Text(
                text = tank.tier.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(Grid.d1))
            Text(
                text = tank.nation,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (tank.isFavourite) {

            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = null
            )
        }

        IconButton(
            onClick = {
                onSelected(tank.id)
                //tank.isSelected = !tank.isSelected
                selected = !selected
            }
        ){
            Icon(
                imageVector = if (selected) Icons.Filled.RemoveCircle else Icons.Outlined.AddBox,
                tint = MaterialTheme.colorScheme.errorContainer,
                contentDescription = null
            )
        }
    }
}

fun onSelectedPreview(input: Int) : Unit {
    val pls = input
}

@Preview
@Composable
private fun PlaceCardPreview(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) = Showcase {
    TankCard(tank = tanks.first(), onClick = {})
}

@Preview
@Composable
private fun PlaceCardPreview2(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) = Showcase {
    TankCard(tank = tanks.first().copy(isFavourite = true), onClick = {})
}
