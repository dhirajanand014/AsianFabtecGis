package aegismatrix.com.asiangis.helper;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebBackForwardList;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@TargetApi(value = 24)
public class AsianGISHelper {

    public Map.Entry<String, ?> getUserDetails(SharedPreferences inSharedPreferences) {
        if (!inSharedPreferences.getAll().isEmpty()) {
            Map.Entry<String, ?> userEntry = inSharedPreferences.getAll().entrySet().stream().findFirst().orElse(null);
            if (null != userEntry) {
                return userEntry;
            }
        }
        return null;
    }

    public boolean isPreviousUrlLogin(WebBackForwardList inWebBackForwardList) {
        String previousUrl = inWebBackForwardList.getItemAtIndex(inWebBackForwardList.getCurrentIndex() - 1).getUrl();
        return !TextUtils.isEmpty(previousUrl) && previousUrl.contains("login.php");
    }
}
