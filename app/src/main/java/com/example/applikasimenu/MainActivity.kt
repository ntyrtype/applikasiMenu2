import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applikasimenu.R

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val items = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi ListView
        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        // Daftarkan ListView untuk Context Menu
        registerForContextMenu(listView)
    }

    // Membuat Option Menu di bagian atas layar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Mengelompokkan dan mengatur item menu saat menu dibuka
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // Dapatkan referensi ke grup menu utama
        menu?.findItem(R.id.group_main)?.isVisible = true // Menampilkan grup utama
        return super.onPrepareOptionsMenu(menu)
    }

    // Menangani item yang dipilih di Option Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Cari dipilih", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_profile -> {
                Toast.makeText(this, "Profil dipilih", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_privacy -> {
                Toast.makeText(this, "Privasi dipilih", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_notifications -> {
                Toast.makeText(this, "Notifikasi dipilih", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Membuat Context Menu untuk ListView
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // Menangani item yang dipilih di Context Menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listItemPosition = info.position

        return when (item.itemId) {
            R.id.edit -> {
                editItem(listItemPosition)
                true
            }
            R.id.delete -> {
                deleteItem(listItemPosition)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Fungsi untuk mengedit item dalam ListView
    private fun editItem(position: Int) {
        val item = items[position]
        Toast.makeText(this, "Edit item: $item", Toast.LENGTH_SHORT).show()
        // Logika edit item bisa ditambahkan di sini
    }

    // Fungsi untuk menghapus item dalam ListView
    private fun deleteItem(position: Int) {
        items.removeAt(position)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Item dihapus", Toast.LENGTH_SHORT).show()
    }
}
