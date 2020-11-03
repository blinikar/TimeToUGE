/*
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <https://www.gnu.org/licenses/>.

        (Это свободная программа: вы можете перераспространять ее и/или изменять
        ее на условиях Стандартной общественной лицензии GNU в том виде, в каком
        она была опубликована Фондом свободного программного обеспечения; либо
        версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.

        Эта программа распространяется в надежде, что она будет полезной,
        но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии ТОВАРНОГО ВИДА
        или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной
        общественной лицензии GNU.

        Вы должны были получить копию Стандартной общественной лицензии GNU
        вместе с этой программой. Если это не так, см.
        <https://www.gnu.org/licenses/>.)
*/

package com.ikar.egetimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    DateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault());
    Date xDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            xDay = simpleDateFormat.parse("01/12/2020 10:00:00");
        }
        catch(Exception e){
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    mainCycle();
                    SystemClock.sleep(1000);
                }
            }
        });
        t.start();
    }

    public void mainCycle(){
        TextView hoursTextView = findViewById(R.id.hours);
        TextView minutesTextView = findViewById(R.id.minutes);
        TextView secondsTextView = findViewById(R.id.seconds);

        Date currentDate = new Date();

        Long ldiff = (xDay.getTime() - currentDate.getTime()) / 1000;//Difference in seconds
        Integer diff = ldiff.intValue();
        Integer diffInMinutes = diff/60;
        Integer diffInHours = diff/3600;

        secondsTextView.setText(diff.toString());
        minutesTextView.setText(diffInMinutes.toString());
        hoursTextView.setText(diffInHours.toString());

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.git:
                Uri gitRepoUri = Uri.parse("https://github.com/blinikar/TimeToUGE");//repoURL
                Intent gitRepo = new Intent(Intent.ACTION_VIEW, gitRepoUri);
                // Verify that the intent will resolve to an activity
                if (gitRepo.resolveActivity(getPackageManager()) != null) {
                    // Here we use an intent without a Chooser unlike the next example
                    startActivity(gitRepo);
                }
                break;
            case R.id.info:
                Uri vkUri = Uri.parse("https://vk.com/blinoveg");
                Intent vk = new Intent(Intent.ACTION_VIEW, vkUri);
                // Verify that the intent will resolve to an activity
                if (vk.resolveActivity(getPackageManager()) != null) {
                    // Here we use an intent without a Chooser unlike the next example
                    startActivity(vk);
                }
                break;
        }
    }
}
