package com.example.slt_andorid_qrcode;

import com.google.zxing.MipcaActivityCapture;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@ViewInject(R.id.tv1)
	TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.button1)
	private void Button1(View v) {
		startActivity(new Intent(MainActivity.this, MakeQRCodeActivity.class));
	}
	
	@OnClick(R.id.button2)
	private void Button2(View v){
		//先点击扫一扫按钮，跳转至另一个页面，打开摄像头扫描，回传扫描结果
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, MipcaActivityCapture.class);
		intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);//将需要启动的ACTIVITY设置在当前的任务中
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == 0){
			String result = data.getExtras().getString("result");
			if(TextUtils.isEmpty(result)){
				Toast.makeText(MainActivity.this, "没有扫描到结果", Toast.LENGTH_SHORT).show();
			}else {
				tv1.setText(result);
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
