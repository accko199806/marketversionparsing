package net.accko.marketversionparsing;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String getMarketVersion, getAppVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            MarketVersionParsing.getPackageName("Your App Package");
            getMarketVersion = MarketVersionParsing.getMarketVersion();

            Button showMarketVersionDialog = findViewById(R.id.showMarketVersionDialog);
            showMarketVersionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Market Version");
                    builder.setMessage(getMarketVersion);
                    builder.setPositiveButton("DISMISS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();
                }
            });
        } //show MarketVersion

        {
            try {
                getAppVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                Button showAppVersionDialog = findViewById(R.id.showAppVersionDialog);
                showAppVersionDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("App Version");
                        builder.setMessage(getAppVersion);
                        builder.setPositiveButton("DISMISS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                    }
                });
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        } //show AppVersion

        {
            Button showVersionCheck = findViewById(R.id.showVersionCheck);
            showVersionCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView current_text, latest_text;
                    Button update_btn;

                    View version_view = MainActivity.this.getLayoutInflater().inflate(R.layout.dialog_version, null);
                    current_text = version_view.findViewById(R.id.current_text);
                    current_text.setText("v" + getAppVersion);
                    latest_text = version_view.findViewById(R.id.latest_text);
                    latest_text.setText("v" + getMarketVersion);

                    update_btn = version_view.findViewById(R.id.update_btn);
                    if (getAppVersion.toString().equals(getMarketVersion)) {
                        update_btn.setText(getString(R.string.same_version));
                        update_btn.setEnabled(false);
                    } else {
                        update_btn.setText(getString(R.string.dif_version));
                        update_btn.setOnClickListener(
                                new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent goMarket = new Intent(Intent.ACTION_VIEW);
                                        goMarket.setData(Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
                                        startActivity(goMarket);
                                    }
                                }
                        );
                    }

                    AlertDialog.Builder version_dialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(version_view);
                    version_dialog.show();
                }
            });
        } //show VersionCheck

    }
}