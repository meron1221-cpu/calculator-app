package android.example.trialassignment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText input1, input2, angleInput;
    private TextView resultDisplay;
    private Switch toggleSwitch;
    private View arithmeticLayout, trigonometricLayout;
    private Button switchButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        angleInput = findViewById(R.id.angleInput);
        resultDisplay = findViewById(R.id.resultDisplay);
        toggleSwitch = findViewById(R.id.toggleSwitch);
        arithmeticLayout = findViewById(R.id.arithmeticLayout);
        trigonometricLayout = findViewById(R.id.trigonometricLayout);
        switchButton = findViewById(R.id.switchButton);

        // Set button listeners for arithmetic operations
        findViewById(R.id.addButton).setOnClickListener(v -> performArithmeticOperation("add"));
        findViewById(R.id.subtractButton).setOnClickListener(v -> performArithmeticOperation("subtract"));
        findViewById(R.id.multiplyButton).setOnClickListener(v -> performArithmeticOperation("multiply"));
        findViewById(R.id.divideButton).setOnClickListener(v -> performArithmeticOperation("divide"));
        findViewById(R.id.modButton).setOnClickListener(v -> performArithmeticOperation("mod"));

        // Set button listeners for trigonometric operations
        findViewById(R.id.sinButton).setOnClickListener(v -> performTrigonometricOperation("sin"));
        findViewById(R.id.cosButton).setOnClickListener(v -> performTrigonometricOperation("cos"));
        findViewById(R.id.tanButton).setOnClickListener(v -> performTrigonometricOperation("tan"));
        findViewById(R.id.asinButton).setOnClickListener(v -> performTrigonometricOperation("asin"));
        findViewById(R.id.acosButton).setOnClickListener(v -> performTrigonometricOperation("acos"));
        findViewById(R.id.atanButton).setOnClickListener(v -> performTrigonometricOperation("atan"));
        findViewById(R.id.cscButton).setOnClickListener(v -> performTrigonometricOperation("csc"));
        findViewById(R.id.secButton).setOnClickListener(v -> performTrigonometricOperation("sec"));
        findViewById(R.id.cotButton).setOnClickListener(v -> performTrigonometricOperation("cot"));
        findViewById(R.id.acscButton).setOnClickListener(v -> performTrigonometricOperation("acsc"));
        findViewById(R.id.asecButton).setOnClickListener(v -> performTrigonometricOperation("asec"));
        findViewById(R.id.acotButton).setOnClickListener(v -> performTrigonometricOperation("acot"));

        // Switch between arithmetic and trigonometric layouts
        switchButton.setOnClickListener(v -> switchLayout());
    }

    private void performArithmeticOperation(String operation) {
        String num1Str = input1.getText().toString();
        String num2Str = input2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            showErrorDialog("Please enter both numbers.");
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result;

        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 == 0) {
                    showErrorDialog("Division by zero is not allowed.");
                    return;
                }
                result = num1 / num2;
                break;
            case "mod":
                result = num1 % num2;
                break;
            default:
                return;
        }

        resultDisplay.setText("Result: " + result);
    }

    private void performTrigonometricOperation(String operation) {
        String angleStr = angleInput.getText().toString();

        if (angleStr.isEmpty()) {
            showErrorDialog("Please enter an angle.");
            return;
        }

        double angle = Double.parseDouble(angleStr);
        if (!toggleSwitch.isChecked()) { // Radians
            angle = Math.toRadians(angle);
        }

        double result;
        switch (operation) {
            case "sin":
                result = Math.sin(angle);
                break;
            case "cos":
                result = Math.cos(angle);
                // Handle special case for cos(90 degrees)
                if (angle == Math.toRadians(90)) {
                    result = 0; // cos(90) = 0
                }
                break;
            case "tan":
                // Handle special case for tan(90 degrees)
                if (angle == Math.toRadians(90)) {
                    showErrorDialog("tan(90 degrees) is undefined.");
                    return;
                }
                result = Math.tan(angle);
                break;
            case "asin":
                result = Math.toDegrees(Math.asin(angle));
                break;
            case "acos":
                result = Math.toDegrees(Math.acos(angle));
                break;
            case "atan":
                result = Math.toDegrees(Math.atan(angle));
                break;
            case "csc":
                result = 1 / Math.sin(angle);
                break;
            case "sec":
                result = 1 / Math.cos(angle);
                break;
            case "cot":
                result = 1 / Math.tan(angle);
                break;
            case "acsc":
                result = Math.toDegrees(Math.asin(1 / angle));
                break;
            case "asec":
                result = Math.toDegrees(Math.acos(1 / angle));
                break;
            case "acot":
                result = Math.toDegrees(Math.atan(1 / angle));
                break;
            default:
                return;
        }

        resultDisplay.setText("Result: " + result);
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void switchLayout() {
        if (arithmeticLayout.getVisibility() == View.VISIBLE) {
            arithmeticLayout.setVisibility(View.GONE);
            trigonometricLayout.setVisibility(View.VISIBLE);
            switchButton.setText("Switch to Arithmetic");
        } else {
            arithmeticLayout.setVisibility(View.VISIBLE);
            trigonometricLayout.setVisibility(View.GONE);
            switchButton.setText("Switch to Trigonometric");
        }
    }
}
