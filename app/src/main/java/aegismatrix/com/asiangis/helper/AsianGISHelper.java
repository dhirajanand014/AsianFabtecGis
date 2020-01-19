package aegismatrix.com.asiangis.helper;

import android.annotation.TargetApi;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@TargetApi(value = 24)
public class AsianGISHelper {

    private static final List<String> UNLOAD_REFRESH_URLS = Collections.unmodifiableList(
            Arrays.asList("user_add_location.php", "add_om.php", "add_ss.php", "add_fd.php", "usrfd_edit.php", "usrss_edit.php", "usrom_edit.php", "user_rmu.php", "user_dtr.php", "user_htpole.php", "user_ugpath.php", "user_ohline.php", "usrdtredit-details.php", "usrrmuedit-details.php", "usrhtedit-details.php", "usrugedit-details.php", "usrohtedit-details.php", "user_dashboard.php", "htedit-details.php", "rmuedit-details.php", "dtredit-details.php", "ohtedit-details.php", "ugedit-details.php", "genrate_map.php"));

    /**
     * @param inSharedPreferences
     * @return
     */
    public Map.Entry<String, ?> getUserDetails(SharedPreferences inSharedPreferences) {
        if (!inSharedPreferences.getAll().isEmpty()) {
            Map.Entry<String, ?> userEntry = inSharedPreferences.getAll().entrySet().stream().findFirst().orElse(null);
            if (null != userEntry) {
                return userEntry;
            }
        }
        return null;
    }

    /**
     * @param inUrl
     * @return
     */
    public boolean isUrlToUnload(final String inUrl) {
        return UNLOAD_REFRESH_URLS.stream().anyMatch(string -> inUrl.contains(string));
    }
}
