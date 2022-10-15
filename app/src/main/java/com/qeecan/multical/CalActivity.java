package com.qeecan.multical;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;
import com.qeecan.multical.Schedule.Alarm;
import com.qeecan.multical.Schedule.ScheduleActivity;
import com.qeecan.multical.Schedule.ScheduleEditActivity;
import com.qeecan.multical.Schedule.adpter.ScheduleAdapter;
import com.qeecan.multical.Schedule.bean.ScheduleBean;
import com.qeecan.multical.Schedule.db.ScheduleDao;
import com.qeecan.multical.Schedule.db.ScheduleSQLiteHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private CalendarView calendarView;
    private TextView chooseDate, title;
    private FloatingActionButton fbtn_alarm_today,  fbtn_alarm_all;

    //将当前的日期存放到数组中
    private int[] cDate = CalendarUtil.getCurrentDate();

    private Context context = this;
    private ScheduleAdapter adapter;
    private ScheduleSQLiteHelper helper;
    private ListView scheduleLv;
    private List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
    private List<ScheduleBean> scheduleBeans = new ArrayList<ScheduleBean>();
    private AlarmManager alarm;

    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        title = (TextView) findViewById(R.id.title);
        chooseDate = findViewById(R.id.choose_date);//选中的日期

        //定位显示的ListView
        scheduleLv = findViewById(R.id.alarm_lvdaily);
        adapter = new ScheduleAdapter(getApplicationContext(), scheduleList);
        //创建适配器对象并设施适配器
        refreshListView();
        scheduleLv.setAdapter(adapter);
        scheduleLv.setOnItemClickListener(this);
        scheduleLv.setOnItemLongClickListener(this);

        calendarView = (CalendarView) findViewById(R.id.calview);
        fbtn_alarm_today = (FloatingActionButton) findViewById(R.id.fbtn_today);
        fbtn_alarm_all = (FloatingActionButton) findViewById(R.id.fbtn_alarm_all);
        fbtn_alarm_today.setOnClickListener(onClickListener);
        fbtn_alarm_all.setOnClickListener(onClickListener);
        //初始化日历起始范围
        calendarView.setStartEndDate("2016.1", "2028.12")
                //禁用范围
                .setDisableStartEndDate("2015.10.10", "2029.10.10")
                //初始显示的年月
                .setInitDate(cDate[0] + "." + cDate[1])
                //单选时默认的选中日期
                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
                //根据设定的日期范围计算日历的页数
                .init();

        //显示日历头的年月
        title.setText(cDate[0] + "年" + cDate[1] + "月");
        chooseDate.setText("当前选中的日期：" + cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");
        setDate(cDate[0],cDate[1],cDate[2]);
        //设置月份切换的显示
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });



        //日历单击事件
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                //如果当前类型为本月
                if (date.getType() == 1) {
                    chooseDate.setText("当前选中的日期：" + date.getSolar()[0] + "年" + date.getSolar()[1] + "月" + date.getSolar()[2] + "日");
                    setDate(date.getSolar()[0],date.getSolar()[1],date.getSolar()[2]);
                    refreshListView();
                }

            }
        });





    }


    //日期的显示设置
    private void setDate(int y, int m, int d) {
        //update tv and dateArray
        temp = y + "";
        if (m < 10) temp += "0";
        temp += (m + "");
        if (d < 10) temp += "0";
        temp += (d+"");

    }

    //更新listview
    public void refreshListView() {
        ScheduleDao sd = new ScheduleDao(this);
        sd.open();
        scheduleList.clear();

        scheduleBeans.addAll(sd.getAllSchedule());
        List<ScheduleBean> slist;
        slist = new ArrayList<>();
        ScheduleBean k;
        for(ScheduleBean schedule : scheduleBeans){
            String t=schedule.getDate();
            if(schedule.getDate().equals(temp)){
                slist.add(schedule);
            }
        }
        scheduleBeans.clear();
        for(int i=0;i<slist.size();i++){
            for(int j=i+1;j<slist.size();j++){
                if(Integer.parseInt(slist.get(i).getTim())>Integer.parseInt(slist.get(j).getTim())){
                    k=slist.get(i);
                    slist.set(i,slist.get(j));
                    slist.set(j,k);
                }
            }
        }


        scheduleList.addAll(slist);
        sd.close();
        adapter.notifyDataSetChanged();

        slist.clear();



    }

    //设置很多提醒
    private void sendAlarms(List<ScheduleBean> sc) {
        for (int i = 0; i < sc.size(); i++) sendAlarm(sc.get(i));
    }

    private void sendAlarm(ScheduleBean s) {
        Calendar c = Calendar.getInstance();
        if (!s.getTime().before(c)) {
            Intent intent1 = new Intent(this, Alarm.class);
            Bundle b = new Bundle();
            b.putLong("id", s.getId());
            b.putString("content", s.getContent());
            intent1.putExtras(b);
            // Toast.makeText(getApplicationContext(), b.toString(), Toast.LENGTH_LONG).show();
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, intent1, 0);
            alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            c.set(Calendar.HOUR_OF_DAY, s.getHour());
            c.set(Calendar.MINUTE, s.getMinute());
            c.set(Calendar.SECOND, 0);
            assert alarm != null;
            alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            //Toast.makeText(getApplicationContext(), "闹钟设置成功", Toast.LENGTH_LONG).show();

        }
    }


    //取消很多提醒
    private void cleanAlarms(List<ScheduleBean> sc) {
        for (int i = 0; i < sc.size(); i++) cleanAlarm(sc.get(i));
    }

    //取消提醒
    private void cleanAlarm(ScheduleBean s) {
        Intent intent1 = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, intent1, 0);
        assert alarm != null;
        alarm.cancel(pendingIntent);

    }

    //listview item的单击编辑事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ScheduleBean sche = (ScheduleBean) parent.getItemAtPosition(position);
        Bundle sb = new Bundle();
        sb.putInt("mode", 5);
        sb.putLong("id", sche.getId());
        sb.putString("content", sche.getContent());
        sb.putString("time", sche.getClock());
        Intent intent1 = new Intent(CalActivity.this, ScheduleEditActivity.class);
        intent1.putExtras(sb);
        startActivity(intent1);//requestCode为0是添加信息

    }

    //listview item的长按删除事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final ScheduleBean sch = scheduleList.get(position);
        new AlertDialog.Builder(CalActivity.this)
                .setMessage("确定删除该条记录?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScheduleDao op = new ScheduleDao(context);
                        op.open();
                        op.removeSchedule(sch);
                        op.close();
                        refreshListView();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
        return true;
    }

    //监听几个floatingactionbutton按钮进行跳转操作
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.fbtn_today://今日日期
                    calendarView.today();
                    chooseDate.setText("当前选中的日期：" + cDate[0] + "年" + cDate[1] + "月" + cDate[2] + "日");
                    break;
                case R.id.fbtn_alarm_all://所有安排
                    intent.setClass(CalActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                    break;

            }

        }
    };
}