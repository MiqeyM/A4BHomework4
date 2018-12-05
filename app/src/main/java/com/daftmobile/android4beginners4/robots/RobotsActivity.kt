package com.daftmobile.android4beginners4.robots


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daftmobile.android4beginners4.robots.viewmodel.ExternalSourceRobotsViewModel
import com.daftmobile.android4beginners4.robots.viewmodel.RobotsViewModel
import kotlinx.android.synthetic.main.activity_robots.*

class RobotsActivity : AppCompatActivity() {
    private lateinit var viewModel: RobotsViewModel
    private var selectedSort = R.id.ascending_option


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robots)
        if (savedInstanceState != null){
            selectedSort = savedInstanceState.getInt("SelectedOption");
        }

        viewModel = ViewModelProviders.of(this).get(ExternalSourceRobotsViewModel::class.java)
        viewModel.getRobotList().observe(
                this,
                Observer { this.robotTextView.text = it }
        )





        addRobotFab.setOnClickListener {
            addNewItem()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("SelectedOption", selectedSort)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        if(selectedSort==R.id.descending_option)
            menu.findItem(R.id.descending_option).isChecked = true
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ascending_option -> {
                item.isChecked = !item.isChecked
                selectedSort = R.id.ascending_option
                viewModel.display(selectedSort)
                true
            }
            R.id.descending_option -> {
                item.isChecked = !item.isChecked
                selectedSort = R.id.descending_option
                viewModel.display(selectedSort)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addNewItem() {
        viewModel.addRobot(selectedSort)
    }
}
