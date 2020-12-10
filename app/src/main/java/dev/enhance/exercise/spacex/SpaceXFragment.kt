package dev.enhance.exercise.spacex

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dev.enhance.exercise.R
import dev.enhance.exercise.core.repository.models.Launch
import dev.enhance.exercise.core.view.Initializer
import kotlinx.android.synthetic.main.fragment_spacex.*
import javax.inject.Inject

class SpaceXFragment: Fragment(), Initializer {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    lateinit var spaceXViewModel: SpaceXViewModel

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_spacex, container, false)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun references() {
        this.spaceXViewModel = ViewModelProvider(this, viewmodelFactory).get(SpaceXViewModel::class.java)
        this.viewManager = LinearLayoutManager(context)
    }

    override fun actions() = spaceXViewModel.getLaunches()

    override fun observables() {
        spaceXViewModel.getLaunchesData().observe(this, { projects ->
            if (projects != null) {
                createList(projects)
            }
        })

        spaceXViewModel.error().observe(this, {
            showDialogErrorMessage(context!!.resources.getString(it))
        })

        spaceXViewModel.loading().observe(this, { loading ->
            if (loading != null) {
                showProgress(loading)
            }
        })
    }

    private fun createList(launches: List<Launch>) {
        viewAdapter = LaunchAdapter(launches)
        projectList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter =viewAdapter
        }
    }

    private fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showDialogErrorMessage(message: String) {
        val dialogBuilder = AlertDialog.Builder(activity!!)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.accept) { dialog, _ ->
                dialog.dismiss()
                dialog.cancel()
                return@setPositiveButton
            }
            .create()

        dialogBuilder.show()
    }
}