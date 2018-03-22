package com.idogs.laosiji.basic.http;

import com.idogs.laosiji.basic.model.YbBaseModel;

/**
 * Created by y on 2017/8/1.
 */

public class YbUser extends YbBaseModel<YbUser> {
    /**
     * 用户名
     */
    public String username;

    /**
     * 真实姓名
     */
    public String actualName;

    /**
     * 昵称
     */
    public String nickname;

    /**
     * 头像地址
     */
    public String headimgurl;

    /**
     * 手机号码
     */
    public String phone;

    /**
     * 性别
     */
    public Integer gender;

    /**
     * 微信OPENID
     */
    public String wxopenid;

    /**
     * 个推身份id
     */
    public String clientid;

    /**
     * 省份
     */
    public String province;

    /**
     * 城市
     */
    public String city;

    /**
     * 国家
     */
    public String country;

    /**
     * 用户特权信息
     */
    public String privilege;

    /**
     * 个人描述
     */
    public String description;

    /**
     * 关注人数
     */
    public Integer follows;

    /**
     * 粉丝数
     */
    public Integer fans;

    /**
     * 好友数
     */
    public Integer friends;

    /**
     * 用户等级
     */
    public Integer level;

    /**
     * 创建时间
     */
    public Long creatTime;

    /**
     * 登录时间
     */
    public Long loginTime;

    /**
     * 更新时间
     */
    public Long updateTime;

    /**
     * 用户登录IP
     */
    public String loginIp;

    /**
     * 个性签名
     */
    public String personSign;

    /**
     * 个人宣言
     */
    private String slogan;

    /**
     * 驾照编号
     */
    public String driveCode;

    /**
     * 驾驶证图片
     */
    public String driveimgurl;

    /**
     * 用户信用值
     */
    public Integer credit;

    /**
     * 用户积分
     */
    public Integer score;

    /**
     * 登录设备唯一编码
     */
    public String deviceCode;

    /**
     * 账户状态
     */
    public Integer status;

    /**
     * 附加字段整形
     */
    public Integer extraInt;

    /**
     * 附加字段浮点型
     */
    public Double extraDouble;

    /**
     * 字符串型
     */
    public String extraStr;

    /**
     * 年龄
     */
    public String age;

    /**
     * 公司
     */
    public String company;

    /**
     * 行业
     */
    public String trade;

    /**
     * 职业
     */
    public String profession;

    /**
     * IMuser
     */
    public String imusername;

    /**
     * 手机号修改时间
     */
    public Long phoneTime;
}
