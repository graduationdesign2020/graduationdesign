package org.graduatiedesign.gdmsservercore.service;

import org.graduatiedesign.gdmsservercore.utils.ReturnInfo;
import org.graduatiedesign.gdmsservercore.utils.UserInfo;

public interface LoginService {
    ReturnInfo register(String wechat_id,String id,String name,String auth);
    ReturnInfo logout(String wechat_id);
    UserInfo getUserData(String id, String role);
}

