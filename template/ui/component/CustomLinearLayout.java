package {{.Print "Package"}}.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import {{.Print "Package"}}.R;

public class {{.Title "ClassName"}} extends LinearLayout {

    // Views
    TextView tv;
    Button btn;
    Button btn2;

    // Listener
    CallbackListener listener;

    /**
     * Init
     */
    private void Init(Context context) {
        listener = null;

        // Inflate Layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.{{.Print "ClassName"}}, this);

        // Find Views
        tv = v.findViewById(R.id.textView);
        btn = v.findViewById(R.id.button);
        btn2 = v.findViewById(R.id.button2);

        // Set Listener
        btn.setOnClickListener(view -> {
            if (listener != null) {
                listener.onConfirm(this);
            }
        });
        btn2.setOnClickListener(view -> {
            if (listener != null) {
                listener.onCancel(this);
            }
        });
    }


    /**
     * Callback Interface
     */
    public interface CallbackListener {
        void onConfirm(View view);

        void onCancel(View view);
    }


    /**
     * Public method
     */
    public void setText(String text) {
        tv.setText(text);
    }

    public void setOnClickListener(CallbackListener _listener) {
        listener = _listener;
    }


    /**
     * Constructor
     */
    public {{.Print "ClassName"}}(Context context) {
        super(context);
        Init(context);
    }

    public {{.Print "ClassName"}}(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public {{.Print "ClassName"}}(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }

}
