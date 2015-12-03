package com.example.slt_andorid_qrcode;

import com.google.zxing.decoding.GenerateQRCode;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MakeQRCodeActivity extends Activity {

	@ViewInject(R.id.editText1)
	EditText contentText;
	
	@ViewInject(R.id.imageView1)
	ImageView qrImageView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_qrcode);
		ViewUtils.inject(this);
	}
	
	
	@OnClick(R.id.button1)
	private void MakeQRCode(View v){
		String content = contentText.getText().toString().trim();
		//利用GOOGLE Zxing框架实现生成二维码
		Bitmap qrImage = GenerateQRCode.generateQRCode(content);
		qrImageView.setImageBitmap(qrImage);//生成后的二维码图片
	}
}
