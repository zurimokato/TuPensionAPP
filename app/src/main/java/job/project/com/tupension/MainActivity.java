package job.project.com.tupension;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import job.project.com.tupension.adapters.TabViewAdapter;
import job.project.com.tupension.fragment.MapFragment;
import job.project.com.tupension.fragment.PensionFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager pager;
    private TabViewAdapter adapter;
    private FloatingActionButton fab;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        pager= (ViewPager) findViewById(R.id.viewPager);
        adapter=new TabViewAdapter(getSupportFragmentManager());
        adapter.addFragment(new PensionFragment(),"Pensiones");
        adapter.addFragment(new MapFragment(),"Mapa");
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
