package org.gdms.test.service;

import org.gdms.test.util.*;

public interface LoginService {
    ReturnInfo register(String wechat_id,String id,String name,String auth);
    ReturnInfo logout(String wechat_id);
    UserInfo getUserData(String id, String role);
}

