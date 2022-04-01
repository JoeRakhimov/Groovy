package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import petros.efthymiou.groovy.R
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    lateinit var viewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        setupViewModel()
        setupList()

        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when(loading){
                true -> loader.visibility = View.VISIBLE
                false -> loader.visibility = View.GONE
            }
        }

        return view
    }

    private fun setupList() {
        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if(playlists.getOrNull()!=null)
                setupList(playlists.getOrNull()!!)
        }
    }

    private fun setupList(
        playlists: List<Playlist>
    ) {
        with(playlists_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = PlaylistAdapter(playlists){ id->
                val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailsFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }

}