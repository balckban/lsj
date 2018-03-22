package com.idogs.laosiji.basic.preference;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idogs.laosiji.basic.http.YbUser;
import com.idogs.laosiji.basic.http.token.YbToken;
import com.idogs.laosiji.core.preference.AppPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 2017/8/15.
 */

public class YbAppPreference extends AppPreference {

    private SPUtils spUtils;

    Gson gson = new Gson();
    public YbAppPreference(SPUtils spUtils) {
        super(spUtils);
        this.spUtils = spUtils;
    }

    /**
     * 保存TOKEN
     * @param
     */
    public void saveToken(String tokenStr){
        put("token",tokenStr);
    }

    /**
     * 获取TOKEN
     * @return
     */
    public String getToken(){
        String tokenStr = getString("token");
        if(!StringUtils.isEmpty(tokenStr))
            return tokenStr;
        return null;
    }

    /**
     * 获取TOKEN
     * @return
     */
    public String getTokenKey(){
        String token="";
        SPUtils spUtils=new SPUtils("idogs");
        YbAppPreference ybAppPreference=new YbAppPreference(spUtils);
        String tokenStr = ybAppPreference.getString("access_token");
        if(!StringUtils.isEmpty(tokenStr)) {
           token=tokenStr;
        }else {
           token="";
        }
        return token;
    }

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(YbUser user){
        String userStr = gson.toJson(user);
        put("user",userStr);
    }

    /**
     * 获取本地用户信息
     * @return
     */
    public YbUser getUser(){
        String userStr = getString("user");
        if(!StringUtils.isEmpty(userStr))
            return gson.fromJson(userStr,YbUser.class);
        return null;
    }

    /**
     * 保存list
     * @param
     */
    public <T>  void savelist(String tag,List<T> list){
        String userStr = gson.toJson(list);
        put(tag,userStr);
    }

    public <T> List<T> getDataList(String tag) {
        List<T> datalist=new ArrayList<T>();
        String strJson = getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }

}
