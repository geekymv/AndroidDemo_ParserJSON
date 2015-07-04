package com.geekymv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import com.geekymv.pojo.Student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.GsmCellLocation;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				parseJSON();
				
//				startActivity(new Intent(MainActivity.this,
//						AnotherActivity.class));
			}
		});

	}

	public void parseJSON() {
		// 读取资源文件
		Class<?> clazz = MainActivity.class;
		InputStream is = clazz.getResourceAsStream("/test.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder builder = new StringBuilder();
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			
			System.out.println(builder.toString());
			
			System.out.println("=======解析结果======");
			// 解析JSON
			JSONObject object = new JSONObject(builder.toString());
			JSONArray arrays = object.getJSONArray("students");
			for(int i = 0;  i < arrays.length(); i++) {
				JSONObject obj = arrays.getJSONObject(i);
				String name = obj.getString("name");
				String gender = obj.getString("gender");
				int age = obj.getInt("age");
				
				Student student = new Student(name, gender, age);
				Toast.makeText(this, "学生" + (i) + "信息：" + student.toString(), Toast.LENGTH_LONG).show();
				
				System.out.println(student.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
