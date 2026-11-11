package com.chizhu.robuxprank;
import android.app.*;import android.os.*;import android.graphics.Color;import android.view.*;import android.widget.*;
public class MainActivity extends Activity {
 LinearLayout root; EditText nick; Spinner amount; Button go; ProgressBar bar; TextView status; Handler handler=new Handler(Looper.getMainLooper());
 int[] values={80,400,800,1700,4500,10000}; int progress=0;
 int dp(int n){return (int)(getResources().getDisplayMetrics().density*n+.5f);}
 TextView label(String t,int size){TextView v=new TextView(this);v.setText(t);v.setTextSize(size);v.setTextColor(Color.rgb(32,35,42));v.setPadding(0,dp(12),0,dp(12));root.addView(v);return v;}
 public void onCreate(Bundle b){super.onCreate(b);root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(24),dp(35),dp(24),dp(24));root.setBackgroundColor(Color.WHITE);setContentView(root);
 label("Robux Generator",27);label("Roblox 닉네임",16);nick=new EditText(this);nick.setSingleLine(true);nick.setHint("닉네임 입력");root.addView(nick);
 label("로벅스 수량",16);amount=new Spinner(this);String[] a={"80 Robux","400 Robux","800 Robux","1,700 Robux","4,500 Robux","10,000 Robux"};ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,a);amount.setAdapter(adapter);root.addView(amount);
 go=new Button(this);go.setText("GENERATE ROBUX");root.addView(go);status=label("",16);bar=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);bar.setMax(100);bar.setVisibility(View.GONE);root.addView(bar);go.setOnClickListener(v->start());
 }
 void start(){String name=nick.getText().toString().trim();if(name.isEmpty()){nick.setError("닉네임을 입력해줘");return;}go.setEnabled(false);nick.setEnabled(false);amount.setEnabled(false);bar.setVisibility(View.VISIBLE);progress=0;bar.setProgress(0);tick(name,values[amount.getSelectedItemPosition()]);}
 void tick(String name,int value){progress=Math.min(100,progress+2);bar.setProgress(progress);String stage=progress<25?"계정 확인 중...":progress<55?"로벅스 생성 중...":progress<85?"전송 준비 중...":"마무리 중...";status.setText(stage+" "+progress+"%");
 if(progress<100)handler.postDelayed(()->tick(name,value),110);else handler.postDelayed(()->{new AlertDialog.Builder(this).setTitle("ㅋㅋㅋ 속았지?").setMessage(name+"에게 "+value+" Robux가 지급된 것처럼 보였지만, 사실 가짜야! 실제 로벅스는 지급되지 않았어.").setPositiveButton("다시 하기",(d,w)->reset()).setCancelable(false).show();status.setText("장난 성공!");},650);}
 void reset(){go.setEnabled(true);nick.setEnabled(true);amount.setEnabled(true);bar.setVisibility(View.GONE);status.setText("");}
}