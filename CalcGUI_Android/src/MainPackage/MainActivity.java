package MainPackage;

import FV.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private Spinner yearSpinner;
	private EditText startingAmount;
	private EditText interestRate;
	private EditText futureValue;
	private Button Calculate;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Add Spinner Control, specifically for the year component
		yearSpinner = (Spinner) findViewById(R.id.spinner1);
		
		startingAmount = (EditText) findViewById(R.id.editText1);
		
		startingAmount.addTextChangedListener(new TextWatcher(){
		
			public void afterTextChanged(Editable s) {
				checkReady();}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
		interestRate = (EditText) findViewById(R.id.editText2);
		interestRate.addTextChangedListener(new TextWatcher(){
			
			public void afterTextChanged(Editable s) {
				checkReady();}

			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});

		futureValue = (EditText)findViewById(R.id.editText3);
		Calculate = (Button) findViewById(R.id.button1);

		Calculate.setOnClickListener(new OnClickListener(){

			
			public void onClick(View v){
				Ready();
			}

		});
	}

	//Set Calculate button to enable(true) or enable(false)
	//if user inputs any value into the textview or not
	private void checkReady() {
		if( (!(startingAmount.getText().toString().equals("")))  &
				(!(interestRate.getText().toString().equals(""))) ){
			Calculate.setEnabled(true);

		}else
			Calculate.setEnabled(false);	

	}

	//Convert to parseDouble
	//then add a formula to calculate the future value
	private void Ready() {
		double totalAmount = Double.parseDouble(startingAmount.getText().toString());
		
		double finalRate = Double.parseDouble(interestRate.getText().toString());
		
		double totalYears = Double.parseDouble((String) yearSpinner.getSelectedItem());
		
		double Formula = totalAmount*Math.pow(1+(finalRate/1200), totalYears*12);
		
		String changeDecimal = String.format("%1$,.2f", Formula);
		
		futureValue.setText(changeDecimal);
		
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
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
