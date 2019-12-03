package com.xhp.testutils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xhp.testutils.dialog.ModeDialog;
import com.xhp.testutils.util.AnimatorUtils;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class TestActivity extends AppCompatActivity {

    private Button mFind;
    private Button mSearch;
    private TextView mResult;
    private CircleImageView mCircle;
    private RecyclerView mList;
    private TestAdater testAdater;
    private ModeDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
//        getIntent().
    }

    public void find(View view) {
//        DataManager.getKaiYanApi().getFindData().enqueue(new Callback<List<FindBean>>() {
//            @Override
//            public void onResponse(Call<List<FindBean>> call, Response<List<FindBean>> response) {
//                Log.i("急急急", "cc" + Thread.currentThread().getName());
//                mResult.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<FindBean>> call, Throwable t) {
//
//            }
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("急急急", "bb" + Thread.currentThread().getName());
//                TestA testA = new TestA("LILI", "two", 12);
//                try {
//                    String path = getBaseContext().getFilesDir() + "/oos.txt";
//                    DataOutputStream oos = new DataOutputStream(new FileOutputStream(
//                            path));
//                    oos.writeUTF(testA.getName());
//                    oos.writeUTF(testA.getGrade());
//                    oos.writeInt(testA.getAge());
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        dialog = new ModeDialog(TestActivity.this);
//        dialog.show();
        Intent intent = new Intent(TestActivity.this, ModeActivity.class);
        TestActivity.this.startActivity(intent);
    }

    public void search(View view) {
//        Log.i("急急急", Thread.currentThread().getName());
//        DataManager.getKaiYanApi().getSearchData(10, "体育", 0).enqueue(new Callback<HotBean>() {
//            @Override
//            public void onResponse(Call<HotBean> call, Response<HotBean> response) {
//                mResult.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<HotBean> call, Throwable t) {
//
//            }
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("急急急", "aa" + Thread.currentThread().getName());
//                String path = getBaseContext().getFilesDir() + "/oos.txt";
//                try {
//                    DataInputStream ois = new DataInputStream(new FileInputStream(path));
////                    TestB testB = (TestB) ois.readObject();
//                    String a = ois.readUTF();
//                    String b = ois.readUTF();
//                    int c = ois.readInt();
//                    ois.close();
//                    Log.i("xhp", "a=" + a + "b=" + b + "c=" + c);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

//        String a = "{\"advItem\":[{\"advContentKey\":\"3#206#0101#&&9^^^^^^^^URL15319870660922769^\",\"advId\":\"01200100\",\"advSubType\":\"001\",\"advURL\":\"VOD://HSGG9920180712001062\",\"assetType\":3,\"backgroudColor\":\"\",\"channelIds\":\"\",\"contentId\":\"HSGG9920180712001062\",\"contentName\":\"回看片头广告\",\"displayTimes\":-1,\"duration\":15,\"interval\":-1,\"mD5\":\"34caeeea7805ad1ae1e03dac30cebb15\",\"offset\":-1,\"offsetType\":0,\"skip\":0,\"videoStyle\":0}],\"checkInterval\":0,\"resultCode\":\"000000\",\"resultCount\":1,\"resultDesc\":\"success\",\"sessionId\":\"1570966195528-22957-1228094560\"}";
//        Adv adv = new Gson().fromJson(a, Adv.class);
//        Log.i("xhp", "adv=" + adv.toString());
        dialog = new ModeDialog(TestActivity.this);
        dialog.show();
    }

    private void initViews() {
        mFind = findViewById(R.id.find);
        mSearch = findViewById(R.id.search);
        mResult = findViewById(R.id.result);
        mCircle = findViewById(R.id.circle);
//        mCircle = findViewById(R.id.circle);
        mCircle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mCircle.setBorderColor(Color.RED);
                    AnimatorUtils.scaleRelCenter(TestActivity.this, mCircle, R.animator.item_scale_normal2big);
                } else {
                    mCircle.setBorderColor(Color.TRANSPARENT);
                    AnimatorUtils.scaleRelCenter(TestActivity.this, mCircle, R.animator.item_scale_big2normal);
                }

            }
        });
        mList = findViewById(R.id.list);
        mList.setLayoutManager(new GridLayoutManager(TestActivity.this, 1, GridLayoutManager.HORIZONTAL, false));
        testAdater = new TestAdater(TestActivity.this);
        String[] strings = {"one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five"};
        testAdater.setDatas(Arrays.asList(strings));
        mList.setAdapter(testAdater);
    }

    //    @Override
    public void onclick(int postion, String messageBean) {
        Toasty.normal(TestActivity.this, "posiion=" + postion + " message=" + messageBean).show();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
