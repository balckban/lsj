package com.idogs.laosiji.config;

/**
 * <b>类名称：</b> RouterConfig <br/>
 * <b>类描述：</b> 路由常量配置<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年05月16日 11:09<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class RouterConfig {
    public static final String MAIN_ACTIVITY = "/main/mainactivity";

    public static final String MAIN_PLAY = "/main/play";

    public static final String USER_LOGIN = "/user/login";

    public static final String USER_SIGN= "/user/login/signup";

    public static final String DES_MOVIE= "/main/movieinfo";
    public static final String MOVIE_SEARCH= "/main/moviesearch";
    /**
     * MainFragment 的 ARouter
     */
    public static final String HOME_OIL_CHARGE= "/home/oil/charge";     //油卡充值
    public static final String HOME_NOTIFY= "/home/notify";             //活动广播
    public static final String HOME_CALL_CHARGE= "/home/call/charge";   //话费充值
    public static final String HOME_FLOW_CHARGE= "/home/flow/charge";   //流量充值
    public static final String HOME_BUY_HISTORY= "/home/buy/history";   //购买记录


    /**
     * MyFragment 的 ARouter
     */
    public static final String USER_ICON= "/user/icon";                 //头像
    public static final String USER_BANK_CARD= "/user/bankcard";        //银行卡
    public static final String USER_BANK_ADD= "/user/addbankcard";        //银行卡
    public static final String USER_OIL_CARD= "/user/oilcard";          //加油卡
    public static final String USER_ORDER_LIST= "/user/order/list";     //订单列表
    public static final String USER_FRIEND_INVITE= "/user/friend/invite";        //邀请好友
    public static final String USER_SYSTEM_MSG= "/user/system/msg";     //系统消息
    public static final String USER_SETTING= "/user/setting";           //设置
    public static final String USER_OIL_ADDCARD="/user/oiladdcard"; //添加加油卡
    /**
     * 地图
     */
    public static final String HOME_OIL_MAP= "/home/map";     //一键加油
}
