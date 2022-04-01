package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlaylistRaw>, List<Playlist>> {

    override fun invoke(playlists: List<PlaylistRaw>): List<Playlist> {
        return playlists.map {
            Playlist(it.id, it.name, it.category, if(it.category=="rock") R.mipmap.rock else R.mipmap.playlist)
        }
    }

}