package com.google.zxing.decoding;

import java.io.UnsupportedEncodingException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

public class GenerateQRCode {

	/**
	 * 生成二维码
	 * @param 写入二维码的参数
	 * @demo
	 * EditText editText = (EditText) findViewById(R.id.code_content);
     * Bitmap qrcode = generateQRCode(editText.getText().toString());
     * ImageView imageView = (ImageView) findViewById(R.id.code_image);
     * imageView.setImageBitmap(qrcode);
	 * @return
	 */
	public static Bitmap generateQRCode(String content) {
        try {
        	content = new String(content.getBytes("ISO-8859-1"),"UTF-8"); 
        	QRCodeWriter writer = new QRCodeWriter();
            // MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 500, 500);
            return bitMatrix2Bitmap(matrix);
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	private static Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] rawData = new int[w * h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = Color.WHITE;
                if (matrix.get(i, j)) {
                    color = Color.BLACK;
                }
                rawData[i + (j * w)] = color;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.RGB_565);
        bitmap.setPixels(rawData, 0, w, 0, 0, w, h);
        return bitmap;
    }
	
	

}
