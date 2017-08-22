package com.kodluyoruz.viewpagersample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ViewPagerSampleActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_sample);
        initView();
    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.activity_view_pager_sample_swipeRefreshLayout);
        tabLayout = findViewById(R.id.activity_view_pager_sample_tabLayout);
        viewPager = findViewById(R.id.activity_view_pager_sample_viewPager);

        initEvent();
    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(this);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        ContactListFragment contactListFragment = new ContactListFragment();
        AddContactFragment addContactFragment = new AddContactFragment();

        viewPagerAdapter.addFragment(contactListFragment, "ContactList");
        viewPagerAdapter.addFragment(addContactFragment, "AddContact");

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this); // sayfayi da scroll yaparak degistirme
        viewPager.setCurrentItem(19); // baslangicda hangisinden başlayacagi icin
    }

    @Override
    public void onRefresh() {

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                Toast.makeText(ViewPagerSampleActivity.this, "Kalan" + l, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }.start();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(this, "İlk kez seçilen tab: " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Toast.makeText(this, "Seçilmeyen tab: " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Toast.makeText(this, "Yeniden seçilen tab: " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Toast.makeText(this, "Fragment Scrolled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "Fragmnet selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Toast.makeText(this, "Fragment onPageScrollStateChanged", Toast.LENGTH_SHORT).show();
    }
}
