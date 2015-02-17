package ev.com.developmentpush;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by androiddevelopment on 2/17/15.
 */

public class ParseKeys extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
         Parse.initialize(this, "qoOgyeunhYhCIjHR8f0OJcwuvkrjT49xDt20ymGq", "tKHKGsqQMzbtDMIVDoAQ977YJejh1NXOPR9XElLv");
    }
}
