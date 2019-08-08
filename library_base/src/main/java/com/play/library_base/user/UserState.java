package com.play.library_base.user;

import com.play.library_base.utils.SharePreUtils;

public class UserState {

    public static String getUserId() {

        return SharePreUtils.getString("userid");
    }

}
