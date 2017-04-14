package com.linzhi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.linzhi.base.BaseActivity;
import com.linzhi.base.BaseFragment;
import com.linzhi.fragment.MessageListFragment;
import com.linzhi.fragment.RegSearchFragment;
import com.linzhi.fragment.VipRegistFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjy on 2017/3/23.
 */

public class MainActivity extends BaseActivity {

    //退出
    @BindView(R.id.tv_quit)
    TextView tv_quit;


    //信息列表
    @BindView(R.id.btn_message)
    RadioButton btn_msg_List;

    //信息查询
    @BindView(R.id.btn_search)
    RadioButton btn_search_vip;

    //vip
    @BindView(R.id.btn_vip)
    RadioButton btn_vip_reg;

    @BindView(R.id.viewPaper)
    ViewPager viewPaper;

    @BindView(R.id.radiogroup)
    RadioGroup mRadioGroup;

    //变量
    MessageListFragment msgListFragment;
    RegSearchFragment msgSearchFragment;
    VipRegistFragment vipFragment;

    private int currentFragment;
    private List<BaseFragment> listFragment;

    private static final String TAG = "SJY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        ButterKnife.bind(this);

        initFragment();
        initListener();






    }

    private void initFragment() {

        msgListFragment = MessageListFragment.newInstance();
        msgSearchFragment = RegSearchFragment.newInstance();
        vipFragment = VipRegistFragment.newInstance();

        listFragment = new ArrayList<>();
        listFragment.add(msgListFragment);
        listFragment.add(msgSearchFragment);
        listFragment.add(vipFragment);
        viewPaper.setOffscreenPageLimit(3);
        viewPaper.setOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mRadioGroup.check(R.id.btn_message);
                    break;
                case 1:
                    mRadioGroup.check(R.id.btn_search);
                    break;
                case 2:
                    mRadioGroup.check(R.id.btn_vip);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_message:
                        currentFragment = 0;
                        break;

                    case R.id.btn_search:
                        currentFragment = 1;
                        break;

                    case R.id.btn_vip:
                        currentFragment = 2;
                        break;

                }


                viewPaper.setCurrentItem(currentFragment, false);

            }
        });

        viewPaper.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return listFragment.get(arg0);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                super.destroyItem(container, position, object);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
