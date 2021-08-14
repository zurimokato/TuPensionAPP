package job.project.com.tupension.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabViewAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>fragments=new ArrayList<>();
    private ArrayList<String>titles= new ArrayList<>();


    public TabViewAdapter(FragmentManager fm){
        super(fm);

    }

    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        titles.add(title);
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
